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
@Table(name = "recordtype", schema = "salesforce")
public class RecordType extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recordtype_id_seq_generator")
	@SequenceGenerator(name = "recordtype_id_seq_generator", sequenceName = "salesforce.recordtype_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(255)", name = "description")
	private String description;

	@Column(columnDefinition = "varchar(80)", name = "developername")
	private String developerName;

	@Column(columnDefinition = "varchar(40)", name = "sobjecttype")
	private String sobjectType;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getSobjectType() {
		return sobjectType;
	}

	public void setSobjectType(String sobjectType) {
		this.sobjectType = sobjectType;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public RecordType() {
		super();
	}

	// end
}