package com.ifs.eportal.bll;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.FundRequestDao;
import com.ifs.eportal.dto.FundRequestDto;
import com.ifs.eportal.model.FundRequest;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author VanPhan 2018-Oct-25
 *
 */
@Service(value = "fundRequestService")
@Transactional
public class FundRequestService {
	// region -- Fields --

	@Autowired
	private FundRequestDao fundRequestDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(FundRequest m) {
		fundRequestDao.create(m);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<FundRequestDto> search(PagingReq req) {
		return fundRequestDao.search(req);
	}
}
