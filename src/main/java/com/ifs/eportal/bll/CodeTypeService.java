package com.ifs.eportal.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.CodeTypeDao;

@Service(value = "codeTypeService")
@Transactional
public class CodeTypeService {
	// region -- Fields --

	@Autowired
	private CodeTypeDao codeTypeDao;

	// end

	// region -- Methods --

	// end
}