package com.ifs.eportal.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.ClientAccountService;
import com.ifs.eportal.dto.ClientAccountCustomerFactoringDto;
import com.ifs.eportal.dto.FactoringDetailDto;
import com.ifs.eportal.dto.LctrDetailDto;
import com.ifs.eportal.dto.LoanDetailDto;
import com.ifs.eportal.dto.SearchClientAccountDto;
import com.ifs.eportal.req.ClientAccountReq;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;

/**
 * 
 * @author NganHo 2018-Oct-16
 *
 */
@RestController
@RequestMapping("/client-account")
public class ClientAccountController {
	// region -- Fields --

	@Autowired
	private ClientAccountService clientAccountService;

	// end

	// region -- Methods --

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<SearchClientAccountDto> t;
			t = clientAccountService.search(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("page", req.getPage());
			data.put("size", req.getSize());
			data.put("total", req.getTotal());
			data.put("data", t);

			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/client-account-customer")
	public ResponseEntity<?> getClientAccountCustomer(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<ClientAccountCustomerFactoringDto> t;
			t = clientAccountService.getClientAccountCustomer(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("page", req.getPage());
			data.put("size", req.getSize());
			data.put("total", req.getTotal());
			data.put("data", t);

			res.setResult(data);
			;
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	@PostMapping("/get-details")
	public ResponseEntity<?> read(@RequestBody ClientAccountReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			String type = req.getType();
			String sfId = req.getSfId();

			if (type.equals("L")) {
				LoanDetailDto t = clientAccountService.getLoanDetail(sfId);
				res.setResult(t);
			} else if (type.equals("F")) {
				FactoringDetailDto t = clientAccountService.getFactoringDetail(sfId);
				res.setResult(t);
			} else {
				LctrDetailDto t = clientAccountService.getLctrDetail(sfId);
				res.setResult(t);
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	@PostMapping("/update-factoring")
	public ResponseEntity<?> update(@RequestBody ClientAccountReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			boolean autoReq = req.isAutoRequest();
			String sfId = req.getSfId();

			clientAccountService.update(autoReq, sfId);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}