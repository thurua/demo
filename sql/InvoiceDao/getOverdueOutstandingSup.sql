SELECT 
	b.name as code, SUM(outstanding_amount__c) as value 
FROM salesforce.invoice__c a 
JOIN salesforce.account b 
	ON a.supplier__c = b.sfid 
WHERE b.name in :names 
	AND a.added_credit_period__c < current_date 
	AND a.client_account__c = :clientAccountId 
GROUP BY b.name 