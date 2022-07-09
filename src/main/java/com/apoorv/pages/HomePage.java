package com.apoorv.pages;

import org.openqa.selenium.By;

import com.apoorv.base.Page;

public class HomePage extends Page {

	public void goToSignUp() {
		driver.findElement(By.linkText("Free Sign Up")).click();
	}
	public LoginPage goToLogin() {
		click("loginlink_linkText");
		return new LoginPage();
	}
	public void goToSupport() {
		
	}
	public void goToLearnMore() {
		
	}
	public void validateFooterLinks() {
		
	}
}
