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

@Entity
@Table(name = "package_promo_price__c", schema = "salesforce")
public class PackagePromoPrice {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_promo_price_id_seq_generator")
	@SequenceGenerator(name = "package_promo_price_id_seq_generator", sequenceName = "salesforce.package_promo_price__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "bool", name = "default__c")
	private Boolean defaultC;

	@Column(columnDefinition = "float8", name = "discount__c")
	private Float discount;

	@Column(columnDefinition = "float8", name = "night_rate_dollars__c")
	private Float nightRateDollars;

	@Column(columnDefinition = "varchar(255)", name = "duration_months__c")
	private String durationMonths;

	@Column(columnDefinition = "text", name = "description__c")
	private String description;

	@Column(columnDefinition = "varchar(255)", name = "type__c")
	private String type;

	@Column(columnDefinition = "float8", name = "rate_dollars__c")
	private Float rateDollars;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "bool", name = "isdeleted")
	private Boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "systemmodstamp")
	private Date systemModStamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "createddate")
	private Date createdDate;

	@Column(columnDefinition = "varchar(18)", name = "package__c")
	private String packageC;

	@Column(columnDefinition = "varchar(18)", name = "sfid")
	private String sfid;

	@Column(columnDefinition = "varchar(32)", name = "_hc_lastop")
	private String hcLastop;

	@Column(columnDefinition = "text", name = "_hc_err")
	private String hcErr;

	@Column(columnDefinition = "float8", name = "current_rate_dollars__c")
	private Float currentRateDollars;

	@Column(columnDefinition = "float8", name = "current_discount__c")
	private Float currentDiscount;

	@Column(columnDefinition = "float8", name = "current_night_rate_dollars__c")
	private Float currentNightRateDollars;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getDefaultC() {
		return defaultC;
	}

	public void setDefaultC(Boolean defaultC) {
		this.defaultC = defaultC;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getNightRateDollars() {
		return nightRateDollars;
	}

	public void setNightRateDollars(Float nightRateDollars) {
		this.nightRateDollars = nightRateDollars;
	}

	public String getDurationMonths() {
		return durationMonths;
	}

	public void setDurationMonths(String durationMonths) {
		this.durationMonths = durationMonths;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getRateDollars() {
		return rateDollars;
	}

	public void setRateDollars(Float rateDollars) {
		this.rateDollars = rateDollars;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getSystemModStamp() {
		return systemModStamp;
	}

	public void setSystemModStamp(Date systemModStamp) {
		this.systemModStamp = systemModStamp;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPackageC() {
		return packageC;
	}

	public void setPackageC(String packageC) {
		this.packageC = packageC;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getHcLastop() {
		return hcLastop;
	}

	public void setHcLastop(String hcLastop) {
		this.hcLastop = hcLastop;
	}

	public String getHcErr() {
		return hcErr;
	}

	public void setHcErr(String hcErr) {
		this.hcErr = hcErr;
	}

	public Float getCurrentRateDollars() {
		return currentRateDollars;
	}

	public void setCurrentRateDollars(Float currentRateDollars) {
		this.currentRateDollars = currentRateDollars;
	}

	public Float getCurrentDiscount() {
		return currentDiscount;
	}

	public void setCurrentDiscount(Float currentDiscount) {
		this.currentDiscount = currentDiscount;
	}

	public Float getCurrentNightRateDollars() {
		return currentNightRateDollars;
	}

	public void setCurrentNightRateDollars(Float currentNightRateDollars) {
		this.currentNightRateDollars = currentNightRateDollars;
	}

	// end

	// region -- Methods --

	public PackagePromoPrice() {
	}

	// end
}