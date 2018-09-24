package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseReq {
	// region -- Fields --

	@JsonProperty(value = "keyword")
	private String keyword;

	@JsonProperty(value = "isOptional")
	private Boolean isOptional;

	// end

	// region -- Get set --

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Boolean getIsOptional() {
		return isOptional;
	}

	public void setIsOptional(Boolean isOptional) {
		this.isOptional = isOptional;
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