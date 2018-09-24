package com.rdp.model;

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
@Table(name = "lead", schema = "salesforce")
public class Lead {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lead_id_seq_generator")
	@SequenceGenerator(name = "lead_id_seq_generator", sequenceName = "salesforce.lead_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(6)", name = "lastname")
	private String lastName;

	@Column(columnDefinition = "varchar(6)", name = "postal_code__c")
	private String postalCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "plan_start_date__c")
	private Date planStartDate;

	@Column(columnDefinition = "varchar(255)", name = "salesperson__c")
	private String salesPerson;

	@Column(columnDefinition = "varchar(18)", name = "plan_name__c")
	private String planName;

	@Column(columnDefinition = "float8", name = "rate__c")
	private Float rate;

	@Column(columnDefinition = "varchar(18)", name = "recordtypeid")
	private String recordTypeId;

	@Column(columnDefinition = "float8", name = "masterrecord__external_id__c")
	private Float masterRecordExternalId;

	@Column(columnDefinition = "float8", name = "percent_discount__c")
	private Float percentDiscount;

	@Column(columnDefinition = "varchar(121)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(40)", name = "mobilephone")
	private String mobilePhone;

	@Column(columnDefinition = "bool", name = "agreed_terms_conditions__c")
	private Boolean agreedTermsConditions;

	@Column(columnDefinition = "varchar(255)", name = "current_meter_type__c")
	private String currentMeterType;

	@Column(columnDefinition = "varchar(18)", name = "masterrecordid")
	private String masterRecordId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "registration_date__c")
	private Date registrationDate;

	@Column(columnDefinition = "float8", name = "duration__c")
	private Float duration;

	@Column(columnDefinition = "bool", name = "isdeleted")
	private Boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "systemmodstamp")
	private Date systemModStamp;

	@Column(columnDefinition = "varchar(255)", name = "meter_self_read__c")
	private String meterSelfRead;

	@Column(columnDefinition = "bool", name = "agreed_fact_sheet__c")
	private Boolean agreedFactSheet;

	@Column(columnDefinition = "varchar(255)", name = "change_to_ami_meter__c")
	private String changeToAmiMeter;

	@Column(columnDefinition = "varchar(255)", name = "ebs_number__c")
	private String ebsNumber;

	@Column(columnDefinition = "varchar(40)", name = "status")
	private String status;

	@Column(columnDefinition = "float8", name = "external_id__c")
	private Float externalId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "createddate")
	private Date createdDate;

	@Column(columnDefinition = "varchar(40)", name = "leadsource")
	private String leadSource;

	@Column(columnDefinition = "varchar(255)", name = "address_2__c")
	private String address2;

	@Column(columnDefinition = "varchar(80)", name = "email")
	private String email;

	@Column(columnDefinition = "varchar(255)", name = "address_1__c")
	private String address1;

	@Column(columnDefinition = "varchar(255)", name = "company")
	private String company;

	@Column(columnDefinition = "varchar(18)", name = "sfid")
	private String sfid;

	@Column(columnDefinition = "varchar(32)", name = "_hc_lastop")
	private String hcLastop;

	@Column(columnDefinition = "text", name = "_hc_err")
	private String hcErr;

	@Column(columnDefinition = "float8", name = "night_rate__c")
	private Float nightRate;

	@Column(columnDefinition = "varchar(255)", name = "residential_type__c")
	private String residentialType;

	@Column(columnDefinition = "varchar(10)", name = "nric_no__c")
	private String nricNo;

	@Column(columnDefinition = "varchar(255)", name = "sales_agreement__c")
	private String salesAgreement;

	@Column(columnDefinition = "varchar(40)", name = "phone")
	private String phone;

	@Column(columnDefinition = "varchar(255)", name = "fact_sheet__c")
	private String factSheet;

	@Column(columnDefinition = "varchar(12)", name = "web_reference_no__c")
	private String webReferenceNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "contract_end_date_oem__c")
	private Date contractEndDateOem;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getNricNo() {
		return nricNo;
	}

	public void setNricNo(String nricNo) {
		this.nricNo = nricNo;
	}

	public String getSalesAgreement() {
		return salesAgreement;
	}

	public void setSalesAgreement(String salesAgreement) {
		this.salesAgreement = salesAgreement;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFactSheet() {
		return factSheet;
	}

	public void setFactSheet(String factSheet) {
		this.factSheet = factSheet;
	}

	public String getWebReferenceNo() {
		return webReferenceNo;
	}

	public void setWebReferenceNo(String webReferenceNo) {
		this.webReferenceNo = webReferenceNo;
	}

	public Date getContractEndDateOem() {
		return contractEndDateOem;
	}

	public void setContractEndDateOem(Date contractEndDateOem) {
		this.contractEndDateOem = contractEndDateOem;
	}

	// end

	// region -- Methods --

	public Lead() {
	}

	// end
}