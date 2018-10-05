package com.ifs.eportal.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * 
 * @author ToanNguyen 2018-Oct-03
 *
 */
public class ZDate {
	// region -- Fields --

	// end

	// region -- Methods --

	/**
	 * Get start of day
	 * 
	 * @param d
	 * @return
	 */
	public static Timestamp getStartOfDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);

		cal.set(year, month, day, 0, 0, 0);
		Date t = cal.getTime();
		Timestamp res = new Timestamp(t.getTime());

		return res;
	}

	/**
	 * Get end of day
	 * 
	 * @param d
	 * @return
	 */
	public static Timestamp getEndOfDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);

		cal.set(year, month, day, 23, 59, 59);
		Date t = cal.getTime();
		Timestamp res = new Timestamp(t.getTime());

		return res;
	}

	/**
	 * Get start of day
	 * 
	 * @param d
	 * @return
	 */
	public static DateTime getStartOfDay(DateTime d) {
		DateTime res = d.withTimeAtStartOfDay();
		return res;
	}

	/**
	 * Get end of day
	 * 
	 * @param d
	 * @return
	 */
	public static DateTime getEndOfDay(DateTime d) {
		DateTime res = d.plusDays(1).withTimeAtStartOfDay();
		return res;
	}

	// end
}