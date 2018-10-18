package com.ifs.eportal.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.CreditNoteService;
import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.dto.CreditNoteDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;

@RestController
@RequestMapping("/credit-note")
public class CreditNoteController {
	// region -- Fields --

	@Autowired
	private CreditNoteService creditNoteService;

	private static final Logger _log = Logger.getLogger(CreditNoteController.class.getName());

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
			CreditNote m = new CreditNote();
			m.setScheduleOfOffer("x");
			// m.setDocumentType("x");
			// m.setRecordTypeId("x");
			m.setClient("x");
			m.setClientAccount("x");
			m.setCurrencyIsoCode("x");
			m.setClientRemarks("x");
			m.setCreditAmount(0f);
			m.setStatus("Accepted");

			creditNoteService.create(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Read by
	 * 
	 * @param sfId
	 * @return
	 */
	@PostMapping("/read")
	public ResponseEntity<?> read(@RequestBody String sfId) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			CreditNoteDto t;
			t = creditNoteService.read(sfId);

			res.setResult(t);
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
//	@PostMapping("/update-credit-notes-details")
//	public ResponseEntity<?> update(@RequestHeader HttpHeaders header, @RequestBody CreditNoteReq req) {
//		BaseRsp res = new BaseRsp();
//
//		try {
//			creService.update(req);
//		} catch (Exception ex) {
//			res.setError(ex.getMessage());
//		}
//
//		return new ResponseEntity<>(res, HttpStatus.OK);
//	}

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
			List<CreditNoteDto> tmp;
			tmp = creditNoteService.search(req);

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
	 * Search by customer
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/search-customer")
	public ResponseEntity<?> searchCustomer(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<CreditNoteDto> l = creditNoteService.read(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", l.size());
			data.put("data", l);
			res.setResult(data);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}