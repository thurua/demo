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
@Table(name = "portal_role__c", schema = "salesforce")
public class PortalRole extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portal_role__c_id_seq_generator")
	@SequenceGenerator(name = "portal_role__c_id_seq_generator", sequenceName = "salesforce.portal_role__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(4099)", name = "access_rights__c")
	private String accessRights;

	@Column(columnDefinition = "bool", name = "active__c")
	private boolean active;

	@Column(columnDefinition = "text", name = "role_description__c")
	private String roleDescription;

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

	public String getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalRole() {
		super();
	}

	// end
}