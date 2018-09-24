package com.ifs.eportal.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.AuthToken;

public interface AuthTokenDao extends CrudRepository<AuthToken, Integer> {
	@Query("FROM AuthToken a WHERE ((a.clientKey = :clientKey AND a.isVerified = FALSE) OR a.module = :module) AND a.createBy = :userId")
	public AuthToken getBy(@Param("clientKey") String clientKey, @Param("module") String module,
			@Param("userId") int userId);
}