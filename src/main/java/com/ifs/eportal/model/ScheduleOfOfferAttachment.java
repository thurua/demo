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
@Table(name = "schedule_of_offer_attachment__c", schema = "salesforce")
public class ScheduleOfOfferAttachment extends BaseModel {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_of_offer_attachment__c_id_seq_generator")
	@SequenceGenerator(name = "schedule_of_offer_attachment__c_id_seq_generator", sequenceName = "salesforce.schedule_of_offer_attachment__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "float(8)", name = "sequence__c")
	private Float sequence;

	@Column(columnDefinition = "varchar(10)", name = "extension__c")
	private String extension;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "uploaded_on__c")
	private Date uploadedOn;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "varchar(18)", name = "uploaded_by__c")
	private String uploadedBy;

	@Column(columnDefinition = "varchar(50)", name = "contenttype__c")
	private String contentType;

	@Column(columnDefinition = "bool", name = "isactive__c")
	private boolean isActive;

	@Column(columnDefinition = "varchar(50)", name = "external_id__c")
	private String externalId;

	@Column(columnDefinition = "varchar(255)", name = "file_path__c")
	private String filePath;

	@Column(columnDefinition = "float(8)", name = "file_size__c")
	private Float fileSize;

	@Column(columnDefinition = "varchar(18)", name = "schedule_of_offer__c")
	private String scheduleOfOffer;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getSequence() {
		return sequence;
	}

	public void setSequence(Float sequence) {
		this.sequence = sequence;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Float getFileSize() {
		return fileSize;
	}

	public void setFileSize(Float fileSize) {
		this.fileSize = fileSize;
	}

	public String getScheduleOfOffer() {
		return scheduleOfOffer;
	}

	public void setScheduleOfOffer(String scheduleOfOffer) {
		this.scheduleOfOffer = scheduleOfOffer;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ScheduleOfOfferAttachment() {
		super();
	}

	// end
}