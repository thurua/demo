package com.ifs.eportal.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.bll.AccountService;
import com.ifs.eportal.bll.ApprovedCustomerLimitService;
import com.ifs.eportal.bll.ClientAccountCustomerService;
import com.ifs.eportal.bll.ClientAccountService;
import com.ifs.eportal.bll.CreditNoteService;
import com.ifs.eportal.bll.CurrencyTypeService;
import com.ifs.eportal.bll.InvoiceService;
import com.ifs.eportal.bll.RecordTypeService;
import com.ifs.eportal.bll.ScheduleOfOfferService;
import com.ifs.eportal.common.Const;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.common.ZError;
import com.ifs.eportal.common.ZValid;
import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.ApprovedCustomerLimitDto;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.CurrencyTypeDto;
import com.ifs.eportal.dto.CustomDto;
import com.ifs.eportal.dto.ExcelDto;
import com.ifs.eportal.dto.InvoiceDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.dto.RecordTypeDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.dto.ValidDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.model.Invoice;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.req.UploadReq;
import com.ifs.eportal.rsp.SingleRsp;

/**
 * 
 * @author ToanNguyen 2018-Oct-02
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(FileController.class.getName());

	private boolean _allowUploadFile;

	@Autowired
	private ScheduleOfOfferService scheduleOfOfferService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private CreditNoteService creditNoteService;

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private CurrencyTypeService currencyTypeService;

	@Autowired
	private RecordTypeService recordTypeService;

	@Autowired
	private ClientAccountCustomerService clientAccountCustomerService;

	@Autowired
	private ApprovedCustomerLimitService approvedCustomerLimitService;

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FileController() {
		_allowUploadFile = false;
	}

	/**
	 * Upload for Angular (ScheduleOfOffer)
	 * 
	 * @param file
	 * @param req
	 * @return
	 * @author ToanNguyen 2018-Oct-04
	 */
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("req") String req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String name = file.getOriginalFilename();
			ObjectMapper mapper = new ObjectMapper();
			UploadReq o = mapper.readValue(req, UploadReq.class);

			// Handle
			res = validateSchedule(file, o);

			// Upload file to S3
			if (_allowUploadFile) {
				InputStream in = file.getInputStream();
				Utils.upload(in, name, "test");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Upload for Apex
	 * 
	 * @param file
	 * @param auth
	 * @return
	 * @author ToanNguyen 2018-Oct-04
	 */
	@PostMapping("/read")
	public String read(@RequestParam("file") MultipartFile file,
			@RequestHeader(value = "Auth", defaultValue = "") String auth) {
		String res = "";

		try {
			if (!Utils.checkAuth(auth)) {
				return "error=invalid-auth";
			}

			// Get data
			String name = file.getOriginalFilename();
			UploadReq o = new UploadReq();

			// Handle
			SingleRsp t = validateSchedule(file, o);

			// Upload file to S3
			if (_allowUploadFile) {
				InputStream in = file.getInputStream();
				Utils.upload(in, name, "test");
			}

			ObjectMapper mapper = new ObjectMapper();
			res = mapper.writeValueAsString(t);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Call to API upload (not used)
	 * 
	 * @param file
	 * @param req
	 * @return
	 * @author ToanNguyen 2018-Oct-02
	 */
	@PostMapping("/call")
	public ResponseEntity<?> call(@RequestParam("file") MultipartFile file, @RequestParam("req") String req) {
		SingleRsp res = new SingleRsp();

		try {
			// Select API URL
			String url = System.getenv("UPLOAD_URL") + "/";
			String name = file.getOriginalFilename();
			if (name.contains("Factoring-INV")) {
				url += "upload-factoring-inv";
			} else if (name.contains("Loan-INV")) {
				url += "upload-loan-inv";
			} else {
				url += "upload-factoring-cn";
			}

			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Auth", Utils.getAuth());

			LinkedMultiValueMap<String, String> mapFile = new LinkedMultiValueMap<>();
			mapFile.add("Content-disposition", "form-data; name=file; filename=" + file.getOriginalFilename());
			HttpEntity<byte[]> doc = new HttpEntity<byte[]>(file.getBytes(), mapFile);

			LinkedMultiValueMap<String, Object> mapReq = new LinkedMultiValueMap<>();
			mapReq.add("file", doc);

			HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(mapReq, headers);
			ResponseEntity<String> resE = rest.exchange(url, HttpMethod.POST, reqEntity, String.class);
			String s = resE.getBody();

			ObjectMapper mapper = new ObjectMapper();
			ExcelDto o = mapper.readValue(s, ExcelDto.class);

			res.setResult(o);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Validate schedule
	 * 
	 * @param file
	 * @param req
	 * @return
	 */

	private SingleRsp validateSchedule(MultipartFile file, UploadReq req) {
		SingleRsp res = new SingleRsp();
		ValidDto dto = new ValidDto();
		String err = "File format is incorrect.";

		// Get data
		ExcelDto o = new ExcelDto();
		String name = file.getOriginalFilename();

		// Handle
		if (name.contains("Factoring-INV")) {
			o = ExcelDto.getFactoringIv(file);
		} else if (name.contains("Loan-INV")) {
			o = ExcelDto.getLoanIv(file);
		} else {
			o = ExcelDto.getFactoringCn(file);
		}
		res.setResult(o);

		String type = o.getType();
		boolean isLoan = "CASH DISBURSEMENT".equals(type);
		boolean isCN = "CREDIT NOTE".equals(type);
		boolean isIV = "INVOICE".equals(type);
		boolean isFactoring = isCN || isIV;

		// Get params
		String clientId = req.getClientId();
		String scheduleNo = req.getScheduleNo().trim();
		Date acceptanceDate = new Date();
		Boolean amendSchedule = req.getAmendSchedule();
		String clientAccountId = req.getClientAccountId();
		String scheduleType = req.getScheduleType();

		// Reset values
		HashMap<String, String> errors = new HashMap<String, String>();

		// Get schedule no from excel
		if (!amendSchedule) {
			scheduleNo = o.getScheduleNo();
		} else {
			/* ToanNguyen 2018-Aug-23 IFS-1013 */
			if (!scheduleNo.equals(o.getScheduleNo())) {
				err = "Schedule No. entered is not the same as in Excel.";
				res = Utils.addError(res, err);
			}
		}

		// Check schedule no
		ScheduleOfOfferDto so = scheduleOfOfferService.read(scheduleNo, clientId);
		Double sequence = 0d;
		if (so.getId() > 0) {
			if (amendSchedule) {
				/* ToanNguyen 2018-Aug-23 IFS-976 */
				if (so.getSequence() != null) {
					sequence = so.getSequence();
				}
				sequence = sequence + 1;
			}
		}

		try {
			String client = o.getClient().trim();
			String clientAccount = o.getClientAccount().trim();
			String allNo = Utils.getAllNo(o.getLineItems());
			/* NguyenMinh 2018-Sep-24 IFS-1203 */
			if (!scheduleType.equalsIgnoreCase(o.getType())) {
				err = "Selected Type of Schedule is not same as in Excel.";
				res = Utils.addError(res, err);
			}

			// Find Client Name (ac)
			AccountDto ac = accountService.read(clientId);
			if (ac.getId() > 0) {
				if (!ac.getName().equalsIgnoreCase(client)) {
					err = "Client Name not same as in excel file.";
					res = Utils.addError(res, err);
				}
			} else {
				/* ToanNguyen 2018-Aug-23 IFS-975 */
				err = "Client Name not found.";
				res = Utils.addError(res, err);
			}

			// Find Client Account (ca)
			ClientAccountDto ca = clientAccountService.read(clientAccountId);
			if (ca.getId() > 0) {
				/* NguyenMinh 2018-Sep-24 IFS-1203 */
				if (!ca.getClientAccount().equals(clientAccount)) {
					err = "Selected Client Account is not same as in Excel.";
					res = Utils.addError(res, err);
				}

				/* ToanNguyen 2018-Sep-10 IFS-1164 */
				String recordTypeName = ca.getRecordTypeName();
				if (isLoan && !"Loan".equals(recordTypeName)) {
					err = "Client Account is not of Loan Type.";
					res = Utils.addError(res, err);
				}
				if (isFactoring && !"Factoring".equals(recordTypeName)) {
					err = "Client Account is not of Factoring Type.";
					res = Utils.addError(res, err);
				}

				if ("Activated".equals(ca.getStatus())) {
					/* ToanNguyen 2018-Aug-23 IFS-974 */
					if (acceptanceDate.before(ca.getActivatedOn())) {
						err = "Schedule Acceptance Date cannot be before client account activation date.";
						res = Utils.addError(res, err);
					}

					/* TriNguyen 2018-Sep-04 IFS-1048 */
					if (!isCN) {
						DateTime t = new DateTime(ca.getActivatedOn());
						t.plusMonths(6).plusDays(-1);
						if (acceptanceDate.after(t.toDate())) {
							// Client - New.
							err = "IVE";
							errors = ZError.addError(errors, err, allNo);
							dto.setInvoiceValid(false);
						}
					}

					/* TriNguyen 2018-Sep-04 IFS-1050 */
					if (ca.getVerification() != null && ca.getVerification() == 100) {
						// Client - 100% Verification Required.
						err = "IVJ";
						errors = ZError.addError(errors, err, allNo);
						dto.setInvoiceValid(false);
					}

					/* ToanNguyen 2018-Aug-30 IFS-977,1027 */
					if (!amendSchedule) {
						if (so.getId() > 0) {
							err = "Schedule Number exists under Client Account.";
							res = Utils.addError(res, err);
						}
					}
				} else if ("Closed".equals(ca.getStatus())) {
					/* ToanNguyen 2018-Aug-30 IFS-1024 */
					err = "Client Account is already closed.";
					res = Utils.addError(res, err);
				} else if ("Suspended".equals(ca.getStatus())) {
					/* NhatNguyen 2018-Sep-03 IFS-1049 */
					// Client - Suspended.
					err = "IVF";
					errors = ZError.addError(errors, err, allNo);
					dto.setInvoiceValid(false);
				} else {
					/* ToanNguyen 2018-Aug-30 IFS-1024 */
					err = "Client Account is not activated.";
					res = Utils.addError(res, err);
				}
			} else {
				/* ToanNguyen 2018-Aug-30 IFS-1024 */
				err = "Client Account not found.";
				res = Utils.addError(res, err);
			}

			/* TienNguyen 2018-Aug-30 IFS-1191 */
			CurrencyTypeDto t = currencyTypeService.read(o.getDocumentCurrency());
			if (t.getId() == 0) {
				err = "Currency is not supported.";
				res = Utils.addError(res, err);
			}
			// ---------------------------------------------------------------------------------------------
			// 1. Validate step 1 (with Excel), add Schedule_Of_Offer__c data
			String rid; // RecordType ID
			if (isLoan) {
				RecordTypeDto rt = recordTypeService.getBy("Schedule_Of_Offer__c", "Loan");
				rid = rt.getSfId();
				so.setRecordTypeId(rid);

				rt = recordTypeService.getBy("Invoice__c", "Loan");
				rid = rt.getSfId();
			}
			if (isCN) {
				RecordTypeDto rt = recordTypeService.getBy("Schedule_Of_Offer__c", "Credit Note");
				rid = rt.getSfId();
				so.setRecordTypeId(rid);
			}

			if (isIV) {
				RecordTypeDto rt = recordTypeService.getBy("Schedule_Of_Offer__c", "Factoring");
				rid = rt.getSfId();
				so.setRecordTypeId(rid);

				rt = recordTypeService.getBy("Invoice__c", "Factoring");
				rid = rt.getSfId();

				/* TriNguyen 2018-Sep-03 IFS-1052 */
				// err = ZValid.acceptanceDate(ac.getSfid(), ca.getSfid(), acceptanceDate,
				// o.getLineItems());
				if (!err.isEmpty()) {
					errors = ZError.addError(errors, err, allNo);
					dto.setInvoiceValid(false);
				}

				if (dto.isSuccess()) {
					if (isCN) {
						res = validateCreditNote(o, req);
					} else {
						res = validateInvoice(o, req);
					}
				} else {
					res.setError(err);
				}

				so.setScheduleDate(o.getDocumentDate());
				if (o.getDocumentDate().toString().isEmpty()) {
					so.setScheduleDate(o.getDocumentDate());
					if (so.getScheduleDate() == null) {
						err = "Invalid Document Date.";
						res = Utils.addError(res, err);
					}

				}
//				if (!o.getProcessDate()().toString().isEmpty()) {
//					 so.Process_Date__c = Utils.toDate(o.processDate);
//					if(so.getScheduleDate().toString() == null) {
//						err = "Invalid Document Date.";
//	                    res = Utils.addError(res, err);
//					}
//				}
//				if (!o.getKeyInByDate().toString().isEmpty()) {
//					so.(Utils.toDate(o.getDocumentDate().toString()));
//					if(so.getScheduleDate().toString() == null) {
//						err = "Invalid Document Date.";
//	                    res = Utils.addError(res, err);
//					}
//				}
			}
			err = res.getMessage();
			if (!err.isEmpty()) {
				if (res.getMessage().isEmpty()) {
					createSchedule(o, sequence, acceptanceDate);
				}
			}
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Validate invoice
	 * 
	 * @param o
	 * @param req
	 * @return
	 */
	private SingleRsp validateInvoice(ExcelDto o, UploadReq req) {
		SingleRsp res = new SingleRsp();
		ValidDto dto = new ValidDto();

		String err = "";
		HashMap<String, String> errors = new HashMap<String, String>();
		String clientAccountId = req.getClientAccountId();
		String clientId = req.getClientId();
		boolean isFac = o.getType().equals("INVOICE");

		List<AccountDto> lac = accountService.getByNames(o.getLineItems());
		ClientAccountDto ca = clientAccountService.read(clientAccountId);
		List<ClientAccountCustomerDto> lcc = clientAccountCustomerService.getByClientId(clientAccountId);

		CustomDto avgInvoiceAmount = invoiceService.getAverage(clientAccountId);
		List<CustomDto> arTotal = invoiceService.getTotalOutstanding(o.getLineItems(), clientAccountId);
		List<CustomDto> arDisputed = invoiceService.getDisputedOutstanding(o.getLineItems(), clientAccountId);
		List<CustomDto> arOverdue = invoiceService.getOverdueOutstanding(o.getLineItems(), clientAccountId);
		List<CustomDto> arInvoiceAvg = invoiceService.getInvoiceAvg(o.getLineItems(), clientAccountId);
		List<CustomDto> arInvoice = invoiceService.getInvoiceSumary(o.getLineItems(), clientAccountId,
				req.getAcceptanceDate());
		List<CustomDto> arTotalAmount = invoiceService.getTotalOutstandingAmount(o.getLineItems(), clientAccountId);
		List<CustomDto> arCredit = invoiceService.getCreditSumary(o.getLineItems(), clientAccountId,
				req.getAcceptanceDate());

		List<Invoice> liv = new ArrayList<>();
		for (LineItemDto i : o.getLineItems()) {
			Invoice iv = new Invoice();
			// Schedule_Of_Offer__c = so.Id,
			// Document_Type__c = so.Document_Type__c,
			// RecordTypeId = rid,
			// Client_Name__c = ac.Id,
			iv.setClientAccount(clientAccountId);
			// CurrencyIsoCode = so.CurrencyIsoCode,
			iv.setClientRemarks(i.getRemarks());
			iv.setInvoiceAmount(0f);
			iv.setStatus("Pending");

			// Loan Invoice
			if (!isFac) {
				if (i.getName() != null && i.getName().isEmpty()) {
					continue;
				}
				iv.setSupplier(Utils.getAccIdByName(lac, i.getName()));
				iv.setName(i.getNo());

				/* ToanNguyen 2018-Sep-03 IFS-1030 */
				if (i.getItemDate() == null) {
					// Invalid Invoice Date.
					err = "IV4";
					errors = ZError.addError(errors, err, i.getName());
					dto.setSuccess(false);
				} else {
					// iv.setInvoiceDate(i.getItemDate());
					if (i.getItemDate().after(req.getAcceptanceDate())) {
						// Invoice Date cannot be after Schedule Acceptance Date.
						err = "IV5";
						errors = ZError.addError(errors, err, i.getName());
						dto.setSuccess(false);
					}
				}

				iv.setInvoiceAmount(i.getAmount().floatValue());

				if (iv.getInvoiceAmount() <= avgInvoiceAmount.getValue()) {
					// Customer - Invoice Amount more than Client Average Invoice Size.
					err = "CCD";
					errors = ZError.addError(errors, err, i.getName());
					dto.setInvoiceValid(false);
				}

				Float period = i.getPeriod().floatValue();
				iv.setCreditPeriod(period);

				/* HanhNguyen 2018-Aug-29 IFS-1035 */
				if (i.getItemDate() != null) {
					DateTime addedPeriod = new DateTime(i.getItemDate());
					addedPeriod.plusDays(period.intValue());
					if (addedPeriod.toDate().after(req.getAcceptanceDate())) {
						// Invoice - Overdue by XXX days.
						err = "IV2";
						errors = ZError.addError(errors, err, i.getName());
						dto.setInvoiceValid(false);
					}
				}

				err = ZValid.ratioOverdue(arOverdue, arTotal, i.getName());
				errors = ZError.addError(errors, err, i.getName());

				if (!err.isEmpty()) {
					dto.setInvoiceValid(false);
				}

				err = ZValid.ratioDisputed(arDisputed, arTotal, i.getName());
				errors = ZError.addError(errors, err, i.getName());

				if (!err.isEmpty()) {
					dto.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-06 IFS-1045 */
				err = ZValid.customerAverage(arInvoiceAvg, i.getAmount(), i.getName());
				errors = ZError.addError(errors, err, i.getName());

				if (!err.isEmpty()) {
					dto.setInvoiceValid(false);
				}

				iv.setPo(i.getPo());
				iv.setContract(i.getContract());
				iv.setSupplierFromExcel(i.getName());

				if (i.getPaymentDate() == null) {
					err = "Invalid Payment Date.";
					res = Utils.addError(res, err);
				}

				liv.add(iv);
			}
			// Factoring Invoice
			else {
				if (i.getName() != null && i.getName().isEmpty()) {
					continue;
				}
				iv.setCustomer(Utils.getAccCusIdByName(lcc, i.getName()));
				iv.setName(i.getNo());
				iv.setCustomerFromExcel(i.getName());
				iv.setCustomerBranch(i.getBranch());

				/* ToanNguyen 2018-Sep-03 IFS-1030 */
				iv.setInvoiceDate(i.getItemDate());
				if (i.getItemDate() == null) {
					// Invalid Invoice Date.
					err = "IV4";
					errors = ZError.addError(errors, err, i.getName());
					dto.setSuccess(false);
				}
				if (i.getItemDate().after(req.getAcceptanceDate())) {
					// Invoice Date cannot be after Schedule Acceptance Date.
					err = "IV5";
					errors = ZError.addError(errors, err, i.getName());
					dto.setSuccess(false);
				}
				/* HanhNguyen 2018-Aug-29 IFS-1034 */
				Date acceptanceDate = (new DateTime(req.getAcceptanceDate())).minusYears(1).toDate();
				if (i.getItemDate().before(acceptanceDate)) {
					// Invoice - Dated 1 year before Schedule Acceptance Date.
					err = "IV6";
					errors = ZError.addError(errors, err, iv.getName());
					dto.setInvoiceValid(false);
				}

				iv.setInvoiceAmount(i.getAmount().floatValue());

				/* HanhNguyen 2018-Aug-29 IFS-1033 */
				if (iv.getInvoiceAmount() >= 50000) {
					// Verification Required - Per Verification Rule.
					err = "IV3";
					errors = ZError.addError(errors, err, i.getName());
					dto.setInvoiceValid(false);
				}

				if (iv.getInvoiceAmount() > 0) {
					/* CongLe 2018-Aug-29 IFS-1031 */
					if (ca.getAccountType().equals("Import")) {
						// Verification Required - Import Factoring.
						err = "IV1";
						errors = ZError.addError(errors, err, iv.getName());
						dto.setInvoiceValid(false);
					}

					/* CongLe 2018-Aug-29 IFS-1032 */
					if (ca.getProgramName().equals("SPOT")) {
						// Verification Required - SPOT Program.
						err = "IV7";
						errors = ZError.addError(errors, err, iv.getName());
						dto.setInvoiceValid(false);
					}
					if (ca.getProgramName().equals("Multiply")) {
						// Verification Required - Multiply Program.
						err = "IV8";
						errors = ZError.addError(errors, err, iv.getName());
						dto.setInvoiceValid(false);
					}
				}

				/* CongLe 2018-Aug-29 IFS-1032 */
				if (ca.getProgramName().equals("Small Ticket Factoring Program") && iv.getInvoiceAmount() >= 10000) {
					// Verification Required - Small Ticket Factoring Program.
					err = "IV9";
					errors = ZError.addError(errors, err, iv.getName());
					dto.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-04 IFS-1044 */
				if (iv.getInvoiceAmount() > avgInvoiceAmount.getValue()) {
					// Customer - Invoice Amount more than Client Average Invoice Size.
					err = "CCD";
					errors = ZError.addError(errors, err, iv.getName());
					dto.setInvoiceValid(false);
				}

				/* NhatNguyen 2018-Sep-06 IFS-1051 */
				if (iv.getInvoiceAmount() > ca.getVerificationExceedingInvoiceAmount()
						|| ca.getVerificationExceedingInvoiceAmount() == null) {
					// Client - Per Verification Amount.
					err = "IVB";
					errors = ZError.addError(errors, err, iv.getName());
					dto.setInvoiceValid(false);
				}

				Float period = i.getPeriod().floatValue();
				iv.setCreditPeriod(period);

				/* HanhNguyen 2018-Aug-29 IFS-1035 */
				if (i.getItemDate() != null) {
					DateTime addedPeriod = new DateTime(i.getItemDate());
					addedPeriod.plusDays(period.intValue());
					if (addedPeriod.toDate().after(req.getAcceptanceDate())) {
						// Invoice - Overdue by XXX days.
						err = "IV2";
						errors = ZError.addError(errors, err, iv.getName());
						dto.setInvoiceValid(false);
					}
				}

				/* ToanNguyen 2018-Sep-06 IFS-1047 */
				err = ZValid.ratioCreditInvoice(arCredit, arInvoice, i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					dto.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-06 IFS-1045 */
				err = ZValid.ratioOverdue(arOverdue, arTotal, i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					dto.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-06 IFS-1046 */
				err = ZValid.ratioDisputed(arDisputed, arTotal, i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					dto.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-11 IFS-1043 */
				err = ZValid.customerAverage(arInvoiceAvg, iv.getInvoiceAmount(), i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					dto.setInvoiceValid(false);
				}

				iv.setPo(i.getPo());
				iv.setContract(i.getContract());
				liv.add(iv);
			}
		}

		if (dto.isSuccess()) {
			/* TriNguyen 2018-Sep-01 IFS-1036 */
			List<ApprovedCustomerLimitDto> lcl = approvedCustomerLimitService.read(liv, clientId);
			if (isFac) {
				for (Invoice i : liv) {
					if (lcc.size() > 0) {
						err = ZValid.customer(lcc, i.getCustomerFromExcel(), i.getInvoiceAmount(), true,
								o.getFactorCode(), req.getAcceptanceDate(), ca);
						errors = ZError.addError(errors, err, i.getName());
					}
					if (!err.isEmpty()) {
						List<String> arr = Arrays.asList(err.split(","));
						int t1 = arr.indexOf("CC9");
						int t2 = arr.indexOf("CCA");
						int t3 = arr.indexOf("CCB");

						if (t1 != -1 || t2 != -1 || t3 != -1) {
							/* ToanNguyen 2018-Aug-23 IFS-974,1025,1026 */
							res.setError(Const.HTTP.STATUS_ERROR);
						}
					}

					/* TriNguyen 2018-Sep-01 IFS-1036,1037,1038,1039 */
					err = ZValid.acl(i, lcl, ca.getFactoringType(), arTotalAmount);
					errors = ZError.addError(errors, err, i.getName());
				}
			}
		}

		dto.setErrors(errors);
		res.setResult(dto);

		return res;
	}

	/**
	 * Validate credit note
	 * 
	 * @param o
	 * @param req
	 * @return
	 */
	private SingleRsp validateCreditNote(ExcelDto o, UploadReq req) {
		SingleRsp res = new SingleRsp();
		ValidDto dto = new ValidDto();

		String err = "";
		HashMap<String, String> errors = new HashMap<String, String>();
		String clientAccountId = req.getClientAccountId();

		ClientAccountDto ca = clientAccountService.read(clientAccountId);
		List<ClientAccountCustomerDto> lcc = clientAccountCustomerService.getByClientId(clientAccountId);

		CustomDto avgInvoiceAmount = creditNoteService.getAverage(clientAccountId);

		List<CreditNote> lcn = new ArrayList<>();
		for (LineItemDto i : o.getLineItems()) {
			CreditNote cn = new CreditNote();
			// Schedule_Of_Offer__c = so.Id,
			// Document_Type__c = so.Document_Type__c,
			// RecordTypeId = rid,
			// Client_Name__c = ac.Id,
			cn.setClientAccount(clientAccountId);
			// CurrencyIsoCode = so.CurrencyIsoCode,
			cn.setClientRemarks(i.getRemarks());
			cn.setCreditAmount(0f);
			cn.setStatus("Pending");

			if (i.getName() != null && i.getName().isEmpty()) {
				continue;
			}

			cn.setCustomer(Utils.getAccCusIdByName(lcc, i.getName()));
			cn.setCustomerFromExcel(i.getName());
			cn.setCustomerBranch(i.getBranch());
			cn.setName(i.getName());

			/* ToanNguyen 2018-Sep-03 IFS-1054 */
			cn.setCreatedDate(i.getItemDate());
			if (i.getItemDate() == null) {
				// Invalid Credit Note Date.
				err = "CN4";
				errors = ZError.addError(errors, err, cn.getName());
				dto.setSuccess(false);
			}
			if (i.getItemDate().after(req.getAcceptanceDate())) {
				// Credit Note Date cannot be after Schedule Acceptance Date.
				err = "CN5";
				errors = ZError.addError(errors, err, cn.getName());
				dto.setSuccess(false);
			}
			Date acceptanceDate = (new DateTime(req.getAcceptanceDate())).minusYears(1).toDate();
			if (i.getItemDate().before(acceptanceDate)) {
				// Credit Note - Dated 1 year before Schedule Acceptance Date.
				err = "CN6";
				errors = ZError.addError(errors, err, cn.getName());
				dto.setSuccess(false);
			}

			cn.setCreditAmount(i.getAmount().floatValue());

			/* ToanNguyen 2018-Sep-04 IFS-1055 */
			if (cn.getCreditAmount() > avgInvoiceAmount.getValue()) {
				// Credit Note - Amount more than Client Average Invoice Size.
				err = "CN7";
				errors = ZError.addError(errors, err, cn.getName());
				dto.setCreditValid(false);
			}
			cn.setAppliedInvoice(i.getInvoiceApplied());

			lcn.add(cn);
		}

		if (dto.isSuccess()) {
			List<InvoiceDto> lin = new ArrayList<>();
			lin = invoiceService.read(o.getLineItems(), clientAccountId);

			for (CreditNote i : lcn) {
				if (lcc.size() > 0) {
					err = ZValid.customer(lcc, i.getCustomerFromExcel(), 0, false, o.getFactorCode(),
							req.getAcceptanceDate(), ca);
					errors = ZError.addError(errors, err, i.getName());
					if (!err.isEmpty()) {
						/* ToanNguyen 2018-Aug-23 IFS-974,1025,1026 */
						dto.setSuccess(false);
					}
				}

				/* ToanNguyen 2018-Sep-02 IFS-1056 */
				if (lin.size() > 0) {
					err = ZValid.appliedInvoices(lin, i);
					errors = ZError.addError(errors, err, i.getName());

					if (!err.isEmpty()) {
						dto.setCreditValid(false);
					}
				} else {
					// Credit Note - Invoice Number not found.
					err = "CN1";
					errors = ZError.addError(errors, err, i.getName());
					if (!err.isEmpty()) {
						dto.setCreditValid(false);
					}
				}
			}
		}

		dto.setErrors(errors);
		res.setResult(dto);

		return res;
	}

	/**
	 * Create schedule
	 * 
	 * @param o
	 * @param sequence
	 * @param acceptanceDate
	 * @return
	 */
	private boolean createSchedule(ExcelDto o, Double sequence, Date acceptanceDate) {
		boolean res = true;

		try {
			String type = o.getType();
			boolean isLoan = "CASH DISBURSEMENT".equals(type);
			boolean isCN = "CREDIT NOTE".equals(type);
			boolean isIV = "INVOICE".equals(type);
			boolean isInvoice = isLoan || isIV;

			ScheduleOfOffer m = new ScheduleOfOffer();
			m.setDocumentType(type);
			m.setScheduleNo(o.getScheduleNo());
			m.setSequence(sequence.floatValue());
			m.setAcceptanceDate(acceptanceDate);
			m.setOriginalAcceptanceDate(acceptanceDate);
			m.setFactorCode(o.getFactorCode());
			m.setCurrencyIsoCode(o.getDocumentCurrency());
			m.setListType(o.getListType());
			m.setClientAccount(o.getClientAccount());
			m.setClientName(o.getClient());
			m.setScheduleStatus("Draft");

			scheduleOfOfferService.create(m);

			List<LineItemDto> l = o.getLineItems();

			if (isCN) {
				createCreditNote(l);
			} else {
				createInvoice(l, isInvoice);
			}
			if (sequence > 0) {
				String temp = m.getName() + "-" + sequence.toString();
				m.setName(temp);
			} else {
				m.setName(o.getScheduleNo());
			}
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Create invoice
	 * 
	 * @param l
	 * @return
	 */
	private boolean createInvoice(List<LineItemDto> l, boolean isInvoice) {
		boolean res = false;

		try {
			String name = isInvoice ? "Factoring" : "Loan";
			RecordTypeDto dto = recordTypeService.getBy("Invoice__c", name);
			List<AccountDto> lac = accountService.getByNames(l);

			for (LineItemDto i : l) {
				Invoice m = new Invoice();
				m.setScheduleOfOffer(m.getSfId());
				m.setDocumentType(m.getDocumentType());
				m.setRecordTypeId(dto.getSfId());
				m.setClientName("x");
				m.setClientAccount("x");
				m.setCurrencyIsoCode("x");
				m.setClientRemarks("x");
				m.setInvoiceAmount(0f);
				m.setStatus("Pending");

				m.setCustomer(Utils.getAccIdByName(lac, i.getName()));
				m.setSupplier(Utils.getAccIdByName(lac, i.getName()));
				m.setCustomerFromExcel(i.getName());
				m.setCustomerBranch(i.getBranch());
				m.setName(i.getNo());
				m.setInvoiceDate(i.getItemDate());
				m.setInvoiceAmount(i.getAmount().floatValue());
				m.setCreditPeriod(i.getPeriod().floatValue());
				m.setPo(i.getPo());
				i.setContract(i.getContract());

				invoiceService.create(m);
			}

			res = true;
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Create credit note
	 * 
	 * @param l
	 * @return
	 */
	private boolean createCreditNote(List<LineItemDto> l) {
		boolean res = false;

		// List<ClientAccountCustomerDto> lcc =
		// clientAccountCustomerService.getByClientId(clientAccountId);

		try {
			for (LineItemDto i : l) {
				CreditNote m = new CreditNote();
				m.setScheduleOfOffer(m.getSfId());
				// m.setDocumentType("x");
				// m.setRecordTypeId("x");
				m.setClient("x");
				m.setClientAccount("x");
				m.setCurrencyIsoCode("x");
				m.setClientRemarks("x");
				m.setCreditAmount(0f);
				m.setStatus("Accepted");

				// m.setCustomer(Utils.getAccCusIdByName(lcc, i.getName()));
				m.setCustomerFromExcel(i.getName());
				m.setCustomerBranch(i.getBranch());
				m.setName(i.getNo());
				m.setCreditNoteDate(i.getItemDate());
				m.setCreditAmount(i.getAmount().floatValue());
				m.setAppliedInvoice(m.getAppliedInvoice());

				creditNoteService.create(m);
			}

			res = true;
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	// end
}