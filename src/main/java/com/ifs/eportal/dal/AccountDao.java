package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.AccountFilter;
import com.ifs.eportal.model.Account;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author MinhDang 2018-Oct-3
 *
 */
@Service(value = "accountDao")
public class AccountDao implements Repository<Account, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(Account entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public Account read(Integer id) {
		return _em.find(Account.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public Account update(Account entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(Account entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _sql;

	// end

	// region -- Methods --
	public AccountDao() {
		_sql = "SELECT \r\n" + "a.id, a.sfid, a.name" + " FROM salesforce.account a\r\n";
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public AccountDto getBy(String sfid) {
		String sql = _sql + " WHERE a.sfid = :sfid";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("sfid", sfid);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		AccountDto res = AccountDto.convert(i);
		return res;
	}

	public AccountDto getByClient(String client) {
		String sql = _sql + " WHERE a.name = :client";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("name", client);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		AccountDto res = AccountDto.convert(i);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AccountDto> getBy(String name, String sfid) {
		String sql = _sql + " WHERE a.name = :name AND a.sfid = :sfid";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("name", name);
		q.setParameter("sfid", sfid);
		List<Object[]> l = q.getResultList();

		// Convert
		List<AccountDto> res = AccountDto.convert(l);
		return res;
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AccountDto> search(PagingReq req) {
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
		String sql = "SELECT \r\n" + "	count(*) \r\n" + " FROM salesforce.account a ";
		String limit = "";
		Query q = createQuery(sql, filter, limit);
		BigInteger total = (BigInteger) q.getSingleResult();
		req.setTotal(total.longValue());

		// Execute to search
		sql = _sql;
		limit = orderBy + " OFFSET " + offset + " LIMIT " + size;
		q = createQuery(sql, filter, limit);
		List<Object[]> l = q.getResultList();

		// Convert
		List<AccountDto> res = AccountDto.convert(l);
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
		AccountFilter filter = AccountFilter.convert(o);
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
			int i = where.indexOf(":clientName");
			i = where.indexOf(":name");
			if (i > 0) {
				q.setParameter("name", name);
			}
		}

		return q;
	}

	// end
}