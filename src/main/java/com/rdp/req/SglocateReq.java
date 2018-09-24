package com.rdp.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SglocateReq {
	// region -- Fields --

	@JsonProperty(value = "postcode")
	private String postcode;

	@JsonProperty(value = "block")
	private String block;

	@JsonProperty(value = "streetName")
	private String streetName;

	// end

	// region -- Get set --

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	// end

	// region -- Methods --

	public SglocateReq() {
	}

	// end
}