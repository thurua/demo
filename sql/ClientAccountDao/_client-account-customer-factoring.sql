SELECT 
	a.id, a.sfid, b.client_account__c, c.name customer, a.activation_date__c, a.status__c 
FROM salesforce.client_account_customer__c a 
LEFT JOIN salesforce.client_account__c b ON a.client_account__c = b.sfid 
LEFT JOIN salesforce.account c ON a.customer__c = c.sfid 