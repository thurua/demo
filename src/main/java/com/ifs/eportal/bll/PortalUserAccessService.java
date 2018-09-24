package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PortalUserAccessDao;
import com.ifs.eportal.model.PortalUserAccess;

@Service(value = "portalUserAccessService")
@Transactional
public class PortalUserAccessService {
	// region -- Fields --

	@Autowired
	private PortalUserAccessDao portalUserAccessDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserAccess getBy(int id) {
		PortalUserAccess res = portalUserAccessDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<PortalUserAccess> search() {
		List<PortalUserAccess> res = portalUserAccessDao.search();
		return res;
	}

	// end
	public String save(PortalUserAccess m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			// m.setActive(true);
			m.setDeleted(false);
			m.setCreatedDate(new Date());

			portalUserAccessDao.save(m);

		} else {
			PortalUserAccess m1 = portalUserAccessDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = portalUserAccessDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				portalUserAccessDao.save(m1);
			}
		}

		return res;
	}

	public String delete(PortalUserAccess m, Integer userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setDeleted(true);

			portalUserAccessDao.save(m);
		}

		return res;
	}
}