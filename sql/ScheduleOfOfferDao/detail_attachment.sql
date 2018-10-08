SELECT 
	a.id, a.sfid, a.name, a.file_size__c, a.uploaded_by__c, a.uploaded_on__c, 
	CAST(ROW_NUMBER() OVER(ORDER BY a.id) AS INT4) row_number, c.name "owner" 
FROM salesforce.schedule_of_offer_attachment__c a 
LEFT JOIN salesforce.portal_user__c b 
	ON a.uploaded_by__c = b.sfid 
JOIN salesforce.contact c 
	ON b.contact__c = c.sfid 