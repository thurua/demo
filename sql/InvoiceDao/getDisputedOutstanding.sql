SELECT 
	b.name as code, SUM(outstanding_amount__c) as value 
FROM salesforce.invoice__c a 
JOIN salesforce.account b 
	ON a.customer__c = b.sfid 
WHERE b.name in :names 
	AND a.status__c = 'Disputed' 
	AND a.client_account__c = :clientAccountId 
GROUP BY b.name 