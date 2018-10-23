package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAccountCustomerFactoringDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "activationDate")
	private Date activationDate;

	@JsonProperty(value = "status")
	private String status;

	// end

	// region -- Get set --

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountCustomerFactoringDto() {
		super();

		clientAccount = "";
		customer = "";
		activationDate = null;
		status = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ClientAccountCustomerFactoringDto convert(Object[] o) {
		ClientAccountCustomerFactoringDto res = new ClientAccountCustomerFactoringDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setClientAccount((String) o[2]);
		res.setCustomer((String) o[3]);
		res.setActivationDate((Date) o[4]);
		res.setStatus((String) o[5]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ClientAccountCustomerFactoringDto> convert(List<Object[]> l) {
		List<ClientAccountCustomerFactoringDto> res = new ArrayList<ClientAccountCustomerFactoringDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}
