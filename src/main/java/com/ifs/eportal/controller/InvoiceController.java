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

import com.ifs.eportal.bll.InvoiceService;
import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.model.Invoice;
import com.ifs.eportal.req.BaseReq;
import com.ifs.eportal.req.InvoiceServiceReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	// region -- Fields --

	@Autowired
	private InvoiceService invoiceService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// PayloadDto pl = Utils.getTokenInfor(header);
			// int id = pl.getId();

			// Get data
			// String keyword = req.getKeyword();
			// Boolean isOptional = req.getIsOptional();

			// Handle
			List<Invoice> tmp = invoiceService.search();

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
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody InvoiceServiceReq req) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			// int userId = pl.getId();

			Integer id = req.getId();
			String customerBranch = req.getCustomerBranch();
			String customerFromExcel = req.getCustomerFromExcel();
			String currencyIsoCode = req.getCurrencyIsoCode();
			Date invoiceDate = req.getInvoiceDate();
			String clientName = req.getClientName();
			String po = req.getPo();
			String clientRemarks = req.getClientRemarks();
			String customer = req.getCustomer();
			String documentType = req.getDocumentType();
			String recordTypeId = req.getRecordTypeId();
			String scheduleOfOffer = req.getScheduleOfOffer();
			String clientAccount = req.getClientAccount();
			String name = req.getName();
			Date systemmodstamp = req.getSystemModStamp();
			String contract = req.getContract();
			String status = req.getStatus();
			Float extermalId = req.getExternalId();
			Date createdDate = req.getCreatedDate();
			String supplier = req.getSupplier();
			Float invoiceAmount = req.getInvoiceAmount();
			Date paymentDate = req.getPaymentDate();
			Float creditPeriod = req.getCreditPeriod();
			String sfid = req.getSfid();
			String hcLastop = req.getHcLastop();
			String hcErr = req.getHcErr();

			Invoice m = new Invoice();

			m.setId(id);
			m.setCustomerBranch(customerBranch);
			m.setCustomerFromExcel(customerFromExcel);
			m.setCurrencyIsoCode(currencyIsoCode);
			m.setInvoiceDate(invoiceDate);
			m.setClientName(clientName);
			m.setPo(po);
			m.setClientRemarks(clientRemarks);
			m.setCustomer(customer);
			m.setDocumentType(documentType);
			m.setRecordTypeId(recordTypeId);
			m.setScheduleOfOffer(scheduleOfOffer);
			m.setClientAccount(clientAccount);
			m.setName(name);
			m.setSystemModStamp(systemmodstamp);
			m.setContract(contract);
			m.setStatus(status);
			m.setExternalId(extermalId);
			m.setCreatedDate(createdDate);
			m.setSupplier(supplier);
			m.setInvoiceAmount(invoiceAmount);
			m.setPaymentDate(paymentDate);
			m.setCreditPeriod(creditPeriod);
			m.setSfid(sfid);
			m.setHcLastop(hcLastop);
			m.setHcErr(hcErr);

			invoiceService.save(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		BaseRsp res = new BaseRsp();

		try {
			Invoice m = invoiceService.getBy(id);

			invoiceService.delete(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}