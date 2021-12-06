package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.AddRolesPage;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.DeleteRolesPage;
import com.buffalocart.pages.DeleteUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.RolesPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UpdateRolesPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

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
	AddRolesPage addrole;
	DeleteUserPage deleteuser;
	DeleteRolesPage deleterole;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_023_Verify Edit Roles page title", priority = 23, enabled = true)
	public void verifyEditRolesPageTitle() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles=usermanagement.clickOnRoleSubMenu();
		addrole=roles.clickOnAddRoleButton();
		addrole.enterRoleName("RoleTest");
		addrole.clickOnUserPermissionSelectAllCheckbox();
		addrole.clickOnCustomerPermissionSelectAllCheckbox();
		roles=addrole.clickOnSaveButton();
		updateroles=roles.clickOnEditRole("RoleTest")	;	
		String actualTitle = updateroles.getUpdateRolesPageTitle();
		String expectedTitle = "Edit Role - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid Edit Role page title");
		PageUtility.navigateToBack(driver);
		deleterole=roles.clickOnDeleteRole("RoleTest");
		roles=deleterole.clickOnOkButton();
	}
	
	
	@Test(description = "TC_024_Verify user can update  a role   ", priority = 24, enabled = true)
	public void verifyEditRole() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles=usermanagement.clickOnRoleSubMenu();
		roles=usermanagement.clickOnRoleSubMenu();
		addrole=roles.clickOnAddRoleButton();
		addrole.enterRoleName("RoleTest");
		addrole.clickOnUserPermissionSelectAllCheckbox();
		addrole.clickOnCustomerPermissionSelectAllCheckbox();
		roles=addrole.clickOnSaveButton();
		updateroles=roles.clickOnEditRole("RoleTest")	;	
		updateroles.clearRoleName();
		updateroles.editRoleName("Agent1");
		//updateroles.editRolesPermissionSelectAllCheckbox();
		roles=updateroles.clickOnUpdateButton();
		roles.iselementLoaded("Agent1");
		List<ArrayList<String>> rolestable=roles.getRolesTable();
		Boolean actualStatus=roles.isElementPresent(rolestable, "Agent1");
		Assert.assertTrue(actualStatus, "role not updated");
		deleterole=roles.clickOnDeleteRole("Agent1");
		roles=deleterole.clickOnOkButton();
		//PageUtility.HardWait();
		//home.isUserMenuLoaded();	
		signout =home.clickOnUserMenu(); 
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
		
	}

	
	

}
