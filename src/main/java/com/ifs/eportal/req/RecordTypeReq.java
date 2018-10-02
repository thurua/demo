package com.ifs.eportal.req;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordTypeReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "systemModStamp")
	private Date systemModStamp;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "hcLastop")
	private String hcLastop;

	@JsonProperty(value = "hcErr")
	private String hcErr;

	@JsonProperty(value = "developerName")
	private String developerName;

	@JsonProperty(value = "sobjectType")
	private String sobjectType;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSystemModStamp() {
		return systemModStamp;
	}

	public void setSystemModStamp(Date systemModStamp) {
		this.systemModStamp = systemModStamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getHcLastop() {
		return hcLastop;
	}

	public void setHcLastop(String hcLastop) {
		this.hcLastop = hcLastop;
	}

	public String getHcErr() {
		return hcErr;
	}

	public void setHcErr(String hcErr) {
		this.hcErr = hcErr;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getSobjectType() {
		return sobjectType;
	}

	public void setSobjectType(String sobjectType) {
		this.sobjectType = sobjectType;
	}

	// end

	// region -- Methods --

	public RecordTypeReq() {

	}

	// end

}