package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-02
 *
 */
public class AttachmentReq {
	// region -- Fields --

	@JsonProperty(value = "scheduleOfOfferId")
	private String scheduleOfOfferId;

	// end

	// region -- Get set --

	public String getScheduleOfOfferId() {
		return scheduleOfOfferId;
	}

	public void setScheduleOfOfferId(String scheduleOfOfferId) {
		this.scheduleOfOfferId = scheduleOfOfferId;
	}

	// end

	// region -- Methods --

	public AttachmentReq() {
		scheduleOfOfferId = "";
	}

	// end
}