SELECT 
	a.id, a.sfid, a.client_account__c, a.Product_Type_Name0__c, a.activated_on__c, 
	a.status__c, b.name record_type 
FROM salesforce.client_account__c a 
JOIN salesforce.recordtype b 
	ON a.recordtypeid = b.sfid 