package com.ifs.eportal.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-08 (verified)
 *
 */
public class LineItemDto {
	// region -- Fields --

	@JsonProperty(value = "index")
	private int index;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "branch")
	private String branch;

	@JsonProperty(value = "no")
	private String no;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "itemDate")
	private Date itemDate;

	@JsonProperty(value = "amount")
	private String amount;

	@JsonProperty(value = "period")
	private String period;

	@JsonProperty(value = "po")
	private String po;

	@JsonProperty(value = "contract")
	private String contract;

	@JsonProperty(value = "remarks")
	private String remarks;

	@JsonProperty(value = "invoiceApplied")
	private String invoiceApplied;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getItemDate() {
		return itemDate;
	}

	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public String getInvoiceApplied() {
		return invoiceApplied;
	}

	public void setInvoiceApplied(String invoiceApplied) {
		this.invoiceApplied = invoiceApplied;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public LineItemDto() {

	}

	// end
}