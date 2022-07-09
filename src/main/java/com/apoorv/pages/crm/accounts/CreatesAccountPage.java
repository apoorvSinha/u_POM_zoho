package com.apoorv.pages.crm.accounts;

import com.apoorv.base.Page;

public class CreatesAccountPage extends Page {
	
	public void createAccount(String accountName) {
		type("enterAccountName_ID", accountName);
	}
}
