package com.buffalocart.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.utilities.ExcelUtility;

public class LoginTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_001_Verify login page title", priority = 1, enabled = false)
	public void verifyLoginPageTitle() {
		login = new LoginPage(driver);
		String actualTitle = login.getLoginPageTitle();
		String expectedTitle = "Login - Demo POS";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid login page title");
	}

	@Test(description = "TC_002_Verify user login with valid user credentials", priority = 2, enabled = false)
	public void verifyUserLoginWithValidUserCredentials() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		Boolean booleanStatus = home.verifyHomePageLogoDisplayed();
		softAssert.assertTrue(booleanStatus, "Login failed");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

	@Test(description = "TC_003_Verify the error message displayed for user login with invalid credentials", priority = 3, enabled = false)
	public void verifyUserLoginWithInValidUserCredentials() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(2, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(2, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		String actualmessage = login.getInvalidLoginMessage();
		String expectedMessage = "These credentials do not match our records.";
		Assert.assertEquals(actualmessage, expectedMessage, "invalid login credentials..Login failed");

	}

	@Test(description = "TC_004_Verify whetehr the user is able to click on 'Remember me' checkbox", priority = 4, enabled = false)
	public void verifyClickOnCheckbox() {
		login = new LoginPage(driver);
		login.clickOnCheckbox();
		boolean checkboxStatus = login.IsCheckboxSelected();
		Assert.assertTrue(checkboxStatus, "Unable to click checkbox");
	}
	
}
