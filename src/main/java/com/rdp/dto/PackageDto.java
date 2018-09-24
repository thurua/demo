package com.rdp.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageDto {
	// region -- Fields --

	@JsonProperty(value = "packageDescription")
	private String packageDescription;

	@JsonProperty(value = "packageDescription")
	private float rate;

	@JsonProperty(value = "packageDescription")
	private String nightRateDollars;

	@JsonProperty(value = "packageDescription")
	private float percentDiscount;

	@JsonProperty(value = "packageDescription")
	private float rateOem;

	@JsonProperty(value = "packageDescription")
	private String name;

	@JsonProperty(value = "packageDescription")
	private float nightRateDollarsOem;

	@JsonProperty(value = "packageDescription")
	private boolean isDeleted;

	@JsonProperty(value = "packageDescription")
	private float currentElectricityRate;

	@JsonProperty(value = "packageDescription")
	private float percentDiscountOem;

	@JsonProperty(value = "packageDescription")
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

	@Column(columnDefinition = "varchar(255)", name = "publish_to_web__c")
	private String publishToWeb;

	// end

	// region -- Get set --

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getNightRateDollars() {
		return nightRateDollars;
	}

	public void setNightRateDollars(String nightRateDollars) {
		this.nightRateDollars = nightRateDollars;
	}

	public float getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(float percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public float getRateOem() {
		return rateOem;
	}

	public void setRateOem(float rateOem) {
		this.rateOem = rateOem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getNightRateDollarsOem() {
		return nightRateDollarsOem;
	}

	public void setNightRateDollarsOem(float nightRateDollarsOem) {
		this.nightRateDollarsOem = nightRateDollarsOem;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public float getCurrentElectricityRate() {
		return currentElectricityRate;
	}

	public void setCurrentElectricityRate(float currentElectricityRate) {
		this.currentElectricityRate = currentElectricityRate;
	}

	public float getPercentDiscountOem() {
		return percentDiscountOem;
	}

	public void setPercentDiscountOem(float percentDiscountOem) {
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

	public String getPublishToWeb() {
		return publishToWeb;
	}

	public void setPublishToWeb(String publishToWeb) {
		this.publishToWeb = publishToWeb;
	}

	// end

	// region -- Methods --

	public PackageDto() {
	}

	// end
}