package com.buffalocart.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.buffalocart.automationcore.Base;
import com.buffalocart.pages.LoginPage;

public class LoginTest extends Base {
	LoginPage login;

	@Test(priority = 1, enabled = true)
	public void verifyLoginPageTitle() {
		login = new LoginPage(driver);
		String actualTitle = login.getLoginPageTitle();
		String expectedTitle = "Login - Demo POS";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid title");
	}
}
