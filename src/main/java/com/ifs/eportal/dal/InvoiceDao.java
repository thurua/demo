package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.CustomDto;
import com.ifs.eportal.dto.InvoiceDto;
import com.ifs.eportal.dto.SortDto;
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

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public InvoiceDao() {
//		_sql = "SELECT \r\n"
//				+ "	a.id, a.customer_branch__c, a.customer_from_excel__c, a.currencyisocode, a.invoice_date__c, \r\n"
//				+ "	a.client_name__c ,a.po__c, a.client_remarks__c,  a.customer__c, a.document_type__c, \r\n"
//				+ "	a.recordtypeid, a.schedule_of_offer__c, a.client_account__c, a.name, a.status__c\r\n"
//				+ "FROM salesforce.invoice__c a ";

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
		_sql = ZFile.read(_path + "detail.sql");
		String sql = _sql + " WHERE a.id = :id";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("id", id);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		InvoiceDto res = InvoiceDto.convert(i);
		return res;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceDto> getBy(List<String> names, String clientAccountId) {
		_sql = ZFile.read(_path + "detail.sql");
		String sql = _sql + " WHERE a.name in :names " + "AND a.client_account__c = :clientAccountId";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("names", names);
		q.setParameter("clientAccountId", clientAccountId);
		List<Object[]> l = q.getResultList();

		// Convert
		List<InvoiceDto> res = InvoiceDto.convert(l);
		return res;
	}

	/**
	 * task IFS-1044, 1055
	 * 
	 * @param clientAccountId
	 * @return
	 */
	public CustomDto getAverage(String clientAccountId) {
		String sql = "SELECT '1' as code, AVG(invoice_amount__c) as value " + "FROM salesforce.invoice__c a "
				+ "WHERE a.client_account__c = :clientAccountId";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("clientAccountId", clientAccountId);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		CustomDto res = CustomDto.convert(i);
		return res;
	}

	/**
	 * 
	 * @param clientAccountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomDto> getOverdueOutstanding(List<String> names, String clientAccountId) {
		String sql = "SELECT b.name as code, SUM(outstanding_amount__c) as value " + "FROM salesforce.invoice__c a "
				+ "JOIN salesforce.account b on a.customer__c = b.sfid " + "WHERE b.name in :names "
				+ "AND a.added_credit_period__c < current_date" + "AND a.client_account__c = :clientAccountId"
				+ "GROUP BY b.name";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("names", names);
		q.setParameter("clientAccountId", clientAccountId);
		List<Object[]> l = q.getResultList();

		// Convert
		List<CustomDto> res = CustomDto.convert(l);
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
		String sql = "SELECT b.name as code, SUM(outstanding_amount__c) as value " + "FROM salesforce.invoice__c a "
				+ "JOIN salesforce.account b on a.customer__c = b.sfid " + "WHERE b.name in :names "
				+ "AND a.status__c = 'Disputed' " + "AND a.client_account__c = :clientAccountId" + "GROUP BY b.name";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("names", names);
		q.setParameter("clientAccountId", clientAccountId);
		List<Object[]> l = q.getResultList();

		// Convert
		List<CustomDto> res = CustomDto.convert(l);
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
		String sql = "SELECT b.name as code, SUM(invoice_amount__c) as value " + "FROM salesforce.invoice__c a "
				+ "JOIN salesforce.account b on a.customer__c = b.sfid " + "WHERE b.name in :names "
				+ "AND a.invoice_date__c >= :fr " + "AND a.invoice_date__c <= :to "
				+ "AND a.client_account__c = :clientAccountId" + "GROUP BY b.name";

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
		List<CustomDto> res = CustomDto.convert(l);
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
		String sql = "SELECT b.name as code, SUM(outstanding_amount__c) as value " + "FROM salesforce.invoice__c a "
				+ "JOIN salesforce.account b on a.customer__c = b.sfid " + "WHERE b.name in :names "
				+ "AND a.client_account__c = :clientAccountId " + "GROUP BY b.name";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("names", names);
		q.setParameter("clientAccountId", clientAccountId);
		List<Object[]> l = q.getResultList();

		// Convert
		List<CustomDto> res = CustomDto.convert(l);
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
		String sql = "SELECT b.sfid as code, SUM(outstanding_amount__c) as value " + "FROM salesforce.invoice__c a "
				+ "JOIN salesforce.account b on a.customer__c = b.sfid " + "WHERE b.name in :names "
				+ "AND a.client_account__c = :clientAccountId " + "GROUP BY b.name";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("names", names);
		q.setParameter("clientAccountId", clientAccountId);
		List<Object[]> l = q.getResultList();

		// Convert
		List<CustomDto> res = CustomDto.convert(l);
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
		String sql = "SELECT b.name as code, AVG(invoice_amount__c) as value " + "FROM salesforce.invoice__c a "
				+ "JOIN salesforce.account b on a.customer__c = b.sfid " + "WHERE b.name in :names "
				+ "AND a.client_account__c = :clientAccountId " + "GROUP BY b.name";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("names", names);
		q.setParameter("clientAccountId", clientAccountId);
		List<Object[]> l = q.getResultList();

		// Convert
		List<CustomDto> res = CustomDto.convert(l);
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
		String sql = "SELECT b.name as code, SUM(credit_amount__c) as value " + "FROM salesforce.credit_note__c a "
				+ "JOIN salesforce.account b on a.customer__c = b.sfid " + "WHERE b.name in :names "
				+ "AND a.credit_note_date__c >= :fr " + "AND a.credit_note_date__c <= :to "
				+ "AND a.client_account__c = :clientAccountId" + "GROUP BY b.name";

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
		List<CustomDto> res = CustomDto.convert(l);
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
		}

		if (!orderBy.isEmpty()) {
			orderBy = " ORDER BY " + orderBy;
		}

		// Execute to count all
		String sql = "SELECT \r\n" + "	count(*)\r\n" + "FROM salesforce.invoice__c a ";
		String limit = "";
		Query q = createQuery(sql, filter, limit);
		BigInteger total = (BigInteger) q.getSingleResult();
		req.setTotal(total.longValue());

		// Execute to search
		sql = _sql;
		limit = orderBy + " OFFSET " + offset + " LIMIT " + size;
		q = createQuery(sql, filter, limit);
		List<Object[]> l = q.getResultList();

		return InvoiceDto.convert(l);
	}

	/**
	 * Create query to prevent SQL injection
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		InvoiceFilter filter = InvoiceFilter.convert(o);
		String status = filter.getStatus();

		// Where
		String where = "";
		if (!status.isEmpty()) {
			where += " AND a.status__c = :status";
		}

		// Replace first
		if (!where.isEmpty()) {
			where = where.replaceFirst("AND", "WHERE");
		}

		Query q = _em.createNativeQuery(sql + where + limit);

		// Set parameter
		if (!where.isEmpty()) {
			int i = where.indexOf(":status");
			if (i > 0) {
				q.setParameter("status", status);
			}
		}

		return q;
	}

	// end
}