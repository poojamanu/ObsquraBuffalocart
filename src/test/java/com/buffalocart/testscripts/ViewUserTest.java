package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UpdateUserPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.pages.ViewUserPage;
import com.buffalocart.utilities.ExcelUtility;

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
	SoftAssert softAssert = new SoftAssert();
	
	@Test(description = "TC_019_Verify  the details displayed on view user page", priority = 19, enabled = true)
	public void verifyEditUser() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu(); 		
		viewuser=users.clickOnViewButton("Mr andrew george");
		String actualName=viewuser.getProfileName();
		String actualEmail=viewuser.getEmail();
		String actualUsername=viewuser.getUserName();
		String actualRole=viewuser.getRole();
		String actualCommission=viewuser.getCommissionPercentage();
		System.out.println(actualUsername);
		System.out.println(actualCommission);
		String expectedName=ExcelUtility.getString(0, 1, Constants.EXCELFILE, "viewUserDetails");
		String expectedEmail=ExcelUtility.getString(0, 3, Constants.EXCELFILE, "viewUserDetails");
		String expectedUserName=ExcelUtility.getString(0, 0, Constants.EXCELFILE, "viewUserDetails");
		String expectedRole=ExcelUtility.getString(0, 2, Constants.EXCELFILE, "viewUserDetails");
		String expectedPercentage=ExcelUtility.getNumeric(0, 4, Constants.EXCELFILE, "viewUserDetails");
		
		softAssert.assertEquals(actualName, expectedName,"Profile name invalid");
		softAssert.assertEquals(actualEmail, expectedEmail,"invalid mail id");
		softAssert.assertEquals(actualUsername, expectedUserName,"invalid username");
		softAssert.assertEquals(actualRole, expectedRole,"invalid role name");
				
		signout =home.clickOnUserMenu(); 
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

}
