package com.apoorv.pages;

import com.apoorv.base.Page;
import com.apoorv.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page{

	public void goToChat() {
		
		click("Chat_XPATH");
	}
	public void goToSheets() {
		click("Sheets_linkText");
	}

	public void goTomAIL() {
		click("Email_linkText");
	}
	public CRMHomePage goToCRM() {
		click("CRM_XPATH");
		return new CRMHomePage();
	}
}
