SELECT 
	a.id, a.sfid, a.customer_branch__c, a.customer_from_excel__c, a.currencyisocode, 
	a.client_remarks__c, a.credit_amount__c, a.customer__c, a.schedule_of_offer__c, 
	a.client_account__c, a.name, a.credit_note_date__c, a.status__c, 
	c.schedule_no__c, b.name account_ame, a.applied_invoice__c, 
	a.ops_remarks__c, a.outstanding_amount__c, d.name client_account, 
	CASE WHEN (a.status__c = 'Submitted' AND a.createdbyid IS NULL) THEN 'IFS OPS' ELSE b.name END created_by 
FROM salesforce.credit_note__c a 
JOIN salesforce.account b
	ON a.customer__c = b.sfid 
JOIN salesforce.schedule_of_offer__c c 
	ON (a.schedule_of_offer__c = c.sfid OR a.parent_uuid__c = c.uuid__c) 
LEFT JOIN salesforce.client_account__c d 
	ON a.client_account__c = d.sfid 