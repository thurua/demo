package com.ifs.eportal.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleRsp extends BaseRsp {
	// region -- Fields --

	@JsonProperty(value = "result")
	private Object result;

	// end

	// region -- Get set --

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	// end

	// region -- Methods --

	public SingleRsp() {
	}

	public SingleRsp(String status, String message, Object result) {
		super(status, message);
		this.result = result;
	}

	// end
}