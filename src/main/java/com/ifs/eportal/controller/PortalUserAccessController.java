package com.ifs.eportal.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.PortalUserAccessService;
import com.ifs.eportal.model.PortalUserAccess;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.rsp.MultipleRsp;

@RestController
@RequestMapping("/portal-user-access")
public class PortalUserAccessController {
	// region -- Fields --

	@Autowired
	private PortalUserAccessService portalUserAccessService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<PortalUserAccess> tmp = portalUserAccessService.search();

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
}