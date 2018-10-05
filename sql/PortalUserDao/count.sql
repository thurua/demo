SELECT COUNT(*) 
FROM salesforce.portal_user__c a 
JOIN salesforce.portal_role__c b 
	ON a.role__c = b.sfid 
JOIN salesforce.account c 
	ON a.client__c = c.sfid 
JOIN salesforce.contact d 
	ON a.contact__c = d.sfid 