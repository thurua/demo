package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAccountCustomerDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "activation_date__c")
	private String activationDate;

	@JsonProperty(value = "status__c")
	private String status;

	@JsonProperty(value = "customer__c")
	private String customer;

	@JsonProperty(value = "verification__c")
	private String verification;

	@JsonProperty(value = "verification_exceeding_invoice_amount__c")
	private String verificationExceedingInvoiceAmountc;

	@JsonProperty(value = "fciName")
	private String fciName;

	@JsonProperty(value = "ccName")
	private String ccName;

	// end

	// region -- Get set --

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(String activationDate) {
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

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getVerificationExceedingInvoiceAmountc() {
		return verificationExceedingInvoiceAmountc;
	}

	public void setVerificationExceedingInvoiceAmountc(String verificationExceedingInvoiceAmountc) {
		this.verificationExceedingInvoiceAmountc = verificationExceedingInvoiceAmountc;
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
		id = 0;
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
		res.setName((String) o[1]);
		res.setActivationDate((String) o[2]);
		res.setStatus((String) o[3]);
		res.setCustomer((String) o[4]);
		res.setVerification((String) o[5]);
		res.setVerificationExceedingInvoiceAmountc((String) o[6]);
		res.setFciName((String) o[7]);
		res.setCcName((String) o[8]);

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
