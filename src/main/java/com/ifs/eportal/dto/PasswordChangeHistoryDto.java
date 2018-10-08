package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author HoanNguyen 2018-Oct-3
 *
 */
public class PasswordChangeHistoryDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "usersfid")
	private String usersfid;

	@JsonProperty(value = "username")
	private String username;

	@JsonProperty(value = "changeby")
	private String changeby;

	// end

	// region -- Get set --

	public String getUsersfid() {
		return usersfid;
	}

	public void setUsersfid(String usersfid) {
		this.usersfid = usersfid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getChangeby() {
		return changeby;
	}

	public void setChangeby(String changeby) {
		this.changeby = changeby;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PasswordChangeHistoryDto() {
		super();

		usersfid = "";
		username = "";
		changeby = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static PasswordChangeHistoryDto convert(Object[] o) {
		PasswordChangeHistoryDto res = new PasswordChangeHistoryDto();

		res.setId((Integer) o[0]);
		res.setUsername((String) o[1]);
		res.setChangeby((String) o[2]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<PasswordChangeHistoryDto> convert(List<Object[]> l) {
		List<PasswordChangeHistoryDto> res = new ArrayList<PasswordChangeHistoryDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}