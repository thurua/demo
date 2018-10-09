package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAccountCustomerDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "activationDate")
	private Date activationDate;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "verification")
	private Float verification;

	@JsonProperty(value = "verificationExceedingInvoiceAmount")
	private Float verificationExceedingInvoiceAmount;

	@JsonProperty(value = "fciName")
	private String fciName;

	@JsonProperty(value = "ccName")
	private String ccName;

	// end

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Float getVerification() {
		return verification;
	}

	public void setVerification(Float verification) {
		this.verification = verification;
	}

	public Float getVerificationExceedingInvoiceAmount() {
		return verificationExceedingInvoiceAmount;
	}

	public void setVerificationExceedingInvoiceAmount(Float verificationExceedingInvoiceAmount) {
		this.verificationExceedingInvoiceAmount = verificationExceedingInvoiceAmount;
	}

	public String getFciName() {
		return fciName;
	}

	public void setFciName(String fciName) {
		this.fciName = fciName;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountCustomerDto() {
		super();

		name = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ClientAccountCustomerDto convert(Object[] o) {
		ClientAccountCustomerDto res = new ClientAccountCustomerDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);
		res.setName((String) o[2]);
		res.setActivationDate((Date) o[3]);
		res.setStatus((String) o[4]);
		res.setCustomer((String) o[5]);
		res.setVerification((Float) o[6]);
		res.setVerificationExceedingInvoiceAmount((Float) o[7]);
		res.setFciName((String) o[8]);
		res.setCcName((String) o[9]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ClientAccountCustomerDto> convert(List<Object[]> l) {
		List<ClientAccountCustomerDto> res = new ArrayList<ClientAccountCustomerDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}
