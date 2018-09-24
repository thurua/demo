package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ReasonDao;
import com.ifs.eportal.model.Reason;

@Service(value = "reasonService")
@Transactional
public class ReasonService {
	// region -- Fields --

	@Autowired
	private ReasonDao reasonDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public Reason getBy(int id) {
		Reason res = reasonDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<Reason> search() {
		List<Reason> res = reasonDao.search();
		return res;
	}

	// end
	public String save(Reason m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			// m.setActive(true);
			m.setDeleted(false);
			m.setCreatedDate(new Date());

			reasonDao.save(m);

		} else {
			Reason m1 = reasonDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = reasonDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				reasonDao.save(m1);
			}
		}

		return res;
	}

	public String delete(Reason m, Integer userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setDeleted(true);

			reasonDao.save(m);
		}

		return res;
	}
}