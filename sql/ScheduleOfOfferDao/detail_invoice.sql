SELECT 
	a.id, a.sfid, a.customer_from_excel__c, b.name customer, a.customer_branch__c, 
	a.name, a.invoice_date__c, a.credit_period__c, a.invoice_amount__c, a.po__c, 
	a.contract__c, a.status__c, c.name as supplier, a.supplier_from_excel__c, a.reason_code__c, 
	a.currencyisocode, a.uuid__c 
FROM salesforce.invoice__c a 
LEFT JOIN salesforce.account b 
	ON a.customer__c = b.sfid 
LEFT JOIN salesforce.account c 
	ON a.supplier__c = c.sfid 