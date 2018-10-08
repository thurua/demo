SELECT 
	a.id, a.sfid, a.customer_from_excel__c, b.name customer, a.customer_branch__c, 
	a.name, a.cn_application_date__c, a.credit_amount__c, a.applied_invoice__c, 
	a.status__c, a.apply_credit_note__c, a.reason_code__c 
FROM salesforce.credit_note__c a 
LEFT JOIN salesforce.account b 
	ON a.client__c = b.sfid 