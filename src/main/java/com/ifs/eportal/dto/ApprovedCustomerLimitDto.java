package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApprovedCustomerLimitDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "valid_to__c")
	private String validTo;

	@JsonProperty(value = "valid_from__c")
	private String validFrom;

	@JsonProperty(value = "customer__c")
	private String customer;

	@JsonProperty(value = "approved_limit__c")
	private double approvedLimit;

	// end

	// region -- Get set --

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public double getApprovedLimit() {
		return approvedLimit;
	}

	public void setApprovedLimit(double approvedLimit) {
		this.approvedLimit = approvedLimit;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ApprovedCustomerLimitDto() {
		super();
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ApprovedCustomerLimitDto convert(Object[] o) {
		ApprovedCustomerLimitDto res = new ApprovedCustomerLimitDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setValidFrom((String) o[2]);
		res.setValidTo((String) o[3]);
		res.setCustomer((String) o[4]);
		res.setApprovedLimit((double) o[5]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ApprovedCustomerLimitDto> convert(List<Object[]> l) {
		List<ApprovedCustomerLimitDto> res = new ArrayList<ApprovedCustomerLimitDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}