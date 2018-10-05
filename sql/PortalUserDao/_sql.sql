SELECT 
	a.id, a.sfid, a.user_id__c, d.firstname, d.lastname, d.salutation, 
	d.mobilephone, a.password__c, a.password_hash__c, a.pass_reminder_token__c, 
	a.pass_reminder_expire__c, a.client__c, b.name role_name, c.name client_name 
FROM salesforce.portal_user__c a 
JOIN salesforce.portal_role__c b 
	ON a.role__c = b.sfid 
JOIN salesforce.account c 
	ON a.client__c = c.sfid 
JOIN salesforce.contact d 
	ON a.contact__c = d.sfid 