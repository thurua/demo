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
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.dto.SupplierDto;
import com.ifs.eportal.filter.SupplierFilter;
import com.ifs.eportal.model.Supplier;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author VanPhan 2018-Oct-09
 *
 */
@Service(value = "supplierDao")
public class SupplierDao implements Repository<Supplier, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(Supplier entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public Supplier read(Integer id) {
		return _em.find(Supplier.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public Supplier update(Supplier entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(Supplier entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(SupplierDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public SupplierDao() {
		_path = ZFile.getPath("/sql/" + SupplierDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	@SuppressWarnings("unchecked")
	public List<SupplierDto> getByClientId(String clientId) {
		List<SupplierDto> res = new ArrayList<SupplierDto>();

		try {
			String sql = _sql + " WHERE a.client1__c = :clientId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("clientId", clientId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = SupplierDto.convert(l);
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
	 * Search
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SupplierDto> search(PagingReq req) {
		List<SupplierDto> res = new ArrayList<SupplierDto>();

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

				if ("name".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " b.name " + direction;
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
			res = SupplierDto.convert(t);
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
	 * @param limit
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		Query res = null;

		try {
			SupplierFilter filter = SupplierFilter.convert(o);
			String client = filter.getClient();

			// Where
			String where = "";
			// if (!client.isEmpty()) {
			where += " AND a.client1__c = :client ";
			// }

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			res = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":client");
				if (i > 0) {
					res.setParameter("client", client);
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

		return res;
	}

	// end
}