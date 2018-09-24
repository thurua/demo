package com.ifs.eportal.controller;

import java.sql.Date;
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

import com.ifs.eportal.bll.PortalRoleService;
import com.ifs.eportal.model.PortalRole;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.req.PortalRoleReq;
import com.ifs.eportal.rsp.MultipleRsp;
//import com.ifs.eportal.common.Utils;
//import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.rsp.BaseRsp;

@RestController
@RequestMapping("/portal-role")
public class PortalRoleController<ExpenseReq> {
	// region -- Fields --

	@Autowired
	private PortalRoleService portalRoleService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<PortalRole> tmp = portalRoleService.search();

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
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody PortalRoleReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// PayloadDto pl = Utils.getTokenInfor(header);
			// int userId = pl.getId();

			Date createdDate = req.getCreatedDate();
			Boolean isDeleted = req.getIsDeleted();
			String name = req.getName();
			Date systemModStamp = req.getSystemModStamp();
			String sfid = req.getSfid();
			String hcLastop = req.get_hc_Lastop();
			String hcErr = req.get_hc_Err();

			PortalRole m = new PortalRole();
			m.setCreatedDate(createdDate);
			m.setIsDeleted(isDeleted);
			m.setName(name);
			m.setSystemModStamp(systemModStamp);
			m.setSfid(sfid);
			m.setHcLastop(hcLastop);
			m.setHcErr(hcErr);

			portalRoleService.save(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}