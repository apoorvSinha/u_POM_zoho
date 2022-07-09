package com.apoorv.pages.crm.accounts;

import com.apoorv.base.Page;

public class AccountsPage extends Page{
	
	public CreatesAccountPage goToCreateAccounts() {
		click("createAccount_XPATH");
		return new CreatesAccountPage();
	}
	public void goToImportAccounts() {
		click("importAccountDropdown_CSS");
		click("importAccounts_linkText");
	}
}
