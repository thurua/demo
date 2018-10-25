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
public class SODropdownDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "title")
	private String title;

	@JsonProperty(value = "value")
	private String value;

	// end

	// region -- Get set --

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public SODropdownDto() {
		super();
		title ="";
		value ="";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static SODropdownDto convert(Object[] o) {
		SODropdownDto res = new SODropdownDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);
		res.setValue((String) o[2]);
		res.setTitle((String) o[3]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<SODropdownDto> convert(List<Object[]> l) {
		List<SODropdownDto> res = new ArrayList<SODropdownDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}