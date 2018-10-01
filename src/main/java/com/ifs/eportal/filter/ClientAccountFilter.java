package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author HoanNguyen 2018-Sep-28
 *
 */
public class ClientAccountFilter {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	// end

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setStatus(String name) {
		this.name = name;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountFilter() {

		name = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ClientAccountFilter convert(Object o) {
		ClientAccountFilter res = new ClientAccountFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, ClientAccountFilter.class);
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