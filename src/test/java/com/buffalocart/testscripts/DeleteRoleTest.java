package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.buffalocart.pages.UpdateUserPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

public class DeleteRoleTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	UpdateUserPage updateuser;
	DeleteUserPage deleteuser;
	DeleteRolesPage deleterole;
	AddRolesPage addrole;
	RolesPage roles;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_025_Verify user can delete a role ", priority = 25, enabled = true)
	public void verifyDeleteRole() throws IOException, InterruptedException {
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
		deleterole=roles.clickOnDeleteRole("RoleTest");
		roles=deleterole.clickOnOkButton();
		PageUtility.HardWait();
		List<ArrayList<String>> rolesTable=roles.getRolesTable();
		
		Boolean actualStatus=roles.isElementPresent(rolesTable, "RoleTest");
		softAssert.assertFalse(actualStatus, "role not deleted");
		//home.isUserMenuLoaded();	
		signout =home.clickOnUserMenu(); 
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
}
