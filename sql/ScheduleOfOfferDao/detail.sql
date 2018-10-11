SELECT 
	a.id, a.sfid, a.schedule_no__c, a.schedule_date__c, a.exchange_rate__c, a.factor_code__c, b.name record_type, 
	e.total, e.total_amount, c.name client_name, a.currencyisocode, d.client_account__c, a.document_type__c, 
	CASE WHEN a.schedule_status__c = 'Submitted' THEN 'Accepted' ELSE a.portal_status__c END portal_status, 
	f.total total_cn, f.total_amount total_amount_cn, 
	CASE WHEN a.document_type__c = 'Credit Note' THEN TRUE ELSE FALSE END is_credit_note 
FROM salesforce.schedule_of_offer__c a 
LEFT JOIN salesforce.recordtype b 
	ON a.recordtypeid = b.sfid 
LEFT JOIN salesforce.account c 
	ON a.client_name__c = c.sfid 
LEFT JOIN salesforce.client_account__c d 
	ON a.client_account__c = d.sfid 
LEFT JOIN	(
				SELECT parent_uuid__c, CAST(COUNT(*) AS FLOAT) total, SUM(invoice_amount__c) total_amount 
				FROM salesforce.invoice__c 
				GROUP BY parent_uuid__c 
			) e 
	ON a.uuid__c = e.parent_uuid__c 
LEFT JOIN	(
				SELECT parent_uuid__c, CAST(COUNT(*) AS FLOAT) total, SUM(credit_amount__c) total_amount 
				FROM salesforce.credit_note__c 
				GROUP BY parent_uuid__c 
			) f 
	ON a.uuid__c = f.parent_uuid__c 