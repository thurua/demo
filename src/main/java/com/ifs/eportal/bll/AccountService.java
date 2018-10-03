package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.AccountDao;
import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.model.Account;
import com.ifs.eportal.req.PagingReq;

@Service(value = "accountService")
@Transactional
public class AccountService {
	// region -- Fields --

	@Autowired
	private AccountDao accountDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public Account getBy(int id) {
		Account res = null;// accountDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<AccountDto> search() {
		List<AccountDto> res = null;// accountDao.search();
		return res;
	}

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(Account m) {
		accountDao.create(m);
	}

	/**
	 * Get by
	 * 
	 * @param sfid
	 * @return
	 */
	public AccountDto getBy(String sfid) {
		return accountDao.getBy(sfid);
	}

	/**
	 * Read by
	 * 
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */
	public List<AccountDto> read(String name, String sfid) {
		return accountDao.getBy(name, sfid);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<AccountDto> search(PagingReq req) {
		return accountDao.search(req);
	}
	// end
}