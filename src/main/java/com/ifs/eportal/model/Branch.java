package com.ifs.eportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "branch", schema = "salesforce")
public class Branch extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_seq_generator")
	@SequenceGenerator(name = "branch_id_seq_generator", sequenceName = "salesforce.branch_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(3)", name = "currencyisocode")
	private String currencyIsoCode;

	@Column(columnDefinition = "varchar(255)", name = "postal_code__c")
	private String postalCode;

	@Column(columnDefinition = "varchar(18)", name = "company__c")
	private String company;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(255)", name = "state__c")
	private String state;

	@Column(columnDefinition = "varchar(10)", name = "code__c")
	private String code;

	@Column(columnDefinition = "varchar(10)", name = "jfactorref_no__c")
	private String jFactorRefNo;

	@Column(columnDefinition = "bool", name = "active__c")
	private String active;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getjFactorRefNo() {
		return jFactorRefNo;
	}

	public void setjFactorRefNo(String jFactorRefNo) {
		this.jFactorRefNo = jFactorRefNo;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public Branch() {
		super();
	}

	// end
}