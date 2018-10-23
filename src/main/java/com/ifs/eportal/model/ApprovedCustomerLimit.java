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
@Table(name = "approved_customer_limit__c", schema = "salesforce")
public class ApprovedCustomerLimit extends BaseModel {
	// region -- Fields --
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approved_customer_limit__c_id_seq_generator")
	@SequenceGenerator(name = "approved_customer_limit__c_id_seq_generator", sequenceName = "salesforce.approved_customer_limit__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "valid_to__c")
	private Date validTo;

	@Column(columnDefinition = "varchar(18)", name = "customer__c")
	private String customer;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "float(8)", name = "approved_limit__c")
	private Float approvedLimit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "valid_from__c")
	private Date validFrom;

	@Column(columnDefinition = "varchar(18)", name = "client__c")
	private String client;

	@Column(columnDefinition = "bool", name = "is_valid__c")
	private boolean isValid;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getApprovedLimit() {
		return approvedLimit;
	}

	public void setApprovedLimit(Float approvedLimit) {
		this.approvedLimit = approvedLimit;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ApprovedCustomerLimit() {
		super();
	}

	// end
}