package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-28
 *
 */
public class ScheduleOfOfferDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "scheduleDate")
	private Date scheduleDate;

	@JsonProperty(value = "scheduleStatus")
	private String scheduleStatus;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "documentType")
	private String documentType;

	// end

	// region -- Get set --

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ScheduleOfOfferDto() {
		super();

		scheduleNo = "";
		clientAccount = "";
		scheduleDate = null;
		scheduleStatus = "";
		createdDate = null;
		documentType = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ScheduleOfOfferDto convert(Object[] o) {
		ScheduleOfOfferDto res = new ScheduleOfOfferDto();

		res.setId((Integer) o[0]);
		res.setScheduleNo((String) o[1]);
		res.setClientAccount((String) o[2]);
		res.setScheduleDate((Date) o[3]);
		res.setScheduleStatus((String) o[4]);
		res.setCreatedDate((Date) o[5]);
		res.setDocumentType((String) o[6]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ScheduleOfOfferDto> convert(List<Object[]> l) {
		List<ScheduleOfOfferDto> res = new ArrayList<ScheduleOfOfferDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}