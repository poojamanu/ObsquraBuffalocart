package com.buffalocart.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.utilities.DateUtility;
import com.buffalocart.utilities.ExcelUtility;

public class HomeTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SoftAssert softAssert=new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_006_Verify home page title", priority = 6, enabled = true)
	public void verifyHomePageTitle() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		String actualTitle = home.getHomePageTitle();
		String expectedTitle = "Home - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid home page title");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

	@Test(description = "TC_007_Verify date displeyed in home page ", priority = 7, enabled = true)
	public void verifyDateDisplayedInHomePage() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		String actualDate=home.getDateDisplayed();
		String expectedDate=DateUtility.getCurrentDate();
		softAssert.assertEquals(actualDate, expectedDate);
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

}
