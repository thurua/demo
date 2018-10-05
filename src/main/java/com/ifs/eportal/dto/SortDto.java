package com.ifs.eportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-28
 *
 */
public class SortDto {
	// region -- Fields --

	@JsonProperty(value = "field")
	private String field;

	@JsonProperty(value = "direction")
	private String direction;

	// end

	// region -- Get set --

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		if (!"ASC".equals(direction) && !"DESC".equals(direction)) {
			direction = "ASC";
		}
		this.direction = direction;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public SortDto() {
		field = "";
		direction = "";
	}

	/**
	 * Initialize
	 * 
	 * @param field
	 */
	public SortDto(String field) {
		this();

		this.field = field;
	}

	/**
	 * Initialize
	 * 
	 * @param field
	 * @param direction
	 */
	public SortDto(String field, String direction) {
		this.field = field;
		this.direction = direction;
	}

	// end
}