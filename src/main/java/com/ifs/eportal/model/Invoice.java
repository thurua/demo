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
@Table(name = "invoice__c", schema = "salesforce")
public class Invoice extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice__c_id_seq_generator")
	@SequenceGenerator(name = "invoice__c_id_seq_generator", sequenceName = "salesforce.invoice__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(64)", name = "customer_branch__c")
	private String customerBranch;

	@Column(columnDefinition = "varchar(255)", name = "customer_from_excel__c")
	private String customerFromExcel;

	@Column(columnDefinition = "varchar(3)", name = "currencyisocode")
	private String currencyIsoCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "invoice_date__c")
	private Date invoiceDate;

	@Column(columnDefinition = "varchar(18)", name = "client_name__c")
	private String clientName;

	@Column(columnDefinition = "varchar(255)", name = "po__c")
	private String po;

	@Column(columnDefinition = "varchar(64)", name = "client_remarks__c")
	private String clientRemarks;

	@Column(columnDefinition = "varchar(18)", name = "customer__c")
	private String customer;

	@Column(columnDefinition = "varchar(255)", name = "document_type__c")
	private String documentType;

	@Column(columnDefinition = "varchar(18)", name = "recordtypeid")
	private String recordTypeId;

	@Column(columnDefinition = "varchar(18)", name = "schedule_of_offer__c")
	private String scheduleOfOffer;

	@Column(columnDefinition = "varchar(18)", name = "lastmodifiedby_portaluserid__c")
	private String lastModifiedByPortalUserId;

	@Column(columnDefinition = "varchar(18)", name = "client_account__c")
	private String clientAccount;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(255)", name = "contract__c")
	private String contract;

	@Column(columnDefinition = "varchar(18)", name = "createdby_portaluserid__c")
	private String createdByPortalUserId;

	@Column(columnDefinition = "varchar(255)", name = "status__c")
	private String status;

	@Column(columnDefinition = "float(8)", name = "external_id__c")
	private Float externalId;

	@Column(columnDefinition = "varchar(18)", name = "supplier__c")
	private String supplier;

	@Column(columnDefinition = "float(8)", name = "invoice_amount__c")
	private Float invoiceAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "payment_date__c")
	private Date paymentDate;

	@Column(columnDefinition = "float(8)", name = "credit_period__c")
	private Float creditPeriod;

	@Column(columnDefinition = "float(8)", name = "outstanding_amount__c")
	private Float outstandingAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "added_credit_period__c")
	private Date addedCreditPeriod;

	@Column(columnDefinition = "varchar(255)", name = "supplier_from_excel__c")
	private String supplierFromExcel;

	@Column(columnDefinition = "text", name = "ops_remarks__c")
	private String opsRemarks;

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

	public String getLastModifiedByPortalUserId() {
		return lastModifiedByPortalUserId;
	}

	public void setLastModifiedByPortalUserId(String lastModifiedByPortalUserId) {
		this.lastModifiedByPortalUserId = lastModifiedByPortalUserId;
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

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getCreatedByPortalUserId() {
		return createdByPortalUserId;
	}

	public void setCreatedByPortalUserId(String createdByPortalUserId) {
		this.createdByPortalUserId = createdByPortalUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getExternalId() {
		return externalId;
	}

	public void setExternalId(Float externalId) {
		this.externalId = externalId;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Float getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Float getCreditPeriod() {
		return creditPeriod;
	}

	public void setCreditPeriod(Float creditPeriod) {
		this.creditPeriod = creditPeriod;
	}

	public Float getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Float outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Date getAddedCreditPeriod() {
		return addedCreditPeriod;
	}

	public void setAddedCreditPeriod(Date addedCreditPeriod) {
		this.addedCreditPeriod = addedCreditPeriod;
	}

	public String getSupplierFromExcel() {
		return supplierFromExcel;
	}

	public void setSupplierFromExcel(String supplierFromExcel) {
		this.supplierFromExcel = supplierFromExcel;
	}

	public String getOpsRemarks() {
		return opsRemarks;
	}

	public void setOpsRemarks(String opsRemarks) {
		this.opsRemarks = opsRemarks;
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

	public Float getTotalAppliedCnAmount() {
		return totalAppliedCnAmount;
	}

	public void setTotalAppliedCnAmount(Float totalAppliedCnAmount) {
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
	public Invoice() {
		super();
	}

	// end
}