package com.ifs.eportal.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author nlduong 2018-Otc-04
 *
 */
public class ScheduleOfOfferDetailsDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "scheduleDate")
	private Date scheduleDate;

	@JsonProperty(value = "scheduleStatus")
	private String scheduleStatus;

	@JsonProperty(value = "documentType")
	private String documentType;

	@JsonProperty(value = "currency")
	private String currency;

	@JsonProperty(value = "exchangeRate")
	private Double exchangeRate;

	@JsonProperty(value = "factorCode")
	private String factorCode;

	@JsonProperty(value = "totalNoInvoice")
	private BigInteger totalNoInvoice;

	@JsonProperty(value = "totalAmountInvoice")
	private Double totalAmountInvoice;

	@JsonProperty(value = "clientName")
	private String clientName;

	// end

	// region -- Get set --

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public BigInteger getTotalNoInvoice() {
		return totalNoInvoice;
	}

	public void setTotalNoInvoice(BigInteger totalNoInvoice) {
		this.totalNoInvoice = totalNoInvoice;
	}

	public Double getTotalAmountInvoice() {
		return totalAmountInvoice;
	}

	public void setTotalAmountInvoice(Double totalAmountInvoice) {
		this.totalAmountInvoice = totalAmountInvoice;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ScheduleOfOfferDetailsDto() {
		super();

		scheduleNo = "";
		clientAccount = "";
		scheduleDate = null;
		scheduleStatus = "";
		documentType = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ScheduleOfOfferDetailsDto convert(Object[] o) {
		ScheduleOfOfferDetailsDto res = new ScheduleOfOfferDetailsDto();

		res.setId((Integer) o[0]);
		res.setCurrency((String) o[1]);
		res.setScheduleNo((String) o[2]);
		res.setScheduleDate((Date) o[3]);
		res.setExchangeRate((Double) o[4]);
		res.setFactorCode((String) o[5]);
		res.setDocumentType((String) o[6]);
		res.setTotalNoInvoice((BigInteger) o[7]);
		res.setTotalAmountInvoice((Double) o[8]);
		res.setClientName((String) o[9]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ScheduleOfOfferDetailsDto> convert(List<Object[]> l) {
		List<ScheduleOfOfferDetailsDto> res = new ArrayList<ScheduleOfOfferDetailsDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}