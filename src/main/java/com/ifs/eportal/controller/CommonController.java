package com.ifs.eportal.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.bll.AccountService;
import com.ifs.eportal.bll.ClientAccountCustomerService;
import com.ifs.eportal.bll.ClientAccountService;
import com.ifs.eportal.bll.CodeService;
import com.ifs.eportal.bll.SupplierService;
import com.ifs.eportal.common.RsaService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.SupplierDto;
import com.ifs.eportal.dto.TokenDto;
import com.ifs.eportal.model.Code;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
@RestController
@RequestMapping("/common")
public class CommonController {
	// region -- Fields --

	@Autowired
	private AccountService accountService;

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private ClientAccountCustomerService clientAccountCustomerService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private CodeService codeService;

	private static final Logger _log = Logger.getLogger(CommonController.class.getName());

	// end

	// region -- Methods --

	/**
	 * Search account
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/search-account")
	public ResponseEntity<?> searchAccount(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<AccountDto> l = accountService.read(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", l.size());
			data.put("data", l);
			res.setResult(data);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Search client account
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/search-client-account")
	public ResponseEntity<?> searchClientAccount(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<ClientAccountDto> l = clientAccountService.read(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", l.size());
			data.put("data", l);
			res.setResult(data);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Search customer
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/search-customer")
	public ResponseEntity<?> searchCustomer(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<ClientAccountCustomerDto> l = clientAccountCustomerService.read(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", l.size());
			data.put("data", l);
			res.setResult(data);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Search supplier
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/search-supplier")
	public ResponseEntity<?> searchSupplier(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<SupplierDto> l = supplierService.read(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", l.size());
			data.put("data", l);
			res.setResult(data);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Search code
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@PostMapping("/search-code")
	public ResponseEntity<?> searchCode(@RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Get data
			String keyword = req.getKeyword();

			// Handle
			List<Code> l = codeService.read(keyword);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", l.size());
			data.put("data", l);
			res.setResult(data);
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Call SF
	 * 
	 * @return
	 */
	@PostMapping("/call")
	public ResponseEntity<?> call() {
		SingleRsp res = new SingleRsp();

		try {
			String url = System.getenv("SF_URL");
			String client_id = System.getenv("SF_CLIENT_ID");
			String client_secret = System.getenv("SF_CLIENT_SECRET");
			String username = System.getenv("SF_USER_NAME");
			String password = System.getenv("SF_PASSWORD");

			// Decrypt
			password = RsaService.decrypt(password);

			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			LinkedMultiValueMap<String, Object> mapReq = new LinkedMultiValueMap<>();
			mapReq.add("grant_type", "password");
			mapReq.add("client_id", client_id);
			mapReq.add("client_secret", client_secret);
			mapReq.add("username", username);
			mapReq.add("password", password);

			HttpEntity<LinkedMultiValueMap<String, Object>> req;
			req = new HttpEntity<>(mapReq, headers);
			ResponseEntity<String> rsp;
			rsp = rest.exchange(url, HttpMethod.POST, req, String.class);
			String s = rsp.getBody();
			ObjectMapper mapper = new ObjectMapper();
			TokenDto t = mapper.readValue(s, TokenDto.class);
			String token = t.getAccessToken();

			url = System.getenv("SF_URL_UI_API") + "/object-info/Account";
			headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			req = new HttpEntity<>(mapReq, headers);
			rsp = rest.exchange(url, HttpMethod.GET, req, String.class);
			s = rsp.getBody();
			res.setResult(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}