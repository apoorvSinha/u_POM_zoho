package com.apoorv.testcases;

import com.apoorv.base.Page;
import com.apoorv.pages.HomePage;
import com.apoorv.pages.LoginPage;
import com.apoorv.pages.ZohoAppPage;
import com.apoorv.pages.crm.accounts.AccountsPage;
import com.apoorv.pages.crm.accounts.CreatesAccountPage;

public class LoginPages {
	
	public static void main(String[] args) {
		
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		ZohoAppPage zp = lp.doLogin("apoorvlifeok@outlook.com", "JUSTdance@96");
		zp.goToCRM();
		AccountsPage account = Page.menu.goToAccounts();
		CreatesAccountPage cap = account.goToCreateAccounts();
		cap.createAccount("Rahul");
		
	}
}
