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
import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.ClientAccountCustomerFactoringDto;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.FactoringDetailDto;
import com.ifs.eportal.dto.LctrDetailDto;
import com.ifs.eportal.dto.LoanDetailDto;
import com.ifs.eportal.dto.SearchClientAccountDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.ClientAccountFilter;
import com.ifs.eportal.model.ClientAccount;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author HoanNguyen 2018-Sep-28
 *
 */
@Service(value = "clientAccountDao")
public class ClientAccountDao implements Repository<ClientAccount, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(ClientAccount entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public ClientAccount read(Integer id) {
		return _em.find(ClientAccount.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public ClientAccount update(ClientAccount entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(ClientAccount entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(ClientAccountDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountDao() {
		_path = ZFile.getPath("/sql/" + ClientAccountDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Read by
	 * 
	 * @param sfId
	 * @return
	 */
	public ClientAccountDto read(String sfId) {
		ClientAccountDto res = new ClientAccountDto();

		try {
			String sql = _sql + " WHERE a.sfid = :sfid";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfid", sfId);
			Object[] i = (Object[]) q.getSingleResult();

			// Convert
			res = ClientAccountDto.convert(i);
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
	public List<ClientAccountDto> search(PagingReq req) {
		List<ClientAccountDto> res = new ArrayList<ClientAccountDto>();

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

				if ("clientAccount".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.client_account__c " + direction;
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
			res = ClientAccountDto.convert(l);
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
			ClientAccountFilter filter = ClientAccountFilter.convert(o);
			String client = filter.getClient();
			String status = filter.getStatus();
			// Where
			String where = "";
			if (!client.isEmpty()) {
				where += " AND a.client__c = :client ";
			}
			if (!status.isEmpty()) {
				where += " AND a.status__c = :status ";
			}
			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			q = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":client");
				if (i > 0) {
					q.setParameter("client", client);
				}
				i = where.indexOf(":status");
				if (i > 0) {
					q.setParameter("status", status);
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

	/**
	 * Search
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SearchClientAccountDto> searchClientAccount(PagingReq req) {
		List<SearchClientAccountDto> res = new ArrayList<SearchClientAccountDto>();

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

				if ("status".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.status " + direction;
				}
			}

			if (!orderBy.isEmpty()) {
				orderBy = " ORDER BY " + orderBy;
			}

			// Execute to count all
			String searchSql = ZFile.read(_path + "_search.sql");
			int i = searchSql.indexOf("FROM");
			String sql = "SELECT COUNT(*) " + searchSql.substring(i);
			String limit = "";
			Query q = createQuerySearch(sql, filter, limit);
			BigInteger total = (BigInteger) q.getSingleResult();
			req.setTotal(total.longValue());

			// Execute to search
			sql = searchSql;
			limit = orderBy;
			if (req.isPaging()) {
				limit += " OFFSET " + offset + " LIMIT " + size;
			}
			q = createQuerySearch(sql, filter, limit);
			List<Object[]> t = q.getResultList();

			// Convert
			res = SearchClientAccountDto.convert(t);
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
	private Query createQuerySearch(String sql, Object o, String limit) {
		Query res = null;

		try {
			ClientAccountFilter filter = ClientAccountFilter.convert(o);
			String recordType = filter.getRecordType();
			String client = filter.getClient();
			String clientAccount = filter.getClientAccount();
			String status = filter.getStatus();
			Utils.toString(filter, true);

			// Where
			String where = "";
			if (!recordType.isEmpty()) {
				where += " AND b.name = :recordType ";
			}
			if (!client.isEmpty()) {
				where += " AND a.client__c = :client";
			}
			if (!clientAccount.isEmpty()) {
				where += " AND a.sfid = :clientAccount";
			}
			if (!status.isEmpty()) {
				where += " AND a.status__c = :status";
			}

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			res = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":recordType");
				if (i > 0) {
					res.setParameter("recordType", recordType);
				}
				i = where.indexOf(":client");
				if (i > 0) {
					res.setParameter("client", client);
				}
				i = where.indexOf(":clientAccount");
				if (i > 0) {
					res.setParameter("clientAccount", clientAccount);
				}
				i = where.indexOf(":status");
				if (i > 0) {
					res.setParameter("status", status);
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

	/**
	 * Read by
	 * 
	 * @param sfId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClientAccountCustomerFactoringDto> getClientAccountCustomer(PagingReq req) {
		List<ClientAccountCustomerFactoringDto> res = new ArrayList<ClientAccountCustomerFactoringDto>();

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

				if ("status".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.status " + direction;
				}
			}

			if (!orderBy.isEmpty()) {
				orderBy = " ORDER BY " + orderBy;
			}

			// Execute to count all
			String searchSql = ZFile.read(_path + "_client-account-customer-factoring.sql");
			int i = searchSql.indexOf("FROM");
			String sql = "SELECT COUNT(*) " + searchSql.substring(i);
			String limit = "";
			Query q = createQueryGetClientAccountCustomer(sql, filter, limit);
			BigInteger total = (BigInteger) q.getSingleResult();
			req.setTotal(total.longValue());

			// Execute to search
			sql = searchSql;
			limit = orderBy;
			if (req.isPaging()) {
				limit += " OFFSET " + offset + " LIMIT " + size;
			}
			q = createQueryGetClientAccountCustomer(sql, filter, limit);
			List<Object[]> t = q.getResultList();

			// Convert
			res = ClientAccountCustomerFactoringDto.convert(t);
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
	private Query createQueryGetClientAccountCustomer(String sql, Object o, String limit) {
		Query res = null;

		try {
			ClientAccountFilter filter = ClientAccountFilter.convert(o);
			String clientAccount = filter.getClientAccount();

			// Where
			String where = "";
			if (!clientAccount.isEmpty()) {
				where += " AND a.client_account__c = :clientAccount";
			}

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			res = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":clientAccount");
				if (i > 0) {
					res.setParameter("clientAccount", clientAccount);
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

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LoanDetailDto getLoanDetail(String sfId) {
		String sql = ZFile.read(_path + "detail_loan.sql");
		LoanDetailDto res = new LoanDetailDto();

		try {
			sql = sql + " WHERE sfid = :sfid";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfid", sfId);
			Object[] i = (Object[]) q.getSingleResult();

			// Funding Account Details
			sql = ZFile.read(_path + "detail_funding.sql");
			sql += " WHERE b.sfid = :sfId";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> fu = q.getResultList();

			// Convert
			res = LoanDetailDto.convert(i, fu);
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
	 * @param sfId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public FactoringDetailDto getFactoringDetail(String sfId) {
		String sql = ZFile.read(_path + "detail_factoring.sql");
		FactoringDetailDto res = new FactoringDetailDto();

		try {
			sql = sql + " WHERE a.sfid = :sfid";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfid", sfId);
			Object[] i = (Object[]) q.getSingleResult();

			sql = ZFile.read(_path + "detail_funding.sql");
			sql += " WHERE b.sfid = :sfId";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> fu = q.getResultList();

			// Convert
			res = FactoringDetailDto.convert(i, fu);
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
	 * @param sfId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LctrDetailDto getLctrDetail(String sfId) {
		String sql = ZFile.read(_path + "detail_lctr.sql");
		LctrDetailDto res = new LctrDetailDto();

		try {
			sql = sql + " WHERE sfid = :sfid";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfid", sfId);
			Object[] i = (Object[]) q.getSingleResult();

			// Funding Account Details
			sql = ZFile.read(_path + "detail_funding.sql");
			sql += " WHERE b.sfid = :sfId";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> fu = q.getResultList();

			// Convert
			res = LctrDetailDto.convert(i, fu);
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