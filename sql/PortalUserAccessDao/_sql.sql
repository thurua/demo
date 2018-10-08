SELECT * 
FROM salesforce.portal_user_access__c a 
WHERE a.user__c = :user 
	AND a.uuid__c = :uuid 
	AND a.logout_on__c IS NULL 