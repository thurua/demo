package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ClientAccountDao;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.model.ClientAccount;

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
	public ClientAccount getBy(int id) {
		ClientAccount res = clientAccountDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<ClientAccountDto> search(String clientId) {
		List<Object[]> l = clientAccountDao.search(clientId);
		List<ClientAccountDto> res = ClientAccountDto.convert(l);
		return res;
	}

	// end
}