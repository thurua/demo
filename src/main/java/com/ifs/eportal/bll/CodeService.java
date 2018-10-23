package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.CodeDao;
import com.ifs.eportal.model.Code;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
@Service(value = "codeService")
@Transactional
public class CodeService {
	// region -- Fields --

	@Autowired
	private CodeDao codeDao;

	// end

	// region -- Methods --

	/**
	 * Read by
	 * 
	 * @param codeType
	 * @return
	 */
	public List<Code> read(String codeType) {
		List<Code> res = codeDao.read(codeType);
		return res;
	}

	// end
}