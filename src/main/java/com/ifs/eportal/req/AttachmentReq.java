package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-10
 *
 */
public class AttachmentReq {
	// region -- Fields --

	@JsonProperty(value = "parentUuId")
	private String parentUuId;

	// end

	// region -- Get set --

	public String getParentUuId() {
		return parentUuId;
	}

	public void setParentUuId(String parentUuId) {
		this.parentUuId = parentUuId;
	}

	// end

	// region -- Methods --

	public AttachmentReq() {
		parentUuId = "";
	}

	// end
}