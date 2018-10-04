package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.ScheduleOfOfferAttachment;

/**
 * 
 * @author ToanNguyen 2018-Oct-04
 *
 */
public interface ScheduleOfOfferAttachmentDao extends CrudRepository<ScheduleOfOfferAttachment, Integer> {
	@Query("FROM ScheduleOfOfferAttachment a WHERE a.uploadedBy = :uploadedBy AND a.isActive = TRUE AND a.isDeleted = FALSE")
	public List<ScheduleOfOfferAttachment> getBy(@Param("uploadedBy") String uploadedBy);
}