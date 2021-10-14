package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.RolesPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UpdateRolesPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;

public class UpdateRolesTest extends Base{
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	RolesPage roles;
	UsersPage users;
	AddUserPage adduser;
	UpdateRolesPage updateroles;
	
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_023_Verify Edit Roles page title", priority = 23, enabled = true)
	public void verifyEditRolesPageTitle() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles=usermanagement.clickOnRoleSubMenu();
		updateroles=roles.clickOnEditRole("RoleTest")	;	
		String actualTitle = updateroles.getUpdateRolesPageTitle();
		String expectedTitle = "Edit Role - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid Edit Role page title");
	}
	
	
	@Test(description = "TC_024_Verify user can update  a role   ", priority = 24, enabled = true)
	public void verifyEditUser() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles=usermanagement.clickOnRoleSubMenu();
		updateroles=roles.clickOnEditRole("RoleTest")	;	
		updateroles.editRoleName("Agent1");
		updateroles.editCustomerPermissionSelectAllCheckbox();
		updateroles.editSupplierPermissionSelectAllCheckbox();
		updateroles.editUserPermissionSelectAllCheckbox();
		roles=updateroles.clickOnUpdateButton();
		Thread.sleep(5000);
		signout =home.clickOnUserMenu(); 
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
		sidebar = home.clickOnSidebar();
		Boolean booleanStatus = home.verifyHomePageLogoDisplayed();
		softAssert.assertTrue(booleanStatus, "Login failed");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		/*
		 * List<String> actualSidebarList=sidebar.getSidebarOptions(); List<String>
		 * expectedList=Arrays.asList("Supplier");
		 * softAssert.assertEquals(actualSidebarList,
		 * expectedList,"invalid role permissions");
		 */
		softAssert.assertAll();
	}

	
	

}
