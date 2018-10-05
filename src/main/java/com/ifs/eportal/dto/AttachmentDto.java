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
public class AttachmentDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "sfId")
	private String sfId;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "fileSize")
	private Double fileSize;

	@JsonProperty(value = "uploadedBy")
	private String uploadedBy;

	@JsonProperty(value = "uploadedOn")
	private Date uploadedOn;

	@JsonProperty(value = "rowNumber")
	private Integer rowNumber;

	// end

	// region -- Get set --

	public String getSfId() {
		return sfId;
	}

	public void setSfId(String sfId) {
		this.sfId = sfId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public AttachmentDto() {
		super();

		sfId = "";
		name = "";
		fileSize = null;
		uploadedBy = "";
		uploadedOn = null;
		rowNumber = 0;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static AttachmentDto convert(Object[] o) {
		AttachmentDto res = new AttachmentDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);
		res.setName((String) o[2]);
		res.setFileSize((Double) o[3]);
		res.setUploadedBy((String) o[4]);
		res.setUploadedOn((Date) o[5]);
		res.setRowNumber((Integer) o[6]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<AttachmentDto> convert(List<Object[]> l) {
		List<AttachmentDto> res = new ArrayList<AttachmentDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}