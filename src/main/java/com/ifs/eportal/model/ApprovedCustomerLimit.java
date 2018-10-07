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
@Table(name = "approved_customer_limit__c", schema = "salesforce")
public class ApprovedCustomerLimit {
	// region -- Fields --
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approved_customer_limit__c_id_seq_generator")
	@SequenceGenerator(name = "approved_customer_limit__c_id_seq_generator", sequenceName = "salesforce.approved_customer_limit__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "valid_to__c")
	private Date validTo;

	@Column(columnDefinition = "varchar(18)", name = "customer__c")
	private String customer;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "bool", name = "isdeleted")
	private boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "systemmodstamp")
	private Date systemModStamp;

	@Column(columnDefinition = "float", name = "approved_limit__c")
	private float approvedLimit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "valid_from__c")
	private Date validFrom;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "createddate")
	private Date createdDate;

	@Column(columnDefinition = "varchar(18)", name = "client__c")
	private String client;

	@Column(columnDefinition = "bool", name = "is_valid__c")
	private String isValid;

	@Column(columnDefinition = "varchar(18)", name = "sfid")
	private String sfid;

	@Column(columnDefinition = "varchar(32)", name = "_hc_lastop")
	private String hcLastop;

	@Column(columnDefinition = "text", name = "_hc_err")
	private String hcErr;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public float getApprovedLimit() {
		return approvedLimit;
	}

	public void setApprovedLimit(float approvedLimit) {
		this.approvedLimit = approvedLimit;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
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

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
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

	// end

	// region -- Methods --

	public ApprovedCustomerLimit() {

	}

	// end
}