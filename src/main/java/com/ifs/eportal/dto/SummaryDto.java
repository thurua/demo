package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryDto {
	// region -- Fields --

	@JsonProperty(value = "month")
	private Integer month;

	@JsonProperty(value = "year")
	private Integer year;

	@JsonProperty(value = "amount")
	private Double amount;

	// end

	// region -- Get set --

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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
	public SummaryDto() {
		month = 0;
		year = 0;
		amount = 0d;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static SummaryDto convert(Object[] o) {
		SummaryDto res = new SummaryDto();

		res.setMonth((Integer) o[0]);
		res.setYear((Integer) o[1]);
		res.setAmount((Double) o[2]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<SummaryDto> convert(List<Object[]> l) {
		List<SummaryDto> res = new ArrayList<SummaryDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}