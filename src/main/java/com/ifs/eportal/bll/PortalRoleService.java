package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PortalRoleDao;
import com.ifs.eportal.model.PortalRole;

@Service(value = "portalRoleService")
@Transactional
public class PortalRoleService {
	// region -- Fields --

	@Autowired
	private PortalRoleDao portalRoleDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalRole getBy(int id) {
		PortalRole res = portalRoleDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<PortalRole> search() {
		List<PortalRole> res = portalRoleDao.search();
		return res;
	}

	// end
	public String save(PortalRole m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			// m.setActive(true);
			m.setIsDeleted(false);
			m.setCreatedDate(new Date());

			portalRoleDao.save(m);

		} else {
			PortalRole m1 = portalRoleDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = portalRoleDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				portalRoleDao.save(m1);
			}
		}

		return res;
	}

	public String delete(PortalRole m, Integer userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setIsDeleted(true);

			portalRoleDao.save(m);
		}

		return res;
	}
}