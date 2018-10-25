package com.ifs.eportal.filter;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.ZConfig;

/**
 * 
 * @author VanPhan 2018-Oct-25
 *
 */
public class FundRequestFilter {
	// region -- Fields --

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "fundRequestNo")
	private String fundRequestNo;

	@JsonProperty(value = "frCreatedDate")
	private Date frCreatedDate;

	@JsonProperty(value = "toCreatedDate")
	private Date toCreatedDate;

	@JsonProperty(value = "recordType")
	private String recordType;

	@JsonProperty(value = "status")
	private String status;

	// end

	// region -- Get set --

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getFundRequestNo() {
		return fundRequestNo;
	}

	public void setFundRequestNo(String fundRequestNo) {
		this.fundRequestNo = fundRequestNo;
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

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FundRequestFilter() {
		client = "";
		fundRequestNo = "";
		frCreatedDate = null;
		toCreatedDate = null;
		recordType = "";
		status = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static FundRequestFilter convert(Object o) {
		FundRequestFilter res = new FundRequestFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, FundRequestFilter.class);
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