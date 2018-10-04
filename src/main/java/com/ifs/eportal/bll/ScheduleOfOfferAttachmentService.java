package com.ifs.eportal.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dal.ScheduleOfOfferAttachmentDao;
import com.ifs.eportal.model.ScheduleOfOfferAttachment;

/**
 * 
 * @author ToanNguyen 2018-Oct-04
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

		scheduleOfOfferAttachmentDao.save(m);
	}

	/**
	 * Get by
	 * 
	 * @param uploadedBy
	 * @return
	 */
	public List<ScheduleOfOfferAttachment> getBy(String uploadedBy) {
		List<ScheduleOfOfferAttachment> res = scheduleOfOfferAttachmentDao.getBy(uploadedBy);
		return res;
	}

	// end
}