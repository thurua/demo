package com.ifs.eportal.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dal.ScheduleOfOfferAttachmentDao;
import com.ifs.eportal.dto.AttachmentDto;
import com.ifs.eportal.model.ScheduleOfOfferAttachment;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.rsp.BaseRsp;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
@Service(value = "scheduleOfOfferAttachmentService")
@Transactional
public class ScheduleOfOfferAttachmentService {
	// region -- Fields --

	@Autowired
	private ScheduleOfOfferAttachmentDao scheduleOfOfferAttachmentDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(ScheduleOfOfferAttachment m) {
		Date now = Utils.getTime(Calendar.HOUR, 24);

		m.setActive(true);
		m.setDeleted(false);
		m.setCreatedDate(now);
		m.setUploadedOn(now);

		scheduleOfOfferAttachmentDao.create(m);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<AttachmentDto> search(PagingReq req) {
		List<AttachmentDto> res = scheduleOfOfferAttachmentDao.search(req);
		return res;
	}

	/**
	 * Delete by
	 * 
	 * @param id
	 * @return
	 */
	public BaseRsp delete(Integer id) {
		BaseRsp res = new BaseRsp();

		ScheduleOfOfferAttachment m = scheduleOfOfferAttachmentDao.read(id);
		if (m.isDeleted()) {
			res.setError("File is not exists");
		} else {
			m.setDeleted(true);
			scheduleOfOfferAttachmentDao.update(m);
		}

		return res;
	}

	// end
}