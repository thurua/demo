package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ScheduleOfOfferDao;
import com.ifs.eportal.dto.ScheduleOfOfferDetailsDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.req.PagingReq;

@Service(value = "scheduleOfOfferService")
@Transactional
public class ScheduleOfOfferService {
	// region -- Fields --

	@Autowired
	private ScheduleOfOfferDao scheduleOfOfferDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(ScheduleOfOffer m) {
		scheduleOfOfferDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDto read(int id) {
		return scheduleOfOfferDao.getBy(id);
	}

	/**
	 * Read by
	 * 
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */
	public List<ScheduleOfOfferDto> read(String scheduleNo, String clientName) {
		return scheduleOfOfferDao.getBy(scheduleNo, clientName);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<ScheduleOfOfferDto> search(PagingReq req) {
		return scheduleOfOfferDao.search(req);
	}

	/**
	 * Get by
	 * 
	 * @param Id
	 * @return
	 */
	public ScheduleOfOfferDetailsDto getById(String Id) {
		return scheduleOfOfferDao.getById(Id);
	}

}