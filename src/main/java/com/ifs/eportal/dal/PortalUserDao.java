package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.dto.PortalUserDto;
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
		_sql = "SELECT \r\n"
				+ "	a.id, a.email__c, a.first_name__c, a.last_name__c, a.salutation__c, a.mobile__c, \r\n"
				+ "	a.password__c, a.password_hash__c, a.pass_reminder_token__c, a.pass_reminder_expire__c, \r\n"
				+ "	a.client__c, b.name role_name, c.name company_name, c.name client_name \r\n"
				+ "FROM salesforce.portal_user__c a \r\n" + "JOIN salesforce.portal_role__c b \r\n"
				+ "	ON a.role__c = b.sfid \r\n" + "JOIN salesforce.account c \r\n" + "	ON a.client__c = c.sfid ";
	}

	/**
	 * Get by
	 * 
	 * @param email
	 * @return
	 */
	public PortalUserDto getBy(String email) {
		String sql = _sql + " WHERE a.email__c = :email";

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
		List<PortalUserDto> res = new ArrayList<PortalUserDto>();

		// Get data
		PortalUserFilter filter = PortalUserFilter.convert(req.getFilter());
		String email = filter.getEmail();
		String firstName = filter.getFirstName();
		String lastName = filter.getLastName();
		String mobile = filter.getMobile();

		int page = req.getPage();
		int size = req.getSize();
		String sort = req.getSort();
		int offset = (page - 1) * size;

		// Where
		String where = "";
		if (!email.isEmpty()) {
			where += " AND a.email__c LIKE '%" + email + "%'";
		}
		if (!firstName.isEmpty()) {
			where += " AND a.first_name__c LIKE '%" + firstName + "%'";
		}
		if (!lastName.isEmpty()) {
			where += " AND a.last_name__c LIKE '%" + lastName + "%'";
		}
		if (!mobile.isEmpty()) {
			where += " AND a.mobile__c LIKE '%" + mobile + "%'";
		}

		// Replace first
		if (!where.isEmpty()) {
			where = where.replaceFirst("AND", "WHERE");
		}

		// Sort by
		String sortBy = "";
		if (sort.isEmpty() || "ASC".equals(sort)) {
			sortBy = "a.id ASC";
		} else {
			sortBy = "a.id DESC";
		}

		// Execute to count all
		String sql = "SELECT \r\n" + "	count(*)\r\n" + "FROM salesforce.portal_user__c a \r\n"
				+ "JOIN salesforce.portal_role__c b \r\n" + "	ON a.role__c = b.sfid \r\n"
				+ "JOIN salesforce.account c \r\n" + "	ON a.client__c = c.sfid " + where;
		Query q = _em.createNativeQuery(sql);
		BigInteger total = (BigInteger) q.getSingleResult();
		req.setTotal(total.longValue());

		// Execute to search
		String limit = " ORDER BY " + sortBy + " OFFSET " + offset + " LIMIT " + size;
		sql = _sql + where + limit;
		q = _em.createNativeQuery(sql);

		List<Object[]> l = q.getResultList();

		// Convert
		for (Object[] i : l) {
			PortalUserDto o = PortalUserDto.convert(i);
			res.add(o);
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
		String sql = "SELECT \r\n" + "	b.name \r\n" + "FROM salesforce.portal_user__c a \r\n"
				+ "JOIN salesforce.portal_role__c b \r\n" + "	ON a.role__c = b.sfid \r\n" + "WHERE a.id = :id";
		Query q = _em.createNativeQuery(sql);
		q.setParameter("id", id);

		List<String> res = q.getResultList();
		return res;
	}

	// end
}