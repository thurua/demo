package com.ifs.eportal.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author ToanNguyen 2018-Oct-16 (verified)
 *
 */
public class ZDate {
	// region -- Fields --

	// end

	// region -- Methods --

	/**
	 * Get date only
	 * 
	 * @return
	 */
	public static Date today() {
		Date res = new Date();
		return removeTime(res);
	}

	/**
	 * Get date and time
	 * 
	 * @return
	 */
	public static Date now() {
		Date res = new Date();
		return res;
	}

	/**
	 * Get start of day
	 * 
	 * @param d
	 * @return
	 */
	public static Timestamp getStartOfDay(Date d) {
		Date t = removeTime(d);
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
		Calendar cal = removeTime(d, -1);
		cal.add(Calendar.DAY_OF_YEAR, 1);

		Date t = cal.getTime();
		Timestamp res = new Timestamp(t.getTime());
		return res;
	}

	/**
	 * Remove time
	 * 
	 * @param d Date and time
	 * @return
	 */
	public static Date removeTime(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Remove time
	 * 
	 * @param d  Date and time
	 * @param ms Millisecond
	 * @return
	 */
	private static Calendar removeTime(Date d, int ms) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, ms);

		return cal;
	}

	// end
}