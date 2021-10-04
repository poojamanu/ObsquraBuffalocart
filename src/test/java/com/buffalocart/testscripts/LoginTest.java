package com.buffalocart.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.buffalocart.automationcore.Base;
import com.buffalocart.pages.ApplicationTourPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;

public class LoginTest extends Base {
	LoginPage login;
	HomePage home;
	ApplicationTourPage tour;
	@Test(description = "TC_001_Verify login page title", priority = 1, enabled = false)
	public void verifyLoginPageTitle() {
		login = new LoginPage(driver);
		String actualTitle = login.getLoginPageTitle();
		String expectedTitle = "Login - Demo POS";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid login page title");
	}
	
	@Test(description = "TC_002_Verify user login with valid user credentials", priority = 2, enabled = false)
	public void verifyUserLoginWithValidUserCredentials() throws InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername("admin");
		login.enterPassword("admin");
		tour=login.clickOnLoginButton();
		home=tour.clickEndTourButton();
		String actualUsername=home.getUsername();
		String expectedUsername="Aju Mathew";
		Assert.assertEquals(actualUsername, expectedUsername,"invalid user credentials");
		
	}
}
