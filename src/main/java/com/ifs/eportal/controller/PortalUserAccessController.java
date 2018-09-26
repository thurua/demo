package com.ifs.eportal.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.PortalUserAccessService;
import com.ifs.eportal.model.PortalRole;
import com.ifs.eportal.model.PortalUserAccess;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.req.PortalUserAccessReq;
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

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody PortalUserAccessReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// PayloadDto pl = Utils.getTokenInfor(header);
			// int userId = pl.getId();

			Date createdDate = req.getCreatedDate();
			Boolean isDeleted = req.getIsDeleted();
			String name = req.getName();
			Date systemModStamp = req.getSystemModStamp();
			String sfid = req.getSfid();
			String hcLastop = req.getHcLastop();
			String hcErr = req.getHcErr();

			PortalUserAccess m = new PortalUserAccess();
			m.setCreatedDate(createdDate);
			m.setDeleted(isDeleted);
			m.setName(name);
			m.setSystemModStamp(systemModStamp);
			m.setSfid(sfid);
			m.setHcLastop(hcLastop);
			m.setHcErr(hcErr);

			portalUserAccessService.save(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		BaseRsp res = new BaseRsp();

		try {
			PortalUserAccess m = portalUserAccessService.getBy(id);

			portalUserAccessService.delete(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// end
}