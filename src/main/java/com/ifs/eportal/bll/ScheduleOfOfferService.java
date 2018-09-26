package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ScheduleOfOfferDao;
import com.ifs.eportal.model.ScheduleOfOffer;

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
	public ScheduleOfOffer getBy(int id) {
		ScheduleOfOffer res = scheduleOfOfferDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<ScheduleOfOffer> search() {
		List<ScheduleOfOffer> res = scheduleOfOfferDao.search();
		return res;
	}

	// end
	public String save(ScheduleOfOffer m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			// m.setActive(true);
			m.setDeleted(false);
			// m.setCreatedDate(new Date());

			scheduleOfOfferDao.save(m);

		} else {
			ScheduleOfOffer m1 = scheduleOfOfferDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = scheduleOfOfferDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				scheduleOfOfferDao.save(m1);
			}
		}

		return res;
	}

	public String delete(ScheduleOfOffer m) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setDeleted(true);

			scheduleOfOfferDao.save(m);
		}

		return res;
	}
}