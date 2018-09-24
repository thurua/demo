package com.rdp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rdp.dao.HerokuSettingsDao;
import com.rdp.dao.PackageDao;
import com.rdp.model.HerokuSettings;
import com.rdp.model.Package;
import com.rdp.service.PackageService;

@Service(value = "packageService")
@Transactional
public class PackageServiceImpl implements PackageService {
	// region -- Fields --

	@Autowired
	private PackageDao packageDao;

	@Autowired
	private HerokuSettingsDao herokuSettingsDao;

	// end

	// region -- Methods --

	@Override
	public List<Package> search() {
		return packageDao.search();
	}

	@Override
	public Package search(String sfid, String duration) {
		return packageDao.search(sfid, duration);
	}

	@Override
	public List<Package> searchPromo() {
		return packageDao.searchPromo();
	}

	@Override
	public Package searchPromo(String sfid, String duration) {
		return packageDao.searchPromo(sfid, duration);
	}

	@Override
	public boolean checkExist(String promoCode) {
		// Handle
		List<HerokuSettings> tmp = herokuSettingsDao.checkExist(promoCode);
		return tmp.size() > 0;
	}

	// end
}