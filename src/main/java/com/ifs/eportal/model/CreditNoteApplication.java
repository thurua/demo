package com.ifs.eportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "credit_note_application__c", schema = "salesforce")
public class CreditNoteApplication extends BaseModel {

	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_note_application__c_id_seq_generator")
	@SequenceGenerator(name = "credit_note_application__c_id_seq_generator", sequenceName = "salesforce.credit_note_application__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(18)", name = "credit_note__c")
	private String creditNote;

	@Column(columnDefinition = "float(8)", name = "amount__c")
	private String amount;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private Float name;

	@Column(columnDefinition = "varchar(18)", name = "invoice__c")
	private String invoice;

	@Column(columnDefinition = "float(8)", name = "isreversed__c")
	private Float isReversed;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(String creditNote) {
		this.creditNote = creditNote;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Float getName() {
		return name;
	}

	public void setName(Float name) {
		this.name = name;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public Float getIsReversed() {
		return isReversed;
	}

	public void setIsReversed(Float isReversed) {
		this.isReversed = isReversed;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public CreditNoteApplication() {
		super();
	}

	// end
}