package com.ifs.eportal.filter;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.ZConfig;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
public class CreditNoteFilter {
	// region -- Fields --

	@JsonProperty(value = "status")
	private String status;

	// HoanNguyen 2018-Oct-05 (Updated)

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "frCreatedDate")
	private Date frCreatedDate;

	@JsonProperty(value = "toCreatedDate")
	private Date toCreatedDate;

	// end

	// region -- Get set --

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public CreditNoteFilter() {
		status = "";
		clientAccount = "";
		customer = "";
		client = "";
		scheduleNo = "";
		name = "";
		frCreatedDate = null;
		toCreatedDate = null;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static CreditNoteFilter convert(Object o) {
		CreditNoteFilter res = new CreditNoteFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, CreditNoteFilter.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
		}

		return res;
	}

	// end
}