package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.ZConfig;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
public class SupplierFilter {
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
	public SupplierFilter() {
		client = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static SupplierFilter convert(Object o) {
		SupplierFilter res = new SupplierFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, SupplierFilter.class);
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