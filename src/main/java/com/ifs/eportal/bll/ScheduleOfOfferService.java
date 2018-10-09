package com.ifs.eportal.bll;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.dal.InvoiceDao;
import com.ifs.eportal.dal.ScheduleOfOfferDao;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.dto.ScheduleOfOfferDetailDto;
import com.ifs.eportal.dto.ScheduleOfOfferDto;
import com.ifs.eportal.dto.SummaryDto;
import com.ifs.eportal.model.ScheduleOfOffer;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.req.ScheduleOfOfferReq;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
@Service(value = "_scheduleOfOfferService")
@Transactional
public class ScheduleOfOfferService {
	// region -- Fields --

	@Autowired
	private ScheduleOfOfferDao _scheduleOfOfferDao;

	@Autowired
	private InvoiceDao _invoiceDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(ScheduleOfOffer m) {
		_scheduleOfOfferDao.create(m);
	}

	/**
	 * Update ScheduleOfOffe
	 * 
	 * @param req
	 * @return
	 */
	public String update(String sfId, ScheduleOfOfferReq req) {
		String res = "";

		// Get data
		Integer id = req.getId();

		String currencyIsoCode = req.getCurrencyIsoCode();
		String scheduleNo = req.getScheduleNo();
		String factorCode = req.getFactorCode();
		String portalStatus = req.getPortalStatus();
		Float exchangeRate = req.getExchangeRate();

		// Handle
		ScheduleOfOffer m = _scheduleOfOfferDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setCurrencyIsoCode(currencyIsoCode);
			m.setScheduleNo(scheduleNo);
			m.setFactorCode(factorCode);
			m.setPortalStatus(portalStatus);
			if ("Authorise".equals(portalStatus)) {
				Date t = new Date();
				m.setAuthorisedDate(t);
				m.setAuthorisedBy(sfId);
			}
			m.setExchangeRate(exchangeRate);

			_scheduleOfOfferDao.update(m);
		}

		return res;
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDto read(int id) {
		ScheduleOfOfferDto res = _scheduleOfOfferDao.getBy(id);
		return res;
	}

	/**
	 * Read by
	 * 
	 * @param sfId
	 * @return
	 */
	public ScheduleOfOfferDetailDto read(String sfId) {
		ScheduleOfOfferDetailDto res = _scheduleOfOfferDao.getBy(sfId);
		return res;
	}

	/**
	 * Read by
	 * 
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */
	public ScheduleOfOfferDto read(String scheduleNo, String clientName) {
		ScheduleOfOfferDto res = _scheduleOfOfferDao.getBy(scheduleNo, clientName);
		return res;
	}

	/**
	 * Valid acceptance date
	 * 
	 * @param clientAccount
	 * @param d
	 * @param l
	 * @return
	 */
	public String acceptanceDate(String clientAccount, Date d, List<LineItemDto> l) {
		String res = "";
		
		Date activatedOn = _scheduleOfOfferDao.acceptanceDate(clientAccount);
		if (activatedOn == null) {
			return res;
		}

		DateTime t = new DateTime(activatedOn);
		t = t.plusMonths(6).plusDays(-1);
		activatedOn = t.toDate();
		if (d.after(activatedOn)) {
			// Client - Dormant Account (Last Schedule submitted more than 6 months ago).
			res = "IVH";
		} else {

			t = new DateTime(d);
			t = t.plusYears(-1).plusDays(-1);
			Date fr = t.toDate();
			Date to = d;
			t = new DateTime(d);
			List<SummaryDto> ll = _invoiceDao.getInvoiceSummary(clientAccount, fr, to);

			Stream<Double> t1;
			t1 = l.stream().map(r -> r.getAmount());
			Double cur = t1.mapToDouble(Double::intValue).sum();

			int m = t.getMonthOfYear();
			int y = t.getYear();
			t1 = ll.stream().filter(r -> r.getMonth().equals(m) && r.getYear().equals(y)).map(r -> r.getAmount());
			Double fv = t1.mapToDouble(Double::intValue).sum();

			t1 = ll.stream().map(r -> r.getAmount());
			Double afv = t1.mapToDouble(Double::intValue).sum();

			if (afv > 0) {
				Double t2 = (fv + cur) / ((afv + cur) / 12);
				if (t2 > 0.3) {
					// Customer - Invoice Amount more than Client Average Invoice Size.
					res = "CCD";
				}
			}
		}

		return res;
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<ScheduleOfOfferDto> search(PagingReq req) {
		List<ScheduleOfOfferDto> res = _scheduleOfOfferDao.search(req);
		return res;
	}

	// end
}