package com.ifs.eportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "fund_request_detail_customer__c", schema = "salesforce")
public class FundRequestDetailCustomer extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fund_request_detail_customer_id_seq_generator")
	@SequenceGenerator(name = "fund_request_detail_customer_id_seq_generator", sequenceName = "salesforce.fund_request_detail_customer_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "float(8)", name = "allocated_cl__c")
	private Float allocatedCl;

	@Column(columnDefinition = "float(8)", name = "ineligible_debts__c")
	private Float ineligibleDebts;

	@Column(columnDefinition = "float(8)", name = "allocated_cl_ca__c")
	private Float allocatedClCa;

	@Column(columnDefinition = "varchar(18)", name = "fund_request_detail__c")
	private String fundRequestDetail;

	@Column(columnDefinition = "varchar(100)", name = "uuid__c")
	private String uuId;

	@Column(columnDefinition = "float(8)", name = "unfunded_amount__c")
	private Float unfundedAmount;

	@Column(columnDefinition = "varchar(18)", name = "client_account__c")
	private String clientAccount;

	@Column(columnDefinition = "float(8)", name = "availability_on_ineligible_debts__c")
	private Float availabilityOnIneligibleDebts;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "float(8)", name = "eligible_debts__c")
	private Float eligibleDebts;

	@Column(columnDefinition = "float(8)", name = "outstanding_balance__c")
	private Float outstandingBalance;

	@Column(columnDefinition = "float(8)", name = "net_outstanding_balance__c")
	private Float netOutstandingBalance;

	@Column(columnDefinition = "float(8)", name = "availability_on_eligible_debts__c")
	private Float availabilityOnEligibleDebts;

	@Column(columnDefinition = "float(8)", name = "dispute_amount__c")
	private Float disputeAmount;

	@Column(columnDefinition = "varchar(100)", name = "parent_uuid__c")
	private String parentuuId;

	@Column(columnDefinition = "float(8)", name = "external_id__c")
	private Float externalId;

	@Column(columnDefinition = "float(8)", name = "dispute_amount_pr__c")
	private Float disputeAmountPr;

	@Column(columnDefinition = "float(8)", name = "advance_ratio__c")
	private Float advanceRatio;

	@Column(columnDefinition = "varchar(255)", name = "customer_name__c")
	private String customerName;

	@Column(columnDefinition = "varchar(18)", name = "client__c")
	private String client;

	@Column(columnDefinition = "float(8)", name = "approved_cl_amount_ownercurrency__c")
	private Float approvedClAmountOwnerCurrency;

	@Column(columnDefinition = "float(8)", name = "approved_cl_amount__c")
	private Float approvedClAmount;

	@Column(columnDefinition = "varchar(20)", name = "acl_currency__c")
	private String aclCurrency;

	@Column(columnDefinition = "varchar(18)", name = "client_account_customer__c")
	private String clientAccountCustomer;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAllocatedCl() {
		return allocatedCl;
	}

	public void setAllocatedCl(Float allocatedCl) {
		this.allocatedCl = allocatedCl;
	}

	public Float getIneligibleDebts() {
		return ineligibleDebts;
	}

	public void setIneligibleDebts(Float ineligibleDebts) {
		this.ineligibleDebts = ineligibleDebts;
	}

	public Float getAllocatedClCa() {
		return allocatedClCa;
	}

	public void setAllocatedClCa(Float allocatedClCa) {
		this.allocatedClCa = allocatedClCa;
	}

	public String getFundRequestDetail() {
		return fundRequestDetail;
	}

	public void setFundRequestDetail(String fundRequestDetail) {
		this.fundRequestDetail = fundRequestDetail;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public Float getUnfundedAmount() {
		return unfundedAmount;
	}

	public void setUnfundedAmount(Float unfundedAmount) {
		this.unfundedAmount = unfundedAmount;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public Float getAvailabilityOnIneligibleDebts() {
		return availabilityOnIneligibleDebts;
	}

	public void setAvailabilityOnIneligibleDebts(Float availabilityOnIneligibleDebts) {
		this.availabilityOnIneligibleDebts = availabilityOnIneligibleDebts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getEligibleDebts() {
		return eligibleDebts;
	}

	public void setEligibleDebts(Float eligibleDebts) {
		this.eligibleDebts = eligibleDebts;
	}

	public Float getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(Float outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	public Float getNetOutstandingBalance() {
		return netOutstandingBalance;
	}

	public void setNetOutstandingBalance(Float netOutstandingBalance) {
		this.netOutstandingBalance = netOutstandingBalance;
	}

	public Float getAvailabilityOnEligibleDebts() {
		return availabilityOnEligibleDebts;
	}

	public void setAvailabilityOnEligibleDebts(Float availabilityOnEligibleDebts) {
		this.availabilityOnEligibleDebts = availabilityOnEligibleDebts;
	}

	public Float getDisputeAmount() {
		return disputeAmount;
	}

	public void setDisputeAmount(Float disputeAmount) {
		this.disputeAmount = disputeAmount;
	}

	public String getParentuuId() {
		return parentuuId;
	}

	public void setParentuuId(String parentuuId) {
		this.parentuuId = parentuuId;
	}

	public Float getExternalId() {
		return externalId;
	}

	public void setExternalId(Float externalId) {
		this.externalId = externalId;
	}

	public Float getDisputeAmountPr() {
		return disputeAmountPr;
	}

	public void setDisputeAmountPr(Float disputeAmountPr) {
		this.disputeAmountPr = disputeAmountPr;
	}

	public Float getAdvanceRatio() {
		return advanceRatio;
	}

	public void setAdvanceRatio(Float advanceRatio) {
		this.advanceRatio = advanceRatio;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Float getApprovedClAmountOwnerCurrency() {
		return approvedClAmountOwnerCurrency;
	}

	public void setApprovedClAmountOwnerCurrency(Float approvedClAmountOwnerCurrency) {
		this.approvedClAmountOwnerCurrency = approvedClAmountOwnerCurrency;
	}

	public Float getApprovedClAmount() {
		return approvedClAmount;
	}

	public void setApprovedClAmount(Float approvedClAmount) {
		this.approvedClAmount = approvedClAmount;
	}

	public String getAclCurrency() {
		return aclCurrency;
	}

	public void setAclCurrency(String aclCurrency) {
		this.aclCurrency = aclCurrency;
	}

	public String getClientAccountCustomer() {
		return clientAccountCustomer;
	}

	public void setClientAccountCustomer(String clientAccountCustomer) {
		this.clientAccountCustomer = clientAccountCustomer;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FundRequestDetailCustomer() {
		super();
	}

	// end
}