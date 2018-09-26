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

		CreditNote m1;
		if (id == null || id == 0) {

			// m.setActive(true);
			m.setIsDeleted(false);
			m.setCreatedDate(new Date());

			m1 = creditNoteDao.save(m);

		} else {
			m1 = creditNoteDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = creditNoteDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				m1.setCustomerBranch(m.getCustomerBranch());
				m1.setCustomerFromExcel(m.getCustomerFromExcel());
				m1.setCurrencyIsoCode(m.getCurrencyIsoCode());
				m1.setClientRemarks(m.getClientRemarks());
				m1.setCreditAmount(m.getCreditAmount());
				m1.setCustomer(m.getCustomer());
				m1.setScheduleOfOffer(m.getScheduleOfOffer());
				m1.setClientAccount(m.getClientAccount());
				m1.setName(m.getName());
				m1.setCreditNoteDate(m.getCreditNoteDate());
				m1.setSystemModStamp(m.getSystemModStamp());
				m1.setStatus(m.getStatus());
				m1.setCnApplicationDate(m.getCnApplicationDate());
				m1.setCnAppliedAmount(m.getCnAppliedAmount());
				m1.setCreatedDate(m.getCreatedDate());
				m1.setFlag(m.isFlag());
				m1.setClient(m.getClient());
				m1.setAppliedInvoice(m.getAppliedInvoice());
				m1.setApplyCreditNote(m.isApplyCreditNote());
				m1.setSelected(m.isSelected());
				m1.setSfid(m.getSfid());
				m1.setHcLastop(m.getHcLastop());
				m1.setHcErr(m.getHcErr());

				creditNoteDao.save(m1);
			}
		}

		return res;
	}

//	public String delete(CreditNote m, Integer userId) {
//		String res = "";
//
//		if (m == null) {
//			res = "Id does not exist";
//		} else {
//
//			// m.setModifyBy(userId);
//			// m.setModifyOn(new Date());
//
//			m.setIsDeleted(true);
//
//			creditNoteDao.save(m);
//		}
//
//		return res;
//	}

	public String delete(CreditNote m) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setIsDeleted(true);

			creditNoteDao.save(m);
		}

		return res;
	}

}