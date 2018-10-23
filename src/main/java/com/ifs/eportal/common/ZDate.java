package com.ifs.eportal.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author ToanNguyen 2018-Oct-19 (verified)
 *
 */
public class ZDate {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(ZDate.class.getName());

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

	/**
	 * Convert String to Date
	 * 
	 * @param s String date & time
	 * @return
	 */
	public static Date toDate(String s) {
		Date res = null;

		try {
			SimpleDateFormat t = new SimpleDateFormat(Const.DateTime.FULL);
			res = t.parse(s);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return res;
	}

	/**
	 * Convert Date to String
	 * 
	 * @param d Date & time
	 * @param s Format date e.g: dd/MM/yyyy
	 * @return
	 */
	public static String toString(Date d, String s) {
		String res = "";

		try {
			SimpleDateFormat t = new SimpleDateFormat(s);
			res = t.format(d);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	// end
}