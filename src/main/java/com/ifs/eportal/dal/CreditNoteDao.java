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

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.common.ZDate;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.CreditNoteDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.CreditNoteFilter;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author HoanNguyen 2018-Oct-05
 *
 */
@Service(value = "_creditNoteDao")
public class CreditNoteDao implements Repository<CreditNote, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(CreditNote entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public CreditNote read(Integer id) {
		return _em.find(CreditNote.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public CreditNote update(CreditNote entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(CreditNote entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(CreditNoteDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public CreditNoteDao() {
		_path = ZFile.getPath("/sql/" + CreditNoteDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public CreditNoteDto getBy(Integer id) {
		CreditNoteDto res = new CreditNoteDto();

		try {
			_sql = ZFile.read(_path + "detail.sql");
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = CreditNoteDto.convert(t);
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
	public CreditNoteDto getBy(String sfId) {
		CreditNoteDto res = new CreditNoteDto();

		try {
			String sql = ZFile.read(_path + "detail.sql");
			sql += " WHERE a.sfid = :sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = CreditNoteDto.convert(t);
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
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */

	public CreditNoteDto getBy(String scheduleNo, String clientName) {
		CreditNoteDto res = new CreditNoteDto();

		try {
			String sql = _sql + " WHERE a.schedule_of_offer__c = :scheduleNo AND a.client_account__c = :clientName";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("scheduleNo", scheduleNo);
			q.setParameter("clientName", clientName);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = CreditNoteDto.convert(t);
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
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CreditNoteDto> search(PagingReq req) {
		List<CreditNoteDto> res = new ArrayList<CreditNoteDto>();

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
					orderBy += " a.status__c " + direction;
				}

				if ("customer".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.customer__c " + direction;
				}
			}

			if (!orderBy.isEmpty()) {
				orderBy = " ORDER BY " + orderBy;
			}

			// Execute to count all
			String sql = ZFile.read(_path + "count.sql");
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
			res = CreditNoteDto.convert(t);
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
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		CreditNoteFilter filter = CreditNoteFilter.convert(o);
		String clientName = filter.getClient();
		String clientAccount = filter.getClientAccount();
		String status = filter.getStatus();
		String scheduleNo = filter.getScheduleNo();
		String creditNoteNo = filter.getName();
		String customer = filter.getCustomer();
		Date fr = filter.getFrCreatedDate();
		Date to = filter.getToCreatedDate();

		// Where
		String where = "";
		if (!clientName.isEmpty()) {
			where += " AND a.client__c = :clientName";
		}
		if (!clientAccount.isEmpty()) {
			where += " AND a.client_account__c = :clientAccount";
		}
		if (!status.isEmpty()) {
			where += " AND a.status__c = :status";
		}
		if (!scheduleNo.isEmpty()) {
			where += " AND LOWER(d.schedule_no__c) like LOWER(:scheduleNo)";
		}
		if (!creditNoteNo.isEmpty()) {
			where += " AND LOWER(a.name) like LOWER(:creditNoteNo)";
		}
		if (!customer.isEmpty()) {
			where += " AND b.name = :customer";
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
			where = where.replaceFirst("AND", "WHERE");
		}

		Query q = _em.createNativeQuery(sql + where + limit);

		// Set parameter
		if (!where.isEmpty()) {
			int i = where.indexOf(":clientName");
			if (i > 0) {
				q.setParameter("clientName", clientName);
			}
			i = where.indexOf(":clientAccount");
			if (i > 0) {
				q.setParameter("clientAccount", clientAccount);
			}
			i = where.indexOf(":status");
			if (i > 0) {
				q.setParameter("status", status);
			}
			i = where.indexOf(":scheduleNo");
			if (i > 0) {
				q.setParameter("scheduleNo", "%" + scheduleNo + "%");
			}
			i = where.indexOf(":creditNoteNo");
			if (i > 0) {
				q.setParameter("creditNoteNo", "%" + creditNoteNo + "%");
			}
			i = where.indexOf(":customer");
			if (i > 0) {
				q.setParameter("customer", customer);
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

		return q;
	}

	// end
}