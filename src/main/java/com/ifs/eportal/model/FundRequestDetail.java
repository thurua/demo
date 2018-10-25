package com.ifs.eportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "fund_request_detail__c", schema = "salesforce")
public class FundRequestDetail extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fund_request_detail_id_seq_generator")
	@SequenceGenerator(name = "fund_request_detail_id_seq_generator", sequenceName = "salesforce.fund_request_detail_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "float(8)", name = "availability_on_ineligible_debt_capped__c")
	private Float availabilityOnIneligibleDebtCapped;

	@Column(columnDefinition = "float(8)", name = "fund_availability_eligible_debts__c")
	private Float fundAvailabilityEligibleDebts;

	@Column(columnDefinition = "float(8)", name = "availability_on_eligible_debt__c")
	private Float availabilityOnEligibleDebt;

	@Column(columnDefinition = "float(8)", name = "pending_payment__c")
	private Float pendingPayment;

	@Column(columnDefinition = "float(8)", name = "fund_request_amt__c")
	private Float fundRequestAmt;

	@Column(columnDefinition = "float(8)", name = "availability_capped_at_investment_limit__c")
	private Float availabilityCappedAtInvestmentLimit;

	@Column(columnDefinition = "varchar(100)", name = "uuid__c")
	private String uuId;

	@Column(columnDefinition = "float(8)", name = "outstanding_debts__c")
	private Float outstandingDebts;

	@Column(columnDefinition = "float(8)", name = "availability_on_ineligible_debt__c")
	private Float availabilityOnIneligibleDebt;

	@Column(columnDefinition = "varchar(18)", name = "client_account__c")
	private String clientAccount;

	@Column(columnDefinition = "float(8)", name = "limits__c")
	private Float limits;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "float(8)", name = "total_availability__c")
	private Float totalAvailability;

	@Column(columnDefinition = "bool", name = "auto_fund__c")
	private boolean autoFund;

	@Column(columnDefinition = "varchar(18)", name = "fund_request__c")
	private String fundRequest;

	@Column(columnDefinition = "bool", name = "loan_in_arrears__c")
	private boolean loanInArrears;

	@Column(columnDefinition = "bool", name = "client_auto_request__c")
	private boolean clientAutoRequest;

	@Column(columnDefinition = "varchar(100)", name = "parent_uuid__c")
	private String parentuuId;

	@Column(columnDefinition = "float(8)", name = "external_id__c")
	private Float externalId;

	@Column(columnDefinition = "float(8)", name = "additional_reserves__c")
	private Float additionalReserves;

	@Column(columnDefinition = "float(8)", name = "investment_limit__c")
	private Float investmentLimit;

	@Column(columnDefinition = "float(8)", name = "total_fund_availability_eligible_inel__c")
	private Float totalFundAvailabilityEligibleInel;

	@Column(columnDefinition = "float(8)", name = "to_date_disbursement__c")
	private Float toDateDisbursement;

	@Column(columnDefinition = "float(8)", name = "overfunding_amount__c")
	private Float overfundingAmount;

	@Column(columnDefinition = "varchar(100)", name = "client__c")
	private String client;

	@Column(columnDefinition = "float(8)", name = "fund_availability_ed_temp__c")
	private Float fundAvailabilityEdTemp;

	@Column(columnDefinition = "float(8)", name = "outstanding_principal__c")
	private Float outstandingPrincipal;

	@Column(columnDefinition = "float(8)", name = "client_fund_request__c")
	private Float clientFundRequest;

	@Column(columnDefinition = "float(8)", name = "availability_after_additional_reserves__c")
	private Float availabilityAfterAdditionalReserves;

	@Column(columnDefinition = "float(8)", name = "loan_amount__c")
	private Float loanAmount;

	@Column(columnDefinition = "float(8)", name = "fund_availability_ineligible_debts__c")
	private Float fundAvailabilityIneligibleDebts;

	@Column(columnDefinition = "float(8)", name = "cap_in_percentage__c")
	private Float capInPercentage;

	@Column(columnDefinition = "float(8)", name = "accrued_interest__c")
	private Float accruedInterest;

	@Column(columnDefinition = "float(8)", name = "total_availability_capped_at_investment__c")
	private Float totalAvailabilityCappedAtInvestment;

	@Column(columnDefinition = "float(8)", name = "overfunding_amount_temp__c")
	private Float overfundingAmountTemp;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAvailabilityOnIneligibleDebtCapped() {
		return availabilityOnIneligibleDebtCapped;
	}

	public void setAvailabilityOnIneligibleDebtCapped(Float availabilityOnIneligibleDebtCapped) {
		this.availabilityOnIneligibleDebtCapped = availabilityOnIneligibleDebtCapped;
	}

	public Float getFundAvailabilityEligibleDebts() {
		return fundAvailabilityEligibleDebts;
	}

	public void setFundAvailabilityEligibleDebts(Float fundAvailabilityEligibleDebts) {
		this.fundAvailabilityEligibleDebts = fundAvailabilityEligibleDebts;
	}

	public Float getAvailabilityOnEligibleDebt() {
		return availabilityOnEligibleDebt;
	}

	public void setAvailabilityOnEligibleDebt(Float availabilityOnEligibleDebt) {
		this.availabilityOnEligibleDebt = availabilityOnEligibleDebt;
	}

	public Float getPendingPayment() {
		return pendingPayment;
	}

	public void setPendingPayment(Float pendingPayment) {
		this.pendingPayment = pendingPayment;
	}

	public Float getFundRequestAmt() {
		return fundRequestAmt;
	}

	public void setFundRequestAmt(Float fundRequestAmt) {
		this.fundRequestAmt = fundRequestAmt;
	}

	public Float getAvailabilityCappedAtInvestmentLimit() {
		return availabilityCappedAtInvestmentLimit;
	}

	public void setAvailabilityCappedAtInvestmentLimit(Float availabilityCappedAtInvestmentLimit) {
		this.availabilityCappedAtInvestmentLimit = availabilityCappedAtInvestmentLimit;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public Float getOutstandingDebts() {
		return outstandingDebts;
	}

	public void setOutstandingDebts(Float outstandingDebts) {
		this.outstandingDebts = outstandingDebts;
	}

	public Float getAvailabilityOnIneligibleDebt() {
		return availabilityOnIneligibleDebt;
	}

	public void setAvailabilityOnIneligibleDebt(Float availabilityOnIneligibleDebt) {
		this.availabilityOnIneligibleDebt = availabilityOnIneligibleDebt;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public Float getLimits() {
		return limits;
	}

	public void setLimits(Float limits) {
		this.limits = limits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getTotalAvailability() {
		return totalAvailability;
	}

	public void setTotalAvailability(Float totalAvailability) {
		this.totalAvailability = totalAvailability;
	}

	public boolean isAutoFund() {
		return autoFund;
	}

	public void setAutoFund(boolean autoFund) {
		this.autoFund = autoFund;
	}

	public String getFundRequest() {
		return fundRequest;
	}

	public void setFundRequest(String fundRequest) {
		this.fundRequest = fundRequest;
	}

	public boolean isLoanInArrears() {
		return loanInArrears;
	}

	public void setLoanInArrears(boolean loanInArrears) {
		this.loanInArrears = loanInArrears;
	}

	public boolean isClientAutoRequest() {
		return clientAutoRequest;
	}

	public void setClientAutoRequest(boolean clientAutoRequest) {
		this.clientAutoRequest = clientAutoRequest;
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

	public Float getAdditionalReserves() {
		return additionalReserves;
	}

	public void setAdditionalReserves(Float additionalReserves) {
		this.additionalReserves = additionalReserves;
	}

	public Float getInvestmentLimit() {
		return investmentLimit;
	}

	public void setInvestmentLimit(Float investmentLimit) {
		this.investmentLimit = investmentLimit;
	}

	public Float getTotalFundAvailabilityEligibleInel() {
		return totalFundAvailabilityEligibleInel;
	}

	public void setTotalFundAvailabilityEligibleInel(Float totalFundAvailabilityEligibleInel) {
		this.totalFundAvailabilityEligibleInel = totalFundAvailabilityEligibleInel;
	}

	public Float getToDateDisbursement() {
		return toDateDisbursement;
	}

	public void setToDateDisbursement(Float toDateDisbursement) {
		this.toDateDisbursement = toDateDisbursement;
	}

	public Float getOverfundingAmount() {
		return overfundingAmount;
	}

	public void setOverfundingAmount(Float overfundingAmount) {
		this.overfundingAmount = overfundingAmount;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Float getFundAvailabilityEdTemp() {
		return fundAvailabilityEdTemp;
	}

	public void setFundAvailabilityEdTemp(Float fundAvailabilityEdTemp) {
		this.fundAvailabilityEdTemp = fundAvailabilityEdTemp;
	}

	public Float getOutstandingPrincipal() {
		return outstandingPrincipal;
	}

	public void setOutstandingPrincipal(Float outstandingPrincipal) {
		this.outstandingPrincipal = outstandingPrincipal;
	}

	public Float getClientFundRequest() {
		return clientFundRequest;
	}

	public void setClientFundRequest(Float clientFundRequest) {
		this.clientFundRequest = clientFundRequest;
	}

	public Float getAvailabilityAfterAdditionalReserves() {
		return availabilityAfterAdditionalReserves;
	}

	public void setAvailabilityAfterAdditionalReserves(Float availabilityAfterAdditionalReserves) {
		this.availabilityAfterAdditionalReserves = availabilityAfterAdditionalReserves;
	}

	public Float getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Float loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Float getFundAvailabilityIneligibleDebts() {
		return fundAvailabilityIneligibleDebts;
	}

	public void setFundAvailabilityIneligibleDebts(Float fundAvailabilityIneligibleDebts) {
		this.fundAvailabilityIneligibleDebts = fundAvailabilityIneligibleDebts;
	}

	public Float getCapInPercentage() {
		return capInPercentage;
	}

	public void setCapInPercentage(Float capInPercentage) {
		this.capInPercentage = capInPercentage;
	}

	public Float getAccruedInterest() {
		return accruedInterest;
	}

	public void setAccruedInterest(Float accruedInterest) {
		this.accruedInterest = accruedInterest;
	}

	public Float getTotalAvailabilityCappedAtInvestment() {
		return totalAvailabilityCappedAtInvestment;
	}

	public void setTotalAvailabilityCappedAtInvestment(Float totalAvailabilityCappedAtInvestment) {
		this.totalAvailabilityCappedAtInvestment = totalAvailabilityCappedAtInvestment;
	}

	public Float getOverfundingAmountTemp() {
		return overfundingAmountTemp;
	}

	public void setOverfundingAmountTemp(Float overfundingAmountTemp) {
		this.overfundingAmountTemp = overfundingAmountTemp;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FundRequestDetail() {
		super();
	}

	// end
}