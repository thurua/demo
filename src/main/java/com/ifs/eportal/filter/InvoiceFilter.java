package com.ifs.eportal.filter;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.Utils;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
public class InvoiceFilter {
	// region -- Fields --

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonProperty(value = "documentType")
	private String documentType;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "supplier")
	private String supplier;

	@JsonProperty(value = "invoiceNo")
	private String invoiceNo;

	@JsonProperty(value = "frCreatedDate")
	private Date frCreatedDate;

	@JsonProperty(value = "toCreatedDate")
	private Date toCreatedDate;

	// end

	// region -- Get set --

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSuppliler(String suppliler) {
		this.supplier = suppliler;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getFrCreatedDate() {
		return frCreatedDate;
	}

	public void setFrCreatedDate(Date frCreatedDate) {
		this.frCreatedDate = frCreatedDate;
	}

	public Date getToCreatedDate() {
		return toCreatedDate;
	}

	public void setToCreatedDate(Date toCreatedDate) {
		this.toCreatedDate = toCreatedDate;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public InvoiceFilter() {
		status = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static InvoiceFilter convert(Object o) {
		InvoiceFilter res = new InvoiceFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, InvoiceFilter.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
		}

		return res;
	}

	// end
}