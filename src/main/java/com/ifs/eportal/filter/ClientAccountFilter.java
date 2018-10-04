package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
public class ClientAccountFilter {
	// region -- Fields --

	@JsonProperty(value = "client")
	private String client;

	// end

	// region -- Get set --

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountFilter() {
		client = "";
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