package com.ifs.eportal.req;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifs.eportal.dto.SortDto;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
public class PagingReq {
	// region -- Fields --

	@JsonProperty(value = "page")
	private int page;

	@JsonProperty(value = "size")
	private int size;

	@JsonProperty(value = "sort")
	private List<SortDto> sort;

	@JsonProperty(value = "filter")
	private Object filter;

	@JsonProperty(value = "total")
	private long total;

	@JsonProperty(value = "paging")
	private boolean paging;

	// end

	// region -- Get set --

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			page = 1;
		}

		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if (size < 1) {
			size = 1;
		}

		this.size = size;
	}

	public List<SortDto> getSort() {
		return sort;
	}

	public void setSort(List<SortDto> sort) {
		this.sort = sort;
	}

	public Object getFilter() {
		return filter;
	}

	public void setFilter(Object filter) {
		this.filter = filter;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public boolean isPaging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}

	// end

	// region -- Methods --

	public PagingReq() {
		page = 1;
		size = 1;
		sort = new ArrayList<SortDto>();
		filter = null;
		total = 0;
		paging = true;
	}

	// end
}