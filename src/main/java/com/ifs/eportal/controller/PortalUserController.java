package com.ifs.eportal.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.PortalRoleService;
import com.ifs.eportal.bll.PortalUserService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.config.JwtTokenUtil;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.ProfileDto;
import com.ifs.eportal.model.PortalUser;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.req.PortalUserReq;
import com.ifs.eportal.req.PortalUserSignInReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;
import com.ifs.eportal.security.CustomAuthenticationProvider;

@RestController
@RequestMapping("/portal-user")
public class PortalUserController {
	// region -- Fields --

	@Autowired
	private PortalUserService portalUserService;

	@Autowired
	private PortalRoleService portalRoleService;

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int userId = pl.getId();

			// Handle
			List<PortalUser> tmp = portalUserService.search();

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

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody PortalUserReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// PayloadDto pl = Utils.getTokenInfor(header);
			// int userId = pl.getId();

			Integer id = req.getId();
			Date createdDate = req.getCreatedDate();
			String name = req.getName();
			Date systemModStamp = req.getSystemModStamp();
			String sfid = req.getSfid();
			String hcLastop = req.getHcLastop();
			String hcErr = req.getHcErr();

			PortalUser m = new PortalUser();

			m.setId(id);
			m.setCreatedDate(createdDate);
			m.setName(name);
			m.setSystemModStamp(systemModStamp);
			m.setSfid(sfid);
			m.setHcLastop(hcLastop);
			m.setHcErr(hcErr);

			portalUserService.save(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		BaseRsp res = new BaseRsp();

		try {
			PortalUser m = portalUserService.getBy(id);

			portalUserService.delete(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody PortalUserSignInReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String email = req.getEmail();
			String password = req.getPassword();

			// Handle
			PortalUser m = portalUserService.getBy(email);
			if (m == null) {
				res.setError("Email doesn't exist!");
			} else {
				UsernamePasswordAuthenticationToken x;
				x = new UsernamePasswordAuthenticationToken(email, password);
				Authentication y = customAuthenticationProvider.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				List<SimpleGrantedAuthority> z = portalUserService.getRole(m.getId());
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

	@PostMapping(value = "/view")
	public ResponseEntity<?> view(@RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			PayloadDto pl = Utils.getTokenInfor(header);
			int userId = pl.getId();

			ProfileDto m = portalUserService.getProfile(userId);

			// Set data;
			res.setResult(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}