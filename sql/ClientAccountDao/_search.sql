SELECT 
	a.id, a.sfid, a.client_account__c, a.product_type_description__c, a.activated_on__c, 
	a.status__c, b.name record_type 
FROM salesforce.client_account__c a 
JOIN salesforce.recordtype b 
	ON a.recordtypeid = b.sfid 