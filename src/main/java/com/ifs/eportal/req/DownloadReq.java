package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-09 (verified)
 *
 */
public class DownloadReq {
	// region -- Fields --

	@JsonProperty(value = "path")
	private String path;

	@JsonProperty(value = "file")
	private String file;

	@JsonProperty(value = "aws")
	private Boolean aws;

	// end

	// region -- Get set --

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Boolean getAws() {
		return aws;
	}

	public void setAws(Boolean aws) {
		this.aws = aws;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public DownloadReq() {

	}

	// end
}