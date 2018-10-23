SELECT 
	a.id, a.sfid, a.customer_branch__c, a.customer_from_excel__c, a.currencyisocode, 
	a.client_remarks__c, a.credit_amount__c, b.name customer, c.name schedule_of_offer, 
	z.name client_name, a.name, a.credit_note_date__c, a.status__c, 
	c.schedule_no__c, b.name account_ame, a.applied_invoice__c, 
	a.ops_remarks__c, a.outstanding_amount__c, d.client_account__c, a.createddate, 
	CASE WHEN (c.schedule_status__c = 'Submitted' AND c.is_eportal_so__c = FALSE) THEN 'IFS OPS' ELSE g.name END created_by, 
	a.uuid__c 
FROM salesforce.credit_note__c a 
JOIN salesforce.account b
	ON a.customer__c = b.sfid 
JOIN salesforce.account z
	ON a.client__c = z.sfid 	
JOIN salesforce.schedule_of_offer__c c 
	ON (a.schedule_of_offer__c = c.sfid OR a.parent_uuid__c = c.uuid__c) 
LEFT JOIN salesforce.client_account__c d 
	ON a.client_account__c = d.sfid 
LEFT JOIN salesforce.portal_user__c h 
	ON c.createdby_portaluserid__c = h.sfid 
LEFT JOIN salesforce.contact g 
	ON h.contact__c = g.sfid 