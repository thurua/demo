SELECT 
	id, sfid, client_account__c, letter_of_offer_signed_date__c, activated_on__c, account_termination_date__c, 
	status__c, availability_period_months__c, account_maturity_date__c, close_date__c, currencyisocode, 
	product_type_description__c, interest_rate_spread__c, interest_rate_spread_2__c, rate_type__c, 
	interest_rate_source__c, interest_rate_source_2__c, facility_fee__c, loan_amount__c, loan_period__c, 
	repayment_period_months__c, repayment_type__c, instalment_frequency__c, due_day_of_the_month__c, 
	repayment_type_to_specify__c, instalment_type__c, prepayment_fee__c, prepayment_duration_month_from_drawdown__c, 
	bank_approved_date_giro__c, account_number_giro__c, deduction_day__c, bank_giro__c, currency_payment_limit_if_applicable__c, 
	remarks_giro__c, cancellation_fee__c, facility_fee_of_loan_amount__c, disbursement_type__c, overdue_interest_rate_spread__c, 
	overdue_interest_rate_source__c
FROM salesforce.client_account__c 