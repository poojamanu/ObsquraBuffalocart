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

public class AddRolesTest extends Base{
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	RolesPage roles;
	AddRolesPage addrole;
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_021_Verify Add Roles page title", priority = 21, enabled = false)
	public void verifyAddRolesPageTitle() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles = usermanagement.clickOnRoleSubMenu();
		addrole=roles.clickOnAddRoleButton();
		String actualTitle = addrole.getAddRolesPageTitle();
		String expectedTitle = "Add Role - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid Add Roles page title");
	}
	
	@Test(description = "TC_021_Verify  user can add roles  ", priority = 21, enabled = false)
	public void verifyAddNewRole() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles = usermanagement.clickOnRoleSubMenu();		
		addrole=roles.clickOnAddRoleButton();
		addrole.enterRoleName("newrole");
		addrole.clickOnUserPermissionSelectAllCheckbox();
		addrole.clickOnCustomerPermissionSelectAllCheckbox();
		roles=addrole.clickOnSaveButton();
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

}
