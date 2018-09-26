package com.ifs.eportal.req;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditNoteReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "customerBranch")
	private String customerBranch;
	
	@JsonProperty(value = "customerFromExcel")
	private String customerFromExcel;
	
	@JsonProperty(value = "currencyIsoCode")
	private String currencyIsoCode;

	@JsonProperty(value = "clientRemarks")
	private String clientRemarks;
	
	@JsonProperty(value = "creditAmount")
	private Float creditAmount;
	
	@JsonProperty(value = "customer")
	private String customer;
	
	@JsonProperty(value = "scheduleOfOffer")
	private String scheduleOfOffer;
	
	@JsonProperty(value = "clientAccount")
	private String clientAccount;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "creditNoteDate")
	private Date creditNoteDate;
	
	@JsonProperty(value = "isDeleted")
	private boolean isDeleted;
	
	@JsonProperty(value = "systemModStamp")
	private Date systemModStamp;
	
	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "cnApplicationDate")
	private Date cnApplicationDate;
	
	@JsonProperty(value = "cnAppliedAmount")
	private Float cnAppliedAmount;
	
	@JsonProperty(value = "createdDate")
	private Date createdDate;
	
	@JsonProperty(value = "flag")
	private boolean flag;
	
	@JsonProperty(value = "client")
	private String client;
	
	@JsonProperty(value = "appliedInvoice")
	private String appliedInvoice;
	
	@JsonProperty(value = "applyCreditNote")
	private boolean applyCreditNote;
	
	@JsonProperty(value = "isSelected")
	private boolean isSelected;
	
	@JsonProperty(value = "sfid")
	private String sfid;
	
	@JsonProperty(value = "hcLastop")
	private String hcLastop;
	
	@JsonProperty(value = "hcErr")
	private String hcErr;
	
	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getClientRemarks() {
		return clientRemarks;
	}

	public void setClientRemarks(String clientRemarks) {
		this.clientRemarks = clientRemarks;
	}

	public Float getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Float creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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

	public Date getCreditNoteDate() {
		return creditNoteDate;
	}

	public void setCreditNoteDate(Date creditNoteDate) {
		this.creditNoteDate = creditNoteDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getSystemModStamp() {
		return systemModStamp;
	}

	public void setSystemModStamp(Date systemModStamp) {
		this.systemModStamp = systemModStamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCnApplicationDate() {
		return cnApplicationDate;
	}

	public void setCnApplicationDate(Date cnApplicationDate) {
		this.cnApplicationDate = cnApplicationDate;
	}

	public Float getCnAppliedAmount() {
		return cnAppliedAmount;
	}

	public void setCnAppliedAmount(Float cnAppliedAmount) {
		this.cnAppliedAmount = cnAppliedAmount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getAppliedInvoice() {
		return appliedInvoice;
	}

	public void setAppliedInvoice(String appliedInvoice) {
		this.appliedInvoice = appliedInvoice;
	}

	public boolean isApplyCreditNote() {
		return applyCreditNote;
	}

	public void setApplyCreditNote(boolean applyCreditNote) {
		this.applyCreditNote = applyCreditNote;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getHcLastop() {
		return hcLastop;
	}

	public void setHcLastop(String hcLastop) {
		this.hcLastop = hcLastop;
	}

	public String getHcErr() {
		return hcErr;
	}

	public void setHcErr(String hcErr) {
		this.hcErr = hcErr;
	}

	// end
	
	// region -- Methods --
	
	public CreditNoteReq() {
	}
	
	// end
}