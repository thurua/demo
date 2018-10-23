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
 * @author ToanNguyen 2018-Oct-08 (verified)
 *
 */
@Entity
@Table(name = "account", schema = "salesforce")
public class Contact extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq_generator")
	@SequenceGenerator(name = "account_id_seq_generator", sequenceName = "salesforce.account_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(50)", name = "identity_no__c")
	private String identityNo;

	@Column(columnDefinition = "varchar(80)", name = "lastname")
	private String lastName;

	@Column(columnDefinition = "varchar(121)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(40)", name = "mobilephone")
	private String mobilephone;

	@Column(columnDefinition = "varchar(40)", name = "phone")
	private String phone;

	@Column(columnDefinition = "varchar(40)", name = "salutation")
	private String salutation;

	@Column(columnDefinition = "varchar(128)", name = "title")
	private String title;

	@Column(columnDefinition = "varchar(40)", name = "firstname")
	private String firstname;

	@Column(columnDefinition = "varchar(80)", name = "email")
	private String email;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public Contact() {
		super();
	}

	// end
}