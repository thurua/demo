SELECT 
	a.id, a.sfid, a.schedule_no__c, c.client_account__c, a.schedule_date__c, 
	a.portal_status__c, CASE WHEN a.portal_status__c = 'Accepted' THEN 'IFS OPS' ELSE d.name END created_by, 
	a.document_type__c, a.sequence__c, a.createddate 
FROM salesforce.schedule_of_offer__c a 
LEFT JOIN salesforce.portal_user__c b 
	ON a.createdby_portaluserid__c = CAST(b.id as VARCHAR) 
LEFT JOIN salesforce.client_account__c c 
	ON a.client_account__c = c.sfid 
LEFT JOIN salesforce.contact d 
	ON b.contact__c = d.sfid 