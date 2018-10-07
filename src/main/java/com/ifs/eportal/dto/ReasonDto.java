package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author HoanNguyen 2018-Oct-1
 *
 */
public class ReasonDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "reason")
	private String reason;

	@JsonProperty(value = "date")
	private Date date;

	@JsonProperty(value = "amount")
	private Double amount;

	// end

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ReasonDto() {
		super();

		sfid = "";
		name = "";
		reason = "";
		date = null;
		amount = null;

	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ReasonDto convert(Object[] o) {
		ReasonDto res = new ReasonDto();

		res.setReason((String) o[0]);
		res.setDate((Date) o[1]);
		res.setAmount((Double) o[2]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ReasonDto> convert(List<Object[]> l) {
		List<ReasonDto> res = new ArrayList<ReasonDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}