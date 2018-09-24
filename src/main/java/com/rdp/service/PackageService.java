package com.rdp.service;

import java.util.List;

import com.rdp.model.Package;

public interface PackageService {
	List<Package> search();

	Package search(String sfid, String duration);

	List<Package> searchPromo();

	Package searchPromo(String sfid, String duration);

	boolean checkExist(String promoCode);
}