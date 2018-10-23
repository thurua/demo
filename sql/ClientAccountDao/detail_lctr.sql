SELECT 
	id, sfid, client_account__c, letter_of_offer_signed_date__c, activated_on__c, account_termination_date__c, 
  	status__c, availability_period_months__c, account_maturity_date__c, close_date__c, currencyisocode, 
  	product_type_description__c, interest_rate_spread__c, interest_rate_spread_2__c, rate_type__c, 
  	interest_rate_source__c, interest_rate_source_2__c, facility_fee__c, loan_amount__c, loan_period__c, 
	tr_period__c, lc_commission_per_validity_period__c, lc_additional_commission_per_mth__c, 
	lc_extension_commission_flat__c, lc_extension_commission_per_mth__c, acceptance_commission_per_mth__c, 
	lc_validity_period__c, lc_commission_min__c, lc_additional_commission_min__c, extension_fee_flat_min__c, 
	extension_fee_per_mth_min__c, usance_acceptance_charge_min__c, facility_fee_of_loan_amount__c, 
	annual_renewal_fee__c, bank_approved_date_giro__c, account_number_giro__c, deduction_day__c, bank_giro__c, 
	currency_payment_limit_if_applicable__c, remarks_giro__c 
FROM salesforce.client_account__c 