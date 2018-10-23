package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-19 (verified)
 *
 */
public class PortalUserAccessDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "uuId")
	private String uuId;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "loginOn")
	private Date loginOn;

	@JsonProperty(value = "logoutOn")
	private Date logoutOn;

	@JsonProperty(value = "lastAccessOn")
	private Date lastAccessOn;

	@JsonProperty(value = "userAgent")
	private String userAgent;

	@JsonProperty(value = "host")
	private String host;

	// end

	// region -- Get set --

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLoginOn() {
		return loginOn;
	}

	public void setLoginOn(Date loginOn) {
		this.loginOn = loginOn;
	}

	public Date getLogoutOn() {
		return logoutOn;
	}

	public void setLogoutOn(Date logoutOn) {
		this.logoutOn = logoutOn;
	}

	public Date getLastAccessOn() {
		return lastAccessOn;
	}

	public void setLastAccessOn(Date lastAccessOn) {
		this.lastAccessOn = lastAccessOn;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	// end

	/**
	 * Initialize
	 */
	public PortalUserAccessDto() {
		super();

		uuId = "";
		name = "";
		loginOn = null;
		logoutOn = null;
		lastAccessOn = null;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static PortalUserAccessDto convert(Object[] o) {
		PortalUserAccessDto res = new PortalUserAccessDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);
		res.setUuId((String) o[2]);
		res.setName((String) o[3]);
		res.setLoginOn((Date) o[4]);
		res.setLogoutOn((Date) o[5]);
		res.setLastAccessOn((Date) o[6]);
		res.setUserAgent((String) o[7]);
		res.setHost((String) o[8]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<PortalUserAccessDto> convert(List<Object[]> l) {
		List<PortalUserAccessDto> res = new ArrayList<PortalUserAccessDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}