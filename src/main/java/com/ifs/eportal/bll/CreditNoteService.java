package com.ifs.eportal.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dal.CreditNoteDao;
import com.ifs.eportal.dto.CreditNoteDto;
import com.ifs.eportal.dto.CustomDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.req.PagingReq;

/**
 * 
 * @author HoanNguyen 2018-Oct-05
 *
 */
@Service(value = "_creditNoteService")
@Transactional
public class CreditNoteService {
	// region -- Fields --

	@Autowired
	private CreditNoteDao _creditNoteDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(CreditNote m) {
		_creditNoteDao.create(m);
	}

	/**
	 * Update CreditNote
	 * 
	 * @param req
	 * @return
	 */
	public String update(CreditNote req) {
		String res = "";

		// Get data
		Integer id = req.getId();

		String status = req.getStatus();

		// Handle
		CreditNote m = _creditNoteDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setStatus(status);

			_creditNoteDao.update(m);
		}

		return res;
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public CreditNoteDto read(int id) {
		CreditNoteDto res = _creditNoteDao.getBy(id);
		return res;
	}

	/**
	 * Read by
	 * 
	 * @param sfId
	 * @return
	 */
	public CreditNoteDto read(String sfId) {
		CreditNoteDto res = _creditNoteDao.getBy(sfId);
		return res;
	}

	/**
	 * 
	 * @param l
	 * @param accountId
	 * @param amendSchedule
	 * @return
	 */
	public List<CustomDto> getListBy(List<LineItemDto> l, String accountId, boolean amendSchedule) {
		List<String> names = Utils.getNo(l);
		return _creditNoteDao.getListBy(names, accountId, amendSchedule);
	}

	/**
	 * Read by
	 * 
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */
	public CreditNoteDto read(String scheduleNo, String clientName) {
		CreditNoteDto res = _creditNoteDao.getBy(scheduleNo, clientName);
		return res;
	}

	/**
	 * Read by
	 * 
	 * @param req
	 * @return
	 */
	public List<CreditNoteDto> read(PagingReq req) {
		return _creditNoteDao.search(req);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<CreditNoteDto> search(PagingReq req) {
		List<CreditNoteDto> res = _creditNoteDao.search(req);
		return res;
	}

	// end
}