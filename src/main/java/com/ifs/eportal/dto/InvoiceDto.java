package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-28
 *
 */
public class InvoiceDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "sfId")
	private String sfId;

	@JsonProperty(value = "customerBranch")
	private String customerBranch;

	@JsonProperty(value = "customerFromExcel")
	private String customerFromExcel;

	@JsonProperty(value = "currencyIsoCode")
	private String currencyIsoCode;

	@JsonProperty(value = "invoiceDate")
	private Date invoiceDate;

	@JsonProperty(value = "clientName")
	private String clientName;

	@JsonProperty(value = "po")
	private String po;

	@JsonProperty(value = "clientRemarks")
	private String clientRemarks;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "documentType")
	private String documentType;

	@JsonProperty(value = "recordTypeId")
	private String recordTypeId;

	@JsonProperty(value = "scheduleOfOffer")
	private String scheduleOfOffer;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "contract")
	private String contract;

	@JsonProperty(value = "creditPeriod")
	private Double creditPeriod;

	@JsonProperty(value = "outstandingAmount")
	private Double outstandingAmount;

	@JsonProperty(value = "invoiceAmount")
	private Double invoiceAmount;

	@JsonProperty(value = "supplier")
	private String supplier;

	// end

	// region -- Get set --

	public String getSfId() {
		return sfId;
	}

	public void setSfId(String sfId) {
		this.sfId = sfId;
	}

	public String getCustomerBranch() {
		return customerBranch;
	}

	public void setCustomerBranch(String customerBranch) {
		this.customerBranch = customerBranch;
	}

	public String getCustomerFromExcel() {
		return customerFromExcel;
	}

	public void setCustomerFromExcel(String customerFromExcel) {
		this.customerFromExcel = customerFromExcel;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getClientRemarks() {
		return clientRemarks;
	}

	public void setClientRemarks(String clientRemarks) {
		this.clientRemarks = clientRemarks;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public String getScheduleOfOffer() {
		return scheduleOfOffer;
	}

	public void setScheduleOfOffer(String scheduleOfOffer) {
		this.scheduleOfOffer = scheduleOfOffer;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public Double getCreditPeriod() {
		return creditPeriod;
	}

	public void setCreditPeriod(Double creditPeriod) {
		this.creditPeriod = creditPeriod;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public InvoiceDto() {
		super();

		sfId = "";
		customerBranch = "";
		customerFromExcel = "";
		currencyIsoCode = "";
		invoiceDate = null;
		clientName = "";
		po = "";
		clientRemarks = "";
		customer = "";
		documentType = "";
		recordTypeId = "";
		scheduleOfOffer = "";
		clientAccount = "";
		name = "";
		status = "";
		contract = "";
		creditPeriod = null;
		outstandingAmount = null;
		invoiceAmount = null;
		supplier = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static InvoiceDto convert(Object[] o) {
		InvoiceDto res = new InvoiceDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);
		res.setCustomerBranch((String) o[2]);
		res.setCustomerFromExcel((String) o[3]);
		res.setCurrencyIsoCode((String) o[4]);
		res.setInvoiceDate((Date) o[5]);
		res.setPo((String) o[6]);
		res.setClientRemarks((String) o[7]);
		res.setCustomer((String) o[8]);
		res.setDocumentType((String) o[9]);
		res.setName((String) o[10]);
		res.setStatus((String) o[11]);
		res.setContract((String) o[12]);
		res.setScheduleOfOffer((String) o[13]);
		res.setClientAccount((String) o[14]);
		res.setClientName((String) o[15]);
		res.setCreditPeriod((Double) o[16]);
		res.setOutstandingAmount((Double) o[17]);
		res.setInvoiceAmount((Double) o[18]);
		res.setSupplier((String) o[19]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<InvoiceDto> convert(List<Object[]> l) {
		List<InvoiceDto> res = new ArrayList<InvoiceDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}