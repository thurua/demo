package com.ifs.eportal.dto;

import java.util.ArrayList;
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
		res.setSfid((String) o[1]);
		res.setClientAccount((String) o[2]);
		res.setClient((String) o[3]);

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