package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.Code;

public interface CodeDao extends CrudRepository<Code, Integer> {
	@Query("FROM Code a WHERE a.codeType = :codeType AND a.status = 'ACT' AND a.isDeleted = FALSE")
	public List<Code> getBy(@Param("codeType") String codeType);
}