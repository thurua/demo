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
public class CreditNoteDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "customerBranch")
	private String customerBranch;

	@JsonProperty(value = "customerFromExcel")
	private String customerFromExcel;

	@JsonProperty(value = "currencyIsoCode")
	private String currencyIsoCode;

	@JsonProperty(value = "clientRemarks")
	private String clientRemarks;

	@JsonProperty(value = "creditAmount")
	private Double creditAmount;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "scheduleOfOffer")
	private String scheduleOfOffer;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "creditNoteDate")
	private Date creditNoteDate;

	@JsonProperty(value = "status")
	private String status;

	// end

	// region -- Get set --

	public String getCustomerBranch() {
		return customerBranch;
	}

	public void setCustomerBranch(String customerBranch) {
		this.customerBranch = customerBranch;
	}

	public String getCustomerFromExcel() {
		return customerFromExcel;
	}

	public void setCustomerFromExcel(String customerFromExcel) {
		this.customerFromExcel = customerFromExcel;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getClientRemarks() {
		return clientRemarks;
	}

	public void setClientRemarks(String clientRemarks) {
		this.clientRemarks = clientRemarks;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getScheduleOfOffer() {
		return scheduleOfOffer;
	}

	public void setScheduleOfOffer(String scheduleOfOffer) {
		this.scheduleOfOffer = scheduleOfOffer;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreditNoteDate() {
		return creditNoteDate;
	}

	public void setCreditNoteDate(Date creditNoteDate) {
		this.creditNoteDate = creditNoteDate;
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
	public CreditNoteDto() {
		super();

		customerBranch = "";
		customerFromExcel = "";
		currencyIsoCode = "";
		clientRemarks = "";
		creditAmount = null;
		customer = "";
		scheduleOfOffer = "";
		clientAccount = "";
		name = "";
		creditNoteDate = null;
		status = "";

	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static CreditNoteDto convert(Object[] o) {
		CreditNoteDto res = new CreditNoteDto();

		res.setId((Integer) o[0]);
		res.setCustomerBranch((String) o[1]);
		res.setCustomerFromExcel((String) o[2]);
		res.setCurrencyIsoCode((String) o[3]);
		res.setClientRemarks((String) o[4]);
		res.setCreditAmount((Double) o[5]);
		res.setCustomer((String) o[6]);
		res.setScheduleOfOffer((String) o[7]);
		res.setClientAccount((String) o[8]);
		res.setName((String) o[9]);
		res.setCreditNoteDate((Date) o[10]);
		res.setStatus((String) o[11]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<CreditNoteDto> convert(List<Object[]> l) {
		List<CreditNoteDto> res = new ArrayList<CreditNoteDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}