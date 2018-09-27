package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.dto.ScheduleOfOfferDto;
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
		_sql = "SELECT \r\n" + "	a.id, a.schedule_no__c, a.client_account__c, a.schedule_date__c, \r\n"
				+ "	a.schedule_status__c, a.createddate, a.document_type__c \r\n"
				+ "FROM salesforce.schedule_of_offer__c a ";
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

	@SuppressWarnings("unchecked")
	public List<ScheduleOfOfferDto> search(PagingReq req) {
		List<ScheduleOfOfferDto> res = new ArrayList<ScheduleOfOfferDto>();

		// Get data
		ScheduleOfOfferFilter filter = ScheduleOfOfferFilter.convert(req.getFilter());
		String clientName = filter.getClient();
		String clientAccount = filter.getClientAccount();
		String scheduleStatus = filter.getScheduleStatus();

		int page = req.getPage();
		int size = req.getSize();
		String sort = req.getSort();
		int offset = (page - 1) * size;

		// Where
		String where = "";
		if (!clientName.isEmpty()) {
			where += " AND a.client_name__c = '" + clientName + "'";
		}
		if (!clientAccount.isEmpty()) {
			where += " AND a.client_account__c = '" + clientAccount + "'";
		}
		if (!scheduleStatus.isEmpty()) {
			where += " AND a.schedule_status__c = '" + scheduleStatus + "'";
		}

		// Replace first
		if (!where.isEmpty()) {
			where = where.replaceFirst("AND", "WHERE");
		}

		// Sort by
		String sortBy = "";
		if (sort.isEmpty() || "ASC".equals(sort)) {
			sortBy = "a.id ASC";
		} else {
			sortBy = "a.id DESC";
		}

		// Execute to count all
		String sql = "SELECT \r\n" + "	count(*) \r\n" + "FROM salesforce.schedule_of_offer__c a " + where;
		Query q = _em.createNativeQuery(sql);
		BigInteger total = (BigInteger) q.getSingleResult();
		req.setTotal(total.longValue());

		// Execute to search
		String limit = " ORDER BY " + sortBy + " OFFSET " + offset + " LIMIT " + size;
		sql = _sql + where + limit;
		q = _em.createNativeQuery(sql);
		List<Object[]> l = q.getResultList();

		// Convert
		for (Object[] i : l) {
			ScheduleOfOfferDto o = ScheduleOfOfferDto.convert(i);
			res.add(o);
		}

		return res;
	}

	// end
}