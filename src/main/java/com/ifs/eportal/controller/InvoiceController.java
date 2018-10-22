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

import com.ifs.eportal.bll.InvoiceService;
import com.ifs.eportal.dto.InvoiceDto;
import com.ifs.eportal.model.Invoice;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;
import com.ifs.eportal.rsp.SingleRsp;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	// region -- Fields --

	@Autowired
	private InvoiceService invoiceService;

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
			Invoice m = new Invoice();
			m.setScheduleOfOffer("x");
			m.setDocumentType("x");
			m.setRecordTypeId("x");
			m.setClientName("x");
			m.setClientAccount("x");
			m.setCurrencyIsoCode("x");
			m.setClientRemarks("x");
			m.setInvoiceAmount(0f);
			m.setStatus("Pending");

			invoiceService.create(m);
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
			List<InvoiceDto> t;
			t = invoiceService.search(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("page", req.getPage());
			data.put("size", req.getSize());
			data.put("total", req.getTotal());
			data.put("data", t);

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
			InvoiceDto t;
			t = invoiceService.read(sfId);

			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}