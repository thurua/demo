package com.ifs.eportal.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduleOfOfferReq {

	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "currencyIsoCode")
	private String currencyIsoCode;

	@JsonProperty(value = "clientName")
	private String clientName;

	@JsonProperty(value = "processDate")
	private Date processDate;

	@JsonProperty(value = "factorCode")
	private String factorCode;

	@JsonProperty(value = "recordTypeId")
	private String recordTypeId;

	@JsonProperty(value = "exchangeRate")
	private Float exchangeRate;

	@JsonProperty(value = "scheduleStatus")
	private String scheduleStatus;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "acceptanceDate")
	private Date acceptanceDate;

	@JsonProperty(value = "scheduleDate")
	private Date scheduleDate;

	@JsonProperty(value = "isDeleted")
	private boolean isDeleted;

	@JsonProperty(value = "systemModStamp")
	private Date systemModStamp;

	@JsonProperty(value = "averageInvoiceSizeLast12Months")
	private Float averageInvoiceSizeLast12Months;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonProperty(value = "originalAcceptanceDate")
	private Date originalAcceptanceDate;

	@JsonProperty(value = "sequence")
	private Float sequence;

	@JsonProperty(value = "keyInByDate")
	private Date keyInByDate;

	@JsonProperty(value = "listType")
	private String listType;

	@JsonProperty(value = "portalStatus")
	private String portalStatus;

	// end

	// region -- Get set --

	// end

	// region -- Methods --

	public ScheduleOfOfferReq() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
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

	public Float getAverageInvoiceSizeLast12Months() {
		return averageInvoiceSizeLast12Months;
	}

	public void setAverageInvoiceSizeLast12Months(Float averageInvoiceSizeLast12Months) {
		this.averageInvoiceSizeLast12Months = averageInvoiceSizeLast12Months;
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

	public Float getSequence() {
		return sequence;
	}

	public void setSequence(Float sequence) {
		this.sequence = sequence;
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

	public String getPortalStatus() {
		return portalStatus;
	}

	public void setPortalStatus(String portalStatus) {
		this.portalStatus = portalStatus;
	}

	// end
}