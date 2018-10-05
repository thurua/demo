package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ClientAccountDao;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
@Service(value = "clientAccountService")
@Transactional
public class ClientAccountService {
	// region -- Fields --

	@Autowired
	private ClientAccountDao clientAccountDao;

	// end

	// region -- Methods --

	/**
	 * Read by
	 * 
	 * @param sfid
	 * @return
	 */
	public ClientAccountDto read(String sfid) {
		return clientAccountDao.read(sfid);
	}

	/**
	 * Read by
	 * 
	 * @param req
	 * @return
	 */
	public List<ClientAccountDto> read(PagingReq req) {
		return clientAccountDao.search(req);
	}

	// end
}