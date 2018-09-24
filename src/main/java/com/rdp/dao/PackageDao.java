package com.rdp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rdp.model.Package;

@Repository
public interface PackageDao extends CrudRepository<Package, Integer> {
	@Query(nativeQuery = true, value = "SELECT\r\n" + "	a.package_description__c,\r\n"
			+ "	b.rate_dollars__c*100 as rate__c,\r\n" + "	a.publish_to_web__c,\r\n"
			+ "	b.night_rate_dollars__c*100 as night_rate_dollars__c,\r\n"
			+ "	b.discount__c as percent_discount__c,\r\n" + "	b.rate_dollars__c*100 as rate_oem__c,\r\n"
			+ "	a.name,\r\n" + "	b.night_rate_dollars__c*100 as night_rate_dollars_oem__c,\r\n"
			+ "	a.isdeleted,\r\n" + "	a.current_electricity_rate__c*100 as current_electricity_rate__c,\r\n"
			+ "	b.discount__c as percent_discount_oem__c,\r\n" + "	a.systemmodstamp,\r\n" + "	a.createddate,\r\n"
			+ "	a.sfid,\r\n" + "	a.id,\r\n" + "	a._hc_lastop,\r\n" + "	a._hc_err\r\n"
			+ "FROM salesforce.package__c a \r\n" + "JOIN salesforce.package_price__c b \r\n"
			+ "	ON a.sfid = b.package__c \r\n" + "WHERE a.publish_to_web__c = 'Yes' \r\n"
			+ "	AND b.type__c = 'Residential' AND b.default__c = TRUE")
	public List<Package> search();

	@Query(nativeQuery = true, value = "SELECT\r\n" + "	a.package_description__c,\r\n"
			+ "	b.rate_dollars__c*100 as rate__c,\r\n" + "	a.publish_to_web__c,\r\n"
			+ "	b.night_rate_dollars__c*100 as night_rate_dollars__c,\r\n"
			+ "	b.discount__c as percent_discount__c,\r\n" + "	b.rate_dollars__c*100 as rate_oem__c,\r\n"
			+ "	a.name,\r\n" + "	b.night_rate_dollars__c*100 as night_rate_dollars_oem__c,\r\n"
			+ "	a.isdeleted,\r\n" + "	a.current_electricity_rate__c*100 as current_electricity_rate__c,\r\n"
			+ "	b.discount__c as percent_discount_oem__c,\r\n" + "	a.systemmodstamp,\r\n" + "	a.createddate,\r\n"
			+ "	a.sfid,\r\n" + "	a.id,\r\n" + "	a._hc_lastop,\r\n" + "	a._hc_err\r\n"
			+ "FROM salesforce.package__c a\r\n" + "JOIN salesforce.package_price__c b\r\n"
			+ "	ON a.sfid = b.package__c\r\n" + "WHERE a.publish_to_web__c = 'Yes'\r\n" + "	AND a.sfid = :sfid\r\n"
			+ "	AND b.type__c = 'Residential'\r\n" + "	AND b.duration_months__c= :duration")
	public Package search(@Param("sfid") String sfid, @Param("duration") String duration);

	@Query(nativeQuery = true, value = "SELECT\r\n" + "	b.description__c as package_description__c,\r\n"
			+ "	b.rate_dollars__c*100 as rate__c,\r\n" + "	a.publish_to_web__c,\r\n"
			+ "	b.night_rate_dollars__c*100 as night_rate_dollars__c,\r\n"
			+ "	b.discount__c as percent_discount__c,\r\n" + "	b.current_rate_dollars__c*100 as rate_oem__c,\r\n"
			+ "	a.name,\r\n" + "	b.current_night_rate_dollars__c*100 as night_rate_dollars_oem__c,\r\n"
			+ "	a.isdeleted,\r\n" + "	a.current_electricity_rate__c*100 as current_electricity_rate__c,\r\n"
			+ "	b.current_discount__c as percent_discount_oem__c,\r\n" + "	a.systemmodstamp,\r\n"
			+ "	a.createddate,\r\n" + "	a.sfid,\r\n" + "	a.id,\r\n" + "	a._hc_lastop,\r\n" + "	a._hc_err\r\n"
			+ "FROM salesforce.package__c a \r\n" + "JOIN salesforce.package_promo_price__c b \r\n"
			+ "	ON a.sfid = b.package__c \r\n" + "WHERE a.publish_to_web__c = 'Yes' \r\n"
			+ "	AND b.type__c = 'Residential' AND b.default__c = TRUE")
	public List<Package> searchPromo();

	@Query(nativeQuery = true, value = "SELECT\r\n" + "	b.description__c as package_description__c,\r\n"
			+ "	b.rate_dollars__c*100 as rate__c,\r\n" + "	a.publish_to_web__c,\r\n"
			+ "	b.night_rate_dollars__c*100 as night_rate_dollars__c,\r\n"
			+ "	b.discount__c as percent_discount__c,\r\n" + "	b.current_rate_dollars__c*100 as rate_oem__c,\r\n"
			+ "	a.name,\r\n" + "	b.current_night_rate_dollars__c*100 as night_rate_dollars_oem__c,\r\n"
			+ "	a.isdeleted,\r\n" + "	a.current_electricity_rate__c*100 as current_electricity_rate__c,\r\n"
			+ "	b.current_discount__c as percent_discount_oem__c,\r\n" + "	a.systemmodstamp,\r\n"
			+ "	a.createddate,\r\n" + "	a.sfid,\r\n" + "	a.id,\r\n" + "	a._hc_lastop,\r\n" + "	a._hc_err\r\n"
			+ "FROM salesforce.package__c a\r\n" + "JOIN salesforce.package_promo_price__c b\r\n"
			+ "	ON a.sfid = b.package__c\r\n" + "WHERE a.publish_to_web__c = 'Yes'\r\n" + "	AND a.sfid = :sfid\r\n"
			+ "	AND b.type__c = 'Residential'\r\n" + "	AND b.duration_months__c= :duration")
	public Package searchPromo(@Param("sfid") String sfid, @Param("duration") String duration);
}