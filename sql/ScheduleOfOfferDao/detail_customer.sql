SELECT 
	a.id, a.sfid, c.sfid code, c.name 
FROM salesforce.schedule_of_offer__c a 
JOIN salesforce.Client_Account_Customer__c b 
	ON b.client_account__c = a.client_account__c
JOIN salesforce.account c
	ON  b.customer__c = c.sfid 