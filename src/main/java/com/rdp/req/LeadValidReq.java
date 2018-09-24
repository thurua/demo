package com.rdp.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeadValidReq {
	// region -- Fields --

	@JsonProperty(value = "ebsNumber")
	private String ebsNumber;

	@JsonProperty(value = "nricNo")
	private String nricNo;

	// end

	// region -- Get set --

	public String getEbsNumber() {
		return ebsNumber;
	}

	public void setEbsNumber(String ebsNumber) {
		this.ebsNumber = ebsNumber;
	}

	public String getNricNo() {
		return nricNo;
	}

	public void setNricNo(String nricNo) {
		this.nricNo = nricNo;
	}

	// end

	// region -- Methods --

	public LeadValidReq() {
	}

	public LeadValidReq(String ebsNumber, String nricNo) {
		this.ebsNumber = ebsNumber;
		this.nricNo = nricNo;
	}

	// end
}