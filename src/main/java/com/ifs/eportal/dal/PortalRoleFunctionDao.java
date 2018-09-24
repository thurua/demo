package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.PortalRoleFunction;

public interface PortalRoleFunctionDao extends CrudRepository<PortalRoleFunction, Integer> {
	@Query("FROM PortalRoleFunction a WHERE a.id = :id")
	public PortalRoleFunction getBy(@Param("id") int id);

	@Query("FROM PortalRoleFunction")
	public List<PortalRoleFunction> search();
}