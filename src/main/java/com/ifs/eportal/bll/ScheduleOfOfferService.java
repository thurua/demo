package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ScheduleOfOfferDao;
import com.ifs.eportal.dto.ExcelDto;
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
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDto getBy(int id) {
		return scheduleOfOfferDao.getBy(id);

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
	 * Save
	 * 
	 * @param req
	 * @return
	 */
	public String save(ExcelDto req) {
		String res = "";

		// Get data
		String type = req.getClient();

		// Handle

		ScheduleOfOffer m = new ScheduleOfOffer();
		scheduleOfOfferDao.create(m);
		return res;
	}
}