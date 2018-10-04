package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-28
 *
 */
public class ClientAccountDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "client")
	private String client;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "activatedOn")
	private Date activatedOn;

	@JsonProperty(value = "fciCountry")
	private String fciCountry;

	@JsonProperty(value = "factoringType")
	private String factoringType;

	@JsonProperty(value = "accountType")
	private String accountType;

	@JsonProperty(value = "programName")
	private String programName;

	@JsonProperty(value = "verification")
	private String verification;

	@JsonProperty(value = "verificationExceedingInvoiceAmount")
	private String verificationExceedingInvoiceAmount;
	// end

	// region -- Get set --

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getActivatedOn() {
		return activatedOn;
	}

	public void setActivatedOn(Date activatedOn) {
		this.activatedOn = activatedOn;
	}

	public String getFciCountry() {
		return fciCountry;
	}

	public void setFciCountry(String fciCountry) {
		this.fciCountry = fciCountry;
	}

	public String getFactoringType() {
		return factoringType;
	}

	public void setFactoringType(String factoringType) {
		this.factoringType = factoringType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getVerificationExceedingInvoiceAmount() {
		return verificationExceedingInvoiceAmount;
	}

	public void setVerificationExceedingInvoiceAmount(String verificationExceedingInvoiceAmount) {
		this.verificationExceedingInvoiceAmount = verificationExceedingInvoiceAmount;
	}
	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountDto() {
		super();

		sfid = "";
		clientAccount = "";
		client = "";

	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ClientAccountDto convert(Object[] o) {
		ClientAccountDto res = new ClientAccountDto();

		res.setId((Integer) o[0]);
		res.setSfid((String) o[10]);
		res.setClientAccount((String) o[1]);
		res.setClient((String) o[11]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ClientAccountDto> convert(List<Object[]> l) {
		List<ClientAccountDto> res = new ArrayList<ClientAccountDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}