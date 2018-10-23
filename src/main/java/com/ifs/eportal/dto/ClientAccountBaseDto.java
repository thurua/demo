package com.ifs.eportal.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author VanPhan 2018-Oct-17
 *
 */
public class ClientAccountBaseDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "letterOfOfferSignedDate")
	private Date letterOfOfferSignedDate;

	@JsonProperty(value = "accountActivationDate")
	private Date accountActivationDate;

	@JsonProperty(value = "accountTerminationDate")
	private Date accountTerminationDate;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "availabilityPeriodMonths")
	private Double availabilityPeriodMonths;

	@JsonProperty(value = "accountMaturityDate")
	private Date accountMaturityDate;

	@JsonProperty(value = "accountClosedDate")
	private Date accountClosedDate;

	@JsonProperty(value = "currencyisocode")
	private String currencyisocode;

	@JsonProperty(value = "productTypeDescription")
	private String productTypeDescription;

	@JsonProperty(value = "interestRateSpread1")
	private Double interestRateSpread1;

	@JsonProperty(value = "interestRateSpread2")
	private Double interestRateSpread2;

	@JsonProperty(value = "rateType")
	private String rateType;

	@JsonProperty(value = "interestRateSource1")
	private String interestRateSource1;

	@JsonProperty(value = "interestRateSource2")
	private String interestRateSource2;

	@JsonProperty(value = "facilityFee")
	private Double facilityFee;

	@JsonProperty(value = "fundings")
	private List<FundingAccountDetailsDto> fundings;

	// end

	// region -- Get set --

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public Date getLetterOfOfferSignedDate() {
		return letterOfOfferSignedDate;
	}

	public void setLetterOfOfferSignedDate(Date letterOfOfferSignedDate) {
		this.letterOfOfferSignedDate = letterOfOfferSignedDate;
	}

	public Date getAccountActivationDate() {
		return accountActivationDate;
	}

	public void setAccountActivationDate(Date accountActivationDate) {
		this.accountActivationDate = accountActivationDate;
	}

	public Date getAccountTerminationDate() {
		return accountTerminationDate;
	}

	public void setAccountTerminationDate(Date accountTerminationDate) {
		this.accountTerminationDate = accountTerminationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAvailabilityPeriodMonths() {
		return availabilityPeriodMonths;
	}

	public void setAvailabilityPeriodMonths(Double availabilityPeriodMonths) {
		this.availabilityPeriodMonths = availabilityPeriodMonths;
	}

	public Date getAccountMaturityDate() {
		return accountMaturityDate;
	}

	public void setAccountMaturityDate(Date accountMaturityDate) {
		this.accountMaturityDate = accountMaturityDate;
	}

	public Date getAccountClosedDate() {
		return accountClosedDate;
	}

	public void setAccountClosedDate(Date accountClosedDate) {
		this.accountClosedDate = accountClosedDate;
	}

	public String getCurrencyisocode() {
		return currencyisocode;
	}

	public void setCurrencyisocode(String currencyisocode) {
		this.currencyisocode = currencyisocode;
	}

	public String getProductTypeDescription() {
		return productTypeDescription;
	}

	public void setProductTypeDescription(String productTypeDescription) {
		this.productTypeDescription = productTypeDescription;
	}

	public Double getInterestRateSpread1() {
		return interestRateSpread1;
	}

	public void setInterestRateSpread1(Double interestRateSpread1) {
		this.interestRateSpread1 = interestRateSpread1;
	}

	public Double getInterestRateSpread2() {
		return interestRateSpread2;
	}

	public void setInterestRateSpread2(Double interestRateSpread2) {
		this.interestRateSpread2 = interestRateSpread2;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getInterestRateSource1() {
		return interestRateSource1;
	}

	public void setInterestRateSource1(String interestRateSource1) {
		this.interestRateSource1 = interestRateSource1;
	}

	public String getInterestRateSource2() {
		return interestRateSource2;
	}

	public void setInterestRateSource2(String interestRateSource2) {
		this.interestRateSource2 = interestRateSource2;
	}

	public Double getFacilityFee() {
		return facilityFee;
	}

	public void setFacilityFee(Double facilityFee) {
		this.facilityFee = facilityFee;
	}

	public List<FundingAccountDetailsDto> getFundings() {
		return fundings;
	}

	public void setFundings(List<FundingAccountDetailsDto> fundings) {
		this.fundings = fundings;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountBaseDto() {
		clientAccount = "";
		letterOfOfferSignedDate = null;
		accountActivationDate = null;
		accountTerminationDate = null;
		status = "";
		availabilityPeriodMonths = null;
		accountMaturityDate = null;
		accountClosedDate = null;
		currencyisocode = "";
		productTypeDescription = "";
		interestRateSpread1 = null;
		interestRateSpread2 = null;
		rateType = null;
		interestRateSource1 = null;
		interestRateSource2 = null;
		facilityFee = null;
	}

	// end
}