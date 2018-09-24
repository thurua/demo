package com.rdp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

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

	@JsonProperty(value = "userName")
	private String userName;

	@JsonProperty(value = "kycStatus")
	private String kycStatus;

	@JsonProperty(value = "statusText")
	private String statusText;

	@JsonProperty(value = "kycStatusText")
	private String kycStatusText;

	// end

	// region -- Get set --

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getKycStatusText() {
		return kycStatusText;
	}

	public void setKycStatusText(String kycStatusText) {
		this.kycStatusText = kycStatusText;
	}

	// end

	// region -- Methods --

	public UserDto() {
	}

	// end
}