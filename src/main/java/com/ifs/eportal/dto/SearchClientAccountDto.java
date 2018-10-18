package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author NganHo 2018-Oct-16
 *
 */
public class SearchClientAccountDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "productTypeName")
	private String productTypeName;

	@JsonProperty(value = "activatedOn")
	private Date activatedOn;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "recordType")
	private String recordType;

	// end

	// region -- Get set --

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Date getActivatedOn() {
		return activatedOn;
	}

	public void setActivatedOn(Date activatedOn) {
		this.activatedOn = activatedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public SearchClientAccountDto() {
		super();

		clientAccount = "";
		productTypeName = "";
		activatedOn = null;
		status = "";
		recordType = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static SearchClientAccountDto convert(Object[] o) {
		SearchClientAccountDto res = new SearchClientAccountDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setClientAccount((String) o[2]);
		res.setProductTypeName((String) o[3]);
		res.setActivatedOn((Date) o[4]);
		res.setStatus((String) o[5]);
		res.setRecordType((String) o[6]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<SearchClientAccountDto> convert(List<Object[]> l) {
		List<SearchClientAccountDto> res = new ArrayList<SearchClientAccountDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}