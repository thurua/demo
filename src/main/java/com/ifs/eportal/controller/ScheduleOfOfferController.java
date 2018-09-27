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

import com.ifs.eportal.bll.ScheduleOfOfferService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.req.ScheduleOfOfferReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;

@RestController
@RequestMapping("/schedule-of-offer")
public class ScheduleOfOfferController {
	// region -- Fields --

	@Autowired
	private ScheduleOfOfferService scheduleOfOfferService;

	// end

	// region -- Methods --

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
			List<ScheduleOfOfferDto> tmp = scheduleOfOfferService.search(req);

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
	 * Save profile
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody ScheduleOfOfferReq req) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();
			req.setId(id);

			// scheduleOfOfferService.save(req);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}