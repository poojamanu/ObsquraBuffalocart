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
import com.buffalocart.utilities.PageUtility;

public class UsersTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_010_Verify Users page title", priority = 10, enabled = true)
	public void verifyUsersPageTitle() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
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
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		users.clickOnSearchBox();
		users.enterSearchKey(ExcelUtility.getString(2, 5, Constants.EXCELFILE, "newuser"));
		List<ArrayList<String>> actualTable=users.getUserTable();	
		PageUtility.HardWait();	
		List<String> actualSearchResult=users.searchUserInfo(actualTable, ExcelUtility.getString(2, 5, Constants.EXCELFILE, "newuser"));
		List<String> expectedResult=ExcelUtility.getString(Constants.EXCELFILE, "userTable"); 		
		softAssert.assertEquals(actualSearchResult, expectedResult,"User is not found");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
	
	@Test(description = "TC_011_Verify user login with newly added user", priority = 11, enabled = true)
	public void verifyUserLoginWithNewlyAddedUser() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser=users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(2, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(2, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(2, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(2, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(2, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(2, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getString(2,6,Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getString(2, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(2, 8, Constants.EXCELFILE, "newuser"));
		users=adduser.clickOnSaveButton();
		PageUtility.HardWait();
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		login.enterUsername("sannoja14");
		login.enterPassword("abcdef");
		home = login.clickOnLoginButton();		
		Boolean booleanStatus = home.verifyHomePageLogoDisplayed();
		softAssert.assertTrue(booleanStatus, "Login failed");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();

	}
	


}
