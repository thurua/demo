package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.PortalUser;

public interface PortalUserDao extends CrudRepository<PortalUser, Integer> {
	@Query("FROM PortalUser a WHERE a.id = :id")
	public PortalUser getBy(@Param("id") int id);

	@Query("FROM PortalUser a WHERE a.email = :email")
	public PortalUser getBy(@Param("email") String email);

	@Query("FROM PortalUser")
	public List<PortalUser> search();
}