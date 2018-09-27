package com.ifs.eportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAccountDto {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "id")
	private String id;

	// end

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// end

	// region -- Methods --

	public ClientAccountDto() {

	}

	// end
}