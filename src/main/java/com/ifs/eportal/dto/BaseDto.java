package com.ifs.eportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class BaseDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public BaseDto() {
		id = 0;
	}

	// end
}