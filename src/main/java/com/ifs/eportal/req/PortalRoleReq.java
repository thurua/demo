package com.ifs.eportal.req;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortalRoleReq {
	// region -- Fields --

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "isDeleted")
	private boolean isDeleted;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "systemModStamp")
	private Date systemModStamp;

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "_hc_Lastop")
	private String _hc_Lastop;

	@JsonProperty(value = "_hc_Err")
	private String _hc_Err;

	// end

	// region -- Get set --

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String get_hc_Lastop() {
		return _hc_Lastop;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void set_hc_Lastop(String _hc_Lastop) {
		this._hc_Lastop = _hc_Lastop;
	}

	public String get_hc_Err() {
		return _hc_Err;
	}

	public void set_hc_Err(String _hc_Err) {
		this._hc_Err = _hc_Err;
	}

	// end

	// region -- Methods --

	public PortalRoleReq() {
	}

	// end
}