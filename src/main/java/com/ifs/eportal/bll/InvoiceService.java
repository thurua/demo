package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.InvoiceDao;
import com.ifs.eportal.dto.InvoiceDto;
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
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<InvoiceDto> search(PagingReq req) {
		return invoiceDao.search(req);
	}
}