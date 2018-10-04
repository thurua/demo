package com.ifs.eportal.dal;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.dto.ClientAccountDto;
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

	private String _sql;

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountDao() {
		_sql = "SELECT \r\n" + "	a.id, now() activated_on__c , a.account_type__c, a.client_account__c, \r\n"
				+ "	'a.factoring_type__c', b.name record_type_name, 'c.name',  a.name, a.program_name__c, \r\n"
				+ "	'a. verification__c', 'a.verification_exceeding_invoice_amount__c',  a.status__c \r\n"
				+ "FROM salesforce.client_account__c a \r\n" + "JOIN salesforce.recordtype b \r\n"
				+ "	ON '012p00000006IAaAAM' = b.sfid ";
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public ClientAccountDto getBy(Integer id) {
		String sql = _sql + " WHERE a.id = :id";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("id", id);
		Object[] i = (Object[]) q.getSingleResult();

		// Convert
		ClientAccountDto res = ClientAccountDto.convert(i);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param clientAccount
	 * @param client
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClientAccountDto> getBy(String clientAccount, String client) {
		String sql = _sql + " WHERE a.client_account__c = :clientAccount AND a.client__c = :client";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("clientAccount", clientAccount);
		q.setParameter("client", client);
		List<Object[]> l = q.getResultList();

		// Convert
		List<ClientAccountDto> res = ClientAccountDto.convert(l);
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

			if ("client".equals(field)) {
				if (!orderBy.isEmpty()) {
					orderBy += ",";
				}
				orderBy += " a.client__c " + direction;
			}
		}

		if (!orderBy.isEmpty()) {
			orderBy = " ORDER BY " + orderBy;
		}

		// Execute to count all
		String sql = "SELECT \r\n" + "	count(*)\r\n" + "FROM salesforce.client_account__c a";
		String limit = "";
		Query q = createQuery(sql, filter, limit);
		BigInteger total = (BigInteger) q.getSingleResult();
		req.setTotal(total.longValue());

		// Execute to search
		sql = _sql;
		limit = orderBy + " OFFSET " + offset + " LIMIT " + size;
		q = createQuery(sql, filter, limit);
		List<Object[]> l = q.getResultList();

		return ClientAccountDto.convert(l);
	}

	/**
	 * Create query to prevent SQL injection
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		ClientAccountFilter filter = ClientAccountFilter.convert(o);
		String client = filter.getClient();

		// Where
		String where = "";
		if (!client.isEmpty()) {
			where += " AND a.client__c = :client ";
		}

		// Replace first
		if (!where.isEmpty()) {
			where = where.replaceFirst("AND", "WHERE");
		}

		Query q = _em.createNativeQuery(sql + where + limit);

		// Set parameter
		if (!where.isEmpty()) {
			int i = where.indexOf(":client");
			if (i > 0) {
				q.setParameter("client", client);
			}
		}

		return q;
	}

	// end
}