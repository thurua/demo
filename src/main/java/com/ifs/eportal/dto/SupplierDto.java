package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierDto extends BaseDto {
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
	public SupplierDto() {
		super();

		name = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static SupplierDto convert(Object[] o) {
		SupplierDto res = new SupplierDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);
		res.setName((String) o[2]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<SupplierDto> convert(List<Object[]> l) {
		List<SupplierDto> res = new ArrayList<SupplierDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}
