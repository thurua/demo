SELECT COUNT(*) 
FROM salesforce.client_account__c a 
JOIN salesforce.recordtype b 
	ON a.recordtypeid = b.sfid 
LEFT JOIN salesforce.fcicountry__c c 
	ON a.fci_country__c = c.sfid 