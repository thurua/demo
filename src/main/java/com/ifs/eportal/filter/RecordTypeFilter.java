package com.ifs.eportal.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author HoanNguyen 2018-Oct-02
 *
 */
public class RecordTypeFilter {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "sfid")
	private String sfid;

	// end

	// region -- Get set --

	// end

	// region -- Methods --

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Initialize
	 */
	public RecordTypeFilter() {
		name = "";
		sfid = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static RecordTypeFilter convert(Object o) {
		RecordTypeFilter res = new RecordTypeFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, RecordTypeFilter.class);
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