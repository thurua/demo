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

import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.ClientAccountCustomerFilter;
import com.ifs.eportal.model.ClientAccountCustomer;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author HoanNguyen 2018-Oct-2
 *
 */
@Service(value = "clientAccountCustomerDao")
public class ClientAccountCustomerDao implements Repository<ClientAccountCustomer, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(ClientAccountCustomer entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public ClientAccountCustomer read(Integer id) {
		return _em.find(ClientAccountCustomer.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public ClientAccountCustomer update(ClientAccountCustomer entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(ClientAccountCustomer entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _sql;

	private String _path;

	private static final Logger _log = Logger.getLogger(ClientAccountDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountCustomerDao() {
		_path = ZFile.getPath("/sql/" + ClientAccountCustomerDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	@SuppressWarnings("unchecked")
	public List<ClientAccountCustomerDto> getByClientId(String clientId) {
		List<ClientAccountCustomerDto> res = new ArrayList<ClientAccountCustomerDto>();

		try {
			String sql = _sql + " WHERE a.client_account__c = :clientId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("clientId", clientId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = ClientAccountCustomerDto.convert(l);
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
	 * Get by ClientAccountCustomer
	 * 
	 * @param sfId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClientAccountCustomerDto> getBy(String sfId) {
		List<ClientAccountCustomerDto> res = new ArrayList<ClientAccountCustomerDto>();

		try {
			_sql = ZFile.read(_path + "_sql.sql");
			String sql = _sql + " WHERE a.client_account__c =:sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);

			List<Object[]> l = q.getResultList();

			// Convert
			res = ClientAccountCustomerDto.convert(l);
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
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClientAccountCustomerDto> search(PagingReq req) {
		List<ClientAccountCustomerDto> res = new ArrayList<ClientAccountCustomerDto>();

		try {
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
					orderBy += " c.name " + direction;
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
			List<Object[]> l = q.getResultList();

			// Convert
			res = ClientAccountCustomerDto.convert(l);
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
	 * Create query to prevent SQL injection
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		Query q = null;

		try {
			ClientAccountCustomerFilter filter = ClientAccountCustomerFilter.convert(o);
			String clientAccount = filter.getClientAccount();

			// Where
			String where = "";
			// if (!client.isEmpty()) {
			where += " AND a.client_account__c = :clientAccount ";
			// }

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			q = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":clientAccount");
				if (i > 0) {
					q.setParameter("clientAccount", clientAccount);
				}
			}
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return q;
	}

	// end
}