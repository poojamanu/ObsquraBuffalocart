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

public class DeleteUserTest extends Base{
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

	@Test(description = "TC_018_Verify user can delete a user ", priority = 18, enabled = true)
	public void verifyDeleteUser() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu(); 	
		adduser = users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(10, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(10, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(10, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(10, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(10, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(10, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getString(10, 6, Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getString(10, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(10, 8, Constants.EXCELFILE, "newuser"));
		users = adduser.clickOnSaveButton();
		users.iselementLoaded(ExcelUtility.getString(10, 5, Constants.EXCELFILE, "newuser"));
		users.ScrollDown();
		deleteuser=users.clickOnDeleteButton(ExcelUtility.getString(10, 5, Constants.EXCELFILE, "newuser"));
		users=deleteuser.clickOnOkButton();
		PageUtility.HardWait();
		List<ArrayList<String>> userTable=users.getUserTable();
		Boolean actualStatus=users.isElementPresent(userTable, ExcelUtility.getString(10, 5, Constants.EXCELFILE, "newuser"));
		softAssert.assertFalse(actualStatus, "user not deleted");
		//home.isUserMenuLoaded();	
		signout =home.clickOnUserMenu(); 
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
}
