package com.ifs.eportal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.ifs.eportal.bll.ScheduleOfOfferService;
import com.ifs.eportal.common.Const;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.ExcelDto;
import com.ifs.eportal.dto.LineItemDto;
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

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FileController() {
		_allowUploadFile = false;
	}

	/**
	 * Upload for Angular
	 * 
	 * @param file
	 * @param req
	 * @return ToanNguyen 2018-Oct-02
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
	 * @return ToanNguyen 2018-Oct-02
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
		} catch (Exception e) {
			e.printStackTrace();
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Validate step 1
	 * 
	 * @param o
	 * @param req
	 */
	private SingleRsp validateSchedule(MultipartFile file, UploadReq req) {
		SingleRsp res = new SingleRsp();
		String err = "File format is incorrect.";

		// Get data
		ExcelDto o = new ExcelDto();
		String name = file.getOriginalFilename();

		// Handle
		if (name.contains("Factoring-INV")) {
			o = getFactoringIv(file);
		} else if (name.contains("Loan-INV")) {
			o = getLoanIv(file);
		} else {
			o = getFactoringCn(file);
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
			if (scheduleType != o.getType()) {
				err = "Selected Type of Schedule is not same as in Excel.";
				res = Utils.addError(res, err);
			}

			/* ToanNguyen 2018-Aug-23 IFS-974 */
			Date now = Utils.getTime(Calendar.HOUR, 24);
			String sNow = Utils.dateFormat(now, Const.DateTime.YMD);
			String sAcceptanceDate = Utils.dateFormat(acceptanceDate, Const.DateTime.YMD);
			if (sNow.equals(sAcceptanceDate)) {
				err = "Schedule Acceptance Date cannot be future date and must be within current month.";
				res = Utils.addError(res, err);
			}

			// Find Client Name (ac)
			List<AccountDto> lac = accountService.read(name, clientId);
			AccountDto ac = new AccountDto();
			if (lac.size() == 1) {
				if (clientId == lac.get(0).getSfid()) {
					ac = lac.get(0);
				} else {
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
			List<ClientAccountDto> lca = clientAccountService.read(clientAccount, client);
			ClientAccountDto ca = new ClientAccountDto();

			if (lca.size() == 1) {
				ca = lca.get(0);

				/* NguyenMinh 2018-Sep-24 IFS-1203 */
				if (ca.getSfid() != clientAccountId) {
					err = "Selected Client Account is not same as in Excel.";
					res = Utils.addError(res, err);
				}

				/* ToanNguyen 2018-Sep-10 IFS-1164 */
//                if(isLoan  && ca.RecordType.Name != 'Loan') {
//                    err = 'Client Account is not of Loan Type.';
//                    res = Utils.addError(res, err);
//                    return res;
//                }
//                if(isFactoring && ca.RecordType.Name != 'Factoring') {
//                    err = 'Client Account is not of Factoring Type.';
//                    res = Utils.addError(res, err);
//                    return res;
//                }

				if (ca.getStatus() != "Activeted") {
					/* ToanNguyen 2018-Aug-23 IFS-974 */
					// chua co du lieu
//                    if(acceptanceDate < ca.Activated_On__c) {
//                        err = 'Schedule Acceptance Date cannot be before client account activation date.';
//                        res = Utils.addError(res, err);
//                    }
//                    
					/* TriNguyen 2018-Sep-04 IFS-1048 */
//                    if(!isCN) {
//                        Date t = ca.Activated_On__c.addMonths(6).addDays(-1);
//                        if(acceptanceDate < t) {
//                            // Client - New.
//                            err = 'IVE';
//                            errors = ZError.addError(errors, err, allNo);
//                            res.invoiceValid = false;
//                        }
//                    }

					/* TriNguyen 2018-Sep-04 IFS-1050 */
//                    if(ca.Verification__c != null && ca.Verification__c == 100) {
//                        // Client - 100% Verification Required.
//                        err = 'IVJ';
//                        errors = ZError.addError(errors, err, allNo);
//                        res.invoiceValid = false;
//                    }
//                    
					/* ToanNguyen 2018-Aug-30 IFS-977,1027 */
//                    if(!amendSchedule) {
//                        lso = [SELECT Id, Sequence__c FROM Schedule_Of_Offer__c
//                               WHERE Schedule_No__c = :scheduleNo
//                               AND Client_Name__c = :ac.Id
//                               AND Client_Account__c = :ca.Id];
//                        if(lso.size() > 0) {
//                            err = 'Schedule Number exists under Client Account.';
//                            res = Utils.addError(res, err);
//                        }
//                    }
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

		} catch (Exception ex) {

		}

		res.setError(err);

		return res;
	}
	/*
	 * if (isCN) { processCreditNote(o, req); } else { processInvoice(o, req); }
	 */

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

	private ExcelDto getFactoringCn(MultipartFile file) {
		ExcelDto note = new ExcelDto();

		try {
			String FILE_NAME = "Factoring-CN-0.2.xlsx";
			Workbook workbook;

			if (file == null) {
				FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
				workbook = new XSSFWorkbook(excelFile);
			} else {
				workbook = new XSSFWorkbook(file.getInputStream());
			}

			Sheet datatypeSheet = workbook.getSheetAt(0);

			String type = "";
			String client = "";
			String clientAccount = "";
			String factorCode = "";
			String scheduleNo = "";
			Date date = null;
			String currency = "";
			String totalAmount = "";
			String listType = "";
			Date processDate = null;
			Date keyInByDate = null;

			try {
				type = datatypeSheet.getRow(0).getCell(6).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				client = datatypeSheet.getRow(3).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				clientAccount = datatypeSheet.getRow(4).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				factorCode = datatypeSheet.getRow(5).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				scheduleNo = datatypeSheet.getRow(3).getCell(7).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				date = datatypeSheet.getRow(4).getCell(7).getDateCellValue();
			} catch (Exception ex) {

			}

			try {
				currency = datatypeSheet.getRow(5).getCell(7).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				totalAmount = datatypeSheet.getRow(6).getCell(7).toString();
			} catch (Exception ex) {

			}

			try {
				listType = datatypeSheet.getRow(8).getCell(0).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				processDate = datatypeSheet.getRow(51).getCell(1).getDateCellValue();
			} catch (Exception ex) {

			}

			try {
				keyInByDate = datatypeSheet.getRow(51).getCell(7).getDateCellValue();
			} catch (Exception ex) {

			}

			Iterator<Row> iterator = datatypeSheet.iterator();

			int count = 0;
			while (iterator.hasNext() && count < 10) {
				count += 1;
				iterator.next();
			}

			int index = 1;
			while (iterator.hasNext() && count < 40) {
				count += 1;
				Row row = iterator.next();

				String customerName = "";
				String customerBranch = "";
				String creditNoteNo = "";
				Date creditNoteDate = null;
				String amount = "";
				String invoiceApplied = "";
				String remarks = "";

				try {
					customerName = row.getCell(0).toString();
				} catch (Exception ex) {

				}

				try {
					customerBranch = row.getCell(2).toString();
				} catch (Exception ex) {

				}

				try {
					creditNoteNo = row.getCell(3).toString();
				} catch (Exception ex) {

				}

				try {
					creditNoteDate = row.getCell(4).getDateCellValue();
				} catch (Exception ex) {

				}

				try {
					amount = row.getCell(5).toString();
				} catch (Exception ex) {

				}

				try {
					invoiceApplied = row.getCell(6).toString();
				} catch (Exception ex) {

				}

				try {
					remarks = row.getCell(7).toString();
				} catch (Exception ex) {

				}

				LineItemDto item = new LineItemDto();

				item.setIndex(index);
				item.setName(customerName);
				item.setBranch(customerBranch);
				item.setNo(creditNoteNo);
				item.setItemDate(creditNoteDate);
				item.setAmount(amount);
				item.setInvoiceApplied(invoiceApplied);
				item.setRemarks(remarks);

				note.addLineItem(item);
				index += 1;
			}

			note.setType(type);
			note.setClient(client);
			note.setClientAccount(clientAccount);
			note.setFactorCode(factorCode);
			note.setScheduleNo(scheduleNo);
			note.setDocumentDate(date);
			note.setDocumentCurrency(currency);
			note.setTotalAmount(totalAmount);
			note.setListType(listType);
			note.setProcessDate(processDate);
			note.setKeyInByDate(keyInByDate);

			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return note;
	}

	private ExcelDto getFactoringIv(MultipartFile file) {
		ExcelDto note = new ExcelDto();

		try {
			String FILE_NAME = "Factoring-INV-0.2.xlsx";
			Workbook workbook;

			if (file == null) {
				FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
				workbook = new XSSFWorkbook(excelFile);
			} else {
				workbook = new XSSFWorkbook(file.getInputStream());
			}

			Sheet datatypeSheet = workbook.getSheetAt(0);

			String type = "";
			String client = "";
			String clientAccount = "";
			String factorCode = "";
			String scheduleNo = "";
			Date date = null;
			String currency = "";
			String totalAmount = "";
			String listType = "";
			Date processDate = null;
			Date keyInByDate = null;

			try {
				type = datatypeSheet.getRow(0).getCell(8).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				client = datatypeSheet.getRow(3).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				clientAccount = datatypeSheet.getRow(4).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				factorCode = datatypeSheet.getRow(5).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				scheduleNo = datatypeSheet.getRow(6).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				date = datatypeSheet.getRow(3).getCell(9).getDateCellValue();
			} catch (Exception ex) {

			}

			try {
				currency = datatypeSheet.getRow(4).getCell(9).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				totalAmount = datatypeSheet.getRow(5).getCell(9).toString();
			} catch (Exception ex) {

			}

			try {
				listType = datatypeSheet.getRow(8).getCell(0).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				processDate = datatypeSheet.getRow(51).getCell(1).getDateCellValue();
			} catch (Exception ex) {

			}

			try {
				keyInByDate = datatypeSheet.getRow(51).getCell(7).getDateCellValue();
			} catch (Exception ex) {

			}

			Iterator<Row> iterator = datatypeSheet.iterator();

			int count = 0;
			while (iterator.hasNext() && count < 10) {
				count += 1;
				iterator.next();
			}

			int index = 1;
			while (iterator.hasNext() && count < 40) {
				count += 1;
				Row row = iterator.next();

				String customerName = "";
				String customerBranch = "";
				String invoiceNo = "";
				Date invoiceDate = null;
				String invoiceAmount = "";
				String creditPeriod = "";
				String po = "";
				String contract = "";
				String remarks = "";

				try {
					customerName = row.getCell(0).toString();
				} catch (Exception ex) {

				}

				try {
					customerBranch = row.getCell(2).toString();
				} catch (Exception ex) {

				}

				try {
					invoiceNo = row.getCell(3).toString();
				} catch (Exception ex) {

				}

				try {
					invoiceDate = row.getCell(4).getDateCellValue();
				} catch (Exception ex) {

				}

				try {
					invoiceAmount = row.getCell(5).toString();
				} catch (Exception ex) {

				}

				try {
					creditPeriod = row.getCell(6).toString();
				} catch (Exception ex) {

				}

				try {
					po = row.getCell(7).toString();
				} catch (Exception ex) {

				}

				try {
					contract = row.getCell(8).toString();
				} catch (Exception ex) {

				}

				try {
					remarks = row.getCell(9).toString();
				} catch (Exception ex) {

				}

				LineItemDto item = new LineItemDto();

				item.setIndex(index);
				item.setName(customerName);
				item.setBranch(customerBranch);
				item.setNo(invoiceNo);
				item.setItemDate(invoiceDate);
				item.setAmount(invoiceAmount);
				item.setPeriod(creditPeriod);
				item.setPo(po);
				item.setContract(contract);
				item.setRemarks(remarks);

				note.addLineItem(item);
				index += 1;
			}

			note.setType(type);
			note.setClient(client);
			note.setClientAccount(clientAccount);
			note.setFactorCode(factorCode);
			note.setScheduleNo(scheduleNo);
			note.setDocumentDate(date);
			note.setDocumentCurrency(currency);
			note.setTotalAmount(totalAmount);
			note.setListType(listType);
			note.setProcessDate(processDate);
			note.setKeyInByDate(keyInByDate);

			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return note;
	}

	private ExcelDto getLoanIv(MultipartFile file) {
		ExcelDto note = new ExcelDto();

		try {
			String FILE_NAME = "Loan-INV-0.3.xlsx";
			Workbook workbook;

			if (file == null) {
				FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
				workbook = new XSSFWorkbook(excelFile);
			} else {
				workbook = new XSSFWorkbook(file.getInputStream());
			}

			Sheet datatypeSheet = workbook.getSheetAt(0);

			String type = "";
			String client = "";
			String clientAccount = "";
			// String factorCode = "";
			String scheduleNo = "";
			Date date = null;
			String currency = "";
			String totalAmount = "";
			String listType = "";
			Date processDate = null;
			Date keyInByDate = null;

			try {
				type = datatypeSheet.getRow(0).getCell(7).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				client = datatypeSheet.getRow(3).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				clientAccount = datatypeSheet.getRow(4).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				// factorCode = datatypeSheet.getRow(5).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				scheduleNo = datatypeSheet.getRow(5).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				date = datatypeSheet.getRow(3).getCell(9).getDateCellValue();
			} catch (Exception ex) {

			}

			try {
				currency = datatypeSheet.getRow(4).getCell(9).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				totalAmount = datatypeSheet.getRow(5).getCell(9).toString();
			} catch (Exception ex) {

			}

			try {
				listType = datatypeSheet.getRow(8).getCell(0).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				processDate = datatypeSheet.getRow(51).getCell(1).getDateCellValue();
			} catch (Exception ex) {

			}

			try {
				keyInByDate = datatypeSheet.getRow(51).getCell(7).getDateCellValue();
			} catch (Exception ex) {

			}

			Iterator<Row> iterator = datatypeSheet.iterator();

			int count = 0;
			while (iterator.hasNext() && count < 10) {
				count += 1;
				iterator.next();
			}

			int index = 1;
			while (iterator.hasNext() && count < 40) {
				count += 1;
				Row row = iterator.next();

				String supplierName = "";
				String invoiceNo = "";
				Date invoiceDate = null;
				String invoiceAmount = "";
				String creditPeriod = "";
				String po = "";
				String contract = "";
				Date paymentDate = null;
				String remarks = "";

				try {
					supplierName = row.getCell(0).toString();
				} catch (Exception ex) {

				}

				try {
					invoiceNo = row.getCell(2).toString();
				} catch (Exception ex) {

				}

				try {
					invoiceDate = row.getCell(3).getDateCellValue();
				} catch (Exception ex) {

				}

				try {
					invoiceAmount = row.getCell(4).toString();
				} catch (Exception ex) {

				}

				try {
					creditPeriod = row.getCell(5).toString();
				} catch (Exception ex) {

				}

				try {
					po = row.getCell(6).toString();
				} catch (Exception ex) {

				}

				try {
					contract = row.getCell(7).toString();
				} catch (Exception ex) {

				}

				try {
					paymentDate = row.getCell(8).getDateCellValue();
				} catch (Exception ex) {

				}

				try {
					remarks = row.getCell(9).toString();
				} catch (Exception ex) {

				}

				LineItemDto item = new LineItemDto();

				item.setIndex(index);
				item.setName(supplierName);
				item.setNo(invoiceNo);
				item.setItemDate(invoiceDate);
				item.setAmount(invoiceAmount);
				item.setPeriod(creditPeriod);
				item.setPo(po);
				item.setContract(contract);
				item.setPaymentDate(paymentDate);
				item.setRemarks(remarks);

				note.addLineItem(item);
				index += 1;
			}

			note.setType(type);
			note.setClient(client);
			note.setClientAccount(clientAccount);
			// note.setFactorCode(factorCode);
			note.setScheduleNo(scheduleNo);
			note.setDocumentDate(date);
			note.setDocumentCurrency(currency);
			note.setTotalAmount(totalAmount);
			note.setListType(listType);
			note.setProcessDate(processDate);
			note.setKeyInByDate(keyInByDate);

			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return note;
	}

	// end
}