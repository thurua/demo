package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PortalUserAccessFilter {
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
	public PortalUserAccessFilter() {
		name = "";
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
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return res;
	}

	// end
}