package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-26
 *
 */
public class PortalUserSignInReq {
	// region -- Fields --

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "password")
	private String password;

	// end

	// region -- Get set --

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// end

	// region -- Methods --

	public PortalUserSignInReq() {
		email = "";
		password = "";
	}

	// end
}