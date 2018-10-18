package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.ZConfig;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
public class PortalUserAccessFilter {
	// region -- Fields --

	@JsonProperty(value = "user")
	private String user;

	// end

	// region -- Get set --

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalUserAccessFilter() {
		user = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static PortalUserAccessFilter convert(Object o) {
		PortalUserAccessFilter res = new PortalUserAccessFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, PortalUserAccessFilter.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
		}

		return res;
	}

	// end
}