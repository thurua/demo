package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.ScheduleOfOffer;

public interface ScheduleOfOfferDao extends CrudRepository<ScheduleOfOffer, Integer> {
	@Query("FROM ScheduleOfOffer a WHERE a.id = :id")
	public ScheduleOfOffer getBy(@Param("id") int id);

	@Query("FROM ScheduleOfOffer")
	public List<ScheduleOfOffer> search();
}