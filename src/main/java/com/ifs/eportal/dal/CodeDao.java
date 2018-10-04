package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.Code;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
public interface CodeDao extends CrudRepository<Code, Integer> {
	@Query("FROM Code a WHERE a.codeType = :codeType AND a.status = 'ACT' AND a.isDeleted = FALSE")
	public List<Code> read(@Param("codeType") String codeType);
}