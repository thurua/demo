package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author HoanNguyen 2018-Oct-1
 *
 */
public class PortalRoleDto extends BaseDto {
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
	public PortalRoleDto() {
		super();

		name = "";

	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static PortalRoleDto convert(Object[] o) {
		PortalRoleDto res = new PortalRoleDto();

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
	public static List<PortalRoleDto> convert(List<Object[]> l) {
		List<PortalRoleDto> res = new ArrayList<PortalRoleDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}