package com.ifs.eportal.dal;

import org.springframework.data.repository.CrudRepository;

import com.ifs.eportal.model.UserRole;

public interface UserRoleDao extends CrudRepository<UserRole, Integer> {

}