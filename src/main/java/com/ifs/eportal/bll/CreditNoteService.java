package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.CreditNoteDao;
import com.ifs.eportal.model.CreditNote;

@Service(value = "creditNoteService")
@Transactional
public class CreditNoteService {
	// region -- Fields --

	@Autowired
	private CreditNoteDao creditNoteDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public CreditNote getBy(int id) {
		CreditNote res = creditNoteDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<CreditNote> search() {
		List<CreditNote> res = creditNoteDao.search();
		return res;
	}

	// end
	public String save(CreditNote m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			// m.setActive(true);
			m.setDeleted(false);
			m.setCreatedDate(new Date());

			creditNoteDao.save(m);

		} else {
			CreditNote m1 = creditNoteDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = creditNoteDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				creditNoteDao.save(m1);
			}
		}

		return res;
	}

	public String delete(CreditNote m, Integer userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setDeleted(true);

			creditNoteDao.save(m);
		}

		return res;
	}
}