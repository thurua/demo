SELECT COUNT(*) 
FROM salesforce.Client_Account_Customer__c a 
JOIN salesforce.account  c 
	ON  a.customer__c = c.sfid 
LEFT JOIN salesforce.fcicountry__c d 
	ON d.sfid = a.fci_factor__c 