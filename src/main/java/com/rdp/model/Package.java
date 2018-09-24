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
@Table(name = "package__c", schema = "salesforce")
public class Package {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_id_seq_generator")
	@SequenceGenerator(name = "package_id_seq_generator", sequenceName = "salesforce.package__c_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(1000)", name = "package_description__c")
	private String packageDescription;

	@Column(columnDefinition = "float8", name = "rate__c")
	private Float rate;

	@Column(columnDefinition = "varchar(255)", name = "publish_to_web__c")
	private String publishToWeb;

	@Column(columnDefinition = "float8", name = "night_rate_dollars__c")
	private Float nightRateDollars;

	@Column(columnDefinition = "float8", name = "percent_discount__c")
	private Float percentDiscount;

	@Column(columnDefinition = "float8", name = "rate_oem__c")
	private Float rateOem;

	@Column(columnDefinition = "varchar(80)", name = "name")
	private String name;

	@Column(columnDefinition = "float8", name = "night_rate_dollars_oem__c")
	private Float nightRateDollarsOem;

	@Column(columnDefinition = "bool", name = "isdeleted")
	private Boolean isDeleted;

	@Column(columnDefinition = "float8", name = "current_electricity_rate__c")
	private Float currentElectricityRate;

	@Column(columnDefinition = "float8", name = "percent_discount_oem__c")
	private Float percentDiscountOem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "systemmodstamp")
	private Date systemModStamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "createddate")
	private Date createdDate;

	@Column(columnDefinition = "varchar(18)", name = "sfid")
	private String sfid;

	@Column(columnDefinition = "varchar(32)", name = "_hc_lastop")
	private String hcLastop;

	@Column(columnDefinition = "text", name = "_hc_err")
	private String hcErr;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getPublishToWeb() {
		return publishToWeb;
	}

	public void setPublishToWeb(String publishToWeb) {
		this.publishToWeb = publishToWeb;
	}

	public Float getNightRateDollars() {
		return nightRateDollars;
	}

	public void setNightRateDollars(Float nightRateDollars) {
		this.nightRateDollars = nightRateDollars;
	}

	public Float getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(Float percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public Float getRateOem() {
		return rateOem;
	}

	public void setRateOem(Float rateOem) {
		this.rateOem = rateOem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getNightRateDollarsOem() {
		return nightRateDollarsOem;
	}

	public void setNightRateDollarsOem(Float nightRateDollarsOem) {
		this.nightRateDollarsOem = nightRateDollarsOem;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Float getCurrentElectricityRate() {
		return currentElectricityRate;
	}

	public void setCurrentElectricityRate(Float currentElectricityRate) {
		this.currentElectricityRate = currentElectricityRate;
	}

	public Float getPercentDiscountOem() {
		return percentDiscountOem;
	}

	public void setPercentDiscountOem(Float percentDiscountOem) {
		this.percentDiscountOem = percentDiscountOem;
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

	// end

	// region -- Methods --

	public Package() {
	}

	// end
}