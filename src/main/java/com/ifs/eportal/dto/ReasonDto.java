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
	public ReasonDto() {
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
	public static ReasonDto convert(Object[] o) {
		ReasonDto res = new ReasonDto();

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
	public static List<ReasonDto> convert(List<Object[]> l) {
		List<ReasonDto> res = new ArrayList<ReasonDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}