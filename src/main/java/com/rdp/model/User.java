package com.rdp.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user", schema = "public")
public class User {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_generator")
	@SequenceGenerator(name = "user_id_seq_generator", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(nullable = false, columnDefinition = "SERIAL")
	private int id;

	@NotNull
	@Type(type = "pg-uuid")
	private UUID uuid;

	@Column(columnDefinition = "text")
	private String userName;

	@Column(columnDefinition = "varchar")
	private String passwordSalt;

	@Column(columnDefinition = "varchar")
	private String passwordHash;

	@Column(columnDefinition = "text")
	private String firstName;

	@Column(columnDefinition = "text")
	private String lastName;

	@Column(columnDefinition = "text")
	private String email;

	@Column(columnDefinition = "text")
	private String contactNo;

	@Column(columnDefinition = "text")
	private String country;

	@Column(columnDefinition = "text")
	private String company;

	@Column(nullable = true)
	private Boolean isEmailVerified;

	@Column(nullable = true)
	private Boolean isPhoneVerified;

	@Type(type = "pg-uuid")
	private UUID eoth;

	@Column(columnDefinition = "varchar(200)")
	private String passwordReminderToken;

	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date eothExpiryOn;

	@Column(columnDefinition = "varchar(50)")
	private String poth;

	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date pothExpiryOn;

	@Column(columnDefinition = "text")
	private String remarks;

	@Column(columnDefinition = "text")
	private String source;

	@Column(columnDefinition = "text")
	private String status;

	@Column(columnDefinition = "varchar(4)")
	private String kycStatus;

	@Column(nullable = true)
	private Boolean isDelete;

	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdOn;

	@Column(nullable = false)
	private int createdBy;

	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date modifiedOn;

	@Column(nullable = false)
	private int modifiedBy;

	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date passwordReminderExpire;

	// end

	// region -- Get set --

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Boolean getIsPhoneVerified() {
		return isPhoneVerified;
	}

	public void setIsPhoneVerified(Boolean isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}

	public UUID getEoth() {
		return eoth;
	}

	public void setEoth(UUID eoth) {
		this.eoth = eoth;
	}

	public String getPasswordReminderToken() {
		return passwordReminderToken;
	}

	public void setPasswordReminderToken(String passwordReminderToken) {
		this.passwordReminderToken = passwordReminderToken;
	}

	public Date getEothExpiryOn() {
		return eothExpiryOn;
	}

	public void setEothExpiryOn(Date eothExpiryOn) {
		this.eothExpiryOn = eothExpiryOn;
	}

	public String getPoth() {
		return poth;
	}

	public void setPoth(String poth) {
		this.poth = poth;
	}

	public Date getPothExpiryOn() {
		return pothExpiryOn;
	}

	public void setPothExpiryOn(Date pothExpiryOn) {
		this.pothExpiryOn = pothExpiryOn;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getPasswordReminderExpire() {
		return passwordReminderExpire;
	}

	public void setPasswordReminderExpire(Date passwordReminderExpire) {
		this.passwordReminderExpire = passwordReminderExpire;
	}

	// end

	// region -- Methods --

	public User() {
	}

	// end
}