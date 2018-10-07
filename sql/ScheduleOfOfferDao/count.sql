SELECT COUNT(*) 
FROM salesforce.schedule_of_offer__c a 
LEFT JOIN salesforce.portal_user__c b 
	ON a.createdby_portaluserid__c = CAST(b.id as VARCHAR) 
LEFT JOIN salesforce.client_account__c c 
	ON a.client_account__c = c.sfid 
LEFT JOIN salesforce.contact d 
	ON b.contact__c = d.sfid 
LEFT JOIN salesforce.recordtype e 
	ON a.recordtypeid = e.sfid 