package com.rdp.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rdp.common.RandomString;
import com.rdp.dao.RunningRefNoDao;
import com.rdp.model.RunningRefNo;

@Service
@Transactional
public class RunningRefNoService {
	// region -- Fields --

	@Autowired
	private RunningRefNoDao runningRefNoDao;

	// end

	// region -- Methods --

	/**
	 * Generate a reference number
	 * 
	 * @param seqGroup
	 * @return
	 */
	public String createRefNo(String seqGroup) {
		DateFormat dfs = new SimpleDateFormat("yyyy");
		Calendar calendar = Calendar.getInstance();
		RunningRefNo no = runningRefNoDao.getRefNo(seqGroup, dfs.format(calendar.getTime()));

		StringBuilder builder = new StringBuilder(seqGroup);
		RandomString rs = new RandomString(4);
		String x = rs.nextString().toUpperCase();
		builder.append(x);

		if (no == null) {
			no = new RunningRefNo();
			no.setCreatedOn(new Date());
			no.setSeqGroup(seqGroup);

			no.setSeqNo(1);
			no.setModifiedOn(calendar.getTime());

			builder.append("000001");
			String t = builder.toString();

			no.setLastRefNo(t);
			no.setYearCode(dfs.format(calendar.getTime()));
			runningRefNoDao.save(no);

		} else {
			int nextNo = no.getSeqNo() + 1;
			String zerolength = "" + (nextNo);
			int zero = zerolength.length();
			int zeros = 6 - zero;

			no.setSeqNo(nextNo);
			no.setModifiedOn(calendar.getTime());

			for (int i = 0; i < zeros; i++) {
				builder.append("0");
			}
			builder.append(nextNo);
			String t = builder.toString();

			no.setLastRefNo(t);
			no.setYearCode(dfs.format(calendar.getTime()));
			runningRefNoDao.save(no);
		}

		return no.getLastRefNo();
	}

	// end
}