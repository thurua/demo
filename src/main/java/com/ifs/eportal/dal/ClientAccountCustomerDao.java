package com.ifs.eportal.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountCustomerDao() {
		_sql = "SELECT a.id, c.Name, a.Activation_Date__c, a.Status__c,  a.Customer__c,\r\n"
				+ "a.Verification__c, a.Verification_Exceeding_Invoice_Amount__c,\r\n"
				+ "d.name as fciname, c.name as ccname\r\n" + "FROM salesforce.Client_Account_Customer__c a \r\n"
				+ "JOIN salesforce.account  c on  a.customer__c = c.sfid \r\n"
				+ "JOIN salesforce.fcicountry__c d on d.sfid = a.fci_factor__c ";
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