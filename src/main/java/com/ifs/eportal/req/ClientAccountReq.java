package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author VanPhan 2018-Oct-17
 *
 */
public class ClientAccountReq {
	// region -- Fields --

	@JsonProperty(value = "sfId")
	private String sfId;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "autoRequest")
	private boolean autoRequest;

	// end

	// region -- Get set --

	public String getSfId() {
		return sfId;
	}

	public void setSfId(String sfId) {
		this.sfId = sfId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAutoRequest() {
		return autoRequest;
	}

	public void setAutoRequest(boolean autoRequest) {
		this.autoRequest = autoRequest;
	}

	// end

	// region -- Methods --

	public ClientAccountReq() {
		sfId = "";
		type = "";
	}

	// end
}