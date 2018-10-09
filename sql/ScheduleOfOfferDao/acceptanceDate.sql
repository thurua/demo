SELECT 
	a.acceptance_date__c 
FROM salesforce.schedule_of_offer__c a 
WHERE client_account__c = :clientAccount 
ORDER BY Acceptance_Date__c DESC LIMIT 1 