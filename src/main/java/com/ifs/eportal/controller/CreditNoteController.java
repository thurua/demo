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

import com.ifs.eportal.bll.CreditNoteService;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;

@RestController
@RequestMapping("/credit-note")
public class CreditNoteController {
	// region -- Fields --

	@Autowired
	private CreditNoteService creditNoteService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {

			// Handle
			List<CreditNote> tmp = creditNoteService.search();

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
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody CreditNote req) {
		BaseRsp res = new BaseRsp();

		try {

			Integer id = req.getId();
			String customerBranch = req.getCustomerBranch();
			String customerFromExcel = req.getCustomerFromExcel();
			String currencyIsoCode = req.getCurrencyIsoCode();
			String clientRemarks = req.getClientRemarks();
			Float creditAmount = req.getCreditAmount();
			String customer = req.getCustomer();
			String scheduleOfOffer = req.getScheduleOfOffer();
			String clientAccount = req.getClientAccount();
			String name = req.getName();
			Date creditNoteDate = req.getCreditNoteDate();
			Date systemModStamp = req.getSystemModStamp();
			String status = req.getStatus();
			Date cnApplicationDate = req.getCnApplicationDate();
			Float cnAppliedAmount = req.getCnAppliedAmount();
			Date createdDate = req.getCreatedDate();
			String client = req.getClient();
			String appliedInvoice = req.getAppliedInvoice();
			String sfid = req.getSfid();
			String hcLastop = req.getHcLastop();
			String hcErr = req.getHcErr();

			CreditNote m = new CreditNote();

			m.setId(id);
			m.setCustomerBranch(customerBranch);
			m.setCustomerFromExcel(customerFromExcel);
			m.setCustomer(customer);
			m.setClient(client);
			m.setClientRemarks(clientRemarks);
			m.setCreditAmount(creditAmount);
			m.setCurrencyIsoCode(currencyIsoCode);
			m.setScheduleOfOffer(scheduleOfOffer);
			m.setClientAccount(clientAccount);
			m.setName(name);
			m.setCreditNoteDate(creditNoteDate);
			m.setSystemModStamp(systemModStamp);
			m.setStatus(status);
			m.setCnApplicationDate(cnApplicationDate);
			m.setCnAppliedAmount(cnAppliedAmount);
			m.setCreatedDate(createdDate);
			m.setClient(client);
			m.setAppliedInvoice(appliedInvoice);
			m.setSfid(sfid);
			m.setHcErr(hcErr);
			m.setHcLastop(hcLastop);

			creditNoteService.save(m);

		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		BaseRsp res = new BaseRsp();

		try {
			CreditNote m = creditNoteService.getBy(id);

			creditNoteService.delete(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}