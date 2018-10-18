package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ClientAccountDao;
import com.ifs.eportal.dto.ClientAccountCustomerFactoringDto;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.FactoringDetailDto;
import com.ifs.eportal.dto.LctrDetailDto;
import com.ifs.eportal.dto.LoanDetailDto;
import com.ifs.eportal.dto.SearchClientAccountDto;
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

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<SearchClientAccountDto> search(PagingReq req) {
		List<SearchClientAccountDto> res = clientAccountDao.searchClientAccount(req);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param sfid
	 * @return
	 */
	public LoanDetailDto getLoanDetail(String sfid) {
		return clientAccountDao.getLoanDetail(sfid);
	}

	/**
	 * Get by
	 * 
	 * @param sfid
	 * @return
	 */
	public FactoringDetailDto getFactoringDetail(String sfid) {
		return clientAccountDao.getFactoringDetail(sfid);
	}

	/**
	 * Get by
	 * 
	 * @param sfid
	 * @return
	 */
	public LctrDetailDto getLctrDetail(String sfid) {
		return clientAccountDao.getLctrDetail(sfid);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<ClientAccountCustomerFactoringDto> getClientAccountCustomer(PagingReq req) {
		List<ClientAccountCustomerFactoringDto> res = clientAccountDao.getClientAccountCustomer(req);
		return res;
	}

	// end
}