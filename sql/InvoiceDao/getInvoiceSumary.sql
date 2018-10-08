SELECT 
	b.name as code, SUM(invoice_amount__c) as value 
FROM salesforce.invoice__c a 
JOIN salesforce.account b 
	ON a.customer__c = b.sfid 
WHERE b.name in :names 
	AND a.invoice_date__c >= :fr 
	AND a.invoice_date__c <= :to 
	AND a.client_account__c = :clientAccountId 
GROUP BY b.name 