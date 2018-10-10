package com.ifs.eportal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.bll.AccountService;
import com.ifs.eportal.bll.ApprovedCustomerLimitService;
import com.ifs.eportal.bll.ClientAccountCustomerService;
import com.ifs.eportal.bll.ClientAccountService;
import com.ifs.eportal.bll.CreditNoteService;
import com.ifs.eportal.bll.CurrencyTypeService;
import com.ifs.eportal.bll.InvoiceService;
import com.ifs.eportal.bll.ReasonService;
import com.ifs.eportal.bll.RecordTypeService;
import com.ifs.eportal.bll.ScheduleOfOfferService;
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
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.RecordTypeDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.dto.ValidDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.model.Invoice;
import com.ifs.eportal.model.Reason;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.req.DownloadReq;
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

	@Autowired
	private ReasonService reasonService;

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FileController() {
		// Utils.allowUpload = true;
	}

	/**
	 * Download file from server
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@PostMapping("/download")
	public ResponseEntity<InputStreamResource> download(@RequestBody DownloadReq req) {
		String fileName = "noname";
		Long len = 0l;
		InputStreamResource res = null;

		try {
			// Get data
			String path = req.getPath();
			String file = req.getFile();
			Boolean aws = req.getAws();

			String p = path + "/" + file;
			fileName = "attachment;filename=" + file;

			// Handle
			if (aws) {
				S3Object s3 = Utils.download(p);
				len = s3.getObjectMetadata().getInstanceLength();
				res = new InputStreamResource(s3.getObjectContent());
			} else {
				File f = new File(p);
				InputStream is = new FileInputStream(f);
				len = f.length();
				res = new InputStreamResource(is);
			}

		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		BodyBuilder bb = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, fileName);
		return bb.contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(len).body(res);
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
	public ResponseEntity<?> upload(@RequestHeader HttpHeaders header, @RequestParam("file") MultipartFile file,
			@RequestParam("req") String req) {
		SingleRsp res = new SingleRsp();

		try {

			// Get data
			String originalName = file.getOriginalFilename();
			ObjectMapper mapper = new ObjectMapper();
			UploadReq o = mapper.readValue(req, UploadReq.class);

			// Handle
			ValidDto dto = validateSchedule(file, o);

			if (dto.isSuccess()) {
				// Get extension
				int t = originalName.lastIndexOf(".") + 1;
				String extension = originalName.substring(t);

				String url = System.getenv("BUCKETEER_BUCKET_URL");
				String path = "InvoiceData";
				String name = UUID.randomUUID().toString() + "." + extension;
				url += "/" + path + "/" + name;

				// Upload file to S3
				if (Utils.allowUpload) {
					InputStream in = file.getInputStream();
					Utils.upload(in, name, path);
					dto.invoiceDataPath = url;
				}

				PayloadDto pl = Utils.getTokenInfor(header);
				dto.lastModifiedByPortalUserId = pl.getSfId();
				List<String> ar = pl.getAccessRights();

				// Check right to select portal status
				String portalStatus = "Pending Authorisation";
				String right = "Schedules - Upload Schedule (Authorisation Not Required)";
				Stream<String> t1;
				t1 = ar.stream().filter(r -> right.equals(r));
				List<String> t2 = t1.collect(Collectors.toList());
				if (t2.size() > 0) {
					portalStatus = "Authorised";
				}

				createSchedule(dto, new Date(), o, portalStatus);
			} else {
				HashMap<String, String> errors = dto.getErrors();
				if (errors.size() > 0) {
					String err = ZError.getError(errors);
					dto = Utils.addResult(dto, err);

					err = ZError.getMessage(errors);
					// dto = Utils.addResult(dto, err);
					System.out.println(err);
				}
				res.setError(dto.getMessage());
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
			String originalName = file.getOriginalFilename();
			// UploadReq o = new UploadReq();

			// Handle
			// ValidDto dto = validateSchedule(file, o);

			ExcelDto o = new ExcelDto();
			SingleRsp rsp = new SingleRsp();

			// Handle
			if (originalName.contains("Factoring-INV")) {
				o = ExcelDto.getFactoringIv(file);
			} else if (originalName.contains("Loan-INV")) {
				o = ExcelDto.getLoanIv(file);
			} else {
				o = ExcelDto.getFactoringCn(file);
			}
			rsp.setResult(o);

			// Upload file to S3
			if (Utils.allowUpload) {
				// Get extension
				int t = originalName.lastIndexOf(".") + 1;
				String extension = originalName.substring(t);

				String path = "InvoiceData";
				String name = UUID.randomUUID().toString() + "." + extension;

				InputStream in = file.getInputStream();
				Utils.upload(in, name, path);
			}

			ObjectMapper mapper = new ObjectMapper();
			res = mapper.writeValueAsString(rsp);
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

	private ValidDto validateSchedule(MultipartFile file, UploadReq req) {
		ValidDto res = new ValidDto();
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
		ScheduleOfOfferDto so = scheduleOfOfferService.getBy(scheduleNo, clientId);
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
						Date temp = ca.getActivatedOn();
						DateTime temp1 = new DateTime(temp);
						temp1 = temp1.plusMonths(6).plusDays(-1);
						Date t = temp1.toDate();
						if (acceptanceDate.before(t)) {
							// Client - New.
							err = "IVE";
							errors = ZError.addError(errors, err, allNo);
							res.setInvoiceValid(false);
						}
					}

					/* TriNguyen 2018-Sep-04 IFS-1050 */
					if (ca.getVerification() != null && ca.getVerification() == 100) {
						// Client - 100% Verification Required.
						err = "IVJ";
						errors = ZError.addError(errors, err, allNo);
						res.setInvoiceValid(false);
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
					res.setInvoiceValid(false);
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
			CurrencyTypeDto t = currencyTypeService.getByIsoCode(o.getDocumentCurrency());
			if (t.getId() == 0) {
				err = "Currency is not supported.";
				res = Utils.addError(res, err);
			}

			// ---------------------------------------------------------------------------------------------
			// 1. Validate step 1 (with Excel), add Schedule_Of_Offer__c data
			if (isIV) {
				/* TriNguyen 2018-Sep-03 IFS-1052 */
				err = scheduleOfOfferService.validateAcceptance(ca.getSfId(), acceptanceDate, o.getLineItems());
				if (!err.isEmpty()) {
					errors = ZError.addError(errors, err, allNo);
					res.setInvoiceValid(false);
				}

			}
			if (res.isSuccess()) {
				req.setAcceptanceDate(acceptanceDate);
				if (isCN) {
					res = validateCreditNote(o, req, res);
				} else {
					res = validateInvoice(o, req, res);
				}
				res.sequence = sequence;
			} else {
				res.setErrors(errors);
			}
			res.data = o;
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
	private ValidDto validateInvoice(ExcelDto o, UploadReq req, ValidDto t) {
		ValidDto res = new ValidDto();
		res.setErrors(t.getErrors());
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
					errors = ZError.addError(errors, err, iv.getName());
					res.setSuccess(false);
				} else {
					// iv.setInvoiceDate(i.getItemDate());
					if (i.getItemDate().after(req.getAcceptanceDate())) {
						// Invoice Date cannot be after Schedule Acceptance Date.
						err = "IV5";
						errors = ZError.addError(errors, err, iv.getName());
						res.setSuccess(false);
					}
				}

				iv.setInvoiceAmount(i.getAmount().floatValue());

				if (iv.getInvoiceAmount() <= avgInvoiceAmount.getValue()) {
					// Customer - Invoice Amount more than Client Average Invoice Size.
					err = "CCD";
					errors = ZError.addError(errors, err, iv.getName());
					res.setInvoiceValid(false);
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
						res.setInvoiceValid(false);
					}
				}

				err = ZValid.ratioOverdue(arOverdue, arTotal, i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					res.setInvoiceValid(false);
				}

				err = ZValid.ratioDisputed(arDisputed, arTotal, i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					res.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-06 IFS-1045 */
				err = ZValid.customerAverage(arInvoiceAvg, i.getAmount(), i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					res.setInvoiceValid(false);
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
					errors = ZError.addError(errors, err, iv.getName());
					res.setSuccess(false);
				}
				if (i.getItemDate().after(req.getAcceptanceDate())) {
					// Invoice Date cannot be after Schedule Acceptance Date.
					err = "IV5";
					errors = ZError.addError(errors, err, iv.getName());
					res.setSuccess(false);
				}
				/* HanhNguyen 2018-Aug-29 IFS-1034 */
				Date acceptanceDate = (new DateTime(req.getAcceptanceDate())).minusYears(1).toDate();
				if (i.getItemDate().before(acceptanceDate)) {
					// Invoice - Dated 1 year before Schedule Acceptance Date.
					err = "IV6";
					errors = ZError.addError(errors, err, iv.getName());
					res.setInvoiceValid(false);
				}

				iv.setInvoiceAmount(i.getAmount().floatValue());

				/* HanhNguyen 2018-Aug-29 IFS-1033 */
				if (iv.getInvoiceAmount() >= 50000) {
					// Verification Required - Per Verification Rule.
					err = "IV3";
					errors = ZError.addError(errors, err, iv.getName());
					res.setInvoiceValid(false);
				}

				if (iv.getInvoiceAmount() > 0) {
					/* CongLe 2018-Aug-29 IFS-1031 */
					if (("Import").equals(ca.getAccountType())) {
						// Verification Required - Import Factoring.
						err = "IV1";
						errors = ZError.addError(errors, err, iv.getName());
						res.setInvoiceValid(false);
					}

					/* CongLe 2018-Aug-29 IFS-1032 */
					if (("SPOT").equals(ca.getProgramName())) {
						// Verification Required - SPOT Program.
						err = "IV7";
						errors = ZError.addError(errors, err, iv.getName());
						res.setInvoiceValid(false);
					}
					if (("Multiply").equals(ca.getProgramName())) {
						// Verification Required - Multiply Program.
						err = "IV8";
						errors = ZError.addError(errors, err, iv.getName());
						res.setInvoiceValid(false);
					}
				}

				/* CongLe 2018-Aug-29 IFS-1032 */
				if (("Small Ticket Factoring Program").equals(ca.getProgramName()) && iv.getInvoiceAmount() >= 10000) {
					// Verification Required - Small Ticket Factoring Program.
					err = "IV9";
					errors = ZError.addError(errors, err, iv.getName());
					res.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-04 IFS-1044 */
				if (iv.getInvoiceAmount() > avgInvoiceAmount.getValue()) {
					// Customer - Invoice Amount more than Client Average Invoice Size.
					err = "CCD";
					errors = ZError.addError(errors, err, iv.getName());
					res.setInvoiceValid(false);
				}

				/* NhatNguyen 2018-Sep-06 IFS-1051 */
				if (ca.getVerificationExceedingInvoiceAmount() == null
						|| iv.getInvoiceAmount() > ca.getVerificationExceedingInvoiceAmount()) {
					// Client - Per Verification Amount.
					err = "IVB";
					errors = ZError.addError(errors, err, iv.getName());
					res.setInvoiceValid(false);
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
						res.setInvoiceValid(false);
					}
				}

				/* ToanNguyen 2018-Sep-06 IFS-1047 */
				err = ZValid.ratioCreditInvoice(arCredit, arInvoice, i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					res.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-06 IFS-1045 */
				err = ZValid.ratioOverdue(arOverdue, arTotal, i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					res.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-06 IFS-1046 */
				err = ZValid.ratioDisputed(arDisputed, arTotal, i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					res.setInvoiceValid(false);
				}

				/* ToanNguyen 2018-Sep-11 IFS-1043 */
				err = ZValid.customerAverage(arInvoiceAvg, iv.getInvoiceAmount(), i.getName());
				errors = ZError.addError(errors, err, iv.getName());

				if (!err.isEmpty()) {
					res.setInvoiceValid(false);
				}

				iv.setPo(i.getPo());
				iv.setContract(i.getContract());
				liv.add(iv);
			}
		}

		if (res.isSuccess()) {
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
							res.setSuccess(false);
						}
					}

					/* TriNguyen 2018-Sep-01 IFS-1036,1037,1038,1039 */
					err = ZValid.acl(i, lcl, ca.getFactoringType(), arTotalAmount);
					errors = ZError.addError(errors, err, i.getName());
				}
			}
		}
		res.setErrors(errors);

		return res;
	}

	/**
	 * Validate credit note
	 * 
	 * @param o
	 * @param req
	 * @return
	 */
	private ValidDto validateCreditNote(ExcelDto o, UploadReq req, ValidDto t) {
		ValidDto res = new ValidDto();
		res.setErrors(t.getErrors());

		String err = "";
		HashMap<String, String> errors = new HashMap<String, String>();
		String clientAccountId = req.getClientAccountId();

		ClientAccountDto ca = clientAccountService.read(clientAccountId);
		List<ClientAccountCustomerDto> lcc = clientAccountCustomerService.getByClientId(clientAccountId);

		CustomDto avgInvoiceAmount = invoiceService.getAverage(clientAccountId);

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
			cn.setName(i.getNo());

			/* ToanNguyen 2018-Sep-03 IFS-1054 */
			cn.setCreatedDate(i.getItemDate());
			if (i.getItemDate() == null) {
				// Invalid Credit Note Date.
				err = "CN4";
				errors = ZError.addError(errors, err, cn.getName());
				res.setSuccess(false);
			} else {
				if (i.getItemDate().after(req.getAcceptanceDate())) {
					// Credit Note Date cannot be after Schedule Acceptance Date.
					err = "CN5";
					errors = ZError.addError(errors, err, cn.getName());
					res.setSuccess(false);
				}
				Date acceptanceDate = (new DateTime(req.getAcceptanceDate())).minusYears(1).toDate();
				if (i.getItemDate().before(acceptanceDate)) {
					// Credit Note - Dated 1 year before Schedule Acceptance Date.
					err = "CN6";
					errors = ZError.addError(errors, err, cn.getName());
					res.setSuccess(false);
				}
			}

			cn.setCreditAmount(i.getAmount().floatValue());

			/* ToanNguyen 2018-Sep-04 IFS-1055 */
			if (cn.getCreditAmount() > avgInvoiceAmount.getValue()) {
				// Credit Note - Amount more than Client Average Invoice Size.
				err = "CN7";
				errors = ZError.addError(errors, err, cn.getName());
				res.setCreditValid(false);
			}
			cn.setAppliedInvoice(i.getInvoiceApplied());

			lcn.add(cn);
		}

		if (res.isSuccess()) {
			List<InvoiceDto> lin = new ArrayList<>();
			lin = invoiceService.read(o.getLineItems(), clientAccountId);

			for (CreditNote i : lcn) {
				if (lcc.size() > 0) {
					err = ZValid.customer(lcc, i.getCustomerFromExcel(), 0, false, o.getFactorCode(),
							req.getAcceptanceDate(), ca);
					errors = ZError.addError(errors, err, i.getName());
					if (!err.isEmpty()) {
						/* ToanNguyen 2018-Aug-23 IFS-974,1025,1026 */
						res.setSuccess(false);
					}
				}

				/* ToanNguyen 2018-Sep-02 IFS-1056 */
				if (lin.size() > 0) {
					err = ZValid.appliedInvoices(lin, i);
					errors = ZError.addError(errors, err, i.getName());

					if (!err.isEmpty()) {
						res.setCreditValid(false);
					}
				} else {
					// Credit Note - Invoice Number not found.
					err = "CN1";
					errors = ZError.addError(errors, err, i.getName());
					if (!err.isEmpty()) {
						res.setCreditValid(false);
					}
				}
			}
		}
		res.setErrors(errors);

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
	private boolean createSchedule(ValidDto dto, Date acceptanceDate, UploadReq req, String portalStatus) {
		boolean res = true;

		try {
			ExcelDto o = dto.data;
			String type = o.getType();
			boolean isLoan = "CASH DISBURSEMENT".equals(type);
			boolean isCN = "CREDIT NOTE".equals(type);
			boolean isIV = "INVOICE".equals(type);
			boolean isInvoice = isLoan || isIV;
			Float sequence = dto.sequence.floatValue();
			type = Utils.formatStr(type);

			ScheduleOfOffer m = new ScheduleOfOffer();
			m.setDocumentType(type);
			m.setScheduleNo(o.getScheduleNo());
			m.setSequence(sequence);
			m.setAcceptanceDate(acceptanceDate);
			m.setOriginalAcceptanceDate(acceptanceDate);
			m.setFactorCode(o.getFactorCode());
			m.setCurrencyIsoCode(o.getDocumentCurrency());
			m.setListType(o.getListType());
			m.setClientAccount(req.getClientAccountId());
			m.setClientName(req.getClientId());
			m.setScheduleStatus("Draft");
			m.setPortalStatus(portalStatus);
			m.setCreatedByPortalUserId(dto.lastModifiedByPortalUserId);
			m.setInvoiceDataPath(dto.invoiceDataPath);
			m.setScheduleDate(o.getDocumentDate());
			m.setProcessDate(o.getProcessDate());
			m.setKeyInByDate(o.getKeyInByDate());
			m.setCreatedDate(new Date());

			if (sequence > 0) {
				String temp = o.getScheduleNo() + "-" + sequence.intValue();
				m.setName(temp);
			} else {
				m.setName(o.getScheduleNo());
			}

			String rid = ""; // RecordType ID
			if (isLoan) {
				rid = "Loan";
			}
			if (isCN) {
				rid = "Credit Note";
			}
			if (isIV) {
				rid = "Factoring";
			}
			RecordTypeDto rt = recordTypeService.getBy("Schedule_Of_Offer__c", rid);
			m.setRecordTypeId(rt.getSfId());

			scheduleOfOfferService.create(m);

			if (isCN) {
				createCreditNote(o, m, dto);
			} else {
				createInvoice(o, isInvoice, m, dto);
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
	private boolean createInvoice(ExcelDto o, boolean isInvoice, ScheduleOfOffer so, ValidDto t) {
		boolean res = false;

		try {
			String name = isInvoice ? "Factoring" : "Loan";
			RecordTypeDto dto = recordTypeService.getBy("Invoice__c", name);
			List<AccountDto> lac = accountService.getByNames(o.getLineItems());
			List<Invoice> l = new ArrayList<Invoice>();

			for (LineItemDto i : o.getLineItems()) {
				if (i.getName() != null && !i.getName().isEmpty()) {
					Invoice m = new Invoice();
					m.setScheduleOfOffer(so.getSfId());
					m.setDocumentType(so.getDocumentType());
					m.setRecordTypeId(dto.getSfId());
					m.setClientName(so.getClientName());
					m.setClientAccount(so.getClientAccount());
					m.setCurrencyIsoCode(so.getCurrencyIsoCode());
					m.setClientRemarks(i.getRemarks());
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
					m.setContract(i.getContract());
					m.setCreatedDate(new Date());
					m.setExternalId(so.getId().floatValue());
					invoiceService.create(m);
					l.add(m);
				}
			}

			// Insert reason error
			// ---------------------------------------
			HashMap<String, String> m1 = new HashMap<>();
			RecordTypeDto rt = new RecordTypeDto();

			if (!t.isInvoiceValid()) {
				// Vinh vost5 https://crimsonworks.atlassian.net/browse/IFS-1228
				if ("CASH DISBURSEMENT".equals(o.getType())) {
					rt = recordTypeService.getBy("Reason__c", "System Rejected");
				} else {
					rt = recordTypeService.getBy("Reason__c", "System Unfunded");
				}
				m1 = Utils.toMapInvoice(l);
			}
			List<String> listId = new ArrayList<String>();
			for (String key : t.getErrors().keySet()) {
				String val = t.getErrors().get(key);
				String[] arr = val.split(", ");

				for (String i : arr) {
					String tid = m1.get(i); // get invoice or credit ID
					listId.add(tid);
					try {
						Reason r = new Reason();
						r.setRecordTypeId(rt.getSfId());
						r.setReason(key);
						r.setInvoice(tid);
						// r.setExternalId();
						r.setCreatedDate(new Date());
						reasonService.create(r);
					} catch (Exception ex) {
						// res.result = "Missing code reason " + key;
					}
				}
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
	private boolean createCreditNote(ExcelDto o, ScheduleOfOffer so, ValidDto t) {
		boolean res = false;

		List<ClientAccountCustomerDto> lcc = clientAccountCustomerService.getByClientId(so.getClientAccount());
		List<CreditNote> l = new ArrayList<CreditNote>();

		try {
			for (LineItemDto i : o.getLineItems()) {
				if (i.getName() != null && !i.getName().isEmpty()) {
					CreditNote m = new CreditNote();
					m.setScheduleOfOffer(so.getSfId());
					// m.setDocumentType("x");
					// m.setRecordTypeId("x");
					m.setClient(so.getClientName());
					m.setClientAccount(so.getClientAccount());
					m.setCurrencyIsoCode(so.getCurrencyIsoCode());
					m.setClientRemarks(i.getRemarks());
					m.setCreditAmount(0f);
					m.setStatus("Accepted");

					m.setCustomer(Utils.getAccCusIdByName(lcc, i.getName()));
					m.setCustomerFromExcel(i.getName());
					m.setCustomerBranch(i.getBranch());
					m.setName(i.getNo());
					m.setCreditNoteDate(i.getItemDate());
					m.setCreditAmount(i.getAmount().floatValue());
					m.setAppliedInvoice(m.getAppliedInvoice());
					m.setCreatedDate(new Date());
					m.setExternalId(so.getId().floatValue());
					creditNoteService.create(m);
					l.add(m);
				}
			}

			// Insert reason error
			// ---------------------------------------
			HashMap<String, String> m1 = new HashMap<>();
			RecordTypeDto rt = new RecordTypeDto();

			if (!t.isCreditValid()) {
				rt = recordTypeService.getBy("Reason__c", "System Unapplied");
				m1 = Utils.toMapCredit(l);
			}
			List<String> listId = new ArrayList<String>();
			for (String key : t.getErrors().keySet()) {
				String val = t.getErrors().get(key);
				String[] arr = val.split(", ");

				for (String i : arr) {
					String tid = m1.get(i); // get invoice or credit ID
					listId.add(tid);
					try {
						Reason r = new Reason();
						r.setRecordTypeId(rt.getSfId());
						r.setReason(key);
						r.setCreditNote(tid);
						// r.setExternalId();
						r.setCreatedDate(new Date());

						reasonService.create(r);
					} catch (Exception ex) {
						// res.result = "Missing code reason " + key;
					}
				}
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