package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserChangePwdReq {
	// region -- Fields --

	@JsonProperty(value = "newPassword")
	private String newPassword;

	@JsonProperty(value = "oldPassword")
	private String oldPassword;

	// end

	// region -- Get set --

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	// end

	// region -- Methods --

	public UserChangePwdReq() {
	}

	// end
}