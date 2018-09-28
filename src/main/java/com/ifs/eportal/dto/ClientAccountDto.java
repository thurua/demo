package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-28
 *
 */
public class ClientAccountDto extends BaseDto {
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
	public ClientAccountDto() {
		super();

		name = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ClientAccountDto convert(Object[] o) {
		ClientAccountDto res = new ClientAccountDto();

		res.setId((Integer) o[0]);
		res.setName((String) o[1]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ClientAccountDto> convert(List<Object[]> l) {
		List<ClientAccountDto> res = new ArrayList<ClientAccountDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}