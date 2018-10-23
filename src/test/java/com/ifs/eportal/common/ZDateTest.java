package com.ifs.eportal.common;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

/**
 * 
 * @author ToanNguyen 2018-Oct-16 (verified)
 *
 */
public class ZDateTest {
	// region -- Fields --

	private Date _now = ZDate.now();

	// end

	// region -- Methods --

	@Test
	public void test01() {
		Timestamp t1 = ZDate.getStartOfDay(_now);
		Timestamp t2 = ZDate.getEndOfDay(_now);
		Date t3 = ZDate.removeTime(_now);
		Date t4 = ZDate.today();

		System.out.println("ZDate.now(): " + _now);
		System.out.println("ZDate.getStartOfDay(_now): " + t1);
		System.out.println("ZDate.getEndOfDay(_now): " + t2);
		System.out.println("ZDate.removeTime(_now): " + t3);
		System.out.println("ZDate.today(): " + t4);
	}

	// end
}