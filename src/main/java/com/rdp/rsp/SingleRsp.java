package com.rdp.rsp;

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

	public SingleRsp(String callstatus, String message, Object result) {
		super(callstatus, message);
		this.result = result;
	}

	// end
}