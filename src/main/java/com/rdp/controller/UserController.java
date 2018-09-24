package com.rdp.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rdp.config.JwtTokenUtil;
import com.rdp.dto.UserDto;
import com.rdp.model.User;
import com.rdp.req.UserLoginReq;
import com.rdp.req.UserSearchReq;
import com.rdp.rsp.MultipleRsp;
import com.rdp.rsp.SingleRsp;
import com.rdp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	// region -- Fields --

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	// end

	// region -- Methods --

	/**
	 * Search by company, status and user name
	 * 
	 * @param header
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody UserSearchReq body) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<UserDto> dtos = userService.search(body);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", dtos.size());
			data.put("data", dtos);
			res.setResult(data);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@Valid @RequestBody UserLoginReq body) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String userName = body.getUserName();
			String password = body.getPassword();

			// Handle
			User m = userService.findUserByUserName(userName);
			if (m == null) {
				res.setCallstatus("error");
				res.setMessage("You do not have account created. Please signup and continue!");
			}

			UsernamePasswordAuthenticationToken x = new UsernamePasswordAuthenticationToken(userName, password);
			Authentication y = authenticationManager.authenticate(x);
			SecurityContextHolder.getContext().setAuthentication(y);

			List<SimpleGrantedAuthority> z = userService.getAuthorityByUserId(m.getId());
			String token = jwtTokenUtil.doGenerateToken(m, z);

			// Set data
			res.setResult(token);
		} catch (AuthenticationException e) {
			res.setCallstatus("error");
			res.setMessage("Unauthorized / Invalid email or password!");
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}