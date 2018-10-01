package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.dto.PortalRoleDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.PortalRoleFilter;
import com.ifs.eportal.model.PortalRole;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author HoanNguyen 2018-Oct-1
 *
 */
@Service(value = "portalRoleDao")
public class PortalRoleDao implements Repository<PortalRole, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(PortalRole entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public PortalRole read(Integer id) {
		return _em.find(PortalRole.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public PortalRole update(PortalRole entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(PortalRole entity) {
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
	public PortalRoleDao() {
		_sql = "SELECT \r\n" + "	a.id, \r\n" + "	a.name, \r\n" + "	a.sfid\r\n"
				+ "FROM salesforce.portal_role__c a ";
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public PortalRoleDto getBy(Integer id) {
		String sql = _sql + " WHERE a.id = :id";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("id", id);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		PortalRoleDto res = PortalRoleDto.convert(i);
		return res;
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PortalRoleDto> search(PagingReq req) {
		// Get data
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

			if ("name".equals(field)) {
				if (!orderBy.isEmpty()) {
					orderBy += ",";
				}
				orderBy += " a.name " + direction;
			}
		}

		if (!orderBy.isEmpty()) {
			orderBy = " ORDER BY " + orderBy;
		}

		// Execute to count all
		String sql = "SELECT \r\n" + "	count(*)\r\n" + "FROM salesforce.portal_role__c a ";
		String limit = "";
		Query q = createQuery(sql, filter, limit);
		BigInteger total = (BigInteger) q.getSingleResult();
		req.setTotal(total.longValue());

		// Execute to search
		sql = _sql;
		limit = orderBy + " OFFSET " + offset + " LIMIT " + size;
		q = createQuery(sql, filter, limit);
		List<Object[]> l = q.getResultList();

		return PortalRoleDto.convert(l);
	}

	/**
	 * Create query to prevent SQL injection
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		PortalRoleFilter filter = PortalRoleFilter.convert(o);
		String name = filter.getName();

		// Where
		String where = "";
		if (!name.isEmpty()) {
			where += " AND a.name = :name";
		}

		// Replace first
		if (!where.isEmpty()) {
			where = where.replaceFirst("AND", "WHERE");
		}

		Query q = _em.createNativeQuery(sql + where + limit);

		// Set parameter
		if (!where.isEmpty()) {
			int i = where.indexOf(":name");
			if (i > 0) {
				q.setParameter("name", name);
			}
		}

		return q;
	}

	// end
}