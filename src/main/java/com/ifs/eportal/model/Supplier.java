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
 * @author VanPhan 2018-Oct-09
 *
 */
@Entity
@Table(name = "supplier_list__c", schema = "salesforce")
public class Supplier extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_list__c_id_seq_generator")
	@SequenceGenerator(name = "supplier_list__c_id_seq_generator", sequenceName = "salesforce.supplier_list__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(18)", name = "recordtypeid")
	private String recordTypeId;

	@Column(columnDefinition = "varchar(18)", name = "client_account__c")
	private String clientAccount;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(18)", name = "client1__c")
	private String client1;

	@Column(columnDefinition = "float(8)", name = "verification__c")
	private Float verification;

	@Column(columnDefinition = "bool", name = "active__c")
	private boolean active;

	@Column(columnDefinition = "varchar(18)", name = "supplier1__c")
	private String supplier1;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
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

	public String getClient1() {
		return client1;
	}

	public void setClient1(String client1) {
		this.client1 = client1;
	}

	public Float getVerification() {
		return verification;
	}

	public void setVerification(Float verification) {
		this.verification = verification;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getSupplier1() {
		return supplier1;
	}

	public void setSupplier1(String supplier1) {
		this.supplier1 = supplier1;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public Supplier() {
		super();
	}

	// end
}