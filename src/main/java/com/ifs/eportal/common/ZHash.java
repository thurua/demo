package com.ifs.eportal.common;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @author ToanNguyen 2018-Oct-16 (verified)
 *
 */
public class ZHash {
	// region -- Fields --

	private static String _uploadHash = "";

	private static String _uploadKey = "";

	// end

	// region -- Methods --

	/**
	 * Check authentication
	 * 
	 * @param auth Authentication key
	 * @return
	 */
	public static boolean checkAuth(String auth) {
		try {
			if (auth == null || auth.isEmpty()) {
				return false;
			}

			getEnv();

			String[] arr = auth.split(":");

			if (!arr[0].equals(_uploadKey)) {
				return false;
			}

			if (System.currentTimeMillis() - Long.parseLong(arr[1]) > 600000) {
				return false;
			}

			String t = hash(arr[0] + ":" + arr[1], _uploadHash);
			if (t.equals(arr[2])) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	/**
	 * Get authentication
	 * 
	 * @return
	 */
	public static String getAuth() {
		getEnv();

		String s = _uploadKey + ':' + System.currentTimeMillis();
		String t = hash(s, _uploadHash);

		String res = s + ':' + t;
		return res;
	}

	/**
	 * Hash
	 * 
	 * @param value
	 * @param key
	 * @return
	 */
	public static String hash(String value, String key) {
		String res = "";

		try {
			String s = "HmacSHA512";
			byte[] b = key.getBytes("UTF-8");
			SecretKeySpec k = new SecretKeySpec(b, s);

			Mac mac = Mac.getInstance(s);
			mac.init(k);

			b = value.getBytes("UTF-8");
			b = mac.doFinal(b);

			res = DatatypeConverter.printHexBinary(b);
			res = res.toUpperCase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return res;
	}

	/**
	 * Get environment variable
	 */
	private static void getEnv() {
		_uploadHash = System.getenv("UPLOAD_HASH");
		_uploadKey = System.getenv("UPLOAD_KEY");
	}

	// end
}