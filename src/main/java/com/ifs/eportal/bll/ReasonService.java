package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ReasonDao;
import com.ifs.eportal.dto.ReasonDto;
import com.ifs.eportal.model.Reason;
import com.ifs.eportal.req.PagingReq;

@Service(value = "reasonService")
@Transactional
public class ReasonService {
	// region -- Fields --

	@Autowired
	private ReasonDao reasonDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(Reason m) {
		reasonDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public ReasonDto read(int id) {
		return reasonDao.getBy(id);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<ReasonDto> search(PagingReq req) {
		return reasonDao.search(req);
	}
}