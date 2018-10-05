package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author TanNguyen 2018-Oct-05 (verified)
 *
 */
public class CurrencyTypeDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "sfid")
	private String sfid;

	@JsonProperty(value = "isocode")
	private String isoCode;

	// end

	// region -- Get set --

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public CurrencyTypeDto() {
		super();

		sfid = "";
		isoCode = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static CurrencyTypeDto convert(Object[] o) {
		CurrencyTypeDto res = new CurrencyTypeDto();

		res.setId((Integer) o[0]);
		res.setSfid((String) o[1]);
		res.setIsoCode((String) o[2]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<CurrencyTypeDto> convert(List<Object[]> l) {
		List<CurrencyTypeDto> res = new ArrayList<CurrencyTypeDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}