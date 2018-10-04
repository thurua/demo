package com.ifs.eportal.common;

import java.util.HashMap;

public class ZError {
	// region -- Fields --

	// end

	// region -- Methods --

	/**
	 * 
	 * @param errs
	 * @return
	 */
	public static String getMessage(HashMap<String, String> errs) {
		String res = "";
		HashMap<String, String> m = invoiceCreditError();

		// Convert error code to error message
		for (String key : errs.keySet()) {
			String row = errs.get(key);
			if (m.get(key) == null) {
				continue;
			}

			row = row.substring(0, row.length() - 2);
			res += m.get(key) + " Inv/CN No. [" + row + "]\n";
		}

		return res;
	}

	/**
	 * 
	 * @param errs
	 * @return
	 */
	public static String getError(HashMap<String, String> errs) {
		String res = "";
		HashMap<String, String> m = scheduleOfferError();

		// Convert error code to error message
		for (String key : errs.keySet()) {
			String row = errs.get(key);
			if (m.get(key) == null) {
				continue;
			}

			row = row.substring(0, row.length() - 2);
			res += m.get(key) + " Inv/CN No. [" + row + "]\n";
		}

		return res;
	}

	/**
	 * 
	 * @param errs
	 * @param key
	 * @param no
	 * @return
	 */
	public static HashMap<String, String> addError(HashMap<String, String> errs, String key, String no) {
		if (key != "") {
			String[] arr = key.split(",");

			for (String i : arr) {
				if (i != null && !i.isEmpty()) {
					continue;
				}

				String s = errs.get(i);
				if (s == null) {
					s = "";
				}
				s += no + ", ";
				errs.put(i, s);
			}
		}

		return errs;
	}

	/**
	 * 
	 * @return
	 */
	private static HashMap<String, String> invoiceCreditError() {
		// Define error code with error message
		HashMap<String, String> m = new HashMap<String, String>();

		/* TriNguyen 2018-Aug-31 IFS-1036 */
		m.put("CC6", "Customer - No Approved CL.");

		/* TriNguyen 2018-Aug-31 IFS-1038 */
		m.put("CC7", "Customer - Invoice dated before Approved CL Date.");

		/* TriNguyen 2018-Aug-31 IFS-1037 */
		m.put("CC8", "Customer - Invoice Credit Period exceed Approved CL Credit Period.");

		/* TriNguyen 2018-Aug-31 IFS-1040 */
		m.put("CC9", "Customer - New.");

		/* TriNguyen 2018-Aug-31 IFS-1041 */
		m.put("CCA", "Customer - 100% Verification Required.");

		/* NhatNguyen 2018-Sep-03 IFS-1042 */
		m.put("CCB", "Customer - Per Verification Amount.");

		/* ToanNguyen 2018-Sep-04 IFS-1043 */
		m.put("CCC", "Customer - Invoice Amount more than Customer Average Invoice Size.");

		/* ToanNguyen 2018-Sep-04 IFS-1044,1052 */
		m.put("CCD", "Customer - Invoice Amount more than Client Average Invoice Size.");

		/* ToanNguyen 2018-Sep-02 IFS-1056 */
		m.put("CN1", "Credit Note - Invoice Number not found.");
		m.put("CN2", "Credit Note - Invoice not outstanding.");

		/* ToanNguyen 2018-Sep-02 IFS-1057 */
		m.put("CN3", "Credit Note - Invoice Outstanding Amount not equal to Credit Note Amount.");

		/* ToanNguyen 2018-Sep-04 IFS-1055 */
		m.put("CN7", "Credit Note - Amount more than Client Average Invoice Size.");

		/* CongLe 2018-Aug-29 IFS-1031 */
		m.put("IV1", "Verification Required - Import Factoring.");

		/* HanhNguyen 2018-Aug-29 IFS-1035 */
		m.put("IV2", "Invoice - Overdue by XXX days.");

		/* HanhNguyen 2018-Aug-29 IFS-1033 */
		m.put("IV3", "Verification Required - Per Verification Rule.");

		/* HanhNguyen 2018-Aug-29 IFS-1034 */
		m.put("IV6", "Invoice - Dated 1 year before Schedule Acceptance Date.");

		/* CongLe 2018-Aug-29 IFS-1032 */
		m.put("IV7", "Verification Required - SPOT Program.");
		m.put("IV8", "Verification Required - Multiply Program.");
		m.put("IV9", "Verification Required - Small Ticket Factoring Program.");

		/* ToanNguyen 2018-Sep-03 IFS-1047 */
		m.put("IVA", "Customer - High % of Invoice offset by Credit Note in past 12 months.");

		/* NhatNguyen 2018-Sep-06 IFS-1051 */
		m.put("IVB", "Client - Per Verification Amount.");

		/* ToanNguyen 2018-Sep-03 IFS-1045 */
		m.put("IVC", "Customer - Slow Payment.");

		/* ToanNguyen 2018-Sep-03 IFS-1046 */
		m.put("IVD", "Customer - Outstanding Invoices are disputed.");

		/* TriNguyen 2018-Sep-10 IFS-1048 */
		m.put("IVE", "Client - New.");

		/* TriNguyen 2018-Sep-10 IFS-1049 */
		m.put("IVF", "Client - Suspended.");

		/* TriNguyen 2018-Sep-11 IFS-1039 */
		m.put("IVG", "Customer - Total Outstanding Invoice Amount more than Approved CL Limit.");

		/* TriNguyen 2018-Sep-11 IFS-1052 */
		m.put("IVH", "Client - Dormant Account (Last Schedule submitted more than 6 months ago).");
		m.put("IVI", "Customer - Invoice Amount more than Client Average Invoice Size");

		/* TriNguyen 2018-Sep-12 IFS-1050 */
		m.put("IVJ", "Client - 100% Verification Required.");

		return m;
	}

	/**
	 * 
	 * @return
	 */
	private static HashMap<String, String> scheduleOfferError() {
		// Define error code with error message
		HashMap<String, String> m = new HashMap<String, String>();

		/* ToanNguyen 2018-Aug-31 IFS-1025 */
		m.put("CC1", "Customer Name is not found in Client Account.");

		/* ToanNguyen 2018-Aug-23 IFS-974 */
		m.put("CC2", "Schedule Acceptance Date cannot be before client account customer activation date.");

		/* ToanNguyen 2018-Aug-31 IFS-1026 */
		m.put("CC3", "Customer Branch and/or Factor Code is not found in Client Account.");
		m.put("CC4", "Customer is already terminated in Client Account.");
		m.put("CC5", "Customer is not activated in Client Account.");

		/* ToanNguyen 2018-Sep-03 IFS-1054 */
		m.put("CN4", "Invalid Credit Note Date.");
		m.put("CN5", "Credit Note Date cannot be after Schedule Acceptance Date.");
		m.put("CN6", "Credit Note - Dated 1 year before Schedule Acceptance Date.");

		/* ToanNguyen 2018-Sep-03 IFS-1030 */
		m.put("IV4", "Invalid Invoice Date.");
		m.put("IV5", "Invoice Date cannot be after Schedule Acceptance Date.");

		return m;
	}

	// end
}