SELECT 
	a.id, a.sfid, a.schedule_no__c, a.schedule_date__c, a.exchange_rate__c, a.factor_code__c, 
	b.name record_type, e.total, e.total_amount, c.name client_name, a.currencyisocode, 
	d.client_account__c, a.document_type__c, a.portal_status__c 
FROM salesforce.schedule_of_offer__c a 
LEFT JOIN salesforce.recordtype b 
	ON a.recordtypeid = b.sfid 
LEFT JOIN salesforce.account c 
	ON a.client_name__c = c.sfid 
LEFT JOIN salesforce.client_account__c d 
	ON a.client_account__c = d.sfid 
LEFT JOIN	(
				SELECT schedule_of_offer__c sfid, CAST(COUNT(*) AS FLOAT) total, SUM(invoice_amount__c) total_amount 
				FROM salesforce.invoice__c 
				GROUP BY schedule_of_offer__c 
			) e 
	ON a.sfid = e.sfid 