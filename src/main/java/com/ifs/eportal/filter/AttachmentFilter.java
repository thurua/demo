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
public class AttachmentFilter {
	// region -- Fields --

	@JsonProperty(value = "parentUuId")
	private String parentUuId;

	// end

	// region -- Get set --

	public String getParentUuId() {
		return parentUuId;
	}

	public void setParentUuId(String parentUuId) {
		this.parentUuId = parentUuId;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public AttachmentFilter() {
		parentUuId = "";
	}

	/**
	 * Initialize
	 * 
	 * @param parentUuId
	 */
	public AttachmentFilter(String parentUuId) {
		this.parentUuId = parentUuId;
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
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
		}

		return res;
	}

	// end
}