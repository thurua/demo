package com.ifs.eportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author VanPhan 2018-Oct-17
 *
 */
public class FundingAccountDetailsDto extends BaseDto {
	// region -- Fields --

	@JsonProperty(value = "primaryFunding")
	private boolean primaryFunding;

	@JsonProperty(value = "currencyisocodeF")
	private String currencyisocodeF;

	@JsonProperty(value = "bankFunding")
	private String bankFunding;

	@JsonProperty(value = "accountNumberF")
	private String accountNumberF;

	@JsonProperty(value = "paymentModeFactoring")
	private String paymentModeFactoring;

	@JsonProperty(value = "paymentModeLLctr")
	private String paymentModeLLctr;

	// end

	// region -- Get set --

	public boolean isPrimaryFunding() {
		return primaryFunding;
	}

	public void setPrimaryFunding(boolean primaryFunding) {
		this.primaryFunding = primaryFunding;
	}

	public String getCurrencyisocodeF() {
		return currencyisocodeF;
	}

	public void setCurrencyisocodeF(String currencyisocodeF) {
		this.currencyisocodeF = currencyisocodeF;
	}

	public String getBankFunding() {
		return bankFunding;
	}

	public void setBankFunding(String bankFunding) {
		this.bankFunding = bankFunding;
	}

	public String getAccountNumberF() {
		return accountNumberF;
	}

	public void setAccountNumberF(String accountNumberF) {
		this.accountNumberF = accountNumberF;
	}

	public String getPaymentModeFactoring() {
		return paymentModeFactoring;
	}

	public void setPaymentModeFactoring(String paymentModeFactoring) {
		this.paymentModeFactoring = paymentModeFactoring;
	}

	public String getPaymentModeLLctr() {
		return paymentModeLLctr;
	}

	public void setPaymentModeLLctr(String paymentModeLLctr) {
		this.paymentModeLLctr = paymentModeLLctr;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FundingAccountDetailsDto() {
		currencyisocodeF = "";
		bankFunding = "";
		accountNumberF = "";
		paymentModeFactoring = "";
		paymentModeLLctr = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static FundingAccountDetailsDto convert(Object[] o) {
		FundingAccountDetailsDto res = new FundingAccountDetailsDto();

		res.setId((Integer) o[0]);
		res.setSfId((String) o[1]);

		res.setPrimaryFunding((boolean) o[2]);
		res.setCurrencyisocodeF((String) o[3]);
		res.setBankFunding((String) o[4]);
		res.setAccountNumberF((String) o[5]);
		res.setPaymentModeFactoring((String) o[6]);
		res.setPaymentModeLLctr((String) o[7]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<FundingAccountDetailsDto> convert(List<Object[]> l) {
		List<FundingAccountDetailsDto> res = new ArrayList<FundingAccountDetailsDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}