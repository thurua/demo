package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PortalUserDao;
import com.ifs.eportal.model.PortalUser;

@Service(value = "portalUserService")
@Transactional
public class PortalUserService {
	// region -- Fields --

	@Autowired
	private PortalUserDao portalUserDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUser getBy(int id) {
		PortalUser res = portalUserDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<PortalUser> search() {
		List<PortalUser> res = portalUserDao.search();
		return res;
	}

	// end
	public String save(PortalUser m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			m.setActive(true);
			m.setDeleted(false);
			m.setCreatedDate(new Date());

			portalUserDao.save(m);

		} else {
			PortalUser m1 = portalUserDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = portalUserDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				portalUserDao.save(m1);
			}
		}

		return res;
	}

	public String delete(PortalUser m, Integer userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setDeleted(true);

			portalUserDao.save(m);
		}

		return res;
	}
}