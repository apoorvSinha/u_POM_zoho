package com.apoorv.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.apoorv.base.Page;
import com.apoorv.pages.ZohoAppPage;
import com.apoorv.pages.crm.accounts.AccountsPage;
import com.apoorv.pages.crm.accounts.CreatesAccountPage;
import com.apoorv.utilitiy.Utilities;

public class CreateAccountTest {
	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void createAccountTest(Hashtable<String, String> data) {
		ZohoAppPage zp = new ZohoAppPage();
		zp.goToCRM();
		AccountsPage account = Page.menu.goToAccounts();
		CreatesAccountPage cap = account.goToCreateAccounts();
		cap.createAccount(data.get("accountName"));
	}
}
