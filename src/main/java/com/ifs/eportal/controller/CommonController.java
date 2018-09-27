package com.ifs.eportal.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.bll.AccountService;
import com.ifs.eportal.bll.ClientAccountService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.ProfileDto;
import com.ifs.eportal.dto.TokenDto;
import com.ifs.eportal.model.Account;
import com.ifs.eportal.model.ClientAccount;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private AccountService accountService;
	private ClientAccountService clientAccountService;

	// end

	// region -- Methods --

	@PostMapping("/search-account")
	public ResponseEntity<?> searchAccount(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// PayloadDto pl = Utils.getTokenInfor(header);
			// int id = pl.getId();

			// Get data
			// String keyword = req.getKeyword();
			// Boolean isOptional = req.getIsOptional();

			// Handle
			List<Account> tmp = accountService.search();

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", tmp.size());
			data.put("data", tmp);
			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end

	// region -- Methods --

	@PostMapping("/search-client-account")
	public ResponseEntity<?> searchClientAccount(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();
			String clientId = "1";

			// Get data
			// String keyword = req.getKeyword();
			// Boolean isOptional = req.getIsOptional();

			// Handle
			List<ClientAccountDto> tmp = clientAccountService.search(clientId);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", tmp.size());
			data.put("data", tmp);
			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/call")
	public ResponseEntity<?> call() {
		SingleRsp res = new SingleRsp();

		try {
			String url = System.getenv("SF_URL");
			String client_id = System.getenv("SF_CLIENT_ID");
			String client_secret = System.getenv("SF_CLIENT_SECRET");
			String username = System.getenv("SF_USER_NAME");
			String password = System.getenv("SF_PASSWORD");

			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			LinkedMultiValueMap<String, Object> mapReq = new LinkedMultiValueMap<>();
			mapReq.add("grant_type", "password");
			mapReq.add("client_id", client_id);
			mapReq.add("client_secret", client_secret);
			mapReq.add("username", username);
			mapReq.add("password", password);

			HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(mapReq, headers);
			ResponseEntity<String> rsp = rest.exchange(url, HttpMethod.POST, reqEntity, String.class);
			String s = rsp.getBody();
			ObjectMapper mapper = new ObjectMapper();
			TokenDto t = mapper.readValue(s, TokenDto.class);
			String token = t.getAccessToken();

			url = System.getenv("SF_URL_UI_API") + "/object-info/Account";
			headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			reqEntity = new HttpEntity<>(mapReq, headers);
			rsp = rest.exchange(url, HttpMethod.GET, reqEntity, String.class);
			s = rsp.getBody();
			res.setResult(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	// end
}