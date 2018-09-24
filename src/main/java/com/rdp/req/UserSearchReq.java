package com.rdp.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSearchReq {
	// region -- Fields --

	@JsonProperty(value = "firstName")
	private String firstName;

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "company")
	private String company;

	@JsonProperty(value = "status")
	private String status;

	// end

	// region -- Get set --

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// end

	// region -- Methods --

	public UserSearchReq() {

	}

	// end
}