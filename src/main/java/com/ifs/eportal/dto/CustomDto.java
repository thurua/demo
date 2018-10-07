package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomDto {
	// region -- Fields --

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "value")
	private double value;

	// end

	// region -- Get set --
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	// end

	// region -- Methods --

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static CustomDto convert(Object[] o) {
		CustomDto res = new CustomDto();

		res.setCode((String) o[0]);
		res.setValue((double) o[1]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<CustomDto> convert(List<Object[]> l) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}
