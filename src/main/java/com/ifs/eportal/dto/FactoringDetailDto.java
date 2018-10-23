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
public class FactoringDetailDto extends ClientAccountBaseDto {
	// region -- Fields --

	@JsonProperty(value = "investmentLimit")
	private Double investmentLimit;

	@JsonProperty(value = "advancedRatio")
	private Double advancedRatio;

	@JsonProperty(value = "fciFactor")
	private String fciFactor;

	@JsonProperty(value = "reassignmentPeriod")
	private Double reassignmentPeriod;

	@JsonProperty(value = "factoringChargeRate1stTier")
	private Double factoringChargeRate1stTier;

	@JsonProperty(value = "factoringChargeRate2ndTier")
	private Double factoringChargeRate2ndTier;

	@JsonProperty(value = "factoringChargeRate3rdTier")
	private Double factoringChargeRate3rdTier;

	@JsonProperty(value = "minFactoringChargePerMonth")
	private Double minFactoringChargePerMonth;

	@JsonProperty(value = "handlingChargePerInvoice")
	private Double handlingChargePerInvoice;

	@JsonProperty(value = "tierUpToFactoredVolume1st")
	private Double tierUpToFactoredVolume1st;

	@JsonProperty(value = "tierUpToFactoredVolume2nd")
	private Double tierUpToFactoredVolume2nd;

	@JsonProperty(value = "factoringChargeRateSpread")
	private Double factoringChargeRateSpread;

	@JsonProperty(value = "factoringChargeSource")
	private String factoringChargeSource;

	@JsonProperty(value = "overdueGracePeriodDays")
	private Double overdueGracePeriodDays;

	@JsonProperty(value = "facilityFeeInvestmentLimit")
	private Double facilityFeeInvestmentLimit;

	@JsonProperty(value = "autoRequest")
	private boolean autoRequest;

	@JsonProperty(value = "addFacRateType")
	private String addFacRateType;

	@JsonProperty(value = "fciName")
	private String fciName;

	// end

	// region -- Get set --

	public Double getInvestmentLimit() {
		return investmentLimit;
	}

	public void setInvestmentLimit(Double investmentLimit) {
		this.investmentLimit = investmentLimit;
	}

	public Double getAdvancedRatio() {
		return advancedRatio;
	}

	public void setAdvancedRatio(Double advancedRatio) {
		this.advancedRatio = advancedRatio;
	}

	public String getFciFactor() {
		return fciFactor;
	}

	public void setFciFactor(String fciFactor) {
		this.fciFactor = fciFactor;
	}

	public Double getReassignmentPeriod() {
		return reassignmentPeriod;
	}

	public void setReassignmentPeriod(Double reassignmentPeriod) {
		this.reassignmentPeriod = reassignmentPeriod;
	}

	public Double getFactoringChargeRate1stTier() {
		return factoringChargeRate1stTier;
	}

	public void setFactoringChargeRate1stTier(Double factoringChargeRate1stTier) {
		this.factoringChargeRate1stTier = factoringChargeRate1stTier;
	}

	public Double getFactoringChargeRate2ndTier() {
		return factoringChargeRate2ndTier;
	}

	public void setFactoringChargeRate2ndTier(Double factoringChargeRate2ndTier) {
		this.factoringChargeRate2ndTier = factoringChargeRate2ndTier;
	}

	public Double getFactoringChargeRate3rdTier() {
		return factoringChargeRate3rdTier;
	}

	public void setFactoringChargeRate3rdTier(Double factoringChargeRate3rdTier) {
		this.factoringChargeRate3rdTier = factoringChargeRate3rdTier;
	}

	public Double getMinFactoringChargePerMonth() {
		return minFactoringChargePerMonth;
	}

	public void setMinFactoringChargePerMonth(Double minFactoringChargePerMonth) {
		this.minFactoringChargePerMonth = minFactoringChargePerMonth;
	}

	public Double getHandlingChargePerInvoice() {
		return handlingChargePerInvoice;
	}

	public void setHandlingChargePerInvoice(Double handlingChargePerInvoice) {
		this.handlingChargePerInvoice = handlingChargePerInvoice;
	}

	public Double getTierUpToFactoredVolume1st() {
		return tierUpToFactoredVolume1st;
	}

	public void setTierUpToFactoredVolume1st(Double tierUpToFactoredVolume1st) {
		this.tierUpToFactoredVolume1st = tierUpToFactoredVolume1st;
	}

	public Double getTierUpToFactoredVolume2nd() {
		return tierUpToFactoredVolume2nd;
	}

	public void setTierUpToFactoredVolume2nd(Double tierUpToFactoredVolume2nd) {
		this.tierUpToFactoredVolume2nd = tierUpToFactoredVolume2nd;
	}

	public Double getFactoringChargeRateSpread() {
		return factoringChargeRateSpread;
	}

	public void setFactoringChargeRateSpread(Double factoringChargeRateSpread) {
		this.factoringChargeRateSpread = factoringChargeRateSpread;
	}

	public String getFactoringChargeSource() {
		return factoringChargeSource;
	}

	public void setFactoringChargeSource(String factoringChargeSource) {
		this.factoringChargeSource = factoringChargeSource;
	}

	public Double getOverdueGracePeriodDays() {
		return overdueGracePeriodDays;
	}

	public void setOverdueGracePeriodDays(Double overdueGracePeriodDays) {
		this.overdueGracePeriodDays = overdueGracePeriodDays;
	}

	public Double getFacilityFeeInvestmentLimit() {
		return facilityFeeInvestmentLimit;
	}

	public void setFacilityFeeInvestmentLimit(Double facilityFeeInvestmentLimit) {
		this.facilityFeeInvestmentLimit = facilityFeeInvestmentLimit;
	}

	public boolean isAutoRequest() {
		return autoRequest;
	}

	public void setAutoRequest(boolean autoRequest) {
		this.autoRequest = autoRequest;
	}

	public String getAddFacRateType() {
		return addFacRateType;
	}

	public void setAddFacRateType(String addFacRateType) {
		this.addFacRateType = addFacRateType;
	}

	public String getFciName() {
		return fciName;
	}

	public void setFciName(String fciName) {
		this.fciName = fciName;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FactoringDetailDto() {
		super();

		investmentLimit = null;
		advancedRatio = null;
		fciFactor = ""; // fci_country__c
		reassignmentPeriod = null;
		factoringChargeRate1stTier = null; // service_charge_rate_tier_1__c
		factoringChargeRate2ndTier = null; // service_charge_rate_tier_2__c
		factoringChargeRate3rdTier = null; // factoring_charge_rate_3rd_tier__c
		minFactoringChargePerMonth = null;
		handlingChargePerInvoice = null;
		tierUpToFactoredVolume1st = null; // x1st_tier_up_to_factored_volume__c
		tierUpToFactoredVolume2nd = null; // x2nd_tier_up_to_factored_volume__c
		factoringChargeRateSpread = null; // service_charge_rate_spread__c
		factoringChargeSource = null; // service_charge_source__c
		overdueGracePeriodDays = null;
		facilityFeeInvestmentLimit = null;
		addFacRateType = null;
		fciName = null;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static FactoringDetailDto convert(Object[] o, List<Object[]> fu) {
		FactoringDetailDto res = new FactoringDetailDto();

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

		res.setInvestmentLimit((Double) o[18]);
		res.setAdvancedRatio((Double) o[19]);
		res.setFciFactor((String) o[20]);
		res.setReassignmentPeriod((Double) o[21]);
		res.setFactoringChargeRate1stTier((Double) o[22]);
		res.setFactoringChargeRate2ndTier((Double) o[23]);
		res.setFactoringChargeRate3rdTier((Double) o[24]);
		res.setMinFactoringChargePerMonth((Double) o[25]);
		res.setHandlingChargePerInvoice((Double) o[26]);
		res.setTierUpToFactoredVolume1st((Double) o[27]);
		res.setTierUpToFactoredVolume2nd((Double) o[28]);
		res.setFactoringChargeRateSpread((Double) o[29]);
		res.setFactoringChargeSource((String) o[30]);
		res.setOverdueGracePeriodDays((Double) o[31]);
		res.setFacilityFeeInvestmentLimit((Double) o[32]);
		res.setAutoRequest((boolean) o[33]);
		res.setAddFacRateType((String) o[34]);
		res.setFciName((String) o[35]);
		res.setFundings(FundingAccountDetailsDto.convert(fu));

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<FactoringDetailDto> convert(List<Object[]> l) {
		List<FactoringDetailDto> res = new ArrayList<FactoringDetailDto>();

		for (Object[] o : l) {
			res.add(convert(o, null));
		}

		return res;
	}
	// end
}