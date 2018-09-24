package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.PortalUserAccess;

public interface PortalUserAccessDao extends CrudRepository<PortalUserAccess, Integer> {

	@Query("FROM PortalUserAccess a WHERE a.id = :id")
	public PortalUserAccess getBy(@Param("id") int id);

	@Query("FROM PortalUserAccess")
	public List<PortalUserAccess> search();
}