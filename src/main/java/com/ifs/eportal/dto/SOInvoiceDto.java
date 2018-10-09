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
public class SOInvoiceDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "customerFromExcel")
	private String customerFromExcel;

	@JsonProperty(value = "customer")
	private String customer;

	@JsonProperty(value = "customerBranch")
	private String customerBranch;

	@JsonProperty(value = "invoiceNo")
	private String invoiceNo;

	@JsonProperty(value = "invoiceDate")
	private Date invoiceDate;

	@JsonProperty(value = "creditPeriod")
	private Double creditPeriod;

	@JsonProperty(value = "invoiceAmount")
	private Double invoiceAmount;

	@JsonProperty(value = "po")
	private String po;

	@JsonProperty(value = "contract")
	private String contract;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "supplier")
	private String supplier;

	@JsonProperty(value = "supplierFromExcel")
	private String supplierFromExcel;

	@JsonProperty(value = "rejectReason")
	private String rejectReason;

	@JsonProperty(value = "currencyIsoCode")
	private String currencyIsoCode;

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

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Double getCreditPeriod() {
		return creditPeriod;
	}

	public void setCreditPeriod(Double creditPeriod) {
		this.creditPeriod = creditPeriod;
	}

	public Double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierFromExcel() {
		return supplierFromExcel;
	}

	public void setSupplierFromExcel(String supplierFromExcel) {
		this.supplierFromExcel = supplierFromExcel;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public SOInvoiceDto() {
		super();

		customerFromExcel = "";
		customer = "";
		customerBranch = "";
		invoiceNo = "";
		invoiceDate = null;
		creditPeriod = null;
		invoiceAmount = null;
		po = "";
		contract = "";
		status = "";
		supplier = "";
		supplierFromExcel = "";
		rejectReason = "";
		currencyIsoCode = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static SOInvoiceDto convert(Object[] o) {
		SOInvoiceDto res = new SOInvoiceDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setCustomerFromExcel((String) o[2]);
		res.setCustomer((String) o[3]);
		res.setCustomerBranch((String) o[4]);
		res.setInvoiceNo((String) o[5]);
		res.setInvoiceDate((Date) o[6]);
		res.setCreditPeriod((Double) o[7]);
		res.setInvoiceAmount((Double) o[8]);
		res.setPo((String) o[9]);
		res.setContract((String) o[10]);
		res.setStatus((String) o[11]);
		res.setSupplier((String) o[12]);
		res.setSupplierFromExcel((String) o[13]);
		res.setRejectReason((String) o[14]);
		res.setCurrencyIsoCode((String) o[15]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<SOInvoiceDto> convert(List<Object[]> l) {
		List<SOInvoiceDto> res = new ArrayList<SOInvoiceDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}