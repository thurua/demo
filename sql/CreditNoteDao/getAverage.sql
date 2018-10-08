SELECT 
	'1' as code, AVG(credit_amount__c) as value 
FROM salesforce.credit_note__c a 
WHERE a.Client_Account__c = :clientAccountId 