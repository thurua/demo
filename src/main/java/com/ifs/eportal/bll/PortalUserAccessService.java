package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PortalUserAccessDao;
import com.ifs.eportal.dto.PortalUserAccessDto;
import com.ifs.eportal.dto.PortalUserDto;
import com.ifs.eportal.model.PortalUserAccess;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
@Service(value = "portalUserAccessService")
@Transactional
public class PortalUserAccessService {
	// region -- Fields --

	@Autowired
	private PortalUserAccessDao portalUserAccessDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param o
	 * @return
	 */
	public String create(PortalUserDto o) {
		Date now = new Date();
		String res = UUID.randomUUID().toString();

		PortalUserAccess m = new PortalUserAccess();
		m.setLoginOn(now);
		m.setLastAccessOn(now);
		m.setCreatedDate(now);
		m.setUuId(res);

		m.setUser(o.getSfId());
		m.setDeleted(false);

		portalUserAccessDao.create(m);

		return res;
	}

	/**
	 * Update logout on
	 * 
	 * @param uuId
	 * @return
	 */
	public int updateLogoutOn(String uuId) {
		int res = portalUserAccessDao.updateLogoutOn(uuId);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserAccessDto getBy(int id) {
		PortalUserAccessDto res = portalUserAccessDao.getBy(id);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	public PortalUserAccessDto getBy(String sfId) {
		PortalUserAccessDto res = portalUserAccessDao.getBy(sfId);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param token
	 * @return
	 */
	public PortalUserAccessDto getByToken(String token) {
		PortalUserAccessDto res = portalUserAccessDao.getByToken(token);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param userId
	 * @return
	 */
	public PortalUserAccessDto getByUserId(String userId) {
		PortalUserAccessDto res = portalUserAccessDao.getByUserId(userId);
		return res;
	}

	/**
	 * Update
	 * 
	 * @param m
	 * @return
	 */
	public PortalUserAccess update(PortalUserAccess m) {
		PortalUserAccess res = portalUserAccessDao.update(m);
		return res;
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<PortalUserAccessDto> search(PagingReq req) {
		List<PortalUserAccessDto> res = portalUserAccessDao.search(req);
		return res;
	}

	// end
}