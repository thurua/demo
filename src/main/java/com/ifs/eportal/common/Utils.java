package com.ifs.eportal.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.dto.ValidDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.model.Invoice;

public class Utils {
	// region -- Fields --

	// end

	// region -- Methods --

	/**
	 * Get authorities
	 * 
	 * @param roles
	 * @return
	 */
	public static List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
		if (roles != null) {
			return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	/**
	 * Get UTC date time
	 * 
	 * @param type Choose attribute to add (Calendar.MINUTE, Calendar.HOUR, ...)
	 * @param n    Number want to add
	 * @return
	 */
	public static Date getTime(int type, int n) {
		Date res = null;

		try {
			TimeZone t = TimeZone.getTimeZone("UTC");
			Calendar c = Calendar.getInstance(t);
			c.add(type, n);
			res = c.getTime();
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				System.out.println(ex.getMessage());
			}
		}

		return res;
	}

	/**
	 * Verify with current date
	 * 
	 * @param d Date
	 * @return
	 */
	public static boolean verify(Date d) {
		TimeZone t = TimeZone.getTimeZone("UTC");
		Calendar c = Calendar.getInstance(t);

		if (d.compareTo(c.getTime()) < 0) {
			return false;
		}

		return true;
	}

	/**
	 * Add error
	 * 
	 * @param rsp
	 * @param err
	 * @return
	 */
	public static ValidDto addError(ValidDto rsp, String err) {
		rsp.setSuccess(false);
		String s = rsp.message + "\n" + err;
		rsp.message = s;
		return rsp;
	}

	/**
	 * Add result
	 * 
	 * @param rsp
	 * @param err
	 * @return
	 */
	public static ValidDto addResult(ValidDto rsp, String err) {
		String s = rsp.message + "\n" + err;
		rsp.message = s;
		return rsp;
	}

	/**
	 * Format string
	 * 
	 * @param s
	 * @return
	 */
	public static String formatStr(String s) {
		String res = "";

		s = s.toLowerCase();
		String[] arr = s.split(" ");

		for (String x : arr) {
			res += x.substring(0, 1).toUpperCase() + x.substring(1, x.length()) + ' ';
		}

		res = res.trim();
		return res;
	}

	/**
	 * get all number
	 * 
	 * @param l
	 * @return
	 */
	public static String getAllNo(List<LineItemDto> l) {
		String res = "";

		// Filter
		Stream<String> t1;
		t1 = l.stream().map(r -> r.getNo()).filter(r -> !r.isEmpty());
		List<String> t2 = t1.collect(Collectors.toList());
		for (String i : t2) {
			res += i.trim() + ", ";
		}

		// Remove ", "
		int t3 = res.length();
		if (t3 > 1) {
			res = res.substring(0, t3 - 2);
		}

		return res;
	}

	/**
	 * Get all fields
	 * 
	 * @param fields
	 * @param type
	 * @return
	 */
	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null) {
			getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	/**
	 * Get all data in object to string
	 * 
	 * @param o Object data
	 * @return
	 */
	public static String toString(Object o) {
		return toString(o, false);
	}

	/**
	 * Get all data in object to string
	 * 
	 * @param o     Object data
	 * @param print Allow to print out screen
	 * @return
	 */
	public static String toString(Object o, boolean print) {
		String res = "";

		StringBuilder sb = new StringBuilder();
		String line = System.getProperty("line.separator");

		sb.append(o.getClass().getName());
		sb.append(" {");
		sb.append(line);

		List<Field> fields = new ArrayList<Field>();
		fields = Utils.getAllFields(fields, o.getClass());

		// Print field names paired with their values
		for (Field field : fields) {
			sb.append("  ");

			try {
				String t1 = field.getName();
				sb.append(t1);
				sb.append(": ");

				// Make get method name
				t1 = "get" + t1.substring(0, 1).toUpperCase() + t1.substring(1);
				Method method = o.getClass().getMethod(t1);
				Object t2 = method.invoke(o);
				t1 = t2 != null ? t2.toString() : "";

				sb.append(t1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			sb.append(line);
		}

		sb.append("}");
		res = sb.toString();

		if (print) {
			System.out.println(res);
		}

		return res;
	}

	/**
	 * Use for supplier
	 * 
	 * @param l
	 * @param name
	 * @return
	 */
	public static String getAccIdByName(List<AccountDto> l, String name) {
		String res = "";

		if (l == null || l.size() == 0) {
			return res;
		}

		name = name.trim();
		for (AccountDto i : l) {
			if (i.getName().equals(name)) {
				res = i.getSfId();
				break;
			}
		}

		return res;
	}

	/**
	 * Use for customer
	 * 
	 * @param l
	 * @param name
	 * @return
	 */
	public static String getAccCusIdByName(List<ClientAccountCustomerDto> l, String name) {
		String res = "";

		if (l == null || l.size() == 0) {
			return res;
		}

		name = name.trim();
		for (ClientAccountCustomerDto i : l) {
			if (i.getCcName().equals(name)) {
				res = i.getCustomer();
				break;
			}
		}

		return res;
	}

	public static List<String> getNames(List<LineItemDto> l) {
		List<String> res = new ArrayList<>();

		for (LineItemDto i : l) {
			if (i.getName() != null && !i.getName().isEmpty()) {
				res.add(i.getName().trim());
			}
		}

		return res;
	}

	public static List<String> getNo(List<LineItemDto> l) {
		List<String> res = new ArrayList<>();

		for (LineItemDto i : l) {
			if (i.getName() != null && !i.getName().isEmpty()) {
				res.add(i.getNo().trim());
			}
		}

		return res;
	}

	/* ToanNguyen 2018-Sep-05 */
	public static HashMap<String, String> toMapInvoice(List<Invoice> l) {
		HashMap<String, String> res = new HashMap<String, String>();

		for (Invoice i : l) {
			res.put(i.getName(), i.getUuId());
		}

		return res;
	}

	/* ToanNguyen 2018-Sep-05 */
	public static HashMap<String, String> toMapCredit(List<CreditNote> l) {
		HashMap<String, String> res = new HashMap<String, String>();

		for (CreditNote i : l) {
			res.put(i.getName(), i.getUuId());
		}

		return res;
	}

	// end
}