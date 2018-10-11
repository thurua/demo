package com.ifs.eportal.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.ApprovedCustomerLimitDao;
import com.ifs.eportal.dto.ApprovedCustomerLimitDto;
import com.ifs.eportal.model.Invoice;

@Service(value = "approvedCustomerLimitService")
@Transactional
public class ApprovedCustomerLimitService {
	// region -- Fields --

	@Autowired
	private ApprovedCustomerLimitDao approvedCustomerLimitDao;

	// end

	// region -- Methods --

	/**
	 * Read by
	 * 
	 * @param sfid
	 * @return
	 */
	public ApprovedCustomerLimitDto read(String sfid) {
		return approvedCustomerLimitDao.getBy(sfid);
	}

	/**
	 * Read by
	 * 
	 * @param l
	 * @param clientId
	 * @return
	 */
	public List<ApprovedCustomerLimitDto> read(List<Invoice> l, String clientId) {
		List<String> customersId = new ArrayList<>();

		for (Invoice i : l) {
			if (i.getCustomer() != null && !i.getCustomer().isEmpty()) {
				customersId.add(i.getCustomer());
			} else {
				break;
			}
		}

		if (customersId.size() != 0) {
			return approvedCustomerLimitDao.getBy(customersId, clientId);
		} else {
			return new ArrayList<ApprovedCustomerLimitDto>();
		}
	}

	// end
}