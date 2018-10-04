package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.ScheduleOfOfferFilter;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Sep-27
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

	private String _sql;

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ScheduleOfOfferDao() {
		_sql = "-- search\r\n" + "SELECT \r\n" + "	a.id, a.schedule_no__c, c.client_account__c, a.schedule_date__c, \r\n"
				+ "	a.portal_status__c, CASE WHEN a.portal_status__c = 'Accepted' THEN 'IFS OPS' ELSE d.name END created_by, \r\n"
				+ "	a.document_type__c, a.sequence__c \r\n" + "FROM salesforce.schedule_of_offer__c a \r\n"
				+ "LEFT JOIN salesforce.portal_user__c b \r\n"
				+ "	ON a.createdby_portaluserid__c = CAST(b.id as VARCHAR) \r\n"
				+ "LEFT JOIN salesforce.client_account__c c \r\n" + "	ON a.client_account__c = c.sfid \r\n"
				+ "LEFT JOIN salesforce.contact d \r\n" + "	ON b.contact__c = d.sfid ";
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDto getBy(Integer id) {
		String sql = _sql + " WHERE a.id = :id";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("id", id);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		ScheduleOfOfferDto res = ScheduleOfOfferDto.convert(i);
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
	public List<ScheduleOfOfferDto> getBy(String scheduleNo, String clientName) {
		String sql = _sql + " WHERE a.schedule_no__c = :scheduleNo AND a.client_name__c = :clientName";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("scheduleNo", scheduleNo);
		q.setParameter("clientName", clientName);
		List<Object[]> l = q.getResultList();

		// Convert
		List<ScheduleOfOfferDto> res = ScheduleOfOfferDto.convert(l);
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

			if ("scheduleStatus".equals(field)) {
				if (!orderBy.isEmpty()) {
					orderBy += ",";
				}
				orderBy += " a.schedule_status__c " + direction;
			}
		}

		if (!orderBy.isEmpty()) {
			orderBy = " ORDER BY " + orderBy;
		}

		// Execute to count all
		String sql = "SELECT \r\n" + "	count(*) \r\n" + "FROM salesforce.schedule_of_offer__c a ";
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
		List<ScheduleOfOfferDto> res = ScheduleOfOfferDto.convert(l);
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

		// Where
		String where = "";
		if (!clientName.isEmpty()) {
			where += " AND a.client_name__c = :clientName";
		}
		if (!clientAccount.isEmpty()) {
			where += " AND a.client_account__c = :clientAccount";
		}
		if (!portalStatus.isEmpty()) {
			where += " AND a.portal_status__c = :portalStatus";
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
		}

		return q;
	}

	// end
}