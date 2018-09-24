package com.rdp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rdp.model.Lead;

@Repository
public interface LeadDao extends CrudRepository<Lead, Integer> {
	@Query("FROM Lead a WHERE (a.company IS NULL OR :company = '' OR UPPER(a.company) LIKE CONCAT('%', :company, '%'))")
	public List<Lead> search(@Param("company") String company);

	@Query("FROM Lead a WHERE a.name = :name AND a.postalCode = :postalCode")
	public List<Lead> search(@Param("name") String name, @Param("postalCode") String postalCode);

	@Query(nativeQuery = true, value = "SELECT * FROM salesforce.lead WHERE ebs_number__c = :ebsNumber AND nric_no__c = :nricNo")
	public List<Lead> checkExist(@Param("ebsNumber") String ebsNumber, @Param("nricNo") String nricNo);
}