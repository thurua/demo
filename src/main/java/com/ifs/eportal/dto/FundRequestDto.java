package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FundRequestDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "fundRequestNo")
	private String fundRequestNo;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "createBy")
	private String createBy;

	@JsonProperty(value = "createdDate")
	private Date createdDate;

	// end

	// region -- Get set --

	public String getFundRequestNo() {
		return fundRequestNo;
	}

	public void setFundRequestNo(String fundRequestNo) {
		this.fundRequestNo = fundRequestNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FundRequestDto() {
		fundRequestNo = "";
		type = "";
		status = "";
		createBy = "";
		createdDate = null;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static FundRequestDto convert(Object[] o) {
		FundRequestDto res = new FundRequestDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setFundRequestNo((String) o[2]);
		res.setType((String) o[3]);
		res.setStatus((String) o[4]);
		// res.setCreateBy((String) o[5]);
		res.setCreatedDate((Date) o[5]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<FundRequestDto> convert(List<Object[]> l) {
		List<FundRequestDto> res = new ArrayList<FundRequestDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}