package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class PortalUserFilter {
	// region -- Fields --

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "firstName")
	private String firstName;

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "mobile")
	private String mobile;

	// end

	// region -- Get set --

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalUserFilter() {
		email = "";
		firstName = "";
		lastName = "";
		mobile = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static PortalUserFilter convert(Object o) {
		PortalUserFilter res = new PortalUserFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, PortalUserFilter.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return res;
	}

	// end
}