package com.ifs.eportal.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.ifs.eportal.bll.ScheduleOfOfferService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.req.PageableReq;
import com.ifs.eportal.req.ScheduleOfOfferReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.SingleRsp;

@RestController
@RequestMapping("/schedule-of-offer")
public class ScheduleOfOfferController {
	// region -- Fields --

	@Autowired
	private ScheduleOfOfferService scheduleOfOfferService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody PageableReq req) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Handle
			Page<ScheduleOfOffer> t = scheduleOfOfferService.search(req);

			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody ScheduleOfOfferReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// PayloadDto pl = Utils.getTokenInfor(header);
			// int userId = pl.getId();

			Integer id = req.getId();
			Date createdDate = req.getCreatedDate();
			String name = req.getName();
			Date systemModStamp = req.getSystemModStamp();
			String sfid = req.getSfId();
			String hcLastop = req.getHcLastop();
			String hcErr = req.getHcErr();

			ScheduleOfOffer m = new ScheduleOfOffer();

			m.setId(id);
			m.setCreatedDate(createdDate);
			m.setName(name);
			m.setSystemModStamp(systemModStamp);
			m.setSfId(sfid);
			m.setHcLastop(hcLastop);
			m.setHcErr(hcErr);

			scheduleOfOfferService.save(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		BaseRsp res = new BaseRsp();

		try {
			ScheduleOfOffer m = scheduleOfOfferService.getBy(id);

			scheduleOfOfferService.delete(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}