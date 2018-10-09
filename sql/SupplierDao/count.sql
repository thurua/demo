SELECT COUNT(*)
FROM salesforce.supplier_list__c a 
JOIN salesforce.account  b
	ON  a.supplier1__c = b.sfid	