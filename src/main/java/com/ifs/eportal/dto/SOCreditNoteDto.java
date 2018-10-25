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
public class SOCreditNoteDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "customerFromExcel")
	private String customerFromExcel;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "customerBranch")
	private String customerBranch;

	@JsonProperty(value = "creditNo")
	private String creditNo;

	@JsonProperty(value = "creditDate")
	private Date creditDate;

	@JsonProperty(value = "creditAmount")
	private Double creditAmount;

	@JsonProperty(value = "appliedInvoiceNo")
	private String appliedInvoiceNo;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "applyCN")
	private Boolean applyCN;

	@JsonProperty(value = "unappliedReason")
	private String unappliedReason;

	@JsonProperty(value = "currencyIsoCode")
	private String currencyIsoCode;

	@JsonProperty(value = "uuId")
	private String uuId;

	@JsonProperty(value = "customerId")
	private String customerId;

	// end

	// region -- Get set --

	public String getCustomerFromExcel() {
		return customerFromExcel;
	}

	public void setCustomerFromExcel(String customerFromExcel) {
		this.customerFromExcel = customerFromExcel;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomerBranch() {
		return customerBranch;
	}

	public void setCustomerBranch(String customerBranch) {
		this.customerBranch = customerBranch;
	}

	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getAppliedInvoiceNo() {
		return appliedInvoiceNo;
	}

	public void setAppliedInvoiceNo(String appliedInvoiceNo) {
		this.appliedInvoiceNo = appliedInvoiceNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getApplyCN() {
		return applyCN;
	}

	public void setApplyCN(Boolean applyCN) {
		this.applyCN = applyCN;
	}

	public String getUnappliedReason() {
		return unappliedReason;
	}

	public void setUnappliedReason(String unappliedReason) {
		this.unappliedReason = unappliedReason;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public SOCreditNoteDto() {
		super();

		customerFromExcel = "";
		customer = "";
		customerBranch = "";
		creditNo = "";
		creditDate = null;
		creditAmount = null;
		appliedInvoiceNo = "";
		status = "";
		applyCN = null;
		unappliedReason = "";
		currencyIsoCode = "";
		uuId = "";
		customerId = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static SOCreditNoteDto convert(Object[] o) {
		SOCreditNoteDto res = new SOCreditNoteDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setCustomerFromExcel((String) o[2]);
		res.setCustomer((String) o[3]);
		res.setCustomerBranch((String) o[4]);
		res.setCreditNo((String) o[5]);
		res.setCreditDate((Date) o[6]);
		res.setCreditAmount((Double) o[7]);
		res.setAppliedInvoiceNo((String) o[8]);
		res.setStatus((String) o[9]);
		res.setApplyCN((Boolean) o[10]);
		res.setUnappliedReason((String) o[11]);
		res.setCurrencyIsoCode((String) o[12]);
		res.setUuId((String) o[13]);
		res.setCustomerId((String) o[14]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<SOCreditNoteDto> convert(List<Object[]> l) {
		List<SOCreditNoteDto> res = new ArrayList<SOCreditNoteDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}