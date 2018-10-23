SELECT
	a.id, a.sfid, a.customer_branch__c, a.customer_from_excel__c, a.currencyisocode, a.invoice_date__c, 
	a.po__c, a.client_remarks__c, e.name customer, a.document_type__c, a.name, a.status__c, a.contract__c, 
	c.name as schedule_no__c, b.client_account__c, d.name client_name, a.credit_period__c, a.outstanding_amount__c,
	a.invoice_amount__c, f.name supplier, a.createddate,
	CASE WHEN (c.schedule_status__c = 'Submitted' AND c.is_eportal_so__c = FALSE) THEN 'IFS OPS' ELSE g.name END created_by, 
	a.uuid__c 
FROM salesforce.invoice__c a 
JOIN salesforce.client_account__c b 
	ON a.client_account__c = b.sfid 
JOIN salesforce.schedule_of_offer__c c 
	ON a.parent_uuid__c = c.uuid__c 
JOIN salesforce.account d 
	ON a.client_name__c = d.sfid 
LEFT JOIN salesforce.account e 
	ON a.customer__c = e.sfid 
LEFT JOIN salesforce.account f 
	ON a.supplier__c = f.sfid 
LEFT JOIN salesforce.portal_user__c h 
	ON c.createdby_portaluserid__c = h.sfid 
LEFT JOIN salesforce.contact g 
	ON h.contact__c = g.sfid 