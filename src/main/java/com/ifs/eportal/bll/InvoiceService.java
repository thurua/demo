package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.InvoiceDao;
import com.ifs.eportal.model.Invoice;

@Service(value = "invoiceService")
@Transactional
public class InvoiceService {
	// region -- Fields --

	@Autowired
	private InvoiceDao invoiceDao;

	// end

	// region -- Methods --

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public Invoice getBy(int id) {
		Invoice res = invoiceDao.getBy(id);
		return res;
	}

	/**
	 * Search all
	 * 
	 * @return
	 */
	public List<Invoice> search() {
		List<Invoice> res = invoiceDao.search();
		return res;
	}

	// end
	public String save(Invoice m) {
		String res = "";

		Integer id = m.getId();
		// int userId = m.getUserId();

		if (id == null || id == 0) {

			// m.setActive(true);
			m.setDeleted(false);
			m.setCreatedDate(new Date());

			invoiceDao.save(m);

		} else {
			Invoice m1 = invoiceDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = invoiceDao.getBy(id);
				// m1.setModifyBy(userId);
				// m1.setModifyOn(new Date());

				invoiceDao.save(m1);
			}
		}

		return res;
	}

	public String delete(Invoice m, Integer userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setDeleted(true);

			invoiceDao.save(m);
		}

		return res;
	}
}