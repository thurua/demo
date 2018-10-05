SELECT COUNT(*) 
FROM salesforce.client_account__c a 
JOIN salesforce.recordtype b 
	ON a.recordtypeid = b.sfid 