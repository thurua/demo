package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.SupplierDao;
import com.ifs.eportal.dto.SupplierDto;
import com.ifs.eportal.req.PagingReq;

@Service(value = "supplierService")
@Transactional
public class SupplierService {
	// region -- Fields --

	@Autowired
	private SupplierDao supplierDao;

	// end

	// region -- Methods --

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<SupplierDto> getByClientId(String clientId) {
		return supplierDao.getByClientId(clientId);
	}

	/**
	 * Read by
	 * 
	 * @param req
	 * @return
	 */
	public List<SupplierDto> read(PagingReq req) {
		return supplierDao.search(req);
	}

	// end
}