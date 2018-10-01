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

import com.ifs.eportal.bll.PortalUserAccessService;
import com.ifs.eportal.dto.PortalUserAccessDto;
import com.ifs.eportal.model.PortalUserAccess;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;

@RestController
@RequestMapping("/portal-user-access")
public class PortalUserAccessController {
	// region -- Fields --

	@Autowired
	private PortalUserAccessService portalUserAccessService;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> create() {
		BaseRsp res = new BaseRsp();

		try {
			PortalUserAccess m = new PortalUserAccess();
			m.setName("x");
			m.setDeleted(false);
			m.setLoginOn(null);
			m.setLogoutOn(null);
			portalUserAccessService.create(m);
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
			List<PortalUserAccessDto> tmp;
			tmp = portalUserAccessService.search(req);

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

	// end
}