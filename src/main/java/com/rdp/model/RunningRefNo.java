package com.rdp.model;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "runningrefno", schema = "public")
public class RunningRefNo {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "runningrefno_id_seq_generator")
	@SequenceGenerator(name = "runningrefno_id_seq_generator", sequenceName = "runningrefno_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@NotNull
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdOn;

	@Column(columnDefinition = "varchar(20)")
	private String lastRefNo;

	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date modifiedOn;

	@NotNull
	@Column(columnDefinition = "varchar(4)")
	private String seqGroup;

	@Column(columnDefinition = "integer")
	private Integer seqNo;

	@Column(columnDefinition = "varchar(4)")
	private String yearCode;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getLastRefNo() {
		return lastRefNo;
	}

	public void setLastRefNo(String lastRefNo) {
		this.lastRefNo = lastRefNo;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getSeqGroup() {
		return seqGroup;
	}

	public void setSeqGroup(String seqGroup) {
		this.seqGroup = seqGroup;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getYearCode() {
		return yearCode;
	}

	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}

	// end

	// region -- Methods --

	public RunningRefNo() {
	}

	// end
}