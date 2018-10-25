SELECT 
	a.id, a.sfid, a.name, b.name recordType, a.status__c, a.createddate 
FROM salesforce.fund_request__c a 
JOIN salesforce.recordtype b 
ON a.recordtypeid = b.sfid 