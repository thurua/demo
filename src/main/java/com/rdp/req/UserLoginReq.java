package com.rdp.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginReq {
	// region -- Fields --

	@JsonProperty(value = "userName")
	private String userName;

	@JsonProperty(value = "password")
	private String password;

	// end

	// region -- Get set --

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// end

	// region -- Methods --

	public UserLoginReq() {

	}

	// end
}