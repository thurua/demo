SELECT 
	a.id, a.sfid, a.name, c.client_account__c, a.schedule_date__c, 
	CASE WHEN a.schedule_status__c = 'Submitted' THEN 'Accepted' ELSE a.portal_status__c END portal_status, 
	CASE WHEN (a.schedule_status__c = 'Submitted' AND a.is_eportal_so__c = FALSE) THEN 'IFS OPS' ELSE d.name END created_by, 
	a.document_type__c, a.sequence__c, a.createddate, a.recordtypeid, e.name record_type_name, a.process_date__c, 
	a.key_in_by_date__c, a.uuid__c 
FROM salesforce.schedule_of_offer__c a 
LEFT JOIN salesforce.portal_user__c b 
	ON a.createdby_portaluserid__c = b.sfid 
LEFT JOIN salesforce.client_account__c c 
	ON a.client_account__c = c.sfid 
LEFT JOIN salesforce.contact d 
	ON b.contact__c = d.sfid 
LEFT JOIN salesforce.recordtype e 
	ON a.recordtypeid = e.sfid 