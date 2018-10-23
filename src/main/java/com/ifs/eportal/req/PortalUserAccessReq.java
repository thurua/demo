package com.ifs.eportal.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortalUserAccessReq {

	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "loginOn")
	private Date loginOn;

	@JsonProperty(value = "logoutOn")
	private Date logoutOn;

	@JsonProperty(value = "isDeleted")
	private boolean isDeleted;

	@JsonProperty(value = "systemModStamp")
	private Date systemModStamp;

	@JsonProperty(value = "lastAccessOn")
	private Date lastAccessOn;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "user")
	private String user;

	@JsonProperty(value = "otp")
	private String otp;

	@JsonProperty(value = "otpExpireOn")
	private Date otpExpireOn;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLoginOn() {
		return loginOn;
	}

	public void setLoginOn(Date loginOn) {
		this.loginOn = loginOn;
	}

	public Date getLogoutOn() {
		return logoutOn;
	}

	public void setLogoutOn(Date logoutOn) {
		this.logoutOn = logoutOn;
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

	public Date getLastAccessOn() {
		return lastAccessOn;
	}

	public void setLastAccessOn(Date lastAccessOn) {
		this.lastAccessOn = lastAccessOn;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtpExpireOn() {
		return otpExpireOn;
	}

	public void setOtpExpireOn(Date otpExpireOn) {
		this.otpExpireOn = otpExpireOn;
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

	// region -- Fields --
	public PortalUserAccessReq() {

	}
	// end

}