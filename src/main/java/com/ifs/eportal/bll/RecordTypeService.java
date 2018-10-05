package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.RecordTypeDao;
import com.ifs.eportal.dto.RecordTypeDto;
import com.ifs.eportal.model.RecordType;
import com.ifs.eportal.req.PagingReq;

@Service(value = "recordTypeService")
@Transactional
public class RecordTypeService {
	// region -- Fields --

	@Autowired
	private RecordTypeDao recordTypeDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(RecordType m) {
		recordTypeDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public RecordTypeDto read(int id) {
		return recordTypeDao.getBy(id);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<RecordTypeDto> search(PagingReq req) {
		return recordTypeDao.search(req);
	}
}