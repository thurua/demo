package com.ifs.eportal.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.model.ClientAccountCustomer;

/**
 * 
 * @author HoanNguyen 2018-Oct-2
 *
 */
@Service(value = "clientAccountCustomerDao")
public class ClientAccountCustomerDao implements Repository<ClientAccountCustomer, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(ClientAccountCustomer entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public ClientAccountCustomer read(Integer id) {
		return _em.find(ClientAccountCustomer.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public ClientAccountCustomer update(ClientAccountCustomer entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(ClientAccountCustomer entity) {
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
	public ClientAccountCustomerDao() {
		_path = ZFile.getPath("/sql/" + ClientAccountCustomerDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	@SuppressWarnings("unchecked")
	public List<ClientAccountCustomerDto> getByClientId(String clientId) {
		String sql = _sql + " WHERE a.client_account__c = :clientId";

		// Execute
		Query q = _em.createNativeQuery(sql);
		q.setParameter("clientId", clientId);
		List<Object[]> l = q.getResultList();

		// Convert
		List<ClientAccountCustomerDto> res = ClientAccountCustomerDto.convert(l);
		return res;
	}

	// end
}