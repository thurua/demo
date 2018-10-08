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
@Table(name = "code", schema = "public")
public class Code {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "code_id_seq_generator")
	@SequenceGenerator(name = "code_id_seq_generator", sequenceName = "public.code_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "integer")
	private Integer parentId;

	@Column(columnDefinition = "text")
	private String codeType;

	@Column(columnDefinition = "text")
	private String value;

	@Column(columnDefinition = "text")
	private String displayAs;

	@Column(columnDefinition = "char(3)")
	private String status;

	@Column(columnDefinition = "integer")
	private Integer sequence;

	@Column(columnDefinition = "bool")
	private boolean isDeleted;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplayAs() {
		return displayAs;
	}

	public void setDisplayAs(String displayAs) {
		this.displayAs = displayAs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public Code() {

	}

	// end
}