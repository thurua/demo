SELECT 
	a.id, a.sfid, a.client_account__c, a.letter_of_offer_signed_date__c, a.activated_on__c, 
	a.account_termination_date__c, a.status__c, a.availability_period_months__c, a.account_maturity_date__c, 
	a.close_date__c, a.currencyisocode, a.product_type_description__c, a.interest_rate_spread__c, 
	a.interest_rate_spread_2__c, a.rate_type__c, a.interest_rate_source__c, a.interest_rate_source_2__c, 
	a.facility_fee__c, a.investment_limit__c, a.advanced_ratio__c, a.fci_country__c, 
	a.reassignment_period__c, a.service_charge_rate_tier_1__c, a.service_charge_rate_tier_2__c, 
	a.factoring_charge_rate_3rd_tier__c, a.min_factoring_charge_per_month__c, 
	a.handling_charge_per_invoice__c, a.x1st_tier_up_to_factored_volume__c, a.x2nd_tier_up_to_factored_volume__c, 
	a.service_charge_rate_spread__c, a.service_charge_source__c, a.overdue_grace_period_days__c, 
	a.facility_fee_of_investment_limit__c, a.auto_request__c, a.add_fac_rate_type__c,  b."name" fciName 
FROM salesforce.client_account__c a 
LEFT JOIN salesforce.fcicountry__c b 
ON a.fci_country__c = b.sfid 