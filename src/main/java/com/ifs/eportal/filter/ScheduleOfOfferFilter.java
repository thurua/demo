package com.ifs.eportal.filter;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.ZConfig;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
public class ScheduleOfOfferFilter {
	// region -- Fields --

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "portalStatus")
	private String portalStatus;

	@JsonProperty(value = "frCreatedDate")
	private Date frCreatedDate;

	@JsonProperty(value = "toCreatedDate")
	private Date toCreatedDate;

	@JsonProperty(value = "documentType")
	private String documentType;

	@JsonProperty(value = "name")
	private String name;

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

	public String getPortalStatus() {
		return portalStatus;
	}

	public void setPortalStatus(String portalStatus) {
		this.portalStatus = portalStatus;
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

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ScheduleOfOfferFilter() {
		client = "";
		clientAccount = "";
		portalStatus = "";
		frCreatedDate = null;
		toCreatedDate = null;
		documentType = "";
		name = "";
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
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
		}

		return res;
	}

	// end
}