package com.ifs.eportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.ClientAccountCustomerService;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.rsp.SingleRsp;

@RestController
@RequestMapping("/client-account-customer")
public class ClientAccountCustomerController {

	// region -- Fields --

	@Autowired
	private ClientAccountCustomerService clientAccountCustomerService;

	// end

	// region -- Methods --

	/**
	 * Read by
	 * 
	 * @param sfId
	 * @return
	 */
	@PostMapping("/read")
	public ResponseEntity<?> read(@RequestBody String sfId) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			List<ClientAccountCustomerDto> t;
			t = clientAccountCustomerService.read(sfId);

			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}