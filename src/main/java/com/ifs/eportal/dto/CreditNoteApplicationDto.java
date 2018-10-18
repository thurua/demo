package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditNoteApplicationDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "invoice")
	private String invoice;

	@JsonProperty(value = "creditNote")
	private String creditNote;

	@JsonProperty(value = "amount")
	private String amount;

	@JsonProperty(value = "isReversed")
	private String isReversed;

	// end

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(String creditNote) {
		this.creditNote = creditNote;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIsReversed() {
		return isReversed;
	}

	public void setIsReversed(String isReversed) {
		this.isReversed = isReversed;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public CreditNoteApplicationDto() {
		super();

		name = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static CreditNoteApplicationDto convert(Object[] o) {
		CreditNoteApplicationDto res = new CreditNoteApplicationDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);
		res.setName((String) o[2]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<CreditNoteApplicationDto> convert(List<Object[]> l) {
		List<CreditNoteApplicationDto> res = new ArrayList<CreditNoteApplicationDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}