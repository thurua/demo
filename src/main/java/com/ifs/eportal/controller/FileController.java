package com.ifs.eportal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
import com.ifs.eportal.bll.CreditNoteService;
import com.ifs.eportal.bll.InvoiceService;
import com.ifs.eportal.bll.ScheduleOfOfferService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.ExcelDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.model.Invoice;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.rsp.SingleRsp;

@RestController
@RequestMapping("/file")
public class FileController {
	// region -- Fields --
	@Autowired
	private ScheduleOfOfferService scheduleOfOfferService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private CreditNoteService creditNoteService;
	// end

	// region -- Methods --

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("req") String req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			ExcelDto note = new ExcelDto();
			String name = file.getOriginalFilename();

			// Handle
			if (name.contains("Factoring-INV")) {
				note = getFactoringIv(file);
			} else if (name.contains("Loan-INV")) {
				note = getLoanIv(file);
			} else {
				note = getFactoringCn(file);
			}

			processInvoice(note, req);

			JSONObject o = new JSONObject(req);
			String a = o.getString("clientName");
			if (a != note.getClient()) {
				res.setError("Client Name not found.");
			}
			a = o.getString("amendScheduleNo");
			if (a != note.getScheduleNo()) {
				res.setError("Duplicate Schedule Number found in Client Account.");
			}

		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/read")
	public String read(@RequestParam("file") MultipartFile file,
			@RequestHeader(value = "Auth", defaultValue = "") String auth) {
		String res = "";

		try {
			String t = Utils.getAuth();
			System.out.println("Auth: " + t);

			if (!Utils.checkAuth(auth)) {
				return "error=invalid-auth";
			}

			ExcelDto note = new ExcelDto();
			String name = file.getOriginalFilename();
			System.out.println("File name: " + name);

			if (name.contains("Factoring-INV")) {
				note = getFactoringIv(file);
			} else if (name.contains("Loan-INV")) {
				note = getLoanIv(file);
			} else {
				note = getFactoringCn(file);
			}

			// Upload file to S3
			// InputStream in = file.getInputStream();
			// Utils.upload(in, name, "test");

			ObjectMapper mapper = new ObjectMapper();
			res = mapper.writeValueAsString(note);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("JSON: " + res);
		return res;
	}

	@PostMapping("/call")
	public ResponseEntity<?> call(@RequestParam("file") MultipartFile file, @RequestParam("req") String req) {
		SingleRsp res = new SingleRsp();

		try {
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

			processInvoice(o, req);

			res.setResult(o);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
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
				item.setCustomerName(customerName);
				item.setCustomerBranch(customerBranch);
				item.setCreditNoteNo(creditNoteNo);
				item.setCreditNoteDate(creditNoteDate);
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
				item.setCustomerName(customerName);
				item.setCustomerBranch(customerBranch);
				item.setInvoiceNo(invoiceNo);
				item.setInvoiceDate(invoiceDate);
				item.setInvoiceAmount(invoiceAmount);
				item.setCreditPeriod(creditPeriod);
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

	private ExcelDto getLoanIv(@RequestParam("file") MultipartFile file) {
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
				item.setSupplierName(supplierName);
				item.setInvoiceNo(invoiceNo);
				item.setInvoiceDate(invoiceDate);
				item.setInvoiceAmount(invoiceAmount);
				item.setCreditPeriod(creditPeriod);
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

	/**
	 * process Invoice by nlduong
	 * 
	 * @param o,req
	 * @return
	 */
	private boolean processInvoice(ExcelDto o, String req) {

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
	 * process Invoice by nlduong
	 * 
	 * @param o,req
	 * @return
	 */
	private boolean processCreditNote(ExcelDto o, String req) {

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