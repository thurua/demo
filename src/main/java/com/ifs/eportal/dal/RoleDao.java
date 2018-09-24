package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
	@Query(nativeQuery = true, value = "SELECT r.name FROM role r, user_role ur"
			+ " WHERE ur.user_id = :userId AND r.id = ur.role_id")
	public List<String> getRoleByUserId(@Param("userId") int userId);
}