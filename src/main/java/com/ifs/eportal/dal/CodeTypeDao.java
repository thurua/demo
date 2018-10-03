package com.ifs.eportal.dal;

import org.springframework.data.repository.CrudRepository;

import com.ifs.eportal.model.CodeType;

public interface CodeTypeDao extends CrudRepository<CodeType, Integer> {
}