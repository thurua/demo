package com.ifs.eportal.common;

import java.util.Date;

/**
 * 
 * @author ToanNguyen 2018-Oct-16 (verified)
 *
 */
public final class ZConfig {
	// region -- Fields --

	public static boolean _printTrace = false;

	public static boolean _writeLog = false;

	public static boolean _allowUpload = false;

	public static int _jwtTime = 0;

	public static String _jwtSigning = "";

	public static long _lastAccess = new Date().getTime() / 1000;

	// end
}