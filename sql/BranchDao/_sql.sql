SELECT a.id, a.sfid, a.code__c, b.name 
FROM salesforce.branch__c a 
JOIN salesforce.account b 
	ON a.company__c = b.sfid 
WHERE a.active__c = true 
	AND b.name IN :names 