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

	@JsonProperty(value = "sfid")
	private String sfid;

	// end

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalRoleDto() {
		super();

		sfid = "";
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
		res.setName((String) o[1]);
		res.setSfid((String) o[2]);

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