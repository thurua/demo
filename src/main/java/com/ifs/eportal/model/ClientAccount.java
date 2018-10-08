package com.ifs.eportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author ToanNguyen 2018-Oct-08 (verified)
 *
 */
@Entity
@Table(name = "client_account__c", schema = "salesforce")
public class ClientAccount extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_account__c_id_seq_generator")
	@SequenceGenerator(name = "client_account__c_id_seq_generator", sequenceName = "salesforce.client_account__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(18)", name = "fci_country__c")
	private String fciCountry;

	@Column(columnDefinition = "float(8)", name = "verification_exceeding_invoice_amount__c")
	private Float verificationExceedingInvoiceAmount;

	@Column(columnDefinition = "float(8)", name = "verification__c")
	private Float verification;

	@Column(columnDefinition = "varchar(255)", name = "client_account__c")
	private String clientAccount;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(255)", name = "status__c")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "activated_on__c")
	private Date activatedOn;

	@Column(columnDefinition = "varchar(255)", name = "account_type__c")
	private String accountType;

	@Column(columnDefinition = "varchar(18)", name = "client__c")
	private String client;

	@Column(columnDefinition = "varchar(255)", name = "factoring_type__c")
	private String factoringType;

	@Column(columnDefinition = "varchar(255)", name = "program_name__c")
	private String programName;

	@Column(columnDefinition = "varchar(18)", name = "recordtypeid")
	private String recordTypeId;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFciCountry() {
		return fciCountry;
	}

	public void setFciCountry(String fciCountry) {
		this.fciCountry = fciCountry;
	}

	public Float getVerificationExceedingInvoiceAmount() {
		return verificationExceedingInvoiceAmount;
	}

	public void setVerificationExceedingInvoiceAmount(Float verificationExceedingInvoiceAmount) {
		this.verificationExceedingInvoiceAmount = verificationExceedingInvoiceAmount;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getActivatedOn() {
		return activatedOn;
	}

	public void setActivatedOn(Date activatedOn) {
		this.activatedOn = activatedOn;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getFactoringType() {
		return factoringType;
	}

	public void setFactoringType(String factoringType) {
		this.factoringType = factoringType;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ClientAccount() {
		super();
	}

	// end
}