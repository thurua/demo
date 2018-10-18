package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author HoanNguyen 2018-Oct-1
 *
 */
public class ReasonDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "reason")
	private String reason;

	@JsonProperty(value = "recordType")
	private String recordType;

	// end

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ReasonDto() {
		super();

		name = "";
		reason = "";
		recordType = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ReasonDto convert(Object[] o) {
		ReasonDto res = new ReasonDto();

		res.setReason((String) o[0]);
		res.setRecordType((String) o[1]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ReasonDto> convert(List<Object[]> l) {
		List<ReasonDto> res = new ArrayList<ReasonDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}