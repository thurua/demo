package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ClientAccountCustomerDao;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.req.PagingReq;

@Service(value = "clientAccountCustomerService")
@Transactional
public class ClientAccountCustomerService {
	// region -- Fields --

	@Autowired
	private ClientAccountCustomerDao clientAccountCustomerDao;

	// end

	// region -- Methods --

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<ClientAccountCustomerDto> getByClientId(String clientId) {
		return clientAccountCustomerDao.getByClientId(clientId);
	}

	/**
	 * Read by
	 * 
	 * @param req
	 * @return
	 */
	public List<ClientAccountCustomerDto> read(PagingReq req) {
		return clientAccountCustomerDao.search(req);
	}

	// end
}