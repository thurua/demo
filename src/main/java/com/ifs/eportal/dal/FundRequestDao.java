package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZDate;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.FundRequestDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.FundRequestFilter;
import com.ifs.eportal.model.FundRequest;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author VanPhan 2018-Oct-25
 *
 */
@Service(value = "fundRequestDao")
public class FundRequestDao implements Repository<FundRequest, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(FundRequest entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public FundRequest read(Integer id) {
		return _em.find(FundRequest.class, id);
	}

	/**
	 */
	@Override
	public FundRequest update(FundRequest entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(FundRequest entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(FundRequestDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FundRequestDao() {
		_path = ZFile.getPath("/sql/" + FundRequestDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundRequestDto> search(PagingReq req) {
		List<FundRequestDto> res = new ArrayList<FundRequestDto>();

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
			res = FundRequestDto.convert(l);
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
			FundRequestFilter filter = FundRequestFilter.convert(o);
			String client = filter.getClient();
			String fundRequestNo = filter.getFundRequestNo();
			String recordType = filter.getRecordType();
			String status = filter.getStatus();
			Date fr = filter.getFrCreatedDate();
			Date to = filter.getToCreatedDate();
			// Where
			String where = "";
			if (!client.isEmpty()) {
				where += " AND a.client__c = :client ";
			}
			if (!fundRequestNo.isEmpty()) {
				where += " AND a.name ILIKE :fundRequestNo ";
			}
			if (!recordType.isEmpty()) {
				where += " AND a.recordtypeid = :recordType ";
			}
			if (!status.isEmpty()) {
				where += " AND a.status__c = :status ";
			}

			if (fr != null && to != null) {
				where += " AND a.createddate BETWEEN :fr AND :to";
			} else if (fr != null && to == null) {
				where += " AND :fr <= a.createddate";
			} else if (fr == null && to != null) {
				where += " AND a.createddate <= :to";
			}

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE a.is_eportal_fr__c = true AND");
			}

			q = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":client");
				if (i > 0) {
					q.setParameter("client", client);
				}
				i = where.indexOf(":fundRequestNo");
				if (i > 0) {
					q.setParameter("fundRequestNo", fundRequestNo);
				}
				i = where.indexOf(":recordType");
				if (i > 0) {
					q.setParameter("recordType", recordType);
				}
				i = where.indexOf(":status");
				if (i > 0) {
					q.setParameter("status", status);
				}

				if (fr != null && to != null) {
					fr = ZDate.getStartOfDay(fr);
					q.setParameter("fr", fr);
					to = ZDate.getEndOfDay(to);
					q.setParameter("to", to);
				} else if (fr != null && to == null) {
					fr = ZDate.getStartOfDay(fr);
					q.setParameter("fr", fr);
				} else if (fr == null && to != null) {
					to = ZDate.getEndOfDay(to);
					q.setParameter("to", to);
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