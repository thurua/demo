SELECT 
	a.id, a.sfid, a.name, a.file_size__c, a.uploaded_by__c, 
	a.uploaded_on__c, ROW_NUMBER() OVER(ORDER BY id) 
FROM schedule_of_offer_attachment__c a 