package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
import com.buffalocart.utilities.ExcelUtility;

public class UpdateUserTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	UpdateUserPage updateuser;
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_016_Verify Edit Users page title", priority = 16, enabled = true)
	public void verifyEditUsersPageTitle() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu(); 		
		updateuser=users.clickOnEditButton("sannoja15");				
		String actualTitle = updateuser.getUpdateUserPageTitle();
		String expectedTitle = "Edit user - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid Edit Users page title");
	}
	
	@Test(description = "TC_017_Verify  user can edit the user details  ", priority = 17, enabled = true)
	public void verifyEditUser() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		users = usermanagement.clickOnUsersSubMenu(); 		
		updateuser=users.clickOnEditButton("sannoja15");		
		updateuser.clearField();
		updateuser.editEmail("sanooja202@gmail.com");
		users=updateuser.clickOnUpdateButton();
		List<ArrayList<String>> userTable=users.getUserTable();
		List<String> actualRow=users.searchUserInfo(userTable, "sannoja15");
		//List<String> expectedRow = ExcelUtility.getString(Constants.EXCELFILE, "editUser");
		List<String> expectedRow = Arrays.asList("sannoja15","ms sanooja beegum","Support Staff","sanooja202@gmail.com");
		softAssert.assertEquals(actualRow, expectedRow,"user data not updated");
		signout =home.clickOnUserMenu(); 
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}


}
