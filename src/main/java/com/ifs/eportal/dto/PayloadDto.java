package com.ifs.eportal.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class PayloadDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "passwordHash")
	private String passwordHash;

	@JsonProperty(value = "passReminderToken")
	private String passReminderToken;

	@JsonProperty(value = "passReminderExpire")
	private Date passReminderExpire;

	@JsonProperty(value = "firstName")
	private String firstName;

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "clientId")
	private String clientId;

	@JsonProperty(value = "clientName")
	private String clientName;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPassReminderToken() {
		return passReminderToken;
	}

	public void setPassReminderToken(String passReminderToken) {
		this.passReminderToken = passReminderToken;
	}

	public Date getPassReminderExpire() {
		return passReminderExpire;
	}

	public void setPassReminderExpire(Date passReminderExpire) {
		this.passReminderExpire = passReminderExpire;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	// end

	// region -- Methods --

	public PayloadDto() {
		id = 0;
		email = "";
		password = "";
		passwordHash = "";
		passReminderToken = null;
		firstName = "";
		lastName = "";
		clientId = "";
		clientName = "";
	}

	// end
}