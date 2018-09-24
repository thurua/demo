package com.rdp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rdp.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	@Query("FROM User a WHERE (a.company IS NULL OR :company = '' OR UPPER(a.company) LIKE CONCAT('%', :company, '%')) "
			+ "AND ((a.status IS NULL OR :status = '' OR a.status = :status)) AND (:username = '' OR UPPER(a.userName) LIKE CONCAT('%', :username, '%'))")
	public List<User> search(@Param("company") String company, @Param("status") String status,
			@Param("username") String username);

	@Query("FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);

	@Query("FROM User u WHERE u.userName = :name")
	public User getUserByUserName(@Param("name") String name);
}