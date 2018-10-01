package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortalUserAccessDto extends BaseDto {

	// region -- Fields --

	@JsonProperty(value = "loginOn")
	private Date loginOn;

	@JsonProperty(value = "logilogoutOnnOn")
	private Date logoutOn;

	@JsonProperty(value = "otp")
	private String otp;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	// end

	// region -- Get set --

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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	// end

	/**
	 * Initialize
	 */
	public PortalUserAccessDto() {
		super();

		loginOn = null;
		logoutOn = null;
		otp = "";
		name = "";
		createdDate = null;
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
		res.setName((String) o[1]);

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