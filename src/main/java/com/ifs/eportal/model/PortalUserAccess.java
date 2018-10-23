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

/**
 * 
 * @author ToanNguyen 2018-Oct-19 (verified)
 *
 */
@Entity
@Table(name = "portal_user_access__c", schema = "salesforce")
public class PortalUserAccess extends BaseModel {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "last_access_on__c")
	private Date lastAccessOn;

	@Column(columnDefinition = "float(8)", name = "external_id__c")
	private Float externalId;

	@Column(columnDefinition = "varchar(18)", name = "user__c")
	private String user;

	@Column(columnDefinition = "varchar(10)", name = "otp__c")
	private String otp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "otp_expire_on__c")
	private Date otpExpireOn;

	@Column(columnDefinition = "varchar(100)", name = "uuid__c")
	private String uuId;

	@Column(columnDefinition = "varchar(255)", name = "user_agent__c")
	private String userAgent;

	@Column(columnDefinition = "varchar(255)", name = "host__c")
	private String host;

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

	public Date getLastAccessOn() {
		return lastAccessOn;
	}

	public void setLastAccessOn(Date lastAccessOn) {
		this.lastAccessOn = lastAccessOn;
	}

	public Float getExternalId() {
		return externalId;
	}

	public void setExternalId(Float externalId) {
		this.externalId = externalId;
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

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalUserAccess() {
		super();
	}

	// end
}