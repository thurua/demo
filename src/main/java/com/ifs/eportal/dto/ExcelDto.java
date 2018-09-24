package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExcelDto {
	// region -- Fields --

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "documentDate")
	private Date documentDate;

	@JsonProperty(value = "documentCurrency")
	private String documentCurrency;

	@JsonProperty(value = "totalAmount")
	private String totalAmount;

	@JsonProperty(value = "listType")
	private String listType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "processDate")
	private Date processDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "keyInByDate")
	private Date keyInByDate;

	@JsonProperty(value = "factorCode")
	private String factorCode;

	@JsonProperty(value = "lineItems")
	private ArrayList<LineItemDto> lineItems = new ArrayList<LineItemDto>();

	// end

	// region -- Get set --

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentCurrency() {
		return documentCurrency;
	}

	public void setDocumentCurrency(String documentCurrency) {
		this.documentCurrency = documentCurrency;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public Date getKeyInByDate() {
		return keyInByDate;
	}

	public void setKeyInByDate(Date keyInByDate) {
		this.keyInByDate = keyInByDate;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public ArrayList<LineItemDto> getLineItems() {
		return lineItems;
	}

	public void setLineItems(ArrayList<LineItemDto> lineItems) {
		this.lineItems = lineItems;
	}

	// end

	// region -- Methods --

	public ExcelDto() {
		lineItems = new ArrayList<LineItemDto>();
	}

	public void addLineItem(LineItemDto item) {
		this.lineItems.add(item);
	}

	// end
}