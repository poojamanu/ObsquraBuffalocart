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
import com.buffalocart.pages.AddSalesCommissionAgentPage;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SalesCommissionAgentPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

public class AddSalesCommissionAgentTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	SalesCommissionAgentPage agent;
	AddSalesCommissionAgentPage addagent;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_027_Verify user can add Sales Commission Agent", priority = 27, enabled = true)
	public void verifyAddNewSalesCommissionAgent() throws IOException, InterruptedException {
		extentTest.get().assignCategory("Sanity");
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		agent = usermanagement.clickOnSalesCommissionAgentPageSubMenu();
		addagent = agent.clickOnAddButton();
		addagent.enterPrefix(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "newAgent"));
		addagent.enterFirstname(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "newAgent"));
		addagent.enterLastname(ExcelUtility.getString(1, 2, Constants.EXCELFILE, "newAgent"));
		addagent.enterEmail(ExcelUtility.getString(1, 3, Constants.EXCELFILE, "newAgent"));
		addagent.enterContactNum(ExcelUtility.getNumeric(1, 4, Constants.EXCELFILE, "newAgent"));
	 	addagent.enterAddress(ExcelUtility.getString(1, 5, Constants.EXCELFILE, "newAgent"));
		addagent.entersalesCommisionPercent(ExcelUtility.getNumeric(1, 6, Constants.EXCELFILE, "newAgent"));
		agent = addagent.clickOnSaveButton();
		PageUtility.HardWait();
		
		List<ArrayList<String>> agentTable = agent.getSalesCommissionAgentTable();
		
		List<String> actualRow = agent.searchAgentInfo(agentTable, "Mr alen many");
		
		List<String> expectedRow = Arrays.asList("Mr alen many","alenmani@gmail.com","3545667","calicut","56.00");
		Assert.assertEquals(actualRow, expectedRow, "Agent is not added");
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
		extentTest.get().log(Status.PASS, "add new agent Test passed");
		
	}

}
