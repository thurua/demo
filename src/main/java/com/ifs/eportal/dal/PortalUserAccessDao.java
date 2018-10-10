package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.PortalUserAccessDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.PortalUserAccessFilter;
import com.ifs.eportal.model.PortalUserAccess;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
@Service(value = "portalUserAccessAccessDao")
public class PortalUserAccessDao implements Repository<PortalUserAccess, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(PortalUserAccess entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public PortalUserAccess read(Integer id) {
		return _em.find(PortalUserAccess.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public PortalUserAccess update(PortalUserAccess entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(PortalUserAccess entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(PortalUserAccessDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalUserAccessDao() {
		_path = ZFile.getPath("/sql/" + PortalUserAccessDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Read by
	 * 
	 * @param uuId
	 * @return
	 */
	public PortalUserAccess read(String uuId) {
		PortalUserAccess res = new PortalUserAccess();

		try {
			String sql = "FROM PortalUserAccess a WHERE a.uuId = :uuId AND a.logoutOn IS NULL";

			// Execute
			Query q = _em.createQuery(sql, PortalUserAccess.class);
			q.setParameter("uuId", uuId);
			res = (PortalUserAccess) q.getSingleResult();
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserAccessDto getBy(Integer id) {
		PortalUserAccessDto res = new PortalUserAccessDto();

		try {
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = PortalUserAccessDto.convert(t);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	public PortalUserAccessDto getBy(String sfId) {
		PortalUserAccessDto res = new PortalUserAccessDto();

		try {
			String sql = _sql + " WHERE a.sfid = :sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = PortalUserAccessDto.convert(t);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get by
	 * 
	 * @param token
	 * @return
	 */
	public PortalUserAccessDto getByToken(String token) {
		PortalUserAccessDto res = new PortalUserAccessDto();

		try {
			String sql = _sql;
			sql += " WHERE a.uuid__c = :token";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("token", token);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = PortalUserAccessDto.convert(t);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get by
	 * 
	 * @param userId
	 * @return
	 */
	public PortalUserAccessDto getByUserId(String userId) {
		PortalUserAccessDto res = new PortalUserAccessDto();

		try {
			String sql = _sql;
			sql += " WHERE a.user__c = :userId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("userId", userId);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = PortalUserAccessDto.convert(t);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Search
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PortalUserAccessDto> search(PagingReq req) {
		List<PortalUserAccessDto> res = new ArrayList<PortalUserAccessDto>();

		try {
			Object filter = req.getFilter();
			int page = req.getPage();
			int size = req.getSize();
			List<SortDto> sort = req.getSort();
			int offset = (page - 1) * size;

			// Order by
			String orderBy = "";
			for (SortDto o : sort) {
				String field = o.getField();
				String direction = o.getDirection();

				if ("id".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.id " + direction;
				}
				if ("loginOn".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.login_on__c " + direction;
				}
			}

			if (!orderBy.isEmpty()) {
				orderBy = " ORDER BY " + orderBy;
			}

			// Execute to count all
			int i = _sql.indexOf("FROM");
			String sql = "SELECT COUNT(*) " + _sql.substring(i);
			String limit = "";
			Query q = createQuery(sql, filter, limit);
			BigInteger total = (BigInteger) q.getSingleResult();
			req.setTotal(total.longValue());

			// Execute to search
			sql = _sql;
			limit = orderBy;
			if (req.isPaging()) {
				limit += " OFFSET " + offset + " LIMIT " + size;
			}
			q = createQuery(sql, filter, limit);
			List<Object[]> t = q.getResultList();

			// Convert
			res = PortalUserAccessDto.convert(t);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Create query to prevent SQL injection
	 * 
	 * @param sql
	 * @param o
	 * @param limit
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		Query res = null;

		try {
			PortalUserAccessFilter filter = PortalUserAccessFilter.convert(o);
			String user = filter.getUser();

			// Where
			String where = "";
			if (!user.isEmpty()) {
				where += " AND a.user__c = :user";
			}

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			res = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":user");
				if (i > 0) {
					res.setParameter("user", user);
				}
			}
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	// end
}