SELECT COUNT(*) 
FROM salesforce.credit_note__c a 
LEFT JOIN salesforce.client_account__c c 
	ON a.client_account__c = c.sfid 
JOIN salesforce.schedule_of_offer__c d 
	ON a.schedule_of_offer__c = d.sfid 