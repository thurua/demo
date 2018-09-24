package com.rdp.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rdp.model.Package;
import com.rdp.req.PackagePriceReq;
import com.rdp.rsp.MultipleRsp;
import com.rdp.rsp.SingleRsp;
import com.rdp.service.PackageService;

@RestController
@RequestMapping("/package")
public class PackageController {
	// region -- Fields --

	@Autowired
	private PackageService packageService;

	// end

	// region -- Methods --

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header,
			@RequestParam(name = "isPromo", required = false) Boolean isPromo) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<Package> l;
			if (isPromo == null || !isPromo) {
				l = packageService.search();
			} else {
				l = packageService.searchPromo();
			}

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", l.size());
			data.put("data", l);
			res.setResult(data);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody PackagePriceReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String sfid = req.getSfid();
			String duration = req.getDuration();
			Boolean isPromo = req.getIsPromo();

			// Handle
			Package m;
			if (isPromo == null || !isPromo) {
				m = packageService.search(sfid, duration);
			} else {
				m = packageService.searchPromo(sfid, duration);
			}

			if (m == null) {
				res.setCallstatus("error");
				res.setMessage("No data");
			}

			// Set data
			res.setResult(m);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/valid", method = RequestMethod.GET)
	public ResponseEntity<?> valid(@RequestHeader HttpHeaders header,
			@RequestParam(name = "promoCode") String promoCode) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			boolean tmp = packageService.checkExist(promoCode);
			res.setResult(tmp);
		} catch (Exception ex) {
			ex.printStackTrace();
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}