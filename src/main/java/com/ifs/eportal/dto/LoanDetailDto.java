package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author VanPhan 2018-Oct-17
 *
 */
public class LoanDetailDto extends ClientAccountBaseDto {
	// region -- Fields --

	@JsonProperty(value = "loanAmount")
	private Double loanAmount;

	@JsonProperty(value = "loanTenure")
	private Double loanTenure;

	@JsonProperty(value = "repaymentType")
	private String repaymentType;

	@JsonProperty(value = "instalmentFrequency")
	private String instalmentFrequency;

	@JsonProperty(value = "repaymentPeriodMonths")
	private Double repaymentPeriodMonths;

	@JsonProperty(value = "repaymentTypeToSpecify")
	private String repaymentTypeToSpecify;

	@JsonProperty(value = "instalmentType")
	private String instalmentType;

	@JsonProperty(value = "interestRateSpread1")
	private Double interestRateSpread1;

	@JsonProperty(value = "dueDayOfTheMonth")
	private String dueDayOfTheMonth;

	@JsonProperty(value = "prepaymentFee")
	private Double prepaymentFee;

	@JsonProperty(value = "prepaymentFromDrawdownMonths")
	private Double prepaymentFromDrawdownMonths;

	@JsonProperty(value = "bankApprovedDateGiro")
	private Date bankApprovedDateGiro;

	@JsonProperty(value = "accountNumberGiro")
	private String accountNumberGiro;

	@JsonProperty(value = "deductionDay")
	private Date deductionDay;

	@JsonProperty(value = "bankGiro")
	private String bankGiro;

	@JsonProperty(value = "currencyPaymentLimitIfApplicable")
	private String currencyPaymentLimitIfApplicable;

	@JsonProperty(value = "remarksGiro")
	private String remarksGiro;

	@JsonProperty(value = "cancellationFee")
	private Double cancellationFee;

	@JsonProperty(value = "facilityFeeOfLoanAmount")
	private Double facilityFeeOfLoanAmount;

	@JsonProperty(value = "disbursementType")
	private String disbursementType;

	@JsonProperty(value = "overdueInterestRateSpread")
	private Double overdueInterestRateSpread;

	@JsonProperty(value = "overdueInterestRateSource")
	private String overdueInterestRateSource;

	@JsonProperty(value = "paymentModeLLctr")
	private String paymentModeLLctr;

	// end

