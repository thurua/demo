package com.ifs.eportal.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.CurrencyTypeDao;
import com.ifs.eportal.dto.CurrencyTypeDto;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
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
	 * Get by
	 * 
	 * @param isoCode
	 * @return
	 */
	public CurrencyTypeDto getByIsoCode(String isoCode) {
		return currencyTypeDao.getByIsoCode(isoCode);
	}

	// end
}