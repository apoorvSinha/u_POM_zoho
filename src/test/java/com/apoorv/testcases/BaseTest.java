package com.apoorv.testcases;

import org.testng.annotations.AfterSuite;

import com.apoorv.base.Page;

public class BaseTest {
	
	@AfterSuite
	public void TearDown() {
		Page.quit();
	}
}
