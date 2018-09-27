package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.ScheduleOfOffer;

public interface ScheduleOfOfferDao extends JpaRepository<ScheduleOfOffer, Integer> {
	@Query("FROM ScheduleOfOffer a WHERE a.id = :id")
	public ScheduleOfOffer getBy(@Param("id") int id);

	@Query("FROM ScheduleOfOffer")
	public List<ScheduleOfOffer> search();

	@Query(nativeQuery = true, value = "SELECT a.schedule_no__c \"scheduleNo\", a.client_account__c \"clientAccount\", a.schedule_date__c \"scheduleDate\", '' \"documentType\", a.schedule_status__c \"scheduleStatus\", a.createddate  \"createdDate\" FROM salesforce.schedule_of_offer__c a where  a.client_name__c=:client and a.client_account__c =:clientAccount and a.schedule_status__c=:status order by  a.schedule_date__c desc")
	public List<Object[]> search(@Param("client") String client, @Param("clientAccount") String clientAccount,
			@Param("status") String status);

}