package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.PortalUser;

public interface PortalUserDao extends CrudRepository<PortalUser, Integer> {
	@Query("FROM PortalUser a WHERE a.id = :id")
	public PortalUser getBy(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT a.id, a.email__c, a.password__c, a.password_hash__c, a.pass_reminder_token__c, a.pass_reminder_expire__c, a.first_name__c, a.last_name__c, a.client__c, b.name FROM salesforce.portal_user__c a JOIN salesforce.account b ON a.client__c = b.sfid WHERE a.email__c = :email")
	public List<Object[]> getBy(@Param("email") String email);

	@Query("FROM PortalUser")
	public List<PortalUser> search();

	@Query(nativeQuery = true, value = "SELECT b.name FROM salesforce.portal_user__c a JOIN salesforce.portal_role__c b on a.role__c = b.sfid WHERE a.id = :id")
	public List<String> getRoleBy(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT a.id, a.email__c, a.first_name__c, a.last_name__c, a.salutation__c, b.name role_name, c.name company_name, a.mobile__c FROM salesforce.portal_user__c a JOIN salesforce.portal_role__c b on a.role__c = b.sfid JOIN salesforce.account c on a.client__c = c.sfid WHERE a.id = :id")
	public List<Object[]> getProfile(@Param("id") int id);
}