package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.PortalRole;

public interface PortalRoleDao extends CrudRepository<PortalRole, Integer> {
	@Query("FROM PortalRole a WHERE a.id = :id")
	public PortalRole getBy(@Param("id") int id);

	@Query("FROM PortalRole")
	public List<PortalRole> search();
}