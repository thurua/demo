package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
public class ScheduleOfOfferDetailDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "scheduleNo")
	private String scheduleNo;

	@JsonProperty(value = "scheduleDate")
	private Date scheduleDate;

	@JsonProperty(value = "exchangeRate")
	private Double exchangeRate;

	@JsonProperty(value = "factorCode")
	private String factorCode;

	@JsonProperty(value = "recordType")
	private String recordType;

	@JsonProperty(value = "total")
	private Double total;

	@JsonProperty(value = "totalAmount")
	private Double totalAmount;

	@JsonProperty(value = "clientName")
	private String clientName;

	@JsonProperty(value = "currencyIsoCode")
	private String currencyIsoCode;

	@JsonProperty(value = "clientAccount")
	private String clientAccount;

	@JsonProperty(value = "documentType")
	private String documentType;

	@JsonProperty(value = "portalStatus")
	private String portalStatus;

	@JsonProperty(value = "totalCN")
	private Double totalCN;

	@JsonProperty(value = "totalAmountCN")
	private Double totalAmountCN;

	List<SOCreditNoteDto> lstCreditNote;

	List<SOInvoiceDto> lstInvoice;

	List<AttachmentDto> lstAttachment;

	// end

	// region -- Get set --

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
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

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getPortalStatus() {
		return portalStatus;
	}

	public void setPortalStatus(String portalStatus) {
		this.portalStatus = portalStatus;
	}

	public Double getTotalCN() {
		return totalCN;
	}

	public void setTotalCN(Double totalCN) {
		this.totalCN = totalCN;
	}

	public Double getTotalAmountCN() {
		return totalAmountCN;
	}

	public void setTotalAmountCN(Double totalAmountCN) {
		this.totalAmountCN = totalAmountCN;
	}

	public List<SOCreditNoteDto> getLstCreditNote() {
		return lstCreditNote;
	}

	public void setLstCreditNote(List<SOCreditNoteDto> lstCreditNote) {
		this.lstCreditNote = lstCreditNote;
	}

	public List<SOInvoiceDto> getLstInvoice() {
		return lstInvoice;
	}

	public void setLstInvoice(List<SOInvoiceDto> lstInvoice) {
		this.lstInvoice = lstInvoice;
	}

	public List<AttachmentDto> getLstAttachment() {
		return lstAttachment;
	}

	public void setLstAttachment(List<AttachmentDto> lstAttachment) {
		this.lstAttachment = lstAttachment;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ScheduleOfOfferDetailDto() {
		super();

		scheduleNo = "";
		scheduleDate = null;
		exchangeRate = 0d;
		factorCode = "";
		recordType = "";
		total = 0d;
		totalAmount = 0d;
		totalCN = 0d;
		totalAmountCN = 0d;
		clientName = "";
		currencyIsoCode = "";
		clientAccount = "";
		portalStatus = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ScheduleOfOfferDetailDto convert(Object[] o, List<Object[]> lc, List<Object[]> li,
			List<Object[]> la) {
		ScheduleOfOfferDetailDto res = new ScheduleOfOfferDetailDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setScheduleNo((String) o[2]);
		res.setScheduleDate((Date) o[3]);
		res.setExchangeRate((Double) o[4]);
		res.setFactorCode((String) o[5]);
		res.setRecordType((String) o[6]);
		res.setTotal((Double) o[7]);
		res.setTotalAmount((Double) o[8]);
		res.setClientName((String) o[9]);
		res.setCurrencyIsoCode((String) o[10]);
		res.setClientAccount((String) o[11]);
		res.setDocumentType((String) o[12]);
		res.setPortalStatus((String) o[13]);
		res.setTotalCN((Double) o[14]);
		res.setTotalAmountCN((Double) o[15]);
		res.setLstCreditNote(SOCreditNoteDto.convert(lc));
		res.setLstInvoice(SOInvoiceDto.convert(li));
		res.setLstAttachment(AttachmentDto.convert(la));

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ScheduleOfOfferDetailDto> convert(List<Object[]> l) {
		List<ScheduleOfOfferDetailDto> res = new ArrayList<ScheduleOfOfferDetailDto>();

		for (Object[] o : l) {
			res.add(convert(o, null, null, null));
		}

		return res;
	}

	// end
}