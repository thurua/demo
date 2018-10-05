package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.Utils;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
public class CreditNoteFilter {
	// region -- Fields --

	@JsonProperty(value = "status")
	private String status;

	// end

	// region -- Get set --

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public CreditNoteFilter() {
		status = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static CreditNoteFilter convert(Object o) {
		CreditNoteFilter res = new CreditNoteFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, CreditNoteFilter.class);
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