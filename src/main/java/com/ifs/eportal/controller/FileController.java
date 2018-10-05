package com.ifs.eportal.controller;

import java.io.InputStream;
import java.util.Calendar;
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
import com.ifs.eportal.bll.ClientAccountService;
import com.ifs.eportal.bll.CreditNoteService;
import com.ifs.eportal.bll.CurrencyTypeService;
import com.ifs.eportal.bll.InvoiceService;
import com.ifs.eportal.bll.RecordTypeService;
import com.ifs.eportal.bll.ScheduleOfOfferAttachmentService;
import com.ifs.eportal.bll.ScheduleOfOfferService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.common.ZError;
import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.CurrencyTypeDto;
import com.ifs.eportal.dto.ExcelDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.dto.RecordTypeDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
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
	private ScheduleOfOfferAttachmentService scheduleOfOfferAttachmentService;

	@Autowired
	private CurrencyTypeService currencyTypeService;

	@Autowired
	private RecordTypeService recordTypeService;

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
//	@PostMapping("/validate")
	private SingleRsp validateSchedule(MultipartFile file, UploadReq req) {
		SingleRsp res = new SingleRsp();
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
		boolean isInvoice = isLoan || isIV;

		// Get params
		String clientId = req.getClientId();
		String scheduleNo = req.getScheduleNo().trim();
		Date acceptanceDate = Utils.getTime(Calendar.HOUR, 24);
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
			if (scheduleNo != o.getScheduleNo()) {
				err = "Schedule No. entered is not the same as in Excel.";
				res = Utils.addError(res, err);
			}
		}

		// Check schedule no
		ScheduleOfOfferDto so = scheduleOfOfferService.read(scheduleNo, clientId);
		Float sequence = 0f;
		if (so.getId() > 0) {
			if (amendSchedule) {
				/* ToanNguyen 2018-Aug-23 IFS-976 */
				if (so.getSequence() != null) {
					sequence = so.getSequence().floatValue();
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
				if (!ca.getClientAccount().equals(o.getClientAccount())) {
					err = "Selected Client Account is not same as in Excel.";
					res = Utils.addError(res, err);
				}

				/* ToanNguyen 2018-Sep-10 IFS-1164 */
				String recordTypeName = ca.getRecordTypeName();
				if (isLoan && !"Loan".equals(recordTypeName)) {
					err = "Client Account is not of Loan Type.";
					res = Utils.addError(res, err);
				}
				if (isLoan && !"Factoring".equals(recordTypeName)) {
					err = "Client Account is not of Factoring Type.";
					res = Utils.addError(res, err);
				}

				if (!"Activated".equals(ca.getStatus())) {
					/* ToanNguyen 2018-Aug-23 IFS-974 */
					if (acceptanceDate.before(ca.getActivatedOn())) {
						err = "Schedule Acceptance Date cannot be before client account activation date.";
						res = Utils.addError(res, err);
					}

					/* TriNguyen 2018-Sep-04 IFS-1048 */
					if (!isCN) {
						DateTime t = new DateTime(ca.getActivatedOn());
						t.plusMonths(6);
						t.plusDays(-1);
						DateTime tempAcceptanceDate = new DateTime(acceptanceDate);
						if (tempAcceptanceDate.isBefore(t)) {
							// Client - New.
							err = "IVE";
							errors = ZError.addError(errors, err, allNo);
							// res.invoiceValid = false;
						}
					}

					/* TriNguyen 2018-Sep-04 IFS-1050 */
					if (ca.getVerification() != null && ca.getVerification() == 100) {
						// Client - 100% Verification Required.
						err = "IVJ";
						errors = ZError.addError(errors, err, allNo);
						// res.invoiceValid = false;
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
					// res.invoiceValid = false;
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

			err = res.getMessage();
			if (!err.isEmpty()) {
				res.setError(err);
			} else {
				if (isCN) {
					res = validateCreditNote(o, req);
				} else {
					res = validateInvoice(o, req);
				}
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
	private boolean createSchedule(ExcelDto o, Float sequence, Date acceptanceDate) {
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
			m.setSequence(sequence);
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

			for (LineItemDto i : l) {
				Invoice m = new Invoice();
				m.setScheduleOfOffer(m.getSfid());
				m.setDocumentType(m.getDocumentType());
				m.setRecordTypeId(dto.getSfId());
				m.setClientName("x");
				m.setClientAccount("x");
				m.setCurrencyIsoCode("x");
				m.setClientRemarks("x");
				m.setInvoiceAmount(0f);
				m.setStatus("Pending");

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

		try {
			for (LineItemDto i : l) {
				CreditNote m = new CreditNote();
				m.setScheduleOfOffer(m.getSfid());
				// m.setDocumentType("x");
				// m.setRecordTypeId("x");
				m.setClient("x");
				m.setClientAccount("x");
				m.setCurrencyIsoCode("x");
				m.setClientRemarks("x");
				m.setCreditAmount(0f);
				m.setStatus("Accepted");

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