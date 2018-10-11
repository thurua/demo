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

	@JsonProperty(value = "owner")
	private String owner;

	@JsonProperty(value = "uuId")
	private String uuId;

	@JsonProperty(value = "parentUuId")
	private String parentUuId;

	@JsonProperty(value = "extension")
	private String extension;

	@JsonProperty(value = "filePath")
	private String filePath;

	@JsonProperty(value = "path")
	private String path;

	@JsonProperty(value = "file")
	private String file;

	// end

	// region -- Get set --

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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getParentUuId() {
		return parentUuId;
	}

	public void setParentUuId(String parentUuId) {
		this.parentUuId = parentUuId;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getPath() {
		String res = System.getenv("BUCKETEER_BUCKET_URL") + "/";
		res = filePath.replace(res, "");
		res = res.replace("/" + uuId + "." + extension, "");
		return res;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile() {
		return uuId + "." + extension;
	}

	public void setFile(String file) {
		this.file = file;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public AttachmentDto() {
		super();

		name = "";
		fileSize = null;
		uploadedBy = "";
		uploadedOn = null;
		rowNumber = 0;
		owner = "";
		uuId = "";
		parentUuId = "";
		extension = "";
		filePath = "";
		path = "";
		file = "";
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
		res.setFileSize(filesize_in_kiloBytes((Double) o[3]));
		res.setUploadedBy((String) o[4]);
		res.setUploadedOn((Date) o[5]);
		res.setRowNumber((Integer) o[6]);
		res.setOwner((String) o[7]);
		res.setUuId((String) o[8]);
		res.setParentUuId((String) o[9]);
		res.setExtension((String) o[10]);
		res.setFilePath((String) o[11]);

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

	private static Double filesize_in_kiloBytes(Double file) {
		return file / 1024;
	}

	// end
}