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
public class LctrDetailDto extends ClientAccountBaseDto {
	// region -- Fields --

	@JsonProperty(value = "loanAmount")
	private Double loanAmount;

	@JsonProperty(value = "loanTenure")
	private Double loanTenure;

	@JsonProperty(value = "trPeriod")
	private Double trPeriod;

	@JsonProperty(value = "serviceCharge")
	private Double serviceCharge;

	@JsonProperty(value = "additionalServiceChargePerMonth")
	private Double additionalServiceChargePerMonth;

	@JsonProperty(value = "extensionFeeFlat")
	private Double extensionFeeFlat;

	@JsonProperty(value = "extensionFeePerMonth")
	private Double extensionFeePerMonth;

	@JsonProperty(value = "usanceAcceptanceChargePerMonth")
	private Double usanceAcceptanceChargePerMonth;

	@JsonProperty(value = "lcValidityPeriod")
	private Double lcValidityPeriod;

	@JsonProperty(value = "serviceChargeMin")
	private Double serviceChargeMin;

	@JsonProperty(value = "additionalServiceChargeMin")
	private Double additionalServiceChargeMin;

	@JsonProperty(value = "extensionFeeFlatMin")
	private Double extensionFeeFlatMin;

	@JsonProperty(value = "extensionFeePerMonthMin")
	private Double extensionFeePerMonthMin;

	@JsonProperty(value = "usanceAcceptanceChargeMin")
	private Double usanceAcceptanceChargeMin;

	@JsonProperty(value = "facilityFeeOfLoanAmount")
	private Double facilityFeeOfLoanAmount;

	@JsonProperty(value = "annualRenewalFee")
	private Double annualRenewalFee;

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

	public Double getTrPeriod() {
		return trPeriod;
	}

	public void setTrPeriod(Double trPeriod) {
		this.trPeriod = trPeriod;
	}

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Double getAdditionalServiceChargePerMonth() {
		return additionalServiceChargePerMonth;
	}

	public void setAdditionalServiceChargePerMonth(Double additionalServiceChargePerMonth) {
		this.additionalServiceChargePerMonth = additionalServiceChargePerMonth;
	}

	public Double getExtensionFeeFlat() {
		return extensionFeeFlat;
	}

	public void setExtensionFeeFlat(Double extensionFeeFlat) {
		this.extensionFeeFlat = extensionFeeFlat;
	}

	public Double getExtensionFeePerMonth() {
		return extensionFeePerMonth;
	}

	public void setExtensionFeePerMonth(Double extensionFeePerMonth) {
		this.extensionFeePerMonth = extensionFeePerMonth;
	}

	public Double getUsanceAcceptanceChargePerMonth() {
		return usanceAcceptanceChargePerMonth;
	}

	public void setUsanceAcceptanceChargePerMonth(Double usanceAcceptanceChargePerMonth) {
		this.usanceAcceptanceChargePerMonth = usanceAcceptanceChargePerMonth;
	}

	public Double getLcValidityPeriod() {
		return lcValidityPeriod;
	}

	public void setLcValidityPeriod(Double lcValidityPeriod) {
		this.lcValidityPeriod = lcValidityPeriod;
	}

	public Double getServiceChargeMin() {
		return serviceChargeMin;
	}

	public void setServiceChargeMin(Double serviceChargeMin) {
		this.serviceChargeMin = serviceChargeMin;
	}

	public Double getAdditionalServiceChargeMin() {
		return additionalServiceChargeMin;
	}

	public void setAdditionalServiceChargeMin(Double additionalServiceChargeMin) {
		this.additionalServiceChargeMin = additionalServiceChargeMin;
	}

	public Double getExtensionFeeFlatMin() {
		return extensionFeeFlatMin;
	}

	public void setExtensionFeeFlatMin(Double extensionFeeFlatMin) {
		this.extensionFeeFlatMin = extensionFeeFlatMin;
	}

	public Double getExtensionFeePerMonthMin() {
		return extensionFeePerMonthMin;
	}

	public void setExtensionFeePerMonthMin(Double extensionFeePerMonthMin) {
		this.extensionFeePerMonthMin = extensionFeePerMonthMin;
	}

	public Double getUsanceAcceptanceChargeMin() {
		return usanceAcceptanceChargeMin;
	}

	public void setUsanceAcceptanceChargeMin(Double usanceAcceptanceChargeMin) {
		this.usanceAcceptanceChargeMin = usanceAcceptanceChargeMin;
	}

	public Double getFacilityFeeOfLoanAmount() {
		return facilityFeeOfLoanAmount;
	}

	public void setFacilityFeeOfLoanAmount(Double facilityFeeOfLoanAmount) {
		this.facilityFeeOfLoanAmount = facilityFeeOfLoanAmount;
	}

	public Double getAnnualRenewalFee() {
		return annualRenewalFee;
	}

	public void setAnnualRenewalFee(Double annualRenewalFee) {
		this.annualRenewalFee = annualRenewalFee;
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
	public LctrDetailDto() {
		super();

		loanAmount = null;
		loanTenure = null; // Loan Period
		trPeriod = null;
		serviceCharge = null; // lc_commission_per_validity_period__c
		additionalServiceChargePerMonth = null; // lc_additional_commission_per_mth__c
		extensionFeeFlat = null; // lc_extension_commission_flat__c
		extensionFeePerMonth = null; // lc_extension_commission_per_mth__c
		usanceAcceptanceChargePerMonth = null; // acceptance_commission_per_mth__c
		lcValidityPeriod = null; // lc_validity_period__c
		serviceChargeMin = null; // lc_commission_min__c
		additionalServiceChargeMin = null; // lc_additional_commission_min__c
		extensionFeeFlatMin = null; // extension_fee_flat_min__c
		extensionFeePerMonthMin = null; // extension_fee_per_mth_min__c
		usanceAcceptanceChargeMin = null; // usance_acceptance_charge_min__c
		facilityFeeOfLoanAmount = null; // facility_fee_of_loan_amount__c
		annualRenewalFee = null; // annual_renewal_fee__c
		bankApprovedDateGiro = null;
		accountNumberGiro = "";
		deductionDay = null;
		bankGiro = "";
		currencyPaymentLimitIfApplicable = "";
		remarksGiro = "";
		paymentModeLLctr = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static LctrDetailDto convert(Object[] o, List<Object[]> fu) {
		LctrDetailDto res = new LctrDetailDto();

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
		res.setTrPeriod((Double) o[20]);
		res.setServiceCharge((Double) o[21]);
		res.setAdditionalServiceChargePerMonth((Double) o[22]);
		res.setExtensionFeeFlat((Double) o[23]);
		res.setExtensionFeePerMonth((Double) o[24]);
		res.setUsanceAcceptanceChargePerMonth((Double) o[25]);
		res.setLcValidityPeriod((Double) o[26]);
		res.setServiceChargeMin((Double) o[27]);
		res.setAdditionalServiceChargeMin((Double) o[28]);
		res.setExtensionFeeFlatMin((Double) o[29]);
		res.setExtensionFeePerMonthMin((Double) o[30]);
		res.setUsanceAcceptanceChargeMin((Double) o[31]);
		res.setFacilityFeeOfLoanAmount((Double) o[32]);
		res.setAnnualRenewalFee((Double) o[33]);
		res.setBankApprovedDateGiro((Date) o[34]);
		res.setAccountNumberGiro((String) o[35]);
		res.setDeductionDay((Date) o[36]);
		res.setBankGiro((String) o[37]);
		res.setCurrencyPaymentLimitIfApplicable((String) o[38]);
		res.setRemarksGiro((String) o[39]);
		res.setFundings(FundingAccountDetailsDto.convert(fu));

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<LctrDetailDto> convert(List<Object[]> l) {
		List<LctrDetailDto> res = new ArrayList<LctrDetailDto>();

		for (Object[] o : l) {
			res.add(convert(o, null));
		}

		return res;
	}

	// end
}