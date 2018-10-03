package com.ifs.eportal.dal;

import org.springframework.data.repository.CrudRepository;

import com.ifs.eportal.model.PasswordChangeHistory;

public interface AppSettingDao extends CrudRepository<PasswordChangeHistory, String> {
}