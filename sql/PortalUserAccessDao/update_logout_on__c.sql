UPDATE salesforce.portal_user_access__c 
SET logout_on__c = now() 
WHERE uuid__c = :uuId