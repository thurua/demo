package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
public class ClientAccountDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "activatedOn")
	private Date activatedOn;

	@JsonProperty(value = "accountType")
	private String accountType;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "factoringType")
	private String factoringType;

	@JsonProperty(value = "programName")
	private String programName;

	@JsonProperty(value = "verification")
	private Double verification;

	@JsonProperty(value = "fciCountry")
	private String fciCountry;

	@JsonProperty(value = "verificationExceedingInvoiceAmount")
	private Double verificationExceedingInvoiceAmount;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "recordTypeName")
	private String recordTypeName;

	@JsonProperty(value = "fciName")
	private String fciName;

	// end

	// region -- Get set --

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public Date getActivatedOn() {
		return activatedOn;
	}

	public void setActivatedOn(Date activatedOn) {
		this.activatedOn = activatedOn;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getFactoringType() {
		return factoringType;
	}

	public void setFactoringType(String factoringType) {
		this.factoringType = factoringType;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Double getVerification() {
		return verification;
	}

	public void setVerification(Double verification) {
		this.verification = verification;
	}

	public String getFciCountry() {
		return fciCountry;
	}

	public void setFciCountry(String fciCountry) {
		this.fciCountry = fciCountry;
	}

	public Double getVerificationExceedingInvoiceAmount() {
		return verificationExceedingInvoiceAmount;
	}

	public void setVerificationExceedingInvoiceAmount(Double verificationExceedingInvoiceAmount) {
		this.verificationExceedingInvoiceAmount = verificationExceedingInvoiceAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecordTypeName() {
		return recordTypeName;
	}

	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}

	public String getFciName() {
		return fciName;
	}

	public void setFciName(String fciName) {
		this.fciName = fciName;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountDto() {
		super();

		sfid = "";
		activatedOn = null;
		accountType = "";
		clientAccount = "";
		factoringType = "";
		programName = "";
		verification = null;
		fciCountry = "";
		verificationExceedingInvoiceAmount = null;
		status = "";
		recordTypeName = "";
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
		res.setSfid((String) o[1]);
		res.setActivatedOn((Date) o[2]);
		res.setAccountType((String) o[3]);
		res.setClientAccount((String) o[4]);
		res.setFactoringType((String) o[5]);
		res.setProgramName((String) o[6]);
		res.setVerification((Double) o[7]);
		res.setFciCountry((String) o[8]);
		res.setVerificationExceedingInvoiceAmount((Double) o[9]);
		res.setStatus((String) o[10]);
		res.setRecordTypeName((String) o[11]);
		res.setFciName((String) o[12]);

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