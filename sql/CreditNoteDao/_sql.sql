SELECT 
	a.id, a.customer_branch__c, a.customer_from_excel__c, a.currencyisocode, 
	a.client_remarks__c, a.credit_amount__c, a.customer__c, a.schedule_of_offer__c, 
	a.client_account__c, a.name, a.credit_note_date__c, a.status__c, 
	d.schedule_no__c 
FROM salesforce.credit_note__c a 
LEFT JOIN salesforce.client_account__c c 
	ON a.client_account__c = c.sfid 
JOIN salesforce.schedule_of_offer__c d 
	ON a.schedule_of_offer__c = d.sfid 