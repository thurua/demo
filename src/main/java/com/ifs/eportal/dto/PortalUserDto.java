package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class PortalUserDto extends BaseDto {
	// region -- Fields --

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

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "passwordHash")
	private String passwordHash;

	@JsonProperty(value = "passReminderToken")
	private String passReminderToken;

	@JsonProperty(value = "passReminderExpire")
	private Date passReminderExpire;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPassReminderToken() {
		return passReminderToken;
	}

	public void setPassReminderToken(String passReminderToken) {
		this.passReminderToken = passReminderToken;
	}

	public Date getPassReminderExpire() {
		return passReminderExpire;
	}

	public void setPassReminderExpire(Date passReminderExpire) {
		this.passReminderExpire = passReminderExpire;
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
	public PortalUserDto() {
		super();

		email = "";
		firstName = "";
		lastName = "";
		salutation = "";
		mobile = "";
		password = "";
		passwordHash = "";
		passReminderToken = "";
		passReminderExpire = null;
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
	public static PortalUserDto convert(Object[] o) {
		PortalUserDto res = new PortalUserDto();

		res.setId((Integer) o[0]);
		res.setEmail((String) o[1]);
		res.setFirstName((String) o[2]);
		res.setLastName((String) o[3]);
		res.setSalutation((String) o[4]);
		res.setMobile((String) o[5]);
		res.setPassword((String) o[6]);
		res.setPasswordHash((String) o[7]);
		res.setPassReminderToken((String) o[8]);
		res.setPassReminderExpire((Date) o[9]);
		res.setClientId((String) o[10]);
		res.setClientName((String) o[11]);
		res.setRoleName((String) o[12]);
		res.setCompanyName((String) o[13]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<PortalUserDto> convert(List<Object[]> l) {
		List<PortalUserDto> res = new ArrayList<PortalUserDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}