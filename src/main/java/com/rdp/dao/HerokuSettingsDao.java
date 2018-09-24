package com.rdp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rdp.model.HerokuSettings;

public interface HerokuSettingsDao extends JpaRepository<HerokuSettings, String> {
	@Query("FROM HerokuSettings WHERE param = :param AND key = 'PROMO_CODE'")
	public List<HerokuSettings> checkExist(@Param("param") String param);
}