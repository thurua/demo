package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ClientAccountDao;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.model.ClientAccount;
import com.ifs.eportal.req.PagingReq;

@Service(value = "clientAccountService")
@Transactional
public class ClientAccountService {
	// region -- Fields --

	@Autowired
	private ClientAccountDao clientAccountDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public void create(ClientAccount m) {
		clientAccountDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public ClientAccountDto read(Integer id) {
		return clientAccountDao.getBy(id);
	}

	public List<ClientAccountDto> read(String clientAccount, String client) {
		return clientAccountDao.getBy(clientAccount, client);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<ClientAccountDto> search(PagingReq req) {
		return clientAccountDao.search(req);
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
//	public List<ClientAccountDto> search(String clientId) {
//		List<Object[]> l = clientAccountDao.search(clientId);
//		List<ClientAccountDto> res = ClientAccountDto.convert(l);
//		return res;
//	}

	// end
}