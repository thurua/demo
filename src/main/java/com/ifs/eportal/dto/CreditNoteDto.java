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

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonProperty(value = "accountName")
	private String accountName;

	@JsonProperty(value = "appliedInvoice")
	private String appliedInvoice;

	@JsonProperty(value = "opsRemarks")
	private String opsRemarks;

	@JsonProperty(value = "outstandingAmount")
	private Double outstandingAmount;

	@JsonProperty(value = "clientAccountNo")
	private String clientAccountNo;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@JsonProperty(value = "createdBy")
	private String createdBy;

	@JsonProperty(value = "uuId")
	private String uuId;

	@JsonProperty(value = "totalAppliedCnAmount")
	private String totalAppliedCnAmount;

	@JsonProperty(value = "reassignedAmount")
	private String reassignedAmount;

	@JsonProperty(value = "reversedAmount")
	private String reversedAmount;

	@JsonProperty(value = "invoiceAppliedAmount")
	private String invoiceAppliedAmount;

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

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAppliedInvoice() {
		return appliedInvoice;
	}

	public void setAppliedInvoice(String appliedInvoice) {
		this.appliedInvoice = appliedInvoice;
	}

	public String getOpsRemarks() {
		return opsRemarks;
	}

	public void setOpsRemarks(String opsRemarks) {
		this.opsRemarks = opsRemarks;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public String getClientAccountNo() {
		return clientAccountNo;
	}

	public void setClientAccountNo(String clientAccountNo) {
		this.clientAccountNo = clientAccountNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getTotalAppliedCnAmount() {
		return totalAppliedCnAmount;
	}

	public void setTotalAppliedCnAmount(String totalAppliedCnAmount) {
		this.totalAppliedCnAmount = totalAppliedCnAmount;
	}

	public String getReassignedAmount() {
		return reassignedAmount;
	}

	public void setReassignedAmount(String reassignedAmount) {
		this.reassignedAmount = reassignedAmount;
	}

	public String getReversedAmount() {
		return reversedAmount;
	}

	public void setReversedAmount(String reversedAmount) {
		this.reversedAmount = reversedAmount;
	}

	public String getInvoiceAppliedAmount() {
		return invoiceAppliedAmount;
	}

	public void setInvoiceAppliedAmount(String invoiceAppliedAmount) {
		this.invoiceAppliedAmount = invoiceAppliedAmount;
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
		scheduleNo = "";
		accountName = "";
		appliedInvoice = "";
		opsRemarks = "";
		outstandingAmount = null;
		clientAccountNo = "";
		createdDate = null;
		createdBy = "";
		uuId = "";

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
		res.setSfId((String) o[1]);

		res.setCustomerBranch((String) o[2]);
		res.setCustomerFromExcel((String) o[3]);
		res.setCurrencyIsoCode((String) o[4]);
		res.setClientRemarks((String) o[5]);
		res.setCreditAmount((Double) o[6]);
		res.setCustomer((String) o[7]);
		res.setScheduleOfOffer((String) o[8]);
		res.setClientAccount((String) o[9]);
		res.setName((String) o[10]);
		res.setCreditNoteDate((Date) o[11]);
		res.setStatus((String) o[12]);
		res.setScheduleNo((String) o[13]);
		res.setAccountName((String) o[14]);
		res.setAppliedInvoice((String) o[15]);
		res.setOpsRemarks((String) o[16]);
		res.setOutstandingAmount((Double) o[17]);
		res.setClientAccountNo((String) o[18]);
		res.setCreatedDate((Date) o[19]);
		res.setCreatedBy((String) o[20]);
		res.setUuId((String) o[21]);

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