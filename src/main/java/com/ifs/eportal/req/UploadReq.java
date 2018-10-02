package com.ifs.eportal.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-02
 *
 */
public class UploadReq {
	// region -- Fields --

	@JsonProperty(value = "clientId")
	private String clientId;

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonProperty(value = "acceptanceDate")
	private Date acceptanceDate;

	@JsonProperty(value = "clientAccountId")
	private String clientAccountId;

	@JsonProperty(value = "scheduleType")
	private String scheduleType;

	@JsonProperty(value = "amendSchedule")
	private Boolean amendSchedule;

	// end

	// region -- Get set --

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getClientAccountId() {
		return clientAccountId;
	}

	public void setClientAccountId(String clientAccountId) {
		this.clientAccountId = clientAccountId;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public Boolean getAmendSchedule() {
		return amendSchedule;
	}

	public void setAmendSchedule(Boolean amendSchedule) {
		this.amendSchedule = amendSchedule;
	}

	// end

	// region -- Methods --

	public UploadReq() {
		clientId = "";
		scheduleNo = "";
		acceptanceDate = null;
		clientAccountId = "";
		scheduleType = "";
		amendSchedule = false;
	}

	// end
}