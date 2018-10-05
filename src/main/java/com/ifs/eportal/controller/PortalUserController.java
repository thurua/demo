package com.ifs.eportal.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.PortalUserService;
import com.ifs.eportal.common.Const;
import com.ifs.eportal.common.RsaService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.config.JwtTokenUtil;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.PortalUserDto;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.req.PasswordReq;
import com.ifs.eportal.req.PortalUserSignInReq;
import com.ifs.eportal.req.ProfileReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;

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
	private AuthenticationManager authenticationManager;

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
			PortalUserDto o = portalUserService.read(id);

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

			portalUserService.update(req);
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
	public ResponseEntity<?> update(@RequestHeader HttpHeaders header, @RequestBody PasswordReq req) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();
			req.setId(id);

			// Handle
			res = portalUserService.update(req, false);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Reset password
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@PostMapping("/reset-password")
	public ResponseEntity<?> update(@RequestBody PasswordReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			res = portalUserService.update(req, true);
			if (Const.HTTP.STATUS_SUCCESS.equals(res.getStatus())) {
				PortalUserDto m = (PortalUserDto) res.getResult();

				List<SimpleGrantedAuthority> z = portalUserService.getRoleBy(m.getId());
				String t = jwtTokenUtil.doGenerateToken(m, z);
				res.setResult(t);
			}
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
	public ResponseEntity<?> update(@RequestBody BaseReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// Get data
			String email = req.getKeyword();

			// Handle
			String t = portalUserService.update(email);
			if (!t.isEmpty()) {
				res.setError(t);
			}
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

			// Decrypt
			password = RsaService.decrypt(password);

			// Handle
			PortalUserDto m = portalUserService.read(email);
			if (m.getId() == 0) {
				res.setError("Email doesn't exist!");
			} else {
				UsernamePasswordAuthenticationToken x;
				x = new UsernamePasswordAuthenticationToken(email, password);

				Authentication y = authenticationManager.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				password = m.getPassword();
				String passwordHash = m.getPasswordHash();

				String t = Utils.hash(password, Const.Authentication.TOKEN_KEY1);
				if (!t.equals(passwordHash)) {
					res.setError("Unauthorized/Invalid email or password!");
				}

				List<SimpleGrantedAuthority> z = portalUserService.getRoleBy(m.getId());
				t = jwtTokenUtil.doGenerateToken(m, z);
				res.setResult(t);
			}
		} catch (AuthenticationException e) {
			res.setError("Unauthorized/Invalid email or password!");
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Get configuration
	 * 
	 * @return
	 */
	@GetMapping("/get-config")
	public ResponseEntity<?> getConfig() {
		MultipleRsp res = new MultipleRsp();

		try {
			// Get environment variable
			String t = System.getenv(Const.Mode.RSA);
			String pbKey = System.getenv(Const.Authentication.RSA_PUBLIC);
			// String prKey = System.getenv(Const.Authentication.RSA_PRIVATE);

			boolean mode = t != null && "Y".equals(t);
			if (pbKey == null || pbKey.isEmpty()) {
				mode = false;
			}

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put(Const.Mode.RSA, mode);
			data.put(Const.Authentication.RSA_PUBLIC, pbKey);
			// data.put(Const.Authentication.RSA_PRIVATE, prKey);

			res.setResult(data);
		} catch (Exception ex) {
			res.setError("Cannot get configuration!");
			ex.printStackTrace();
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Check Expired
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/checkExpired")
	public ResponseEntity<?> checkExpired(@RequestBody BaseReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			String token = req.getKeyword();
			String s = portalUserService.checkExpired(token);

			res.setStatus(s);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}