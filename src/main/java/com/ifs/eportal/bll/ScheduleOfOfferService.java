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
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
@Service(value = "scheduleOfOfferService")
@Transactional
public class ScheduleOfOfferService {
	// region -- Fields --

	@Autowired
	private ScheduleOfOfferDao scheduleOfOfferDao;

	@Autowired
	private InvoiceDao invoiceDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(ScheduleOfOffer m) {
		scheduleOfOfferDao.create(m);
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDto getBy(Integer id) {
		ScheduleOfOfferDto res = scheduleOfOfferDao.getBy(id);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	public ScheduleOfOfferDto getBy(String sfId) {
		ScheduleOfOfferDto res = scheduleOfOfferDao.getBy(sfId);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param scheduleNo
	 * @param clientName
	 * @return
	 */
	public ScheduleOfOfferDto getBy(String scheduleNo, String clientName) {
		ScheduleOfOfferDto res = scheduleOfOfferDao.getBy(scheduleNo, clientName);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleOfOfferDetailDto getDetailBy(Integer id) {
		ScheduleOfOfferDetailDto res = scheduleOfOfferDao.getDetailBy(id);
		return res;
	}

	/**
	 * Get by
	 * 
	 * @param sfId
	 * @return
	 */
	public ScheduleOfOfferDetailDto getDetailBy(String sfId) {
		ScheduleOfOfferDetailDto res = scheduleOfOfferDao.getDetailBy(sfId);
		return res;
	}

	/**
	 * Update
	 * 
	 * @param req
	 * @return
	 */
	public String update(ScheduleOfOfferReq req) {
		String res = "";

		// Get data
		Integer id = req.getId();
		String scheduleNo = req.getScheduleNo();
		String portalStatus = req.getPortalStatus();
		String authorisedBy = req.getAuthorisedBy();

		// Handle
		ScheduleOfOffer m = scheduleOfOfferDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setScheduleNo(scheduleNo);
			m.setPortalStatus(portalStatus);

			if ("Authorised".equals(portalStatus)) {
				m.setAuthorisedDate(new Date());
				m.setAuthorisedBy(authorisedBy);
			}

			scheduleOfOfferDao.update(m);
		}

		return res;
	}

	/**
	 * Validate acceptance date
	 * 
	 * @param clientAccount
	 * @param d
	 * @param l
	 * @return
	 */
	public String validateAcceptance(String clientAccount, Date d, List<LineItemDto> l) {
		String res = "";

		Date acceptanceDate = scheduleOfOfferDao.getAcceptanceDate(clientAccount);
		if (acceptanceDate == null) {
			return res;
		}

		DateTime t = new DateTime(acceptanceDate);
		t = t.plusMonths(6).plusDays(-1);
		acceptanceDate = t.toDate();

		if (d.after(acceptanceDate)) {
			// Client - Dormant Account (Last Schedule submitted more than 6 months ago).
			res = "IVH";
		} else {
			t = new DateTime(d);
			t = t.plusYears(-1).plusDays(-1);
			Date fr = t.toDate();

			Date to = d;
			t = new DateTime(d);
			List<SummaryDto> ls = invoiceDao.getInvoiceSummary(clientAccount, fr, to);

			Stream<Double> s;
			s = l.stream().map(r -> r.getAmount());
			Double cur = s.mapToDouble(Double::intValue).sum();

			int m = t.getMonthOfYear();
			int y = t.getYear();
			s = ls.stream().filter(r -> r.getMonth().equals(m) && r.getYear().equals(y)).map(r -> r.getAmount());
			Double fv = s.mapToDouble(Double::intValue).sum();

			s = ls.stream().map(r -> r.getAmount());
			Double afv = s.mapToDouble(Double::intValue).sum();

			if (afv > 0) {
				Double x = (fv + cur) / ((afv + cur) / 12);
				if (x > 0.3) {
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
		List<ScheduleOfOfferDto> res = scheduleOfOfferDao.search(req);
		return res;
	}

	// end
}