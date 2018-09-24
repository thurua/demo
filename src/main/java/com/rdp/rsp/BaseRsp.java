package com.rdp.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRsp {
	// region -- Fields --

	@JsonProperty(value = "callstatus")
	private String callstatus;

	@JsonProperty(value = "message")
	private String message;

	// end

	// region -- Get set --

	public String getCallstatus() {
		return callstatus;
	}

	public void setCallstatus(String callstatus) {
		this.callstatus = callstatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// end

	// region -- Methods --

	public BaseRsp() {
		this.callstatus = "success";
		this.message = "";
	}

	public BaseRsp(String callstatus, String message) {
		this.callstatus = callstatus;
		this.message = message;
	}

	// end
}