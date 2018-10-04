package com.ifs.eportal.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.Utils;

/**
 * 
 * @author ToanNguyen 2018-Sep-28
 *
 */
public class ExcelDto {
	// region -- Fields --

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "documentDate")
	private Date documentDate;

	@JsonProperty(value = "documentCurrency")
	private String documentCurrency;

	@JsonProperty(value = "totalAmount")
	private String totalAmount;

	@JsonProperty(value = "listType")
	private String listType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "processDate")
	private Date processDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "keyInByDate")
	private Date keyInByDate;

	@JsonProperty(value = "factorCode")
	private String factorCode;

	@JsonProperty(value = "lineItems")
	private ArrayList<LineItemDto> lineItems = new ArrayList<LineItemDto>();

	// end

	// region -- Get set --

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentCurrency() {
		return documentCurrency;
	}

	public void setDocumentCurrency(String documentCurrency) {
		this.documentCurrency = documentCurrency;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public Date getKeyInByDate() {
		return keyInByDate;
	}

	public void setKeyInByDate(Date keyInByDate) {
		this.keyInByDate = keyInByDate;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public ArrayList<LineItemDto> getLineItems() {
		return lineItems;
	}

	public void setLineItems(ArrayList<LineItemDto> lineItems) {
		this.lineItems = lineItems;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ExcelDto() {
		lineItems = new ArrayList<LineItemDto>();
	}

	/**
	 * Add line item
	 * 
	 * @param item
	 */
	public void addLineItem(LineItemDto item) {
		this.lineItems.add(item);
	}

	/**
	 * Read JSON data
	 * 
	 * @param file
	 * @return
	 */
	public static ExcelDto read(String file) {
		ExcelDto res = new ExcelDto();

		try {
			FileReader s = new FileReader(file);
			ObjectMapper mapper = new ObjectMapper();
			res = mapper.readValue(s, ExcelDto.class);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				System.out.println(ex.getMessage());
			}
		}

		return res;
	}

	/**
	 * Get factoring credit note
	 * 
	 * @param file Excel
	 * @return
	 */
	public static ExcelDto getFactoringCn(MultipartFile file) {
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
				scheduleNo = datatypeSheet.getRow(6).getCell(1).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				date = datatypeSheet.getRow(3).getCell(7).getDateCellValue();
			} catch (Exception ex) {

			}

			try {
				currency = datatypeSheet.getRow(4).getCell(7).getStringCellValue();
			} catch (Exception ex) {

			}

			try {
				totalAmount = datatypeSheet.getRow(5).getCell(7).getNumericCellValue() + "";
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
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				System.out.println(ex.getMessage());
			}
		}

		return note;
	}

	/**
	 * Get factoring invoice
	 * 
	 * @param file Excel
	 * @return
	 */
	public static ExcelDto getFactoringIv(MultipartFile file) {
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
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				System.out.println(ex.getMessage());
			}
		}

		return note;
	}

	/**
	 * Get loan invoice
	 * 
	 * @param file Excel
	 * @return
	 */
	public static ExcelDto getLoanIv(MultipartFile file) {
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
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				System.out.println(ex.getMessage());
			}
		}

		return note;
	}

	// end
}