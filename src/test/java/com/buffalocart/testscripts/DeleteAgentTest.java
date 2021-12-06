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
import com.buffalocart.pages.AddSalesCommissionAgentPage;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.DeleteAgentPage;
import com.buffalocart.pages.DeleteRolesPage;
import com.buffalocart.pages.DeleteUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.RolesPage;
import com.buffalocart.pages.SalesCommissionAgentPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UpdateUserPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

public class DeleteAgentTest extends Base{
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	UpdateUserPage updateuser;
	DeleteAgentPage deleteagent;
	SalesCommissionAgentPage agent;
	AddSalesCommissionAgentPage addagent;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_030_Verify user can delete Sales Commission Agent ", priority = 30, enabled = true)
	public void verifyDeleteAgent() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		agent=usermanagement.clickOnSalesCommissionAgentPageSubMenu();
		addagent = agent.clickOnAddButton();
		addagent.enterPrefix(ExcelUtility.getString(2, 0, Constants.EXCELFILE, "newAgent"));
		addagent.enterFirstname(ExcelUtility.getString(2, 1, Constants.EXCELFILE, "newAgent"));
		addagent.enterLastname(ExcelUtility.getString(2, 2, Constants.EXCELFILE, "newAgent"));
		addagent.enterEmail(ExcelUtility.getString(2, 3, Constants.EXCELFILE, "newAgent"));
		addagent.enterContactNum(ExcelUtility.getNumeric(2, 4, Constants.EXCELFILE, "newAgent"));
		addagent.enterAddress(ExcelUtility.getString(2, 5, Constants.EXCELFILE, "newAgent"));
		addagent.entersalesCommisionPercent(ExcelUtility.getNumeric(2, 6, Constants.EXCELFILE, "newAgent"));
		agent = addagent.clickOnSaveButton();
		//PageUtility.HardWait();
		agent.iselementLoaded("Mr praveen vijay");
		deleteagent=agent.clickOnDeleteButton("Mr praveen vijay");
		agent=deleteagent.clickOnOkButton();
		//users.applyHardWait();
		PageUtility.HardWait();
		List<ArrayList<String>> agentTable = agent.getSalesCommissionAgentTable();		
		Boolean actualStatus=agent.isElementPresent(agentTable, "Mr praveen vijay");
		softAssert.assertFalse(actualStatus, "agent not deleted");		
		//home.isUserMenuLoaded();	
		signout =home.clickOnUserMenu(); 
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}
}
