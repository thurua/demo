SELECT 
	b.name as code, AVG(invoice_amount__c) as value 
FROM salesforce.invoice__c a 
JOIN salesforce.account b 
	ON a.customer__c = b.sfid 
WHERE b.name in :names 
	AND a.client_account__c = :clientAccountId 
GROUP BY b.name 