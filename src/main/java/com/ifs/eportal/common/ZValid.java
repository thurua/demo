package com.ifs.eportal.common;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.ifs.eportal.dto.ApprovedCustomerLimitDto;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.dto.ClientAccountDto;
import com.ifs.eportal.dto.CustomDto;
import com.ifs.eportal.dto.InvoiceDto;
import com.ifs.eportal.model.CreditNote;
import com.ifs.eportal.model.Invoice;

public class ZValid {
	// region -- Fields --

	// end

	// region -- Methods --

	/**
	 * 
	 * @param curr
	 * @param total
	 * @param name
	 * @return
	 */
	public static String ratioOverdue(List<CustomDto> curr, List<CustomDto> total, String name) {
		String res = "";

		// Find customer in curr sumary
		double amountCurr = 0;
		for (CustomDto i : curr) {
			if (name.trim().equals(i.getCode())) {
				amountCurr = i.getValue();
			}
		}

		// Find customer in total sumary
		double amountTotal = 0;
		for (CustomDto i : total) {
			if (name.trim().equals(i.getCode())) {
				amountTotal = i.getValue();
			}
		}

		if (amountTotal > 0) {
			double t = amountCurr / amountTotal;
			if (t > 0.5) {
				// Customer - Slow Payment.
				res = "IVC";
			}
		}

		return res;
	}

	/**
	 * 
	 * @param curr
	 * @param total
	 * @param name
	 * @return
	 */
	public static String ratioDisputed(List<CustomDto> curr, List<CustomDto> total, String name) {
		String res = "";

		// Find customer in curr sumary
		double amountCurr = 0;
		for (CustomDto i : curr) {
			if (name.trim().equals(i.getCode())) {
				amountCurr = i.getValue();
			}
		}

		// Find customer in total sumary
		double amountTotal = 0;
		for (CustomDto i : total) {
			if (name.trim().equals(i.getCode())) {
				amountTotal = i.getValue();
			}
		}

		if (amountTotal > 0) {
			double t = amountCurr / amountTotal;
			if (t > 0.2) {
				// Customer - Outstanding Invoices are disputed.
				res = "IVD";
			}
		}

		return res;
	}

	/**
	 * 
	 * @param curr
	 * @param amount
	 * @param name
	 * @return
	 */
	public static String customerAverage(List<CustomDto> curr, double amount, String name) {
		String res = "";

		// Find customer in curr sumary
		double amountCurr = 0;
		for (CustomDto i : curr) {
			if (name.trim().equals(i.getCode())) {
				amountCurr = i.getValue();
			}
		}
		if (amount > amountCurr) {
			// Customer - Invoice Amount more than Customer Average Invoice Size.
			res = "CCC";
		}

		return res;
	}

	/**
	 * 
	 * @param lcc
	 * @param name
	 * @param amount
	 * @param isInvoiceFactoring
	 * @param factor
	 * @param d
	 * @param ca
	 * @return
	 */
	public static String customer(List<ClientAccountCustomerDto> lcc, String name, double amount,
			Boolean isInvoiceFactoring, String factor, Date d, ClientAccountDto ca) {
		String res = "";
		// Boolean found = false;
		name = name.trim();
		for (ClientAccountCustomerDto i : lcc) {
			if (!name.equals(i.getCcName())) {
				continue;
			}

			// found = true;
			res = "";

			if (i.getStatus().equals("Activated")) {
				Boolean t1 = (factor.equals(ca.getFciName()) && ca.getAccountType().equals("Import"));
				Boolean t2 = (factor.equals(i.getFciName()) && ca.getAccountType().equals("Export"));
				DateTime activationDate = new DateTime(i.getActivationDate());
				if (t1 || t2 || ca.getAccountType().equals("Domestic")) {
					if (d.after(activationDate.toDate())) {
						/* ToanNguyen 2018-Aug-23 IFS-974 */
						// Schedule Acceptance Date cannot be before client account customer activation
						// date.
						res = "CC2";
					} else {
						if (isInvoiceFactoring) {
							Boolean t3 = i.getVerificationExceedingInvoiceAmount() == null;
							if (t3 || amount > i.getVerificationExceedingInvoiceAmount()) {
								/* NhatNguyen 2018-Sep-03 IFS-1042 */
								// Customer - Per Verification Amount.
								res = "CCB";
							}

							Date t = activationDate.plusMonths(6).minusDays(1).toDate();
							if (d.after(t)) {
								/* TriNguyen 2018-Aug-31 IFS-1040 */
								// Customer - New.
								res += ",CC9";
							}

							if (i.getVerification() != null && i.getVerification() == 100) {
								/* TriNguyen 2018-Aug-31 IFS-1041 */
								// Customer - 100% Verification Required.
								res += ",CCA";
							}
						}
					}
				} else {
					/* ToanNguyen 2018-Aug-31 IFS-1026 */
					// Customer Branch and/or Factor Code is not found in Client Account.
					res = "CC3";
				}
			} else if (i.getStatus().equals("Terminated") || i.getStatus().equals("Closed")) {
				/* ToanNguyen 2018-Aug-31 IFS-1026 */
				// Customer is already terminated in Client Account.
				res = "CC4";
			} else {
				/* ToanNguyen 2018-Aug-31 IFS-1026 */
				// Customer is not activated in Client Account.
				res = "CC5";
			}
		}
		/* Comment by NhatNguyen 2018-Oct-01 IFS-1247 */
		/*
		 * if(!found) { //ToanNguyen 2018-Aug-31 IFS-1025 // Customer Name is not found
		 * in Client Account. res = 'CC1'; }
		 */

		return res;
	}

	/**
	 * 
	 * @param credit
	 * @param invoice
	 * @param name
	 * @return
	 */
	public static String ratioCreditInvoice(List<CustomDto> credit, List<CustomDto> invoice, String name) {
		String res = "";

		// Find customer in credit sumary
		double amountCredit = 0;
		for (CustomDto i : credit) {
			if (name.trim().equals(i.getCode())) {
				amountCredit = i.getValue();
			}
		}

		// Find customer in invoice sumary
		double amountInvoice = 0;
		for (CustomDto i : invoice) {
			if (name.trim().equals(i.getCode())) {
				amountInvoice = i.getValue();
			}
		}

		if (amountInvoice > 0) {
			double t = amountCredit / amountInvoice;
			if (t > 0.2) {
				// Customer - High % of Invoice offset by Credit Note in past 12 months.
				res = "IVA";
			}
		}

		return res;
	}

	public static String acl(Invoice o, List<ApprovedCustomerLimitDto> lcl, String factoringType,
			List<CustomDto> total) {
		String res = "";
		Boolean isExistsACL = false;

		switch (factoringType) {
		case "Recourse":
		case "Non-Recourse":
			if (lcl.size() == 0) {
				/* TriNguyen 2018-Aug-30 IFS-1036 */
				// Customer - No Approved CL.
				res = "CC6";
			} else {
				isExistsACL = false;

				for (ApprovedCustomerLimitDto acl : lcl) {
					if (acl.getCustomer().equals(o.getCustomer())) {
						if (acl.getValidFrom().before(o.getInvoiceDate())) {
							/* TriNguyen 2018-Aug-30 IFS-1038 */
							// Customer - Invoice dated before Approved CL Date.
							res = "CC7,";
						}

						/* TriNguyen 2018-Aug-30 IFS-1039 */
						for (Integer i = 0; i < total.size(); i++) {
							String name = total.get(i).getCode();
							double totalOutstandingInvAmount = (double) total.get(i).getValue();
							if (acl.getCustomer().equals(name) && acl.getApprovedLimit() < totalOutstandingInvAmount) {
								// Customer - Total Outstanding Invoice Amount more than Approved CL Limit.
								res += "IVG";
							}
						}

						isExistsACL = true;
					}
				}

				if (!isExistsACL) {
					/* TriNguyen 2018-Aug-30 IFS-1036 */
					// Customer - No Approved CL.
					res = "CC6";
				}
			}
			break;
		default: {
			if (lcl.size() == 0) {
				/* TriNguyen 2018-Aug-30 IFS-1036 */
				// Customer - No Approved CL.
				res = "CC6";
			} else {
				isExistsACL = false;

				for (ApprovedCustomerLimitDto acl : lcl) {
					if (acl.getCustomer().equals(o.getCustomer())) {
						Date t = (new DateTime(o.getInvoiceDate())).plusDays(o.getCreditPeriod().intValue()).toDate();
						if (acl.getValidTo().after(t)) {
							/* TriNguyen 2018-Aug-30 IFS-1037 */
							// Customer - Invoice Credit Period exceed Approved CL Credit Period.
							res = "CC8";
						}

						isExistsACL = true;
					}
				}

				if (!isExistsACL) {
					/* TriNguyen 2018-Aug-30 IFS-1036 */
					// Customer - No Approved CL.
					res = "CC6";
				}
			}
		}
		}
		return res;
	}

	/**
	 * 
	 * @param l
	 * @param o
	 * @return
	 */
	public static String appliedInvoices(List<InvoiceDto> l, CreditNote cn) {
		String err = "";
		Boolean found = false;

		for (InvoiceDto i : l) {
			if (!cn.getAppliedInvoice().equals(i.getName())) {
				continue;
			}

			found = true;
			err = "";
			// o.Applied_INV_As_per_schedule__c = i.Id;

			/* ToanNguyen 2018-Sep-02 IFS-1056 */
			if (i.getStatus().equals("Reassigned") || i.getStatus().equals("Reversed")
					|| i.getStatus().equals("Disputed")) {
				// Credit Note - Invoice not outstanding.
				err = "CN2";
			}
			/* ToanNguyen 2018-Sep-02 IFS-1057 */
			if (i.getOutstandingAmount() != (double) cn.getCreditAmount()) {
				// Credit Note - Invoice Outstanding Amount not equal to Credit Note Amount.
				err += ",CN3";
			}
		}

		if (!found) {
			/* ToanNguyen 2018-Sep-02 IFS-1056 */
			// Credit Note - Invoice Number not found.
			err = "CN1";
		}

		return err;
	}

	// end
}