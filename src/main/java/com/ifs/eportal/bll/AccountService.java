package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dal.AccountDao;
import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
@Service(value = "accountService")
@Transactional
public class AccountService {
	// region -- Fields --

	@Autowired
	private AccountDao accountDao;

	// end

	// region -- Methods --

	/**
	 * Read by
	 * 
	 * @param sfid
	 * @return
	 */
	public AccountDto read(String sfid) {
		return accountDao.getBy(sfid);
	}

	public List<AccountDto> getByNames(List<LineItemDto> l) {
		List<String> names = Utils.getNames(l);
		return accountDao.getByNames(names);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<AccountDto> read(PagingReq req) {
		return accountDao.search(req);
	}

	// end
}