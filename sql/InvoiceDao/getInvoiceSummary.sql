SELECT 
	CAST(EXTRACT(MONTH FROM b.acceptance_date__c) AS INT4) "month", 
	CAST(EXTRACT(YEAR FROM b.acceptance_date__c) AS INT4) "year", 
	SUM(a.invoice_amount__c) amount 
FROM salesforce.invoice__c a 
JOIN salesforce.schedule_of_offer__c b 
	ON a.schedule_of_offer__c = b.sfid 
WHERE Status__c != 'Rejected' AND Status__c != 'Reversed' AND Status__c != 'Pending'
	AND b.client_account__c = :clientAccount 
	AND b.acceptance_date__c >= :fr AND b.acceptance_date__c <= :to	
GROUP BY EXTRACT(MONTH FROM b.acceptance_date__c), EXTRACT(YEAR FROM b.acceptance_date__c) 
ORDER BY EXTRACT(MONTH FROM b.acceptance_date__c) DESC, EXTRACT(YEAR FROM b.acceptance_date__c) DESC 