package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.dal.PortalUserAccessDao;
import com.ifs.eportal.dto.PortalUserAccessDto;
import com.ifs.eportal.model.PortalUserAccess;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-19 (verified)
 *
 */
@Service(value = "portalUserAccessService")
@Transactional
public class PortalUserAccessService {
	// region -- Fields --

	@Autowired
	private PortalUserAccessDao portalUserAccessDao;

	private static final Logger _log = Logger.getLogger(PortalUserAccessDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param id        User SFID
	 * @param host
	 * @param userAgent
	 * @return
	 */
	public String create(String id, String host, String userAgent) {
		String res = "";

		try {
			res = UUID.randomUUID().toString();
			Date now = new Date();

			PortalUserAccess m = new PortalUserAccess();
			m.setLoginOn(now);
			m.setLastAccessOn(now);
			m.setCreatedDate(now);
			m.setUuId(res);
			m.setHost(host);
			m.setUserAgent(userAgent);
			m.setUser(id);
			m.setDeleted(false);
			portalUserAccessDao.create(m);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get by
	 * 
	 * @param uuId
	 * @return
	 */
	public PortalUserAccessDto getBy(String uuId) {
		PortalUserAccessDto res = portalUserAccessDao.getBy(uuId);
		return res;
	}

	/**
	 * Update logout on
	 * 
	 * @param id         UUID or User SFID
	 * @param signOutAll If true update by User SFID, else update by UUID
	 * @return
	 */
	public int updateLogoutOn(String id, boolean signOutAll) {
		int res = portalUserAccessDao.updateLogoutOn(id, signOutAll);
		return res;
	}

	/**
	 * Search
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