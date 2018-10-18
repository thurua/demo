package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchDto extends BaseDto {

	// region -- Fields --

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "name")
	private String name;

	// end

	// region -- Get set --

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// end

	// region -- Methods --

	public BranchDto() {
		super();

		name = "";
	}

	public static BranchDto convert(Object[] o) {
		BranchDto res = new BranchDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);
		res.setCode((String) o[2]);
		res.setName((String) o[3]);

		return res;
	}

	public static List<BranchDto> convert(List<Object[]> l) {
		List<BranchDto> res = new ArrayList<BranchDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}