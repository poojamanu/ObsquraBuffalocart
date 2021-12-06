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
import com.buffalocart.pages.ViewUserPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

public class ViewUserTest extends Base{
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	UpdateUserPage updateuser;
	ViewUserPage viewuser;
	DeleteUserPage deleteuser;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	
	@Test(description = "TC_019_Verify  the details displayed on view user page", priority = 19, enabled = true)
	public void verifyViewUser() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu(); 	
		adduser = users.clickOnAddUserButton();
		adduser.enterPrefix(ExcelUtility.getString(5, 0, Constants.EXCELFILE, "newuser"));
		adduser.enterFirstname(ExcelUtility.getString(5, 1, Constants.EXCELFILE, "newuser"));
		adduser.enterLastname(ExcelUtility.getString(5, 2, Constants.EXCELFILE, "newuser"));
		adduser.enterEmail(ExcelUtility.getString(5, 3, Constants.EXCELFILE, "newuser"));
		adduser.selectRole(ExcelUtility.getString(5, 4, Constants.EXCELFILE, "newuser"));
		adduser.enterUsername(ExcelUtility.getString(5, 5, Constants.EXCELFILE, "newuser"));
		adduser.enterPassword(ExcelUtility.getNumeric(5, 6, Constants.EXCELFILE, "newuser"));
		adduser.enterconfirmPassword(ExcelUtility.getNumeric(5, 7, Constants.EXCELFILE, "newuser"));
		adduser.enterSalesCommissionPercentage(ExcelUtility.getNumeric(5, 8, Constants.EXCELFILE, "newuser"));
		users = adduser.clickOnSaveButton();		
		PageUtility.ScrollBy(driver);
		viewuser=users.clickOnViewButton(ExcelUtility.getString(5, 5, Constants.EXCELFILE, "newuser"));
		String actualName=viewuser.getProfileName();
		String actualEmail=viewuser.getEmail();
		String actualUsername=viewuser.getUserName();
		String actualRole=viewuser.getRole();
		//String actualCommission=viewuser.getCommissionPercentage();
	
		String expectedName=ExcelUtility.getString(1, 1, Constants.EXCELFILE, "viewUserDetails");
		String expectedEmail=ExcelUtility.getString(1, 3, Constants.EXCELFILE, "viewUserDetails");
		String expectedUserName=ExcelUtility.getString(1, 0, Constants.EXCELFILE, "viewUserDetails");
		String expectedRole=ExcelUtility.getString(1, 2, Constants.EXCELFILE, "viewUserDetails");
	//	String expectedPercentage=ExcelUtility.getNumeric(1, 4, Constants.EXCELFILE, "viewUserDetails");
		
		softAssert.assertEquals(actualName, expectedName,"Profile name invalid");
		softAssert.assertEquals(actualEmail, expectedEmail,"invalid mail id");
		softAssert.assertEquals(actualUsername, expectedUserName,"invalid username");
		softAssert.assertEquals(actualRole, expectedRole,"invalid role name");
		//softAssert.assertEquals(actualCommission, expectedPercentage,"invalid commission %");
		
		users=viewuser.navigateToBackPage();
		deleteuser=users.clickOnDeleteButton(ExcelUtility.getString(5, 5, Constants.EXCELFILE, "newuser"));
		users=deleteuser.clickOnOkButton();
		//PageUtility.HardWait();
		signout =home.clickOnUserMenu(); 
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

}
