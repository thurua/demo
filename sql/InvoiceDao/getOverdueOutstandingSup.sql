SELECT 
	b.name as code, 
	SUM(a.invoice_amount__c - COALESCE(a.reversed_amount__c, 0) -
  		COALESCE(a.reassigned_amount__c, 0) -
		(SELECT COALESCE(sum(c.amount__c), 0)
		FROM salesforce.credit_note_application__c c
		WHERE c.invoice__c = a.sfid)) as value 
FROM salesforce.invoice__c a 
JOIN salesforce.account b 
	ON a.supplier__c = b.sfid 
WHERE b.name in :names 
	AND a.added_credit_period__c < current_date 
	AND a.client_account__c = :clientAccountId 
GROUP BY b.name 