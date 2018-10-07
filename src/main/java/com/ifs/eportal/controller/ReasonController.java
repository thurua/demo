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

import com.ifs.eportal.bll.ReasonService;
import com.ifs.eportal.dto.ReasonDto;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;

@RestController
@RequestMapping("/reason")
public class ReasonController {

	// region -- Fields --

	@Autowired
	private ReasonService reasonService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<ReasonDto> tmp;
			tmp = reasonService.search(req);

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
	 * Read by
	 * 
	 * @param Id
	 * @return
	 */
	@PostMapping("/read")
	public ResponseEntity<?> read(@RequestBody String sfId) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			List<ReasonDto> t;
			t = reasonService.read(sfId);

			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}