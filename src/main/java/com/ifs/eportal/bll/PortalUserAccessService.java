package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PortalUserAccessDao;
import com.ifs.eportal.dto.PortalUserAccessDto;
import com.ifs.eportal.model.PortalUserAccess;
import com.ifs.eportal.req.PagingReq;

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
	 * @param m
	 */
	public void create(PortalUserAccess m) {
		portalUserAccessDao.create(m);
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
	 * Get by
	 * 
	 * @param user
	 * @param uuid
	 * @return
	 */
	public PortalUserAccess getBy(String user, String uuid) {
		PortalUserAccess res = portalUserAccessDao.getBy(user, uuid);
		return res;
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<PortalUserAccessDto> search(PagingReq req) {
		return portalUserAccessDao.search(req);
	}

	// end
}