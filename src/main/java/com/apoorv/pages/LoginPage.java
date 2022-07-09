package com.apoorv.pages;

import com.apoorv.base.Page;

public class LoginPage extends Page {


	public ZohoAppPage doLogin(String username, String password) {
		type("email_CSS", username);
		click("next_CSS");
		type("password_CSS", password);
		click("next_CSS");
		
		return new ZohoAppPage();
	}

	
}
