package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.ClientAccount;

public interface ClientAccountDao extends CrudRepository<ClientAccount, Integer> {
	@Query("FROM ClientAccount a WHERE a.id = :id")
	public ClientAccount getBy(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT a.id , a.name  FROM salesforce.client_account__c a WHERE a.client__c = :clientId")
	public List<Object[]> search(@Param("clientId") String clientId);
}