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
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserAccessDto read(int id) {
		return portalUserAccessDao.getBy(id);
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