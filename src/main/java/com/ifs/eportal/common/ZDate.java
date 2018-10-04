package com.ifs.eportal.common;

import java.util.Calendar;
import java.util.Date;

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
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 0, 0, 0);
		return calendar.getTime();
	}

	/**
	 * Get end of day
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		return calendar.getTime();
	}

	// end
}