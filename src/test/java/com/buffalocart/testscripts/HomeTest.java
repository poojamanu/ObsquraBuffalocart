package com.buffalocart.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.utilities.ExcelUtility;

public class HomeTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	
	@Test(description = "TC_006_Verify home page title", priority = 6, enabled = false)
	public void verifyHomePageTitle() throws IOException {
		login=new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home=login.clickOnLoginButton();
		home.clickEndTourButton();
		String actualTitle=home.getHomePageTitle();
		String expectedTitle="Home - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle,"invalid home page title");
	}
	
	
}
