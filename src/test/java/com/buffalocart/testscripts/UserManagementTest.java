package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.utilities.ExcelUtility;

public class UserManagementTest extends Base {

	UserManagementPage usermanagement;
	LoginPage login;
	HomePage home;
	SidebarPage sidebar;
	SignOutPage signout;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_009_verify sub menus are displayed on clicking User management", enabled = true, priority = 9)
	public void verifyUserManagementSubmenus() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		List<String> actualList=usermanagement.getUserManagementSubmenu();
		List<String> expectedList = ExcelUtility.getString(Constants.EXCELFILE, "UserManagementSubmenu");
		softAssert.assertEquals(actualList, expectedList,"invalid sub menu items");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
}
