package com.ifs.eportal.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.PortalUserService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.config.JwtTokenUtil;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.PortalUserDto;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.req.ChangePasswordReq;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.req.PortalUserSignInReq;
import com.ifs.eportal.req.ProfileReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;
import com.ifs.eportal.security.CustomAuthenticationProvider;

/**
 * 
 * @author ToanNguyen 2018-Sep-28
 *
 */
@RestController
@RequestMapping("/portal-user")
public class PortalUserController {
	// region -- Fields --

	@Autowired
	private PortalUserService portalUserService;

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	// end

	// region -- Methods --

	/**
	 * Read
	 * 
	 * @param header
	 * @return
	 */
	@PostMapping(value = "/read")
	public ResponseEntity<?> read(@RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			Integer id = pl.getId();

			// Handle
			PortalUserDto o = portalUserService.getBy(id);

			// Set data;
			res.setResult(o);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Update profile
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@PostMapping("/update-profile")
	public ResponseEntity<?> update(@RequestHeader HttpHeaders header, @RequestBody ProfileReq req) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();
			req.setId(id);

			portalUserService.save(req);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Update password
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@PostMapping("/update-password")
	public ResponseEntity<?> update(@RequestHeader HttpHeaders header, @RequestBody ChangePasswordReq req) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();
			req.setId(id);

			portalUserService.save(req);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Update token
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/update-token")
	public ResponseEntity<?> updateToken(@RequestBody BaseReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// Get data
			String email = req.getKeyword();

			// Handle
			portalUserService.verifyMail(email);

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
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<PortalUserDto> tmp = portalUserService.search(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("page", req.getPage());
			data.put("size", req.getSize());
			data.put("total", req.getTotal());
			data.put("data", tmp);
			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Sign in
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody PortalUserSignInReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String email = req.getEmail();
			String password = req.getPassword();

			// Handle
			PortalUserDto m = portalUserService.getBy(email);
			if (m.getId() == 0) {
				res.setError("Email doesn't exist!");
			} else {
				UsernamePasswordAuthenticationToken x;
				x = new UsernamePasswordAuthenticationToken(email, password);

				Authentication y = customAuthenticationProvider.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				List<SimpleGrantedAuthority> z = portalUserService.getRoleBy(m.getId());
				String t = jwtTokenUtil.doGenerateToken(m, z);
				res.setResult(t);
			}
		} catch (AuthenticationException e) {
			res.setError("Unauthorized/Invalid email or password!");
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}