package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.DeleteUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;
import com.buffalocart.utilities.WaitUtility;

public class UsersTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	DeleteUserPage deleteuser;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_010_Verify Users page title", priority = 10, enabled = true)
	public void verifyUsersPageTitle() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		String actualTitle = users.getUsersPageTitle();
		String expectedTitle = "Users - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid Users page title");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
	
	@Test(description = "TC_013_Verify  user search ", priority = 13, enabled = true)
	public void verifyUserSearch() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser = users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(7, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(7, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(7, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(7, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(7, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(7, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getNumeric(7, 6, Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getNumeric(7, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(7, 8, Constants.EXCELFILE, "newuser"));
		users = adduser.clickOnSaveButton();
		users.clickOnSearchBox();
		users.enterSearchKey(ExcelUtility.getString(7, 5, Constants.EXCELFILE, "newuser"));
		//PageUtility.HardWait();	
		List<ArrayList<String>> actualTable=users.getUserTable();					
		List<String> actualSearchResult=users.searchUserInfo(actualTable, ExcelUtility.getString(7, 5, Constants.EXCELFILE, "newuser"));
		List<String> expectedResult=ExcelUtility.getRow(Constants.EXCELFILE, "userTable",3); 		
		softAssert.assertEquals(actualSearchResult, expectedResult,"User is not found");
		//PageUtility.HardWait();	
		deleteuser=users.clickOnDeleteButton(ExcelUtility.getString(7, 5, Constants.EXCELFILE, "newuser"));
		users=deleteuser.clickOnOkButton();
		//home.isUserMenuLoaded();	
		//PageUtility.HardWait();	
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
	
	@Test(description = "TC_011_Verify user login with newly added user", priority = 11, enabled = true)
	public void verifyUserLoginWithNewlyAddedUser() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser=users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(8, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(8, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(8, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(8, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(8, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(8, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getString(8,6,Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getString(8, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(8, 8, Constants.EXCELFILE, "newuser"));
		users=adduser.clickOnSaveButton();
		//PageUtility.HardWait();
		//home.isUserMenuLoaded();	
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		login.enterUsername(ExcelUtility.getString(8, 5, Constants.EXCELFILE, "newuser"));
		login.enterPassword(ExcelUtility.getString(8,6,Constants.EXCELFILE, "newuser"));
		home = login.clickOnLoginButton();		
		Boolean booleanStatus = home.verifyHomePageLogoDisplayed();
		softAssert.assertTrue(booleanStatus, "Login failed");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		users.ScrollDown();
		deleteuser=users.clickOnDeleteButton(ExcelUtility.getString(8, 5, Constants.EXCELFILE, "newuser"));
		users=deleteuser.clickOnOkButton();
		//PageUtility.HardWait();
		//home.isUserMenuLoaded();	
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();

	}
	


}
