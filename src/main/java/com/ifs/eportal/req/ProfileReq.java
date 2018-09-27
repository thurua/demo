package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileReq {

	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "firstName")
	private String firstName;

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "salutation")
	private String salutation;

	@JsonProperty(value = "mobile")
	private String mobile;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	// end

	// region -- Methods --

	public ProfileReq() {
		id = 0;
	}

	// end
}