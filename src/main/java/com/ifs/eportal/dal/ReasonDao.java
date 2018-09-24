package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.Reason;

public interface ReasonDao extends CrudRepository<Reason, Integer> {
	@Query("FROM Reason a WHERE a.id = :id")
	public Reason getBy(@Param("id") int id);

	@Query("FROM Reason")
	public List<Reason> search();
}