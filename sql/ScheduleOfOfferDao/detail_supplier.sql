SELECT 
	a.id, a.sfid, b.sfid code, c.name
FROM salesforce.schedule_of_offer__c a 
 JOIN salesforce.supplier_list__c b
	ON a.client_account__c = b.client_account__c 
JOIN salesforce.account  c 
	ON  b.supplier1__c = c.sfid 