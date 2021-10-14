package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
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
	RolesPage roles;
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_025_Verify user can delete a role ", priority = 25, enabled = true)
	public void verifyDeleteRole() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		roles=usermanagement.clickOnRoleSubMenu();
		deleterole=roles.clickOnDeleteRole("Clerk");
		roles=deleterole.clickOnOkButton();
		List<ArrayList<String>> rolesTable=roles.getRolesTable();
		Boolean actualStatus=roles.isElementPresent(rolesTable, "Clerk");
		softAssert.assertFalse(actualStatus, "role not deleted");
		Thread.sleep(5000);
		//users.applyHardWait(7000);
		signout =home.clickOnUserMenu(); 
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
}
