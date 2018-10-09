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
import com.ifs.eportal.dto.ScheduleOfOfferDetailDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.ScheduleOfOfferFilter;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
@Service(value = "_scheduleOfOfferDao")
public class ScheduleOfOfferDao implements Repository<ScheduleOfOffer, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(ScheduleOfOffer entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public ScheduleOfOffer read(Integer id) {
		return _em.find(ScheduleOfOffer.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public ScheduleOfOffer update(ScheduleOfOffer entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(ScheduleOfOffer entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(ScheduleOfOfferDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ScheduleOfOfferDao() {
		_path = ZFile.getPath("/sql/" + ScheduleOfOfferDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDto getBy(Integer id) {
		ScheduleOfOfferDto res = new ScheduleOfOfferDto();

		try {
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = ScheduleOfOfferDto.convert(t);
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
	@SuppressWarnings("unchecked")
	public ScheduleOfOfferDetailDto getBy(String sfId) {
		ScheduleOfOfferDetailDto res = new ScheduleOfOfferDetailDto();

		try {
			String sql = ZFile.read(_path + "detail.sql");
			sql += " WHERE a.sfid = :sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			Object[] t = (Object[]) q.getSingleResult();

			// Credit note
			sql = ZFile.read(_path + "detail_credit_note.sql");
			sql += " WHERE a.schedule_of_offer__c = :sfId";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> lc = q.getResultList();

			// Invoice
			sql = ZFile.read(_path + "detail_invoice.sql");
			sql += " WHERE a.schedule_of_offer__c = :sfId";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> li = q.getResultList();

			// Attachment
			sql = ZFile.read(_path + "detail_attachment.sql");
			sql += " WHERE a.schedule_of_offer__c = :sfId AND a.isdeleted = FALSE AND a.isactive__c = TRUE";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> la = q.getResultList();

			// Convert
			res = ScheduleOfOfferDetailDto.convert(t, lc, li, la);
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

	public ScheduleOfOfferDto getBy(String scheduleNo, String clientName) {
		ScheduleOfOfferDto res = new ScheduleOfOfferDto();

		try {
			String sql = _sql + " WHERE a.schedule_no__c = :scheduleNo AND a.client_name__c = :clientName";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("scheduleNo", scheduleNo);
			q.setParameter("clientName", clientName);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = ScheduleOfOfferDto.convert(t);
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

	public Date acceptanceDate(String clientAccount) {
		Date res = null;

		try {
			String sql = ZFile.read(_path + "acceptanceDate.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("clientAccount", clientAccount);
			res = (Date) q.getSingleResult();

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
	public List<ScheduleOfOfferDto> search(PagingReq req) {
		List<ScheduleOfOfferDto> res = new ArrayList<ScheduleOfOfferDto>();

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

				if ("portalStatus".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.portal_status__c " + direction;
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
			res = ScheduleOfOfferDto.convert(t);
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
		ScheduleOfOfferFilter filter = ScheduleOfOfferFilter.convert(o);
		String clientName = filter.getClient();
		String clientAccount = filter.getClientAccount();
		String portalStatus = filter.getPortalStatus();
		Date fr = filter.getFrCreatedDate();
		Date to = filter.getToCreatedDate();

		// Where
		String where = "";
		if (!clientName.isEmpty()) {
			where += " AND a.client_name__c = :clientName";
		}
		if (!clientAccount.isEmpty()) {
			where += " AND a.client_account__c = :clientAccount";
		}
		if (!portalStatus.isEmpty()) {
			if ("Accepted".equals(portalStatus)) {
				where += " AND a.schedule_status__c = 'Submitted'";
			} else {
				where += " AND a.portal_status__c = :portalStatus";
			}
		} else {
			where += " AND (a.schedule_status__c = 'Submitted' OR a.createdby_portaluserid__c IS NOT NULL)";
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
			i = where.indexOf(":portalStatus");
			if (i > 0) {
				q.setParameter("portalStatus", portalStatus);
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