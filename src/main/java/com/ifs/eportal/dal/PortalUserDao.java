package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.dto.PortalUserDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.PortalUserFilter;
import com.ifs.eportal.model.PortalUser;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
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

	private String _sql;

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalUserDao() {
		_sql = "SELECT \r\n" + "	a.id, a.user_id__c, d.firstname, d.lastname, d.salutation, d.mobilephone, \r\n"
				+ "	a.password__c, a.password_hash__c, a.pass_reminder_token__c, a.pass_reminder_expire__c, \r\n"
				+ "	a.client__c, b.name role_name, c.name company_name, c.name client_name \r\n"
				+ "FROM salesforce.portal_user__c a \r\n" + "JOIN salesforce.portal_role__c b \r\n"
				+ "	ON a.role__c = b.sfid \r\n" + "JOIN salesforce.account c \r\n" + "	ON a.client__c = c.sfid \r\n"
				+ "JOIN salesforce.contact d \r\n" + "	ON a.contact__c = d.sfid ";
	}

	/**
	 * Get by
	 * 
	 * @param token
	 * @return
	 */
	public PortalUserDto getBy(Object token) {
		String sql = _sql + " WHERE a.pass_reminder_expire__c >= now() AND a.pass_reminder_token__c = :token";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("token", token);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		PortalUserDto res = PortalUserDto.convert(i);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param email
	 * @return
	 */
	public PortalUserDto getBy(String email) {
		String sql = _sql + " WHERE a.user_id__c = :email";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("email", email);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		PortalUserDto res = PortalUserDto.convert(i);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalUserDto getBy(Integer id) {
		String sql = _sql + " WHERE a.id = :id";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("id", id);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		PortalUserDto res = PortalUserDto.convert(i);
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
		String sql = "SELECT \r\n" + "	count(*)\r\n" + "FROM salesforce.portal_user__c a \r\n"
				+ "JOIN salesforce.portal_role__c b \r\n" + "	ON a.role__c = b.sfid \r\n"
				+ "JOIN salesforce.account c \r\n" + "	ON a.client__c = c.sfid \r\n" + "JOIN salesforce.contact d \r\n"
				+ "	ON a.contact__c = d.sfid ";
		String limit = "";
		Query q = createQuery(sql, filter, limit);
		BigInteger total = (BigInteger) q.getSingleResult();
		req.setTotal(total.longValue());

		// Execute to search
		sql = _sql;
		limit = orderBy + " OFFSET " + offset + " LIMIT " + size;
		q = createQuery(sql, filter, limit);
		List<Object[]> l = q.getResultList();

		return PortalUserDto.convert(l);
	}

	/**
	 * Get role by
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getRoleBy(Integer id) {
		String sql = "SELECT \r\n" + "	b.name \r\n" + "FROM salesforce.portal_user__c a \r\n"
				+ "JOIN salesforce.portal_role__c b \r\n" + "	ON a.role__c = b.sfid \r\n" + "WHERE a.id = :id";
		Query q = _em.createNativeQuery(sql);
		q.setParameter("id", id);

		List<String> res = q.getResultList();
		return res;
	}

	/**
	 * Create query to prevent SQL injection
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
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

		Query q = _em.createNativeQuery(sql + where + limit);

		// Set parameter
		if (!where.isEmpty()) {
			int i = where.indexOf(":email");
			if (i > 0) {
				q.setParameter("email", email);
			}
			i = where.indexOf(":firstName");
			if (i > 0) {
				q.setParameter("firstName", firstName);
			}
			i = where.indexOf(":lastName");
			if (i > 0) {
				q.setParameter("lastName", lastName);
			}
			i = where.indexOf(":mobile");
			if (i > 0) {
				q.setParameter("mobile", mobile);
			}
		}

		return q;
	}

	// end
}