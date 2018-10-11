package com.ifs.eportal.dto;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen 2018-Oct-08 (verified)
 *
 */
public class ValidDto {
	// region -- Fields --

	public ExcelDto data;

	public Double sequence;

	public String message;

	public String portalUserId;

	public String invoiceDataPath;

	@JsonProperty(value = "success")
	private boolean success;

	@JsonProperty(value = "invoiceValid")
	private boolean invoiceValid;

	@JsonProperty(value = "creditValid")
	private boolean creditValid;

	@JsonProperty(value = "errors")
	private HashMap<String, String> errors;

	// end

	// region -- Get set --

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isInvoiceValid() {
		return invoiceValid;
	}

	public void setInvoiceValid(boolean invoiceValid) {
		this.invoiceValid = invoiceValid;
	}

	public boolean isCreditValid() {
		return creditValid;
	}

	public void setCreditValid(boolean creditValid) {
		this.creditValid = creditValid;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ValidDto() {
		success = true;
		invoiceValid = true;
		creditValid = true;
		errors = new HashMap<String, String>();
		message = "";
	}

	// end
}