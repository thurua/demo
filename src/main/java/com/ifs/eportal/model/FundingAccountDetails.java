package com.ifs.eportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author TrangNguyen 2018-Oct-18 (verified)
 *
 */
@Entity
@Table(name = "funding_account_details__c", schema = "salesforce")
public class FundingAccountDetails extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funding_account_details__c_id_seq_generator")
	@SequenceGenerator(name = "funding_account_details__c_id_seq_generator", sequenceName = "salesforce.funding_account_details__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(3)", name = "currencyisocode")
	private String currencyISoCode;

	@Column(columnDefinition = "bool", name = "primary_funding__c")
	private boolean primaryFunding;

	@Column(columnDefinition = "varchar(255)", name = "bank__c")
	private String bank;

	@Column(columnDefinition = "varchar(255)", name = "account_number__c")
	private String accountNumber;

	@Column(columnDefinition = "varchar(255)", name = "payment_mode_factoring__c")
	private String paymentModeFactoring;

	@Column(columnDefinition = "varchar(255)", name = "payment_mode_l_lctr__c")
	private String paymentModeLLctr;

	@Column(columnDefinition = "varchar(18)", name = "bank_funding__c")
	private String bankFunding;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrencyISoCode() {
		return currencyISoCode;
	}

	public void setCurrencyISoCode(String currencyISoCode) {
		this.currencyISoCode = currencyISoCode;
	}

	public boolean getPrimaryFunding() {
		return primaryFunding;
	}

	public void setPrimaryFunding(boolean primaryFunding) {
		this.primaryFunding = primaryFunding;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getBankFunding() {
		return bankFunding;
	}

	public void setBankFunding(String bankFunding) {
		this.bankFunding = bankFunding;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FundingAccountDetails() {
		super();
	}

	// end
}