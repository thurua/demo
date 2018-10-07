package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.Utils;

public class ApprovedCustomerLimitFilter {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	// end

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ApprovedCustomerLimitFilter() {
		name = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ApprovedCustomerLimitFilter convert(Object o) {
		ApprovedCustomerLimitFilter res = new ApprovedCustomerLimitFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, ApprovedCustomerLimitFilter.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
		}

		return res;
	}

	// end
}