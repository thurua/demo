package com.ifs.eportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class PayloadDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "email")
	private String email;

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

	@JsonProperty(value = "clientName")
	private String clientName;

	@JsonProperty(value = "roleName")
	private String roleName;

	@JsonProperty(value = "companyName")
	private String companyName;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PayloadDto() {
		id = 0;
		email = "";
		firstName = "";
		lastName = "";
		salutation = "";
		mobile = "";
		clientId = "";
		clientName = "";
		roleName = "";
		companyName = "";
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
		res.setEmail(o.getEmail());
		res.setFirstName(o.getFirstName());
		res.setLastName(o.getLastName());
		res.setSalutation(o.getSalutation());
		res.setMobile(o.getMobile());
		res.setClientId(o.getClientId());
		res.setClientName(o.getClientName());
		res.setRoleName(o.getRoleName());
		res.setCompanyName(o.getCompanyName());

		return res;
	}

	// end
}