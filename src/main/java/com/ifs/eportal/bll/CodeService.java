package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.CodeDao;
import com.ifs.eportal.model.Code;

@Service(value = "codeService")
@Transactional
public class CodeService {
	// region -- Fields --

	@Autowired
	private CodeDao codeDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public List<Code> getBy(String codeType) {
		List<Code> res = codeDao.getBy(codeType);
		return res;
	}

	// end
}