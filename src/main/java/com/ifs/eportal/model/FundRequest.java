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
@Table(name = "fund_request__c", schema = "salesforce")
public class FundRequest extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fund_request_id_seq_generator")
	@SequenceGenerator(name = "fund_request_id_seq_generator", sequenceName = "salesforce.fund_request_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "submitted_on__c")
	private Date submittedOn;

	@Column(columnDefinition = "varchar(3)", name = "currencyisocode")
	private String currencyIsoCode;

	@Column(columnDefinition = "varchar(18)", name = "recordtypeid")
	private String recordTypeId;

	@Column(columnDefinition = "varchar(100)", name = "uuid__c")
	private String uuId;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(255)", name = "status__c")
	private String status;

	@Column(columnDefinition = "float(8)", name = "external_id__c")
	private Float externalId;

	@Column(columnDefinition = "varchar(18)", name = "client__c")
	private String client;

	@Column(columnDefinition = "float(8)", name = "approval_routing__c")
	private Float approvalRouting;

	@Column(columnDefinition = "varchar(18)", name = "schedule_no__c")
	private String scheduleNo;

	@Column(columnDefinition = "varchar(255)", name = "batch_no__c")
	private String batchNo;
	
	@Column(columnDefinition = "bool", name = "is_eportal_fr__c")
	private boolean isEportalFr;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Float getApprovalRouting() {
		return approvalRouting;
	}

	public void setApprovalRouting(Float approvalRouting) {
		this.approvalRouting = approvalRouting;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public boolean isEportalFr() {
		return isEportalFr;
	}

	public void setEportalFr(boolean isEportalFr) {
		this.isEportalFr = isEportalFr;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FundRequest() {
		super();
	}

	// end
}