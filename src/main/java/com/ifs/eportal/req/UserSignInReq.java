package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSignInReq {
	// region -- Fields --

	@JsonProperty(value = "userName")
	private String userName;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "clientKey")
	private String clienKey;

	@JsonProperty(value = "token")
	private String token;

	@JsonProperty(value = "sendToken")
	private boolean sendToken;

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

	public String getClienKey() {
		return clienKey;
	}

	public void setClienKey(String clienKey) {
		this.clienKey = clienKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSendToken() {
		return sendToken;
	}

	public void setSendToken(boolean sendToken) {
		this.sendToken = sendToken;
	}

	// end

	// region -- Methods --

	public UserSignInReq() {
	}

	// end
}