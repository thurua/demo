package com.ifs.eportal.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateReq {
	// region -- Fields --

	@JsonProperty(value = "firstName")
	private String firstName;

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "createBy")
	private Integer createBy;

	@JsonProperty(value = "contactNo")
	private String contactNo;

	@JsonProperty(value = "remarks")
	private String remarks;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "createOn")
	private Date createOn;

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

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	// region -- Methods --

	public UserUpdateReq() {
	}

	// end
}