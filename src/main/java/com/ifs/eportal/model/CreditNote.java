package com.ifs.eportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "credit_note__c", schema = "salesforce")
public class CreditNote {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_note__c_id_seq_generator")
	@SequenceGenerator(name = "credit_note__c_id_seq_generator", sequenceName = "salesforce.credit_note__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(255)", name = "customer_branch__c")
	private String customerBranch;

	@Column(columnDefinition = "varchar(255)", name = "customer_from_excel__c")
	private String customerFromExcel;

	@Column(columnDefinition = "varchar(3)", name = "currencyisocode")
	private String currencyIsoCode;

	@Column(columnDefinition = "text", name = "client_remarks__c")
	private String clientRemarks;

	@Column(columnDefinition = "float8", name = "credit_amount__c")
	private Float creditAmount;

	@Column(columnDefinition = "varchar(18)", name = "customer__c")
	private String customer;

	@Column(columnDefinition = "varchar(18)", name = "schedule_of_offer__c")
	private String scheduleOfOffer;

	@Column(columnDefinition = "varchar(18)", name = "client_account__c")
	private String clientAccount;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "credit_note_date__c")
	private Date creditNoteDate;

	@Column(columnDefinition = "bool", name = "isdeleted")
	private boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "systemmodstamp")
	private Date systemModStamp;

	@Column(columnDefinition = "varchar(255)", name = "status__c")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "cn_application_date__c")
	private Date cnApplicationDate;

	@Column(columnDefinition = "float8", name = "external_id__c")
	private String externalId;

	@Column(columnDefinition = "float8", name = "cn_applied_amount__c")
	private Float cnAppliedAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "createddate")
	private Date createdDate;

	@Column(columnDefinition = "bool", name = "flag__c")
	private boolean flag;

	@Column(columnDefinition = "varchar(18)", name = "client__c")
	private String client;

	@Column(columnDefinition = "varchar(255)", name = "applied_invoice__c")
	private String appliedInvoice;

	@Column(columnDefinition = "bool", name = "apply_credit_note__c")
	private boolean applyCreditNote;

	@Column(columnDefinition = "bool", name = "isselected__c")
	private boolean isSelected;

	@Column(columnDefinition = "varchar(18)", name = "sfid")
	private String sfid;

	@Column(columnDefinition = "varchar(32)", name = "_hc_lastop")
	private String hcLastop;

	@Column(columnDefinition = "text", name = "_hc_err")
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

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
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

	public CreditNote() {

	}

	// end
}