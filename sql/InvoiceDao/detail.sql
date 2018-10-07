SELECT
	a.id, a.sfid, a.customer_branch__c, a.customer_from_excel__c, a.currencyisocode, 
	a.invoice_date__c, a.po__c, a.client_remarks__c, e.name customer, a.document_type__c, 
	a.name, a.status__c, a.contract__c, c.schedule_no__c, b.client_account__c, 
	d.name client_name, a.credit_period__c, a.outstanding_amount__c, a.invoice_amount__c 
FROM salesforce.invoice__c a 
JOIN salesforce.client_account__c b 
	ON a.client_account__c = b.sfid 
JOIN salesforce.schedule_of_offer__c c 
	ON a.schedule_of_offer__c = c.sfid 
JOIN salesforce.account d 
	ON a.client_name__c = d.sfid 
JOIN salesforce.account e 
	ON a.customer__c = e.sfid 