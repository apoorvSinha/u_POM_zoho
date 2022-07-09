package com.apoorv.base;

import org.openqa.selenium.WebDriver;

import com.apoorv.pages.crm.accounts.AccountsPage;

public class TopMenu {
	
/*	Top menu is a page
	
	*Accounts HASA topMenu
	*AccountsPage HASA topmenu
	*
	*/
	WebDriver driver;
	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToHome() {

	}

	public void goToLeads() {

	}

	public void goToContacts() {

	}

	public AccountsPage goToAccounts() {
		Page.click("Accouns_linkText");
		return new AccountsPage();
	}

	public void goToDeals() {

	}

	public void goToTasks() {

	}

	public void goToMeetings() {

	}
	public void signOut() {
		
	}
}
