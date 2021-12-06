package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.AddRolesPage;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.DeleteRolesPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.RolesPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

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
	DeleteRolesPage deleterole;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_021_Verify Add Roles page title", priority = 21, enabled = true)
	public void verifyAddRolesPageTitle() throws IOException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles = usermanagement.clickOnRoleSubMenu();
		addrole=roles.clickOnAddRoleButton();
		String actualTitle = addrole.getAddRolesPageTitle();
		String expectedTitle = "Add Role - Reobeen HHC";
		softAssert.assertEquals(actualTitle, expectedTitle, "invalid Add Roles page title");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
	
	@Test(description = "TC_022_Verify  user can add roles  ", priority = 22, enabled = true)
	public void verifyAddNewRole() throws IOException, InterruptedException {
		extentTest.get().assignCategory("Sanity");
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles = usermanagement.clickOnRoleSubMenu();		
		addrole=roles.clickOnAddRoleButton();
		addrole.enterRoleName("RoleTest");
		addrole.clickOnUserPermissionSelectAllCheckbox();
		addrole.clickOnCustomerPermissionSelectAllCheckbox();
		roles=addrole.clickOnSaveButton();
		//PageUtility.HardWait();
		List<ArrayList<String>> rolesTable=roles.getRolesTable();
		Boolean actualStatus=roles.isElementPresent(rolesTable, "RoleTest");
		softAssert.assertTrue(actualStatus, "Role is not added");
		deleterole=roles.clickOnDeleteRole("RoleTest");
		roles=deleterole.clickOnOkButton();
		//PageUtility.HardWait();
		//home.isUserMenuLoaded();	
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
		extentTest.get().log(Status.PASS, "Add roles Test passed");
	}

}
