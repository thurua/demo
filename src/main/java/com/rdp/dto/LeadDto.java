package com.rdp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeadDto {
	// region -- Fields --

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "postalCode")
	private String postalCode;

	@JsonProperty(value = "planStartDate")
	private Date planStartDate;

	@JsonProperty(value = "salesPerson")
	private String salesPerson;

	@JsonProperty(value = "planName")
	private String planName;

	@JsonProperty(value = "rate")
	private Float rate;

	@JsonProperty(value = "recordTypeId")
	private String recordTypeId;

	@JsonProperty(value = "masterRecordExternalId")
	private Float masterRecordExternalId;

	@JsonProperty(value = "percentDiscount")
	private Float percentDiscount;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "mobilePhone")
	private String mobilePhone;

	@JsonProperty(value = "agreedTermsConditions")
	private Boolean agreedTermsConditions;

	@JsonProperty(value = "currentMeterType")
	private String currentMeterType;

	@JsonProperty(value = "masterRecordId")
	private String masterRecordId;

	@JsonProperty(value = "registrationDate")
	private Date registrationDate;

	@JsonProperty(value = "duration")
	private Float duration;

	@JsonProperty(value = "isDeleted")
	private Boolean isDeleted;

	@JsonProperty(value = "systemModStamp")
	private Date systemModStamp;

	@JsonProperty(value = "meterSelfRead")
	private String meterSelfRead;

	@JsonProperty(value = "agreedFactSheet")
	private Boolean agreedFactSheet;

	@JsonProperty(value = "changeToAmiMeter")
	private String changeToAmiMeter;

	@JsonProperty(value = "ebsNumber")
	private String ebsNumber;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "externalId")
	private Float externalId;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "leadSource")
	private String leadSource;

	@JsonProperty(value = "address2")
	private String address2;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "address1")
	private String address1;

	@JsonProperty(value = "company")
	private String company;

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "hcLastop")
	private String hcLastop;

	@JsonProperty(value = "hcErr")
	private String hcErr;

	@JsonProperty(value = "nightRate")
	private Float nightRate;

	@JsonProperty(value = "residentialType")
	private String residentialType;

	// end

	// region -- Get set --

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public Float getMasterRecordExternalId() {
		return masterRecordExternalId;
	}

	public void setMasterRecordExternalId(Float masterRecordExternalId) {
		this.masterRecordExternalId = masterRecordExternalId;
	}

	public Float getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(Float percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Boolean getAgreedTermsConditions() {
		return agreedTermsConditions;
	}

	public void setAgreedTermsConditions(Boolean agreedTermsConditions) {
		this.agreedTermsConditions = agreedTermsConditions;
	}

	public String getCurrentMeterType() {
		return currentMeterType;
	}

	public void setCurrentMeterType(String currentMeterType) {
		this.currentMeterType = currentMeterType;
	}

	public String getMasterRecordId() {
		return masterRecordId;
	}

	public void setMasterRecordId(String masterRecordId) {
		this.masterRecordId = masterRecordId;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getSystemModStamp() {
		return systemModStamp;
	}

	public void setSystemModStamp(Date systemModStamp) {
		this.systemModStamp = systemModStamp;
	}

	public String getMeterSelfRead() {
		return meterSelfRead;
	}

	public void setMeterSelfRead(String meterSelfRead) {
		this.meterSelfRead = meterSelfRead;
	}

	public Boolean getAgreedFactSheet() {
		return agreedFactSheet;
	}

	public void setAgreedFactSheet(Boolean agreedFactSheet) {
		this.agreedFactSheet = agreedFactSheet;
	}

	public String getChangeToAmiMeter() {
		return changeToAmiMeter;
	}

	public void setChangeToAmiMeter(String changeToAmiMeter) {
		this.changeToAmiMeter = changeToAmiMeter;
	}

	public String getEbsNumber() {
		return ebsNumber;
	}

	public void setEbsNumber(String ebsNumber) {
		this.ebsNumber = ebsNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getExternalId() {
		return externalId;
	}

	public void setExternalId(Float externalId) {
		this.externalId = externalId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public Float getNightRate() {
		return nightRate;
	}

	public void setNightRate(Float nightRate) {
		this.nightRate = nightRate;
	}

	public String getResidentialType() {
		return residentialType;
	}

	public void setResidentialType(String residentialType) {
		this.residentialType = residentialType;
	}

	// end

	// region -- Methods --

	public LeadDto() {
	}

	// end
}