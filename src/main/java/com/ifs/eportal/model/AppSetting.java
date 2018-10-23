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
@Table(name = "app_setting", schema = "public")
public class AppSetting {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_setting_key_seq_generator")
	@SequenceGenerator(name = "app_setting_key_seq_generator", sequenceName = "public.app_setting_key_seq", allocationSize = 1)
	@Column(columnDefinition = "varchar(255)")
	private String key;

	@Column(columnDefinition = "varchar(20)")
	private String dataType;

	@Column(columnDefinition = "text")
	private String description;

	@Column(columnDefinition = "text")
	private String value;

	// end

	// region -- Get set --

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public AppSetting() {

	}

	// end
}