package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-05
 *
 */
public class AttachmentReq {
	// region -- Fields --

	@JsonProperty(value = "scheduleOfOffer")
	private String scheduleOfOffer;

	// end

	// region -- Get set --

	public String getScheduleOfOffer() {
		return scheduleOfOffer;
	}

	public void setScheduleOfOffer(String scheduleOfOffer) {
		this.scheduleOfOffer = scheduleOfOffer;
	}

	// end

	// region -- Methods --

	public AttachmentReq() {
		scheduleOfOffer = "";
	}

	// end
}