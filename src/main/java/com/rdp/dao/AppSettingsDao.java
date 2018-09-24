package com.rdp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdp.model.AppSettings;

public interface AppSettingsDao extends JpaRepository<AppSettings, String> {
}