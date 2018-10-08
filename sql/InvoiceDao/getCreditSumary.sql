SELECT 
	b.name as code, SUM(credit_amount__c) as value 
FROM salesforce.credit_note__c a 
JOIN salesforce.account b 
	ON a.customer__c = b.sfid 
WHERE b.name in :names 
	AND a.credit_note_date__c >= :fr 
	AND a.credit_note_date__c <= :to 
	AND a.client_account__c = :clientAccountId 
GROUP BY b.name 