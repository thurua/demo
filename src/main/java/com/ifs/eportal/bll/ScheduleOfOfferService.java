package com.ifs.eportal.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ScheduleOfOfferDao;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.SheduleOfOfferDto;
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
	/**
	 * Search schedule Of Offer
	 * 
	 * @return
	 */
	public List<SheduleOfOfferDto> search(String client,String clientAccount,String status) {
		List<SheduleOfOfferDto> res = new ArrayList<>();
		List<Object[]> t = scheduleOfOfferDao.search(client,clientAccount,status);
		for (Object[] item : t) {
			SheduleOfOfferDto t1 = new SheduleOfOfferDto();
			t1.setScheduleNo(item[0].toString());
			t1.setClientAccount(item[1].toString());
			t1.setScheduleDate(item[2].toString());
			t1.setDocumentType(item[3].toString());
			t1.setScheduleStatus(item[4].toString());
			t1.setCreatedDate(item[5].toString());			
			res.add(t1);
		}
		return res;
	}

}