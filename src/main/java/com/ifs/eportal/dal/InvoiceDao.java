package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZDate;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.CustomDto;
import com.ifs.eportal.dto.InvoiceDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.dto.SummaryDto;
import com.ifs.eportal.filter.InvoiceFilter;
import com.ifs.eportal.model.Invoice;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
 *
 */
@Service(value = "invoiceDao")
public class InvoiceDao implements Repository<Invoice, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(Invoice entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public Invoice read(Integer id) {
		return _em.find(Invoice.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public Invoice update(Invoice entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(Invoice entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _sql;

	private String _path;

	private static final Logger _log = Logger.getLogger(InvoiceDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public InvoiceDao() {
		_path = ZFile.getPath("/sql/" + InvoiceDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public InvoiceDto getBy(Integer id) {
		InvoiceDto res = new InvoiceDto();

		try {
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] i = (Object[]) q.getSingleResult();

			// Convert
			res = InvoiceDto.convert(i);
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
	public InvoiceDto getBy(String sfId) {
		InvoiceDto res = new InvoiceDto();

		try {
			String sql = _sql + " WHERE a.sfid = :sfId OR a.uuid__c = :sfId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("sfId", sfId);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = InvoiceDto.convert(t);
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
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceDto> getBy(List<String> names, String clientAccountId) {
		List<InvoiceDto> res = new ArrayList<InvoiceDto>();

		try {
			String sql = _sql;
			sql += " WHERE a.name IN :names AND a.client_account__c = :clientAccountId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = InvoiceDto.convert(l);
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
	 * task IFS-1044, 1055
	 * 
	 * @param clientAccountId
	 * @return
	 */
	public CustomDto getAverage(String clientAccountId) {
		CustomDto res = new CustomDto();

		try {
			String sql = "SELECT '1' as code, AVG(invoice_amount__c) as value " + "FROM salesforce.invoice__c a "
					+ "WHERE a.client_account__c = :clientAccountId";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("clientAccountId", clientAccountId);
			Object[] i = (Object[]) q.getSingleResult();

			// Convert
			res = CustomDto.convert(i);
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
	 * 
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getOverdueOutstanding(List<String> names, String clientAccountId) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getOverdueOutstanding.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * 
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getOverdueOutstandingSup(List<String> names, String clientAccountId) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getOverdueOutstandingSup.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * 
	 * @param l
	 * @param caId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getDisputedOutstanding(List<String> names, String clientAccountId) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getDisputedOutstanding.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * 
	 * @param names
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getInvoiceSumary(List<String> names, String clientAccountId, Date d) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getInvoiceSumary.sql");

			// Execute
			Date fr = (new DateTime(d)).minusYears(1).withDayOfMonth(1).toDate();
			Date to = (new DateTime(d)).plusMonths(1).withDayOfMonth(1).minusDays(1).toDate();
			Query q = _em.createNativeQuery(sql);
			q.setParameter("fr", fr);
			q.setParameter("to", to);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * Get invoice summary
	 * 
	 * @param clientAccount
	 * @param fr
	 * @param to
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SummaryDto> getInvoiceSummary(String clientAccount, Date fr, Date to) {
		List<SummaryDto> res = new ArrayList<SummaryDto>();

		try {
			String sql = ZFile.read(_path + "getInvoiceSummary.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("fr", fr);
			q.setParameter("to", to);
			q.setParameter("clientAccount", clientAccount);
			List<Object[]> l = q.getResultList();

			// Convert
			res = SummaryDto.convert(l);
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
	 * 
	 * @param names
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getTotalOutstanding(List<String> names, String clientAccountId) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getTotalOutstanding.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * 
	 * @param names
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getTotalOutstandingSup(List<String> names, String clientAccountId) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getTotalOutstandingSup.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * 
	 * @param names
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getTotalOutstandingAmount(List<String> names, String clientAccountId) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {

			String sql = ZFile.read(_path + "getTotalOutstandingAmount.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * 
	 * @param names
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getInvoiceAvg(List<String> names, String clientAccountId) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getInvoiceAvg.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * 
	 * @param names
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getInvoiceAvgSup(List<String> names, String clientAccountId) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getInvoiceAvgSup.sql");

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * 
	 * @param names
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getCreditSumary(List<String> names, String clientAccountId, Date d) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = ZFile.read(_path + "getCreditSumary.sql");

			// Execute
			Date fr = (new DateTime(d)).minusYears(1).withDayOfMonth(1).toDate();
			Date to = (new DateTime(d)).plusMonths(1).withDayOfMonth(1).minusDays(1).toDate();
			Query q = _em.createNativeQuery(sql);
			q.setParameter("fr", fr);
			q.setParameter("to", to);
			q.setParameter("names", names);
			q.setParameter("clientAccountId", clientAccountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	 * Select invoice number exist in db.
	 * 
	 * @param names
	 * @param accountId
	 * @param amendSchedule
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getListBy(List<String> names, String accountId, boolean amendSchedule) {
		List<CustomDto> res = new ArrayList<CustomDto>();

		try {
			String sql = "SELECT a.name as code, NULL as value FROM salesforce.invoice__c a "
					+ "WHERE a.name IN :names " + "AND a.client_name__c = :accountId ";
			if (amendSchedule) {
				sql += "AND a.status__c != 'Rejected' AND a.status__c != 'Reversed' ";
			}
			sql += "GROUP BY a.name";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("names", names);
			q.setParameter("accountId", accountId);
			List<Object[]> l = q.getResultList();

			// Convert
			res = CustomDto.convert(l);
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
	public List<InvoiceDto> search(PagingReq req) {
		List<InvoiceDto> res = new ArrayList<InvoiceDto>();

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
			res = InvoiceDto.convert(t);
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
			InvoiceFilter filter = InvoiceFilter.convert(o);
			String clientName = filter.getClient();
			String clientAccount = filter.getClientAccount();
			String status = filter.getStatus();
			String scheduleNo = filter.getScheduleNo();
			String documentType = filter.getDocumentType();
			String customer = filter.getCustomer();
			String supplier = filter.getSupplier();
			String invoiceNo = filter.getInvoiceNo();
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
			if (!status.isEmpty()) {
				where += " AND a.status__c = :status";
			}
			if (!scheduleNo.isEmpty()) {
				where += " AND c.name ILIKE :scheduleNo";
			}
			if (!invoiceNo.isEmpty()) {
				where += " AND a.name ILIKE :invoiceNo";
			}
			if (!documentType.isEmpty()) {
				where += " AND a.document_type__c = :documentType";
			}
			if (!customer.isEmpty()) {
				where += " AND a.customer__c = :customer";
			}
			if (!supplier.isEmpty()) {
				where += " AND a.customer__c = :supplier";
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
				where = where.replaceFirst("AND", "WHERE c.schedule_status__c = 'Submitted' AND");
			}

			q = _em.createNativeQuery(sql + where + limit);

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
					q.setParameter("scheduleNo", scheduleNo);
				}
				i = where.indexOf(":documentType");
				if (i > 0) {
					q.setParameter("documentType", documentType);
				}
				i = where.indexOf(":customer");
				if (i > 0) {
					q.setParameter("customer", customer);
				}
				i = where.indexOf(":supplier");
				if (i > 0) {
					q.setParameter("supplier", supplier);
				}
				i = where.indexOf(":invoiceNo");
				if (i > 0) {
					q.setParameter("invoiceNo", invoiceNo);
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