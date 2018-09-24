package com.ifs.eportal.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LineItemDto {
	// region -- Fields --

	@JsonProperty(value = "index")
	private int index;

	@JsonProperty(value = "customerName")
	private String customerName;

	@JsonProperty(value = "customerBranch")
	private String customerBranch;

	@JsonProperty(value = "invoiceNo")
	private String invoiceNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "invoiceDate")
	private Date invoiceDate;

	@JsonProperty(value = "invoiceAmount")
	private String invoiceAmount;

	@JsonProperty(value = "creditPeriod")
	private String creditPeriod;

	@JsonProperty(value = "po")
	private String po;

	@JsonProperty(value = "contract")
	private String contract;

	@JsonProperty(value = "remarks")
	private String remarks;

	@JsonProperty(value = "creditNoteNo")
	private String creditNoteNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "creditNoteDate")
	private Date creditNoteDate;

	@JsonProperty(value = "amount")
	private String amount;

	@JsonProperty(value = "invoiceApplied")
	private String invoiceApplied;

	@JsonProperty(value = "supplierName")
	private String supplierName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "paymentDate")
	private Date paymentDate;

	// end

	// region -- Get set --

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerBranch() {
		return customerBranch;
	}

	public void setCustomerBranch(String customerBranch) {
		this.customerBranch = customerBranch;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getCreditPeriod() {
		return creditPeriod;
	}

	public void setCreditPeriod(String creditPeriod) {
		this.creditPeriod = creditPeriod;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreditNoteNo() {
		return creditNoteNo;
	}

	public void setCreditNoteNo(String creditNoteNo) {
		this.creditNoteNo = creditNoteNo;
	}

	public Date getCreditNoteDate() {
		return creditNoteDate;
	}

	public void setCreditNoteDate(Date creditNoteDate) {
		this.creditNoteDate = creditNoteDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInvoiceApplied() {
		return invoiceApplied;
	}

	public void setInvoiceApplied(String invoiceApplied) {
		this.invoiceApplied = invoiceApplied;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	// end

	// region -- Methods --

	public LineItemDto() {

	}

	// end
}