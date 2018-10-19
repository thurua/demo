SELECT 
	a.id, a.sfid, a.uuid__c, c.name, a.login_on__c, 
	a.logout_on__c, a.last_access_on__c, a.user_agent__c, a.host__c 
FROM salesforce.portal_user_access__c a 
JOIN salesforce.portal_user__c b 
	ON a.user__c = b.sfid 
JOIN salesforce.contact c 
	ON b.contact__c = c.sfid 