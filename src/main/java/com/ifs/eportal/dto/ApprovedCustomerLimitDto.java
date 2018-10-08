package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApprovedCustomerLimitDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "validTo")
	private Date validTo;

	@JsonProperty(value = "validFrom")
	private Date validFrom;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "approvedLimit")
	private double approvedLimit;

	// end

	// region -- Get set --

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
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

		res.setValidFrom((Date) o[2]);
		res.setValidTo((Date) o[3]);
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