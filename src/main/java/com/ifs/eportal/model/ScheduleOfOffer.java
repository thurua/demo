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
@Table(name = "schedule_of_offer__c", schema = "salesforce")
public class ScheduleOfOffer {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_of_offer__c_id_seq_generator")
	@SequenceGenerator(name = "schedule_of_offer__c_id_seq_generator", sequenceName = "salesforce.schedule_of_offer__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(18)", name = "authorised_by__c")
	private String authorisedBy;

	@Column(columnDefinition = "varchar(3)", name = "currencyisocode")
	private String currencyIsoCode;

	@Column(columnDefinition = "float(8)", name = "sequence__c")
	private Float sequence;

	@Column(columnDefinition = "varchar(18)", name = "client_name__c")
	private String clientName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "process_date__c")
	private Date processDate;

	@Column(columnDefinition = "varchar(255)", name = "document_type__c")
	private String documentType;

	@Column(columnDefinition = "varchar(255)", name = "factor_code__c")
	private String factorCode;

	@Column(columnDefinition = "varchar(18)", name = "recordtypeid")
	private String recordTypeId;

	@Column(columnDefinition = "float(8)", name = "exchange_rate__c")
	private Float exchangeRate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "key_in_by_date__c")
	private Date keyInByDate;

	@Column(columnDefinition = "varchar(255)", name = "list_type__c")
	private String listType;

	@Column(columnDefinition = "varchar(18)", name = "lastmodifiedby_portaluserid__c")
	private String lastModifiedByPortalUserId;

	@Column(columnDefinition = "varchar(255)", name = "schedule_status__c")
	private String scheduleStatus;

	@Column(columnDefinition = "varchar(18)", name = "client_account__c")
	private String clientAccount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "authorised_date__c")
	private Date authorisedDate;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "acceptance_date__c")
	private Date acceptanceDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "schedule_date__c")
	private Date scheduleDate;

	@Column(columnDefinition = "bool", name = "isdeleted")
	private boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "systemmodstamp")
	private Date systemModStamp;

	@Column(columnDefinition = "varchar(18)", name = "createdby_portaluserid__c")
	private String createdByPortalUserId;

	@Column(columnDefinition = "float(8)", name = "average_invoice_size_last_12_months__c")
	private Float averageInvoiceSizeLast12Months;

	@Column(columnDefinition = "float(8)", name = "external_id__c")
	private Float externalId;

	@Column(columnDefinition = "bool", name = "allow_submit__c")
	private boolean allowSubmit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "createddate")
	private Date createdDate;

	@Column(columnDefinition = "varchar(255)", name = "schedule_no__c")
	private String scheduleNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "original_acceptance_date__c")
	private Date originalAcceptanceDate;

	@Column(columnDefinition = "varchar(255)", name = "portal_status__c")
	private String portalStatus;

	@Column(columnDefinition = "varchar(18)", name = "sfid")
	private String sfId;

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

	public String getAuthorisedBy() {
		return authorisedBy;
	}

	public void setAuthorisedBy(String authorisedBy) {
		this.authorisedBy = authorisedBy;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public Float getSequence() {
		return sequence;
	}

	public void setSequence(Float sequence) {
		this.sequence = sequence;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public Float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Date getKeyInByDate() {
		return keyInByDate;
	}

	public void setKeyInByDate(Date keyInByDate) {
		this.keyInByDate = keyInByDate;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getLastModifiedByPortalUserId() {
		return lastModifiedByPortalUserId;
	}

	public void setLastModifiedByPortalUserId(String lastModifiedByPortalUserId) {
		this.lastModifiedByPortalUserId = lastModifiedByPortalUserId;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public Date getAuthorisedDate() {
		return authorisedDate;
	}

	public void setAuthorisedDate(Date authorisedDate) {
		this.authorisedDate = authorisedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
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

	public String getCreatedByPortalUserId() {
		return createdByPortalUserId;
	}

	public void setCreatedByPortalUserId(String createdByPortalUserId) {
		this.createdByPortalUserId = createdByPortalUserId;
	}

	public Float getAverageInvoiceSizeLast12Months() {
		return averageInvoiceSizeLast12Months;
	}

	public void setAverageInvoiceSizeLast12Months(Float averageInvoiceSizeLast12Months) {
		this.averageInvoiceSizeLast12Months = averageInvoiceSizeLast12Months;
	}

	public Float getExternalId() {
		return externalId;
	}

	public void setExternalId(Float externalId) {
		this.externalId = externalId;
	}

	public boolean isAllowSubmit() {
		return allowSubmit;
	}

	public void setAllowSubmit(boolean allowSubmit) {
		this.allowSubmit = allowSubmit;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public Date getOriginalAcceptanceDate() {
		return originalAcceptanceDate;
	}

	public void setOriginalAcceptanceDate(Date originalAcceptanceDate) {
		this.originalAcceptanceDate = originalAcceptanceDate;
	}

	public String getPortalStatus() {
		return portalStatus;
	}

	public void setPortalStatus(String portalStatus) {
		this.portalStatus = portalStatus;
	}

	public String getSfId() {
		return sfId;
	}

	public void setSfId(String sfId) {
		this.sfId = sfId;
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

	public ScheduleOfOffer() {

	}

	// end
}