package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.CreditNote;

public interface CreditNoteDao extends CrudRepository<CreditNote, Integer> {
	@Query("FROM CreditNote a WHERE a.id = :id")
	public CreditNote getBy(@Param("id") int id);

	@Query("FROM CreditNote")
	public List<CreditNote> search();
}