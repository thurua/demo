package com.rdp.common;

import java.io.File;
import java.net.URISyntaxException;
import java.security.ProtectionDomain;

/**
 * Directory utilities
 * 
 * @author T
 *
 */
public class Directory {
	/**
	 * Get running path
	 * 
	 * @return
	 */
	public static String getRunPath() {
		String res = "";

		if (runningFromJar()) {
			res = getCurrentJarPath();
		} else {
			res = getCurrentPath();
		}

		return res.concat("\\");
	}

	/**
	 * Get jar name
	 * 
	 * @return
	 */
	private static String getJarName() {
		ProtectionDomain a = Directory.class.getProtectionDomain();
		String b = a.getCodeSource().getLocation().getPath();
		String res = new File(b).getName();

		return res;
	}

	/**
	 * Check running from jar
	 * 
	 * @return
	 */
	private static boolean runningFromJar() {
		boolean res = getJarName().contains(".jar");

		return res;
	}

	/**
	 * Get current path
	 * 
	 * @return
	 */
	private static String getCurrentPath() {
		String res = new File("").getAbsolutePath();

		return res;
	}

	/**
	 * Get current jar path
	 * 
	 * @return
	 */
	private static String getCurrentJarPath() {
		String res = "";

		try {
			ProtectionDomain a = Directory.class.getProtectionDomain();
			String b = a.getCodeSource().getLocation().toURI().getPath();
			res = new File(b).getParent();
		} catch (URISyntaxException exception) {
			exception.printStackTrace();
		}

		return res;
	}
}