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
import com.ifs.eportal.common.ZConfig;
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
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
@Service(value = "scheduleOfOfferDao")
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
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDto getBy(String sfId) {
		ScheduleOfOfferDto res = new ScheduleOfOfferDto();

		try {
			String sql = _sql + " WHERE a.sfid = :sfId OR a.uuid__c = :sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = ScheduleOfOfferDto.convert(t);
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
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */

	public ScheduleOfOfferDto getBy(String scheduleNo, String clientName) {
		ScheduleOfOfferDto res = new ScheduleOfOfferDto();

		try {
			String sql = _sql;
			sql += " WHERE a.schedule_no__c = :scheduleNo AND a.client_name__c = :clientName";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("scheduleNo", scheduleNo);
			q.setParameter("clientName", clientName);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = ScheduleOfOfferDto.convert(t);
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
	 * Get detail by
	 * 
	 * @param sfId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ScheduleOfOfferDetailDto getDetailBy(Integer id) {
		ScheduleOfOfferDetailDto res = new ScheduleOfOfferDetailDto();

		try {
			String sql = ZFile.read(_path + "detail.sql");
			sql += " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Credit note
			sql = ZFile.read(_path + "detail_credit_note.sql");
			sql += " WHERE a.external_id__c = :id";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			List<Object[]> lc = q.getResultList();

			// Invoice
			sql = ZFile.read(_path + "detail_invoice.sql");
			sql += " WHERE a.external_id__c = :id";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			List<Object[]> li = q.getResultList();

			// Attachment
			sql = ZFile.read(_path + "detail_attachment.sql");
			sql += " WHERE a.external_id__c = :id AND a.isdeleted = FALSE AND a.isactive__c = TRUE";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			List<Object[]> la = q.getResultList();

			// CustomerDropdown
			sql = ZFile.read(_path + "detail_customer.sql");
			sql += " WHERE a.id = :id";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			List<Object[]> ld = q.getResultList();

			// Supplier Dropdown
			sql = ZFile.read(_path + "detail_customer.sql");
			sql += " WHERE a.id = :id";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			List<Object[]> ls = q.getResultList();

			// Convert
			res = ScheduleOfOfferDetailDto.convert(t, lc, li, la, ld, ls);
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
	 * Get detail by
	 * 
	 * @param sfId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ScheduleOfOfferDetailDto getDetailBy(String sfId) {
		ScheduleOfOfferDetailDto res = new ScheduleOfOfferDetailDto();

		try {
			String sql = ZFile.read(_path + "detail.sql");
			sql += " WHERE a.sfid = :sfId OR a.uuid__c = :sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			Object[] t = (Object[]) q.getSingleResult();

			// Credit note
			sql = ZFile.read(_path + "detail_credit_note.sql");
			sql += " WHERE a.schedule_of_offer__c = :sfId OR a.parent_uuid__c = :sfId";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> lc = q.getResultList();

			// Invoice
			sql = ZFile.read(_path + "detail_invoice.sql");
			sql += " WHERE a.schedule_of_offer__c = :sfId OR a.parent_uuid__c = :sfId";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> li = q.getResultList();

			// Attachment
			sql = ZFile.read(_path + "detail_attachment.sql");
			sql += " WHERE (a.schedule_of_offer__c = :sfId  OR a.parent_uuid__c = :sfId) AND a.isdeleted = FALSE AND a.isactive__c = TRUE";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> la = q.getResultList();

			// CustomerDropdown
			sql = ZFile.read(_path + "detail_customer.sql");
			sql += " WHERE a.sfid = :sfId OR a.uuid__c = :sfId AND b.status__c = 'Activated'";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> ld = q.getResultList();

			// Supplier Dropdown
			sql = ZFile.read(_path + "detail_supplier.sql");
			sql += " WHERE a.sfid = :sfId OR a.uuid__c = :sfId";

			// Execute
			q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			List<Object[]> ls = q.getResultList();

			// Convert
			res = ScheduleOfOfferDetailDto.convert(t, lc, li, la, ld, ls);
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
	 * Get acceptance date
	 * 
	 * @param clientAccount
	 * @return
	 */
	public Date getAcceptanceDate(String clientAccount) {
		Date res = null;

		try {
			String sql = ZFile.read(_path + "getAcceptanceDate.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("clientAccount", clientAccount);
			res = (Date) q.getSingleResult();

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

				if ("createdDate".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.createddate " + direction;
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
			res = ScheduleOfOfferDto.convert(t);
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
			ScheduleOfOfferFilter filter = ScheduleOfOfferFilter.convert(o);
			String clientName = filter.getClient();
			String clientAccount = filter.getClientAccount();
			String portalStatus = filter.getPortalStatus();
			Date fr = filter.getFrCreatedDate();
			Date to = filter.getToCreatedDate();
			String documentType = filter.getDocumentType();
			String name = filter.getName();
			Utils.toString(filter, true);

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
			if (!documentType.isEmpty()) {
				where += " AND a.document_type__c = :documentType";
			}
			if (!name.isEmpty()) {
				where += " AND a.name ILIKE :name";
			}

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			res = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":clientName");
				if (i > 0) {
					res.setParameter("clientName", clientName);
				}
				i = where.indexOf(":clientAccount");
				if (i > 0) {
					res.setParameter("clientAccount", clientAccount);
				}
				i = where.indexOf(":portalStatus");
				if (i > 0) {
					res.setParameter("portalStatus", portalStatus);
				}

				if (fr != null && to != null) {
					fr = ZDate.getStartOfDay(fr);
					to = ZDate.getEndOfDay(to);

					System.out.println("createQuery -> fr: " + fr);
					System.out.println("createQuery -> to: " + to);

					res.setParameter("to", to);
					res.setParameter("fr", fr);
				} else if (fr != null && to == null) {
					fr = ZDate.getStartOfDay(fr);
					res.setParameter("fr", fr);
				} else if (fr == null && to != null) {
					to = ZDate.getEndOfDay(to);
					res.setParameter("to", to);
				}
				i = where.indexOf(":documentType");
				if (i > 0) {
					res.setParameter("documentType", documentType);
				}
				i = where.indexOf(":name");
				if (i > 0) {
					res.setParameter("name", name);
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