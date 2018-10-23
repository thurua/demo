package com.ifs.eportal.common;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author ToanNguyen 2018-Oct-18 (verified)
 *
 */
public class ZNum {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(ZNum.class.getName());

	// end

	// region -- Methods --

	/**
	 * Convert String to Integer, default value = 0 when error
	 * 
	 * @param s
	 * @return
	 */
	public static int toInt(String s) {
		int res = 0;

		try {
			res = Integer.parseInt(s);
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
	 * Convert String to Long, default value = 0 when error
	 * 
	 * @param s
	 * @return
	 */
	public static long toLong(String s) {
		long res = 0;

		try {
			res = Long.parseLong(s);
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
	 * Convert String to Float, default value = 0 when error
	 * 
	 * @param s
	 * @return
	 */
	public static float toFloat(String s) {
		float res = 0;

		try {
			res = Float.parseFloat(s);
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
	 * Convert String to Double, default value = 0 when error
	 * 
	 * @param s
	 * @return
	 */
	public static double toDouble(String s) {
		double res = 0;

		try {
			res = Double.parseDouble(s);
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
	 * Convert String to Integer, default value = null when error
	 * 
	 * @param s
	 * @return
	 */
	public static Integer toIntNull(String s) {
		Integer res = null;

		try {
			res = Integer.parseInt(s);
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
	 * Convert String to Long, default value = null when error
	 * 
	 * @param s
	 * @return
	 */
	public static Long toLongNull(String s) {
		Long res = null;

		try {
			res = Long.parseLong(s);
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
	 * Convert String to Float, default value = null when error
	 * 
	 * @param s
	 * @return
	 */
	public static Float toFloatNull(String s) {
		Float res = null;

		try {
			res = Float.parseFloat(s);
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
	 * Convert String to Double, default value = null when error
	 * 
	 * @param s
	 * @return
	 */
	public static Double toDoubleNull(String s) {
		Double res = null;

		try {
			res = Double.parseDouble(s);
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