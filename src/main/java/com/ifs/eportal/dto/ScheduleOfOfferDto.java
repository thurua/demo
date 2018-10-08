package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
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

	@JsonProperty(value = "portalStatus")
	private String portalStatus;

	@JsonProperty(value = "createdBy")
	private String createdBy;

	@JsonProperty(value = "documentType")
	private String documentType;

	@JsonProperty(value = "sequence")
	private Double sequence;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "recordTypeId")
	private String recordTypeId;

	@JsonProperty(value = "recordTypeName")
	private String recordTypeName;
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

	public String getPortalStatus() {
		return portalStatus;
	}

	public void setPortalStatus(String portalStatus) {
		this.portalStatus = portalStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Double getSequence() {
		return sequence;
	}

	public void setSequence(Double sequence) {
		this.sequence = sequence;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public String getRecordTypeName() {
		return recordTypeName;
	}

	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
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
		portalStatus = "";
		createdBy = null;
		documentType = "";
		sequence = null;
		createdDate = null;
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
		res.setSfId((String) o[1]);

		res.setScheduleNo((String) o[2]);
		res.setClientAccount((String) o[3]);
		res.setScheduleDate((Date) o[4]);
		res.setPortalStatus((String) o[5]);
		res.setCreatedBy((String) o[6]);
		res.setDocumentType((String) o[7]);
		res.setSequence((Double) o[8]);
		res.setCreatedDate((Date) o[9]);
		res.setRecordTypeId((String) o[10]);
		res.setRecordTypeName((String) o[11]);
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