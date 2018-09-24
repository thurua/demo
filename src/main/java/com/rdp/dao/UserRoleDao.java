package com.rdp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rdp.model.UserRole;

@Repository
public interface UserRoleDao extends CrudRepository<UserRole, Integer> {
	@Query(nativeQuery = true, value = "SELECT r.name FROM role r INNER JOIN userrole ur on r.id = ur.role_id where ur.user_id = :userId")
	public List<String> getRoleByUserId(@Param("userId") int userId);

	@Query(nativeQuery = true, value = "SELECT r.id, r.name from role r INNER JOIN userrole ur on r.id = ur.role_id WHERE ur.user_id = :userId")
	public List<Object[]> getUserRoleByUserId(@Param("userId") int userId);
}