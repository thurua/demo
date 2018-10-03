package com.ifs.eportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "password_change_history", schema = "public")
public class PasswordChangeHistory {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_change_history_id_seq_generator")
	@SequenceGenerator(name = "password_change_history_id_seq_generator", sequenceName = "public.password_change_history_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "text")
	private String userSfid;

	@Column(columnDefinition = "text")
	private String userName;

	@Column(columnDefinition = "varchar(50)")
	private String changeBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date changedOn;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserSfid() {
		return userSfid;
	}

	public void setUserSfid(String userSfid) {
		this.userSfid = userSfid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getChangeBy() {
		return changeBy;
	}

	public void setChangeBy(String changeBy) {
		this.changeBy = changeBy;
	}

	public Date getChangedOn() {
		return changedOn;
	}

	public void setChangedOn(Date changedOn) {
		this.changedOn = changedOn;
	}

	// end

	// region -- Methods --

	public PasswordChangeHistory() {

	}

	// end
}