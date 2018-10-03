package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDto extends BaseDto {
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
	public AccountDto() {
		super();

		name = "";
		sfid = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static AccountDto convert(Object[] o) {
		AccountDto res = new AccountDto();

		res.setId((Integer) o[0]);
		res.setSfid((String) o[1]);
		res.setName((String) o[2]);
		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<AccountDto> convert(List<Object[]> l) {
		List<AccountDto> res = new ArrayList<AccountDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end

}