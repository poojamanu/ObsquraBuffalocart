package com.buffalocart.testscripts;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.utilities.ExcelUtility;

public class SignOutTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_007_Verify whether user is navigating to login page by clicking on Sign out button", priority = 7, enabled = true)

	public void verifyNavigationToLoginOnClickSignOut() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home=login.clickOnLoginButton();
		home.clickEndTourButton();
		signout=home.clickOnUserMenu();
		login=signout.clickOnSignoutButton();
		String actualHeading=login.getLoginPanelHeading();
		String expectedHeading="Login";
		softAssert.assertEquals(actualHeading, expectedHeading);
		softAssert.assertAll();
	}
}
