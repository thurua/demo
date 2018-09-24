package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.Account;

public interface AccountDao extends CrudRepository<Account, Integer> {
	@Query("FROM Account a WHERE a.id = :id")
	public Account getBy(@Param("id") int id);

	@Query("FROM Account")
	public List<Account> search();
}