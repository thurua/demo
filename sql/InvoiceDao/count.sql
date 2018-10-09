SELECT COUNT(*) 
FROM salesforce.invoice__c a 
JOIN salesforce.client_account__c b 
	ON a.client_account__c = b.sfid 
JOIN salesforce.schedule_of_offer__c c 
	ON a.schedule_of_offer__c = c.sfid 
JOIN salesforce.account d 
	ON a.client_name__c = d.sfid 
LEFT JOIN salesforce.account e 
	ON a.customer__c = e.sfid 
LEFT JOIN salesforce.supplier_list__c f 
	ON a.supplier__c = f.sfid 