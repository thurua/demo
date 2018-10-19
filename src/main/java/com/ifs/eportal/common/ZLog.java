package com.ifs.eportal.common;

import java.util.Date;

/**
 * 
 * @author ToanNguyen 2018-Oct-19 (verified)
 *
 */
public class ZLog {
	// region -- Fields --

	// end

	// region -- Methods --

	/**
	 * Prints a string
	 * 
	 * @param s
	 */
	public static void print(String s) {
		Date now = ZDate.now();
		String t = ZLog.class.getSimpleName() + " ";
		t += ZDate.toString(now, Const.DateTime.FULL);
		System.out.print("##### " + t + " | " + s);
	}

	/**
	 * Prints a string with new line
	 * 
	 * @param s
	 */
	public static void println(String s) {
		print(s);
		System.out.println();
	}

	// end
}