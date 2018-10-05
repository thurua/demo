package com.ifs.eportal.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author ToanNguyen 2018-Oct-03
 *
 */
public class ZFile {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(ZFile.class.getName());

	// end

	// region -- Methods --

	/**
	 * Read file
	 * 
	 * @param s Full path file name
	 * @return
	 */
	public static String read(String s) {
		String res = "";

		try {
			FileReader fr = new FileReader(s);
			BufferedReader br = new BufferedReader(fr);

			StringBuilder sb = new StringBuilder();
			String ls = System.getProperty("line.separator");

			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(ls);
			}

			res = sb.toString();
			br.close();
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get real path of Java application at runtime + sub-path
	 * 
	 * @param s Sub-path
	 * @return
	 */
	public static String getPath(String s) {
		return System.getProperty("user.dir") + "/" + s + "/";
	}

	// end
}