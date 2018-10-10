SELECT 
	a.id, a.sfid, b.name, a.supplier1__c 
FROM salesforce.supplier_list__c a 
JOIN salesforce.account  b 
	ON  a.supplier1__c = b.sfid 