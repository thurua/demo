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
public class AttachmentFilter {
	// region -- Fields --

	@JsonProperty(value = "scheduleOfOffer")
	private String scheduleOfOffer;

	// end

	// region -- Get set --

	public String getScheduleOfOffer() {
		return scheduleOfOffer;
	}

	public void setScheduleOfOffer(String scheduleOfOffer) {
		this.scheduleOfOffer = scheduleOfOffer;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public AttachmentFilter() {
		scheduleOfOffer = "";
	}

	/**
	 * Initialize
	 * 
	 * @param scheduleOfOffer
	 */
	public AttachmentFilter(String scheduleOfOffer) {
		this.scheduleOfOffer = scheduleOfOffer;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static AttachmentFilter convert(Object o) {
		AttachmentFilter res = new AttachmentFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, AttachmentFilter.class);
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