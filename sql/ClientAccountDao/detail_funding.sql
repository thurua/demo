SELECT 
	a.id, a.sfid, a.primary_funding__c, a.currencyisocode, c.short_name__c, a.account_number__c, 
	a.payment_mode_l_lctr__c, a.payment_mode_factoring__c 
FROM salesforce.funding_account_details__c a 
JOIN salesforce.client_account__c b 
	ON a.client_account__c = b.sfid 
LEFT JOIN salesforce.bank__c c 
	ON a.bank_funding__c = c.sfid 