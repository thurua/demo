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
import com.ifs.eportal.dto.PortalUserDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.PortalUserFilter;
import com.ifs.eportal.model.PortalUser;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
@Service(value = "portalUserDao")
public class PortalUserDao implements Repository<PortalUser, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(PortalUser entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public PortalUser read(Integer id) {
		return _em.find(PortalUser.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public PortalUser update(PortalUser entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(PortalUser entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(PortalUserDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalUserDao() {
		_path = ZFile.getPath("/sql/" + PortalUserDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserDto getBy(Integer id) {
		PortalUserDto res = new PortalUserDto();

		try {
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = PortalUserDto.convert(t);
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
	public PortalUserDto getBy(String sfId) {
		PortalUserDto res = new PortalUserDto();

		try {
			String sql = _sql + " WHERE a.sfid = :sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = PortalUserDto.convert(t);
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
	public PortalUserDto getByToken(String token) {
		PortalUserDto res = new PortalUserDto();

		try {
			String sql = _sql;
			sql += " WHERE a.pass_reminder_token__c = :token AND a.pass_reminder_expire__c >= now()";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("token", token);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = PortalUserDto.convert(t);
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
	public PortalUserDto getByUserId(String userId) {
		PortalUserDto res = new PortalUserDto();

		try {
			String sql = _sql;
			sql += " WHERE a.user_id__c = :userId AND a.status__c = 'ACTD'";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("userId", userId);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = PortalUserDto.convert(t);
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
	public List<PortalUserDto> search(PagingReq req) {
		List<PortalUserDto> res = new ArrayList<PortalUserDto>();

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
				if ("email".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.user_id__c " + direction;
				}
				if ("firstName".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " d.firstname " + direction;
				}
				if ("lastName".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " d.lastname " + direction;
				}
				if ("mobile".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " d.mobilephone " + direction;
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
			res = PortalUserDto.convert(t);
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
	 * Get role by
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getRoleBy(Integer id) {
		List<String> res = new ArrayList<String>();

		try {
			String sql = ZFile.read(_path + "getRoleBy.sql");
			sql += " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			res = q.getResultList();
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
			PortalUserFilter filter = PortalUserFilter.convert(o);
			String email = filter.getEmail();
			String firstName = filter.getFirstName();
			String lastName = filter.getLastName();
			String mobile = filter.getMobile();

			// Where
			String where = "";
			if (!email.isEmpty()) {
				where += " AND a.user_id__c LIKE :email";
			}
			if (!firstName.isEmpty()) {
				where += " AND d.firstname LIKE :firstName";
			}
			if (!lastName.isEmpty()) {
				where += " AND d.lastname LIKE :lastName";
			}
			if (!mobile.isEmpty()) {
				where += " AND d.mobilephone LIKE :mobile";
			}

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			res = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":email");
				if (i > 0) {
					res.setParameter("email", email);
				}
				i = where.indexOf(":firstName");
				if (i > 0) {
					res.setParameter("firstName", firstName);
				}
				i = where.indexOf(":lastName");
				if (i > 0) {
					res.setParameter("lastName", lastName);
				}
				i = where.indexOf(":mobile");
				if (i > 0) {
					res.setParameter("mobile", mobile);
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