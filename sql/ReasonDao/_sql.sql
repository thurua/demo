SELECT 
	a.reason__c, b.name 
FROM salesforce.reason__c a 
JOIN salesforce.recordtype b 
	ON a.recordtypeid = b.sfid 