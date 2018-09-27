package com.ifs.eportal.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class PageableReq {
	// region -- Fields --

	@JsonProperty(value = "page")
	private int page;

	@JsonProperty(value = "size")
	private int size;

	@JsonProperty(value = "sort")
	private String sort;

	// end

	// region -- Get set --

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 0) {
			page = 1;
		}

		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if (size < 0) {
			size = 1;
		}

		this.size = size;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	// end

	// region -- Methods --

	public PageableReq() {
		page = 1;
		size = 1;
		sort = "ASC";
	}

	// end
}