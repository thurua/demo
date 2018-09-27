package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class ScheduleOfOfferFilter {
	// region -- Fields --

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "scheduleStatus")
	private String scheduleStatus;

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

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ScheduleOfOfferFilter() {
		client = "";
		clientAccount = "";
		scheduleStatus = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ScheduleOfOfferFilter convert(Object o) {
		ScheduleOfOfferFilter res = new ScheduleOfOfferFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, ScheduleOfOfferFilter.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return res;
	}

	// end
}