package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;

public class AddUserTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_014_Verify Add Users page title", priority = 14, enabled = false)
	public void verifyAddUsersPageTitle() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser = users.clickOnAddUserButton();
		String actualTitle = adduser.getAddUserPageTitle();
		String expectedTitle = "Add user - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid Add Users page title");
	}

	@Test(description = "TC_015_Verify  user can add user details ", priority = 15, enabled = false)
	public void verifyAddUser() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton(); 
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser = users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(1, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(1, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(1, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(1, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getNumeric(1, 6, Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getNumeric(1, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(1, 8, Constants.EXCELFILE, "newuser"));
		users = adduser.clickOnSaveButton();
		List<ArrayList<String>> usertable=users.getAddedUsersTable();
		System.out.println(usertable);
		
		//Boolean elementpresent=users.searchForElement(usertable,"arun");
		//softAssert.assertTrue(elementpresent,"new user record not found");
		/*
		 * signout = home.clickOnUserMenu(); login = signout.clickOnSignoutButton();
		 * softAssert.assertAll();
		 */
	}


	@Test(description = "TC_012_Verify the error message displayed without filling mandatory fields in add user form ", priority = 12, enabled = false)
	public void verifyErrorMessageOnNotFillingMandatoryUserFields() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser = users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(3, 0, Constants.EXCELFILE, "newuser"));		
		adduser.enterLastname(ExcelUtility.getString(3, 2, Constants.EXCELFILE, "newuser"));		
		adduser.enterUsername(ExcelUtility.getString(3, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(3, 8, Constants.EXCELFILE, "newuser"));
		users = adduser.clickOnSaveButton();
		Boolean actualNameStatus = adduser.isFirstnameErrorDisplayed();
		Boolean actualEmailStatus = adduser.isEmailErrorDisplayed();
		Boolean actualPasswordStatus = adduser.isPasswordErrorDisplayed();
		Boolean actualConfirmPassword = adduser.isConfirmPasswordErrorDisplayed();
		softAssert.assertTrue(actualNameStatus, "firstname required error message not displayed");
		softAssert.assertTrue(actualEmailStatus, "email required error message not displayed");
		softAssert.assertTrue(actualPasswordStatus, "password required error message not displayed");
		softAssert.assertTrue(actualConfirmPassword, "confirm password required error message not displayed");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

}
