SELECT
	a.id, a.customer_branch__c, a.customer_from_excel__c, a.currencyisocode, 
	a.invoice_date__c, a.client_name__c ,a.po__c, a.client_remarks__c, 
	a.customer__c, a.document_type__c, a.recordtypeid, a.schedule_of_offer__c, 
	a.client_account__c, a.name, a.status__c 
FROM salesforce.invoice__c a 