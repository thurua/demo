package com.ifs.eportal.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifs.eportal.model.Invoice;

public interface InvoiceDao extends CrudRepository<Invoice, Integer> {
	@Query("FROM Invoice a WHERE a.id = :id")
	public Invoice getBy(@Param("id") int id);

	@Query("FROM Invoice")
	public List<Invoice> search();
}