package com.ifs.eportal.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortalUserReq {

	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "mobile")
	private String mobile;

	@JsonProperty(value = "currencyIsoCode")
	private String currencyIsoCode;

	@JsonProperty(value = "contact")
	private String contact;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "firstName")
	private String firstName;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "salutation")
	private String salutation;

	@JsonProperty(value = "role")
	private String role;

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "isDeleted")
	private boolean isDeleted;

	@JsonProperty(value = "systemModStamp")
	private Date systemModStamp;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "active")
	private boolean active;

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "hcLastop")
	private String hcLastop;

	@JsonProperty(value = "hcErr")
	private String hcErr;

	@JsonProperty(value = "externalId")
	private float externalId;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getSystemModStamp() {
		return systemModStamp;
	}

	public void setSystemModStamp(Date systemModStamp) {
		this.systemModStamp = systemModStamp;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getHcLastop() {
		return hcLastop;
	}

	public void setHcLastop(String hcLastop) {
		this.hcLastop = hcLastop;
	}

	public String getHcErr() {
		return hcErr;
	}

	public void setHcErr(String hcErr) {
		this.hcErr = hcErr;
	}

	public float getExternalId() {
		return externalId;
	}

	public void setExternalId(float externalId) {
		this.externalId = externalId;
	}

	// end

	// region -- Methods --

	public PortalUserReq() {
	}

	// end

}