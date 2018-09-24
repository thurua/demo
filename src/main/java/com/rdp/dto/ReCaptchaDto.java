package com.rdp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReCaptchaDto {
	// region -- Fields --

	@JsonProperty(value = "success")
	private boolean success;

	@JsonProperty(value = "challenge_ts")
	private String challenge_ts;

	@JsonProperty(value = "hostName")
	private String hostName;

	// end

	// region -- Get set --

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getChallenge_ts() {
		return challenge_ts;
	}

	public void setChallenge_ts(String challenge_ts) {
		this.challenge_ts = challenge_ts;
	}

	public String getHostname() {
		return hostName;
	}

	public void setHostname(String hostName) {
		this.hostName = hostName;
	}

	// end

	// region -- Methods --

	public ReCaptchaDto() {
	}

	// end
}