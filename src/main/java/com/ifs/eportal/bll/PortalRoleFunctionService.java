package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PortalRoleFunctionDao;
import com.ifs.eportal.model.PortalRoleFunction;

@Service(value = "portalRoleFunctionService")
@Transactional
public class PortalRoleFunctionService {
	// region -- Fields --

	@Autowired
	private PortalRoleFunctionDao portalRoleFunctionDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalRoleFunction getBy(int id) {
		PortalRoleFunction res = portalRoleFunctionDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<PortalRoleFunction> search() {
		List<PortalRoleFunction> res = portalRoleFunctionDao.search();
		return res;
	}

	// end
	public String save(PortalRoleFunction m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			// m.setActive(true);
			m.setDeleted(false);
			m.setCreatedDate(new Date());

			portalRoleFunctionDao.save(m);

		} else {
			PortalRoleFunction m1 = portalRoleFunctionDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = portalRoleFunctionDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				portalRoleFunctionDao.save(m1);
			}
		}

		return res;
	}

	public String delete(PortalRoleFunction m, Integer userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setDeleted(true);

			portalRoleFunctionDao.save(m);
		}

		return res;
	}
}