package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ScheduleOfOfferDao;
import com.ifs.eportal.dto.ScheduleOfOfferDetailDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
@Service(value = "_scheduleOfOfferService")
@Transactional
public class ScheduleOfOfferService {
	// region -- Fields --

	@Autowired
	private ScheduleOfOfferDao _scheduleOfOfferDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(ScheduleOfOffer m) {
		_scheduleOfOfferDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDto read(int id) {
		ScheduleOfOfferDto res = _scheduleOfOfferDao.getBy(id);
		return res;
	}

	/**
	 * Read by
	 * 
	 * @param sfId
	 * @return
	 */
	public ScheduleOfOfferDetailDto read(String sfId) {
		ScheduleOfOfferDetailDto res = _scheduleOfOfferDao.getBy(sfId);
		return res;
	}

	/**
	 * Read by
	 * 
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */
	public List<ScheduleOfOfferDto> read(String scheduleNo, String clientName) {
		List<ScheduleOfOfferDto> res = _scheduleOfOfferDao.getBy(scheduleNo, clientName);
		return res;
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<ScheduleOfOfferDto> search(PagingReq req) {
		List<ScheduleOfOfferDto> res = _scheduleOfOfferDao.search(req);
		return res;
	}

	// end
}