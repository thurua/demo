SELECT 
	a.id, a.sfid, a.activated_on__c, a.account_type__c, a.client_account__c, 
	a.factoring_type__c, a.program_name__c, a.verification__c, a.fci_country__c, 
	a.verification_exceeding_invoice_amount__c, a.status__c, b.name record_type_name, 
	c.name fci_name 
FROM salesforce.client_account__c a 
JOIN salesforce.recordtype b 
	ON a.recordtypeid = b.sfid 
LEFT JOIN salesforce.fcicountry__c c 
	ON a.fci_country__c = c.sfid 