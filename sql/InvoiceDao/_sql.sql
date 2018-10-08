SELECT 
	a.id, a.sfid, a.customer_branch__c, a.customer_from_excel__c, a.currencyisocode, 
	a.invoice_date__c, a.po__c,a.client_remarks__c, a.customer__c, a.document_type__c, 
	a.name, a.status__c , a.contract__c,  a.schedule_of_offer__c, a.client_account__c, 
	a.client_name__c, a.credit_period__c, a.outstanding_amount__c, a.invoice_amount__c, a.supplier__c 
FROM salesforce.invoice__c a 