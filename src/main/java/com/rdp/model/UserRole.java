package com.rdp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "userrole", schema = "public")
public class UserRole {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userrole_id_seq_generator")
	@SequenceGenerator(name = "userrole_id_seq_generator", sequenceName = "userrole_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	private Role roleId;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	// end

	// region -- Methods --

	public UserRole() {
	}

	public UserRole(User userId, Role roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	// end
}