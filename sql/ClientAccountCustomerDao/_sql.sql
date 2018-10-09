SELECT 
	a.id, a.sfid, c.Name, a.Activation_Date__c, a.Status__c,  a.Customer__c, 
	a.Verification__c, a.Verification_Exceeding_Invoice_Amount__c, 
	d.name as fciname, c.name as ccname 
FROM salesforce.Client_Account_Customer__c a 
JOIN salesforce.account  c 
	ON  a.customer__c = c.sfid 
LEFT JOIN salesforce.fcicountry__c d 
	ON d.sfid = a.fci_factor__c 