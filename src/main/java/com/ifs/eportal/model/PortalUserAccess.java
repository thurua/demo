package com.ifs.eportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "portal_user_access__c", schema = "salesforce")
public class PortalUserAccess {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portal_user_access__c_id_seq_generator")
	@SequenceGenerator(name = "portal_user_access__c_id_seq_generator", sequenceName = "salesforce.portal_user_access__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "login_on__c")
	private Date loginOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "logout_on__c")
	private Date logoutOn;

	@Column(columnDefinition = "bool", name = "isdeleted")
	private boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "systemmodstamp")
	private Date systemModStamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "last_access_on__c")
	private Date lastAccessOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "createddate")
	private Date createdDate;

	@Column(columnDefinition = "varchar(18)", name = "user__c")
	private String user;

	@Column(columnDefinition = "varchar(18)", name = "otp__c")
	private String otp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "otp_expire_on__c")
	private Date otpExpireOn;

	@Column(columnDefinition = "varchar(18)", name = "sfid")
	private String sfid;

	@Column(columnDefinition = "varchar(32)", name = "_hc_lastop")
	private String hcLastop;

	@Column(columnDefinition = "text", name = "_hc_err")
	private String hcErr;

	@Column(columnDefinition = "float", name = "external_id__c")
	private float externalId;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
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

	// region -- Methods --

	public PortalUserAccess() {

	}

	// end
}