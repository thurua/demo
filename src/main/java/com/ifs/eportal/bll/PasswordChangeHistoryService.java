package com.ifs.eportal.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.PasswordChangeHistoryDao;
import com.ifs.eportal.model.PasswordChangeHistory;

@Service(value = "passwordChangeHistoryService")
@Transactional
public class PasswordChangeHistoryService {
	// region -- Fields --

	@Autowired
	private PasswordChangeHistoryDao passwordChangeHistoryDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(PasswordChangeHistory m) {
		passwordChangeHistoryDao.create(m);
	}

}