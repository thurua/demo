package com.rdp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rdp.model.RunningRefNo;

@Repository
public interface RunningRefNoDao extends JpaRepository<RunningRefNo, Integer> {
	@Query("FROM RunningRefNo a WHERE a.seqGroup= :seqGroup AND a.yearCode =:year")
	RunningRefNo getRefNo(@Param("seqGroup") String seqGroup, @Param("year") String year);
}