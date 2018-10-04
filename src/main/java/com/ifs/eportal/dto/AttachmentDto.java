package com.ifs.eportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-04
 *
 */
public class AttachmentDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "no")
	private Integer no;

	@JsonProperty(value = "attachedFile")
	private String attachedFile;

	@JsonProperty(value = "fileSize")
	private String fileSize;

	@JsonProperty(value = "owner")
	private String owner;

	@JsonProperty(value = "uploadedOn")
	private String uploadedOn;

	// end

	// region -- Get set --

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(String attachedFile) {
		this.attachedFile = attachedFile;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(String uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public AttachmentDto() {
		super();

		no = 1;
		attachedFile = "";
		fileSize = "";
		owner = "";
		uploadedOn = "";
	}

	// end
}