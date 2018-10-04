package com.ifs.eportal.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
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
import com.ifs.eportal.bll.InvoiceService;
import com.ifs.eportal.bll.ScheduleOfOfferAttachmentService;
import com.ifs.eportal.bll.ScheduleOfOfferService;
import com.ifs.eportal.common.Const;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.ExcelDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.model.Invoice;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.model.ScheduleOfOfferAttachment;
import com.ifs.eportal.req.AttachmentReq;
import com.ifs.eportal.req.UploadReq;
import com.ifs.eportal.rsp.MultipleRsp;
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

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FileController() {
		_allowUploadFile = false;
	}

	/**
	 * Upload for Angular (ScheduleOfOfferAttachment)
	 * 
	 * @param header
	 * @param files
	 * @return
	 * @author ToanNguyen 2018-Oct-04
	 */
	@PostMapping("/upload-multi")
	public ResponseEntity<?> upload(@RequestHeader HttpHeaders header, @RequestParam("files") MultipartFile[] files,
			@RequestParam("req") String req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Get data
			PayloadDto pl = Utils.getTokenInfor(header);
			String sfid = pl.getSfid();
			String fullName = pl.getName();

			ObjectMapper mapper = new ObjectMapper();
			AttachmentReq o = mapper.readValue(req, AttachmentReq.class);
			List<ScheduleOfOfferAttachment> l = new ArrayList<ScheduleOfOfferAttachment>();

			for (int i = 0; i < files.length; i++) {
				String originalName = files[i].getOriginalFilename() + "";
				String type = files[i].getContentType();
				float size = (float) files[i].getSize();

				// Get extension
				int t = originalName.lastIndexOf(".") + 1;
				String extension = originalName.substring(t);

				String url = System.getenv("BUCKETEER_BUCKET_URL");
				String path = o.getScheduleOfOfferId() + "/Attachment/";
				String name = UUID.randomUUID().toString() + extension;
				url += "/" + path + name;

				// Upload file to S3
				if (_allowUploadFile) {
					InputStream in = files[i].getInputStream();
					Utils.upload(in, name, path);
				}

				ScheduleOfOfferAttachment m = new ScheduleOfOfferAttachment();
				m.setSequence((float) i);
				m.setName(originalName);
				m.setExtension(extension);
				m.setUploadedBy(sfid);
				m.setContentType(type);
				m.setFilePath(url);
				m.setFileSize(size);

				scheduleOfOfferAttachmentService.create(m);

				m.setUploadedBy(fullName);
				l.add(m);

				// Set data
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("size", l.size());
				data.put("data", l);

				res.setResult(data);
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
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
		Date acceptanceDate = req.getAcceptanceDate();
		Boolean amendSchedule = req.getAmendSchedule();
		String clientAccountId = req.getClientAccountId();
		String scheduleType = req.getScheduleType();

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
		List<ScheduleOfOfferDto> lso = scheduleOfOfferService.read(scheduleNo, clientId);

		Double sequence = 0d;
		if (lso.size() > 0) {
			if (amendSchedule) {
				/* ToanNguyen 2018-Aug-23 IFS-976 */
				if (lso.get(0).getSequence() != null) {
					sequence = lso.get(0).getSequence();
				}
				sequence = sequence + 1;
			}
		}

		try {
			String client = o.getClient().trim();
			String clientAccount = o.getClientAccount().trim();
			/* NguyenMinh 2018-Sep-24 IFS-1203 */
			if (!scheduleType.equalsIgnoreCase(o.getType())) {
				err = "Selected Type of Schedule is not same as in Excel.";
				res = Utils.addError(res, err);
			}

			/* ToanNguyen 2018-Aug-23 IFS-974 */
			Date now = Utils.getTime(Calendar.HOUR, 24);
			String sNow = Utils.dateFormat(now, Const.DateTime.YMD);
			String sAcceptanceDate = Utils.dateFormat(acceptanceDate, Const.DateTime.YMD);
			if (sNow.equals(sAcceptanceDate)) {
				err = "Schedule Acceptance Date cannot be future date and must be within current month.";
				// res = Utils.addError(res, err);
			}

			// Find Client Name (ac)
			AccountDto ac = accountService.read(clientId);
			if (ac.getId() > 0) {
				if (!ac.getName().equalsIgnoreCase(client)) {
					err = "Client Name not same as in excel file.";
					res = Utils.addError(res, err);
					return res;
				}
			} else {
				/* ToanNguyen 2018-Aug-23 IFS-975 */
				err = "Client Name not found.";
				res = Utils.addError(res, err);
				return res;
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
					return res;
				}
				if (isLoan && !"Factoring".equals(recordTypeName)) {
					err = "Client Account is not of Factoring Type.";
					res = Utils.addError(res, err);
					return res;
				}

				if (ca.getStatus() != "Activeted") {
					/* ToanNguyen 2018-Aug-23 IFS-974 */

//                    if(acceptanceDate < ca.getActivatedOn()) {
//                        err = "Schedule Acceptance Date cannot be before client account activation date.";
//                        res = Utils.addError(res, err);
//                    }

					/* ToanNguyen 2018-Aug-30 IFS-977,1027 */
					if (!amendSchedule) {
//                        List<ScheduleOfOfferDto> lso1 = scheduleOfOfferService.read(scheduleNo, clientId);
//                        if(lso1.size() > 0) {
//                            err = "Schedule Number exists under Client Account.";
//                            res = Utils.addError(res, err);
//                        }
					}
				} else if (ca.getStatus() == "Closed") {
					/* ToanNguyen 2018-Aug-30 IFS-1024 */
					err = "Client Account is already closed.";
					res = Utils.addError(res, err);
				} else if (ca.getStatus() == "Suspended") {
					/* NhatNguyen 2018-Sep-03 IFS-1049 */
					// Client - Suspended.
					err = "IVF";
					// errors = ZError.addError(errors, err, allNo);
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
				return res;
			}

			// chua co du lieu

//			//vost9 - Tien Nguyen - validation currency - task IFS-1191
//            List<CurrencyType> lstCurrencyTemp = [SELECT Id, IsoCode, IsActive FROM CurrencyType
//                                                  WHERE IsoCode = :o.documentCurrency AND IsActive = true];
//            if(lstCurrencyTemp.size() == 0)
//            {
//                err = 'Currency is not supported.';
//                res = Utils.addError(res, err);
//                return res;
//            }

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
					if (isCN) {
						processCreditNote(o, req);
					} else {
						processInvoice(o, req);
					}
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
	 * Process invoice
	 * 
	 * @param o
	 * @param req
	 * @return
	 * @author DuongNguyen 2018-Oct-01
	 */
	private boolean processInvoice(ExcelDto o, UploadReq req) {
		boolean res = true;
		try {
			JSONObject jso = new JSONObject(req);
			String a = jso.getString("clientName");
			a = jso.getString("amendScheduleNo");

			ScheduleOfOffer so = new ScheduleOfOffer();
			so.setScheduleNo(o.getScheduleNo());
			so.setFactorCode(o.getFactorCode());
			so.setListType(o.getListType());
			so.setCurrencyIsoCode(o.getDocumentCurrency());
			so.setClientName(jso.getString("clientName"));
			// so.setClientAccount(jso.getString("clientAccount"));
			so.setScheduleStatus("Draft");

			scheduleOfOfferService.create(so);
			ArrayList<LineItemDto> arr = o.getLineItems();

			for (LineItemDto ob : arr) {
				Invoice iv = new Invoice();
				iv.setClientRemarks(ob.getRemarks());
				iv.setInvoiceAmount(0);
				iv.setStatus("Pending");
				// iv.setClientAccount(jso.getString("clientAccount"));
				iv.setClientName(jso.getString("clientName"));
				iv.setScheduleOfOffer(so.getId().toString());
				iv.setDocumentType(so.getDocumentType());
				iv.setCurrencyIsoCode(so.getCurrencyIsoCode());
				invoiceService.create(iv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Process credit note
	 * 
	 * @param o
	 * @param req
	 * @return
	 * @author DuongNguyen 2018-Oct-01
	 */
	private boolean processCreditNote(ExcelDto o, UploadReq req) {
		boolean res = true;
		try {
			JSONObject jso = new JSONObject(req);
			String a = jso.getString("clientName");
			a = jso.getString("amendScheduleNo");

			ScheduleOfOffer so = new ScheduleOfOffer();
			so.setScheduleNo(o.getScheduleNo());
			so.setFactorCode(o.getFactorCode());
			so.setListType(o.getListType());
			so.setCurrencyIsoCode(o.getDocumentCurrency());
			so.setClientName(jso.getString("clientName"));
			// so.setClientAccount(jso.getString("clientAccount"));
			so.setScheduleStatus("Draft");

			scheduleOfOfferService.create(so);
			ArrayList<LineItemDto> arr = o.getLineItems();

			for (LineItemDto ob : arr) {
				CreditNote cd = new CreditNote();
				cd.setClientRemarks(ob.getRemarks());
				cd.setCreditAmount((float) 0);
				cd.setStatus("Accepted");
				cd.setScheduleOfOffer(so.getId().toString());
				cd.setCurrencyIsoCode(so.getCurrencyIsoCode());
				creditNoteService.create(cd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	// end
}