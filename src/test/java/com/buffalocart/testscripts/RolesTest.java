package com.buffalocart.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.pages.AddRolesPage;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.RolesPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;

public class RolesTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	RolesPage roles;
	AddRolesPage addrole;
	AddUserPage adduser;
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_020_Verify Roles page title", priority = 20, enabled = false)
	public void verifyRolesPageTitle() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles = usermanagement.clickOnRoleSubMenu();
		String actualTitle = roles.getRolesPageTitle();
		String expectedTitle = "Roles - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid Roles page title");
	}

	@Test(description = "TC_026_Verify whether the added role in Role page is listed in roles field in user add page  ", priority = 26, enabled = false)
	public void verifyNewRoleListedInUserAddPage() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles = usermanagement.clickOnRoleSubMenu();
		addrole = roles.clickOnAddRoleButton();
		addrole.enterRoleName("sales executive");
		addrole.clickOnUserPermissionSelectAllCheckbox();
		addrole.clickOnCustomerPermissionSelectAllCheckbox();
		roles = addrole.clickOnSaveButton();
		Thread.sleep(5000);
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser = users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(4, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(4, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(4, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(4, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(4, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(4, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getNumeric(4, 6, Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getNumeric(4, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(4, 8, Constants.EXCELFILE, "newuser"));
		users = adduser.clickOnSaveButton();
		Thread.sleep(5000);
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		login.enterUsername(ExcelUtility.getString(4, 5, Constants.EXCELFILE, "newuser"));
		login.enterPassword(ExcelUtility.getNumeric(4, 6, Constants.EXCELFILE, "newuser"));
		home = login.clickOnLoginButton();
		Boolean booleanStatus = home.verifyHomePageLogoDisplayed();
		softAssert.assertTrue(booleanStatus, "Login failed");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

}