	// region -- Get set --

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Double loanTenure) {
		this.loanTenure = loanTenure;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getInstalmentFrequency() {
		return instalmentFrequency;
	}

	public void setInstalmentFrequency(String instalmentFrequency) {
		this.instalmentFrequency = instalmentFrequency;
	}

	public Double getRepaymentPeriodMonths() {
		return repaymentPeriodMonths;
	}

	public void setRepaymentPeriodMonths(Double repaymentPeriodMonths) {
		this.repaymentPeriodMonths = repaymentPeriodMonths;
	}

	public String getRepaymentTypeToSpecify() {
		return repaymentTypeToSpecify;
	}

	public void setRepaymentTypeToSpecify(String repaymentTypeToSpecify) {
		this.repaymentTypeToSpecify = repaymentTypeToSpecify;
	}

	public String getInstalmentType() {
		return instalmentType;
	}

	public void setInstalmentType(String instalmentType) {
		this.instalmentType = instalmentType;
	}

	public Double getInterestRateSpread1() {
		return interestRateSpread1;
	}

	public void setInterestRateSpread1(Double interestRateSpread1) {
		this.interestRateSpread1 = interestRateSpread1;
	}

	public String getDueDayOfTheMonth() {
		return dueDayOfTheMonth;
	}

	public void setDueDayOfTheMonth(String dueDayOfTheMonth) {
		this.dueDayOfTheMonth = dueDayOfTheMonth;
	}

	public Double getPrepaymentFee() {
		return prepaymentFee;
	}

	public void setPrepaymentFee(Double prepaymentFee) {
		this.prepaymentFee = prepaymentFee;
	}

	public Double getPrepaymentFromDrawdownMonths() {
		return prepaymentFromDrawdownMonths;
	}

	public void setPrepaymentFromDrawdownMonths(Double prepaymentFromDrawdownMonths) {
		this.prepaymentFromDrawdownMonths = prepaymentFromDrawdownMonths;
	}

	public Date getBankApprovedDateGiro() {
		return bankApprovedDateGiro;
	}

	public void setBankApprovedDateGiro(Date bankApprovedDateGiro) {
		this.bankApprovedDateGiro = bankApprovedDateGiro;
	}

	public String getAccountNumberGiro() {
		return accountNumberGiro;
	}

	public void setAccountNumberGiro(String accountNumberGiro) {
		this.accountNumberGiro = accountNumberGiro;
	}

	public Date getDeductionDay() {
		return deductionDay;
	}

	public void setDeductionDay(Date deductionDay) {
		this.deductionDay = deductionDay;
	}

	public String getBankGiro() {
		return bankGiro;
	}

	public void setBankGiro(String bankGiro) {
		this.bankGiro = bankGiro;
	}

	public String getCurrencyPaymentLimitIfApplicable() {
		return currencyPaymentLimitIfApplicable;
	}

	public void setCurrencyPaymentLimitIfApplicable(String currencyPaymentLimitIfApplicable) {
		this.currencyPaymentLimitIfApplicable = currencyPaymentLimitIfApplicable;
	}

	public String getRemarksGiro() {
		return remarksGiro;
	}

	public void setRemarksGiro(String remarksGiro) {
		this.remarksGiro = remarksGiro;
	}

	public Double getCancellationFee() {
		return cancellationFee;
	}

	public void setCancellationFee(Double cancellationFee) {
		this.cancellationFee = cancellationFee;
	}

	public Double getFacilityFeeOfLoanAmount() {
		return facilityFeeOfLoanAmount;
	}

	public void setFacilityFeeOfLoanAmount(Double facilityFeeOfLoanAmount) {
		this.facilityFeeOfLoanAmount = facilityFeeOfLoanAmount;
	}

	public String getDisbursementType() {
		return disbursementType;
	}

	public void setDisbursementType(String disbursementType) {
		this.disbursementType = disbursementType;
	}

	public Double getOverdueInterestRateSpread() {
		return overdueInterestRateSpread;
	}

	public void setOverdueInterestRateSpread(Double overdueInterestRateSpread) {
		this.overdueInterestRateSpread = overdueInterestRateSpread;
	}

	public String getOverdueInterestRateSource() {
		return overdueInterestRateSource;
	}

	public void setOverdueInterestRateSource(String overdueInterestRateSource) {
		this.overdueInterestRateSource = overdueInterestRateSource;
	}

	public String getPaymentModeLLctr() {
		return paymentModeLLctr;
	}

	public void setPaymentModeLLctr(String paymentModeLLctr) {
		this.paymentModeLLctr = paymentModeLLctr;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public LoanDetailDto() {
		super();

		loanAmount = null;
		loanTenure = null; // Loan Period
		repaymentType = "";
		instalmentFrequency = "";
		repaymentPeriodMonths = null;
		repaymentTypeToSpecify = "";
		instalmentType = "";
		interestRateSpread1 = null;
		dueDayOfTheMonth = "";
		cancellationFee = null;
		bankApprovedDateGiro = null;
		accountNumberGiro = "";
		deductionDay = null;
		bankGiro = "";
		currencyPaymentLimitIfApplicable = "";
		remarksGiro = "";
		prepaymentFee = null;
		prepaymentFromDrawdownMonths = null; // prepayment_duration_month_from_drawdown__c
		facilityFeeOfLoanAmount = null;
		disbursementType = "";
		overdueInterestRateSpread = null;
		overdueInterestRateSource = "";
		paymentModeLLctr = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static LoanDetailDto convert(Object[] o, List<Object[]> fu) {
		LoanDetailDto res = new LoanDetailDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setClientAccount((String) o[2]);
		res.setLetterOfOfferSignedDate((Date) o[3]);
		res.setAccountActivationDate((Date) o[4]);
		res.setAccountTerminationDate((Date) o[5]);
		res.setStatus((String) o[6]);
		res.setAvailabilityPeriodMonths((Double) o[7]);
		res.setAccountMaturityDate((Date) o[8]);
		res.setAccountClosedDate((Date) o[9]);
		res.setCurrencyisocode((String) o[10]);
		res.setProductTypeDescription((String) o[11]);
		res.setInterestRateSpread1((Double) o[12]);
		res.setInterestRateSpread2((Double) o[13]);
		res.setRateType((String) o[14]);
		res.setInterestRateSource1((String) o[15]);
		res.setInterestRateSource2((String) o[16]);
		res.setFacilityFee((Double) o[17]);

		res.setLoanAmount((Double) o[18]);
		res.setLoanTenure((Double) o[19]);
		res.setRepaymentPeriodMonths((Double) o[20]);
		res.setRepaymentType((String) o[21]);
		res.setInstalmentFrequency((String) o[22]);
		res.setDueDayOfTheMonth((String) o[23]);
		res.setRepaymentTypeToSpecify((String) o[24]);
		res.setInstalmentType((String) o[25]);
		res.setPrepaymentFee((Double) o[26]);
		res.setPrepaymentFromDrawdownMonths((Double) o[27]);
		res.setBankApprovedDateGiro((Date) o[28]);
		res.setAccountNumberGiro((String) o[29]);
		res.setDeductionDay((Date) o[30]);
		res.setBankGiro((String) o[31]);
		res.setCurrencyPaymentLimitIfApplicable((String) o[32]);
		res.setRemarksGiro((String) o[33]);
		res.setCancellationFee((Double) o[34]);
		res.setFacilityFeeOfLoanAmount((Double) o[35]);
		res.setDisbursementType((String) o[36]);
		res.setOverdueInterestRateSpread((Double) o[37]);
		res.setOverdueInterestRateSource((String) o[38]);
		res.setFundings(FundingAccountDetailsDto.convert(fu));

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<LoanDetailDto> convert(List<Object[]> l) {
		List<LoanDetailDto> res = new ArrayList<LoanDetailDto>();

		for (Object[] o : l) {
			res.add(convert(o, null));
		}

		return res;
	}

	// end
}