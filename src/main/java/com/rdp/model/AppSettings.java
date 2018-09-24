package com.rdp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "appsettings", schema = "public")
public class AppSettings {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String key;

	@Column(columnDefinition = "text")
	private String value;

	@Column(columnDefinition = "text")
	private String description;

	@Column(columnDefinition = "varchar(10)")
	private String dataType;

	@Column(nullable = true)
	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@UpdateTimestamp
	private Date modifiedOn;

	// end

	// region -- Get set --

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	// end

	// region -- Methods --

	public AppSettings() {

	}

	// end
}