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
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.DeleteUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UpdateUserPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

public class UpdateUserTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	UpdateUserPage updateuser;
	DeleteUserPage deleteuser;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_016_Verify Edit Users page title", priority = 16, enabled = true)
	public void verifyEditUsersPageTitle() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		updateuser = users.clickOnEditButton("mr ravi pillai");
		String actualTitle = updateuser.getUpdateUserPageTitle();
		String expectedTitle = "Edit user - Reobeen HHC";
		softAssert.assertEquals(actualTitle, expectedTitle, "invalid Edit Users page title");

		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();

	}

	@Test(description = "TC_017_Verify  user can edit the user details  ", priority = 17, enabled = true)
	public void verifyEditUser() throws IOException, InterruptedException {
		extentTest.get().assignCategory("Sanity");
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu();
		adduser = users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(2, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(2, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(2, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(2, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(2, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(2, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getString(2, 6, Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getString(2, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(2, 8, Constants.EXCELFILE, "newuser"));
		users = adduser.clickOnSaveButton();
		users.ScrollDown();
		
		updateuser = users.clickOnEditButton(ExcelUtility.getString(2, 5, Constants.EXCELFILE, "newuser"));
		updateuser.clearField();
		updateuser.editEmail("sanooja202@gmail.com");
		users = updateuser.clickOnUpdateButton();
		List<ArrayList<String>> userTable = users.getUserTable();
		List<String> actualRow = users.searchUserInfo(userTable,
				ExcelUtility.getString(2, 5, Constants.EXCELFILE, "newuser"));
		List<String> expectedRow = ExcelUtility.getRow(Constants.EXCELFILE, "userTable", 2);
		softAssert.assertEquals(actualRow, expectedRow, "user data not updated");
		// PageUtility.HardWait();
		users.ScrollDown();
		deleteuser = users.clickOnDeleteButton(ExcelUtility.getString(2, 5, Constants.EXCELFILE, "newuser"));
		users = deleteuser.clickOnOkButton();
		//PageUtility.HardWait();
		 //home.isUserMenuLoaded();
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
		extentTest.get().log(Status.PASS, "Edit user Test passed");

	}

}
