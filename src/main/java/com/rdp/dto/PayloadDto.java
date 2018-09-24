package com.rdp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayloadDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "firstname")
	private String firstName;

	@JsonProperty(value = "lastname")
	private String lastName;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "company")
	private String company;

	@JsonProperty(value = "username")
	private String userName;

	@JsonProperty(value = "accessrights")
	private List<String> accessRights;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(List<String> accessRights) {
		this.accessRights = accessRights;
	}

	// end

	// region -- Methods --

	public PayloadDto() {
	}

	// end
}