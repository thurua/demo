package com.ifs.eportal.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.CurrencyTypeDao;
import com.ifs.eportal.dto.CurrencyTypeDto;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
@Service(value = "currencyTypeService")
@Transactional
public class CurrencyTypeService {
	// region -- Fields --

	@Autowired
	private CurrencyTypeDao currencyTypeDao;

	// end

	// region -- Methods --

	/**
	 * Read by
	 * 
	 * @param isoCode
	 * @return
	 */
	public CurrencyTypeDto read(String isoCode) {
		return currencyTypeDao.read(isoCode);
	}

	// end
}