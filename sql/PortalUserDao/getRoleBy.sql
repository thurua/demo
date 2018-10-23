SELECT 
	b.name 
FROM salesforce.portal_user__c a 
JOIN salesforce.portal_role__c b 
	ON a.role__c = b.sfid 