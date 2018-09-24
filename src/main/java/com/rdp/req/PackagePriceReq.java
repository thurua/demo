package com.rdp.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackagePriceReq {
	// region -- Fields --

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "duration")
	private String duration;

	@JsonProperty(value = "isPromo")
	private Boolean isPromo;

	// end

	// region -- Get set --

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Boolean getIsPromo() {
		return isPromo;
	}

	public void setIsPromo(Boolean isPromo) {
		this.isPromo = isPromo;
	}

	// end

	// region -- Methods --

	public PackagePriceReq() {
	}

	// end
}