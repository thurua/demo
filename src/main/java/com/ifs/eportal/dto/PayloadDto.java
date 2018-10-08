package com.ifs.eportal.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.common.Utils;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class PayloadDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "userId")
	private String userId;

	@JsonProperty(value = "firstName")
	private String firstName;

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "salutation")
	private String salutation;

	@JsonProperty(value = "mobile")
	private String mobile;

	@JsonProperty(value = "clientId")
	private String clientId;

	@JsonProperty(value = "roleName")
	private String roleName;

	@JsonProperty(value = "clientName")
	private String clientName;

	@JsonProperty(value = "accessRights")
	private List<String> accessRights;

	// end

	// region -- Get set --

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<String> getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(List<String> accessRights) {
		this.accessRights = accessRights;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PayloadDto() {
		super();

		userId = "";
		firstName = "";
		lastName = "";
		salutation = "";
		mobile = "";
		clientId = "";
		roleName = "";
		clientName = "";
		accessRights = new ArrayList<String>();
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static PayloadDto convert(PortalUserDto o) {
		PayloadDto res = new PayloadDto();

		res.setId(o.getId());
		res.setSfId(o.getSfId());

		res.setUserId(o.getUserId());
		res.setFirstName(o.getFirstName());
		res.setLastName(o.getLastName());
		res.setSalutation(o.getSalutation());
		res.setMobile(o.getMobile());
		res.setClientId(o.getClientId());
		res.setRoleName(o.getRoleName());
		res.setClientName(o.getClientName());

		List<String> t;
		t = Arrays.asList(o.getAccessRights().split(";"));
		res.setAccessRights(t);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static PayloadDto convert(Object o) {
		PayloadDto res = new PayloadDto();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, PayloadDto.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
		}

		return res;
	}

	// end
}