package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.CreditNoteDao;
import com.ifs.eportal.dto.CreditNoteDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.req.PagingReq;

@Service(value = "creditNoteService")
@Transactional
public class CreditNoteService {
	// region -- Fields --

	@Autowired
	private CreditNoteDao creditNoteDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(CreditNote m) {
		creditNoteDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public CreditNoteDto read(int id) {
		return creditNoteDao.getBy(id);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<CreditNoteDto> search(PagingReq req) {
		return creditNoteDao.search(req);
	}
}