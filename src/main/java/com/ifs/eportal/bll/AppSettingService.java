package com.ifs.eportal.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.AppSettingDao;

@Service(value = "appSettingService")
@Transactional
public class AppSettingService {
	// region -- Fields --

	@Autowired
	private AppSettingDao appSettingDao;

	// end

	// region -- Methods --

	// end
}