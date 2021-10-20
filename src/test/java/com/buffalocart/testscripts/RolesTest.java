package com.buffalocart.testscripts;

import java.io.IOException;
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
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.RolesPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

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
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_020_Verify Roles page title", priority = 20, enabled = true)
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

	@Test(description = "TC_026_Verify whether the added role in Role page is listed in roles field in user add page  ", priority = 26, enabled = true)
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
		addrole.enterRoleName("Commission Agent");
		addrole.clickOnUserPermissionSelectAllCheckbox();
		addrole.clickOnCustomerPermissionSelectAllCheckbox();
		roles = addrole.clickOnSaveButton();
		PageUtility.HardWait();
		//home.isUserMenuLoaded();	
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser = users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(6, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(6, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(6, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(6, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(6, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(6, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getString(6, 6, Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getString(6, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(6, 8, Constants.EXCELFILE, "newuser"));
		users = adduser.clickOnSaveButton();
		PageUtility.HardWait();
		//home.isUserMenuLoaded();	
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		login.enterUsername(ExcelUtility.getString(6, 5, Constants.EXCELFILE, "newuser"));
		login.enterPassword(ExcelUtility.getString(6, 6, Constants.EXCELFILE, "newuser"));
		home = login.clickOnLoginButton();
		sidebar=home.clickOnSidebar();
		List<String> sidebarlist=sidebar.getSidebarOptions();
		List<String> expectedList=ExcelUtility.getString(Constants.EXCELFILE, "Rolesidebar");
		softAssert.assertEquals(sidebarlist, expectedList,"invalid role permissions");
		PageUtility.HardWait();
		//home.isUserMenuLoaded();	
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

}
