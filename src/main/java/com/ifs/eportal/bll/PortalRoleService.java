package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PortalRoleDao;
import com.ifs.eportal.dto.PortalRoleDto;
import com.ifs.eportal.model.PortalRole;
import com.ifs.eportal.req.PagingReq;

@Service(value = "portalRoleService")
@Transactional
public class PortalRoleService {
	// region -- Fields --

	@Autowired
	private PortalRoleDao portalRoleDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(PortalRole m) {
		portalRoleDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public PortalRoleDto read(int id) {
		return portalRoleDao.getBy(id);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<PortalRoleDto> search(PagingReq req) {
		return portalRoleDao.search(req);
	}
}