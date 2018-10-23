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

/**
 * 
 * @author ToanNguyen 2018-Oct-08 (verified)
 *
 */
@Entity
@Table(name = "credit_note__c", schema = "salesforce")
public class CreditNote extends BaseModel {
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

	@Column(columnDefinition = "float(8)", name = "credit_amount__c")
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

	@Column(columnDefinition = "varchar(255)", name = "status__c")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "cn_application_date__c")
	private Date cnApplicationDate;

	@Column(columnDefinition = "float(8)", name = "external_id__c")
	private Float externalId;

	@Column(columnDefinition = "float(8)", name = "cn_applied_amount__c")
	private Float cnAppliedAmount;

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

	@Column(columnDefinition = "varchar(18)", name = "createdbyid")
	private String createdById;

	@Column(columnDefinition = "float(8)", name = "outstanding_amount_ca__c")
	private Float outstandingAmountCa;

	@Column(columnDefinition = "float(8)", name = "outstanding_amount__c")
	private Float outstandingAmount;

	@Column(columnDefinition = "text", name = "ops_remarks__c")
	private String opsRemarks;

	@Column(columnDefinition = "varchar(18)", name = "unapplied_reason__c")
	private String unappliedReason;

	@Column(columnDefinition = "varchar(255)", name = "reason_code__c")
	private String reasonCode;

	@Column(columnDefinition = "varchar(100)", name = "uuid__c")
	private String uuId;

	@Column(columnDefinition = "varchar(100)", name = "parent_uuid__c")
	private String parentUuId;

	@Column(columnDefinition = "float(8)", name = "total_applied_c_n_amount__c")
	private Float totalAppliedCnAmount;

	@Column(columnDefinition = "float(8)", name = "reassigned_amount__c")
	private Float reassignedAmount;

	@Column(columnDefinition = "float(8)", name = "reversed_amount__c")
	private Float reversedAmount;

	@Column(columnDefinition = "float(8)", name = "invoice_applied_amount__c")
	private Float invoiceAppliedAmount;

	@Column(columnDefinition = "varchar(18)", name = "customer_branch_code__c")
	private String customerBranchCode;

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

	public Float getExternalId() {
		return externalId;
	}

	public void setExternalId(Float externalId) {
		this.externalId = externalId;
	}

	public Float getCnAppliedAmount() {
		return cnAppliedAmount;
	}

	public void setCnAppliedAmount(Float cnAppliedAmount) {
		this.cnAppliedAmount = cnAppliedAmount;
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

	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public Float getOutstandingAmountCa() {
		return outstandingAmountCa;
	}

	public void setOutstandingAmountCa(Float outstandingAmountCa) {
		this.outstandingAmountCa = outstandingAmountCa;
	}

	public Float getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Float outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public String getOpsRemarks() {
		return opsRemarks;
	}

	public void setOpsRemarks(String opsRemarks) {
		this.opsRemarks = opsRemarks;
	}

	public String getUnappliedReason() {
		return unappliedReason;
	}

	public void setUnappliedReason(String unappliedReason) {
		this.unappliedReason = unappliedReason;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getParentUuId() {
		return parentUuId;
	}

	public void setParentUuId(String parentUuId) {
		this.parentUuId = parentUuId;
	}

	public Float getTotalAppliedCNAmount() {
		return totalAppliedCnAmount;
	}

	public void setTotalAppliedCNAmount(Float totalAppliedCnAmount) {
		this.totalAppliedCnAmount = totalAppliedCnAmount;
	}

	public Float getReassignedAmount() {
		return reassignedAmount;
	}

	public void setReassignedAmount(Float reassignedAmount) {
		this.reassignedAmount = reassignedAmount;
	}

	public Float getReversedAmount() {
		return reversedAmount;
	}

	public void setReversedAmount(Float reversedAmount) {
		this.reversedAmount = reversedAmount;
	}

	public Float getInvoiceAppliedAmount() {
		return invoiceAppliedAmount;
	}

	public void setInvoiceAppliedAmount(Float invoiceAppliedAmount) {
		this.invoiceAppliedAmount = invoiceAppliedAmount;
	}

	public String getCustomerBranchCode() {
		return customerBranchCode;
	}

	public void setCustomerBranchCode(String customerBranchCode) {
		this.customerBranchCode = customerBranchCode;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public CreditNote() {
		super();
	}

	// end
}