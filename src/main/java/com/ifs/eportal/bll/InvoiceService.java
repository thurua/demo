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
			m.setIsDeleted(false);
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

				m1.setCustomerBranch(m.getCustomerBranch());
				m1.setCustomerFromExcel(m.getCustomerFromExcel());
				m1.setCurrencyIsoCode(m.getCurrencyIsoCode());
				m1.setInvoiceDate(m.getInvoiceDate());
				m1.setClientName(m.getClientName());
				m1.setPo(m.getPo());
				m1.setClientRemarks(m.getClientRemarks());
				m1.setCustomer(m.getCustomer());
				m1.setDocumentType(m.getDocumentType());
				m1.setRecordTypeId(m.getRecordTypeId());
				m1.setScheduleOfOffer(m.getScheduleOfOffer());
				m1.setClientAccount(m.getClientAccount());
				m1.setName(m.getName());
				m1.setSystemModStamp(m.getSystemModStamp());
				m1.setContract(m.getContract());
				m1.setStatus(m.getStatus());
				m1.setExternalId(m.getExternalId());
				m1.setCreatedDate(m.getCreatedDate());
				m1.setSupplier(m.getSupplier());
				m1.setInvoiceAmount(m.getInvoiceAmount());
				m1.setPaymentDate(m.getPaymentDate());
				m1.setCreditPeriod(m.getCreditPeriod());
				m1.setSfid(m.getSfid());
				m1.setHcLastop(m.getHcLastop());
				m1.setHcErr(m.getHcErr());

				invoiceDao.save(m1);
			}
		}

		return res;
	}

	public String delete(Invoice m) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {

			// m.setModifyBy(userId);
			// m.setModifyOn(new Date());

			m.setIsDeleted(true);

			invoiceDao.save(m);
		}

		return res;
	}
}