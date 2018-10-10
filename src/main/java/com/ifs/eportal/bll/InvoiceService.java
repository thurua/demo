package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dal.InvoiceDao;
import com.ifs.eportal.dto.CustomDto;
import com.ifs.eportal.dto.InvoiceDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.model.Invoice;
import com.ifs.eportal.req.PagingReq;

@Service(value = "invoiceService")
@Transactional
public class InvoiceService {
	// region -- Fields --

	@Autowired
	private InvoiceDao invoiceDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(Invoice m) {
		invoiceDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public InvoiceDto read(int id) {
		return invoiceDao.getBy(id);
	}

	/**
	 * Read by
	 * 
	 * @param sfId
	 * @return
	 */
	public InvoiceDto read(String sfId) {
		return invoiceDao.getBy(sfId);
	}

	/**
	 * 
	 * @param l
	 * @param clientAccountId
	 * @return
	 */
	public List<InvoiceDto> read(List<LineItemDto> l, String clientAccountId) {
		List<String> names = Utils.getNames(l);
		return invoiceDao.getBy(names, clientAccountId);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<InvoiceDto> search(PagingReq req) {
		List<InvoiceDto> res = invoiceDao.search(req);
		return res;
	}

	/**
	 * 
	 * @param clientAccountId
	 * @return
	 */
	public CustomDto getAverage(String clientAccountId) {
		return invoiceDao.getAverage(clientAccountId);
	}

	/**
	 * 
	 * @param l
	 * @param clientAccountId
	 * @return
	 */
	public List<CustomDto> getOverdueOutstanding(List<LineItemDto> l, String clientAccountId) {
		List<String> names = Utils.getNames(l);
		return invoiceDao.getOverdueOutstanding(names, clientAccountId);
	}

	/**
	 * 
	 * @param l
	 * @param clientAccountId
	 * @return
	 */
	public List<CustomDto> getInvoiceAvg(List<LineItemDto> l, String clientAccountId) {
		List<String> names = Utils.getNames(l);
		return invoiceDao.getInvoiceAvg(names, clientAccountId);
	}

	/**
	 * 
	 * @param l
	 * @param clientAccountId
	 * @return
	 */
	public List<CustomDto> getDisputedOutstanding(List<LineItemDto> l, String clientAccountId) {
		List<String> names = Utils.getNames(l);
		return invoiceDao.getDisputedOutstanding(names, clientAccountId);
	}

	/**
	 * 
	 * @param l
	 * @param clientAccountId
	 * @return
	 */
	public List<CustomDto> getTotalOutstanding(List<LineItemDto> l, String clientAccountId) {
		List<String> names = Utils.getNames(l);
		return invoiceDao.getTotalOutstanding(names, clientAccountId);
	}

	/**
	 * 
	 * @param l
	 * @param clientAccountId
	 * @return
	 */
	public List<CustomDto> getTotalOutstandingAmount(List<LineItemDto> l, String clientAccountId) {
		List<String> names = Utils.getNames(l);
		return invoiceDao.getTotalOutstandingAmount(names, clientAccountId);
	}

	/**
	 * 
	 * @param l
	 * @param clientAccountId
	 * @param d
	 * @return
	 */
	public List<CustomDto> getCreditSumary(List<LineItemDto> l, String clientAccountId, Date d) {
		List<String> names = Utils.getNames(l);
		return invoiceDao.getCreditSumary(names, clientAccountId, d);
	}

	/**
	 * 
	 * @param l
	 * @param clientAccountId
	 * @param d
	 * @return
	 */
	public List<CustomDto> getInvoiceSumary(List<LineItemDto> l, String clientAccountId, Date d) {
		List<String> names = Utils.getNames(l);
		return invoiceDao.getInvoiceSumary(names, clientAccountId, d);
	}

	// end
}