package com.rdp.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseReq {
	// region -- Fields --

	@JsonProperty(value = "keyword")
	private String keyword;

	// end

	// region -- Get set --

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	// end

	// region -- Methods --

	public BaseReq() {
	}

	public BaseReq(String keyword) {
		this.keyword = keyword;
	}

	// end
}