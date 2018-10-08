package com.ifs.eportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author ToanNguyen 2018-Oct-08 (verified)
 *
 */
@Entity
@Table(name = "client_account_customer__c", schema = "salesforce")
public class ClientAccountCustomer extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_account_customer__c_id_seq_generator")
	@SequenceGenerator(name = "client_account_customer__c_id_seq_generator", sequenceName = "salesforce.client_account_customer__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(18)", name = "fci_factor__c")
	private String fciFactor;

	@Column(columnDefinition = "float(8)", name = "verification_exceeding_invoice_amount__c")
	private Float verificationExceedingInvoiceAmount;

	@Column(columnDefinition = "varchar(18)", name = "customer__c")
	private String customer;

	@Column(columnDefinition = "float(8)", name = "verification__c")
	private Float verification;

	@Column(columnDefinition = "varchar(18)", name = "client_account__c")
	private String clientAccount;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "date", name = "activation_date__c")
	private Date activationDate;

	@Column(columnDefinition = "varchar(255)", name = "status__c")
	private String status;

	@Column(columnDefinition = "float(8)", name = "invoice_amount__c")
	private Float invoiceAmount;

	@Column(columnDefinition = "date", name = "termination_date__c")
	private Date terminationDate;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFciFactor() {
		return fciFactor;
	}

	public void setFciFactor(String fciFactor) {
		this.fciFactor = fciFactor;
	}

	public Float getVerificationExceedingInvoiceAmount() {
		return verificationExceedingInvoiceAmount;
	}

	public void setVerificationExceedingInvoiceAmount(Float verificationExceedingInvoiceAmount) {
		this.verificationExceedingInvoiceAmount = verificationExceedingInvoiceAmount;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Float getVerification() {
		return verification;
	}

	public void setVerification(Float verification) {
		this.verification = verification;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccountCustomer() {
		super();
	}

	// end
}