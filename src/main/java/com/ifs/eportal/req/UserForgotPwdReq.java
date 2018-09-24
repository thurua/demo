package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserForgotPwdReq {
	// region -- Fields --

	@JsonProperty(value = "token")
	private String token;

	@JsonProperty(value = "password")
	private String password;

	// end

	// region -- Get set --

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// end

	// region -- Methods --

	public UserForgotPwdReq() {
	}

	// end
}