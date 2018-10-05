package com.ifs.eportal.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.RecordTypeDao;
import com.ifs.eportal.dto.RecordTypeDto;
import com.ifs.eportal.model.RecordType;

@Service(value = "recordTypeService")
@Transactional
public class RecordTypeService {
	// region -- Fields --

	@Autowired
	private RecordTypeDao recordTypeDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(RecordType m) {
		recordTypeDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public RecordTypeDto read(int id) {
		RecordTypeDto res = recordTypeDao.getBy(id);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param sObjectType
	 * @param name
	 * @return
	 */
	public RecordTypeDto getBy(String sObjectType, String name) {
		RecordTypeDto res = recordTypeDao.getBy(sObjectType, name);
		return res;
	}

	// end
}