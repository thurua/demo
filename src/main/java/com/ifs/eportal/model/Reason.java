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
@Table(name = "reason__c", schema = "salesforce")
public class Reason extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reason__c_id_seq_generator")
	@SequenceGenerator(name = "reason__c_id_seq_generator", sequenceName = "salesforce.reason__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(3)", name = "currencyisocode")
	private String currencyIsoCode;

	@Column(columnDefinition = "varchar(18)", name = "credit_note__c")
	private String creditNote;

	@Column(columnDefinition = "float(8)", name = "amount__c")
	private Float amount;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(255)", name = "remarks__c")
	private String remarks;

	@Column(columnDefinition = "varchar(50)", name = "external_id__c")
	private String externalId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "date__c")
	private Date date;

	@Column(columnDefinition = "varchar(18)", name = "invoice__c")
	private String invoice;

	@Column(columnDefinition = "varchar(255)", name = "reason__c")
	private String reason;

	@Column(columnDefinition = "varchar(255)", name = "recordtypeid")
	private String recordTypeId;

	@Column(columnDefinition = "varchar(10)", name = "type__c")
	private String type;

	@Column(columnDefinition = "varchar(100)", name = "uuid__c")
	private String uuId;

	@Column(columnDefinition = "varchar(100)", name = "parent_uuid__c")
	private String parentUuId;

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

	public String getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(String creditNote) {
		this.creditNote = creditNote;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getParentUuId() {
		return parentUuId;
	}

	public void setParentUuId(String parentUuId) {
		this.parentUuId = parentUuId;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public Reason() {
		super();
	}

	// end
}