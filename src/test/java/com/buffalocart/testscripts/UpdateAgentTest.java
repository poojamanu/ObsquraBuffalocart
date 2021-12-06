 package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.AddSalesCommissionAgentPage;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.DeleteAgentPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SalesCommissionAgentPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UpdateAgentPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

public class UpdateAgentTest extends Base {
	LoginPage login;
	HomePage home;
	SignOutPage signout;
	SidebarPage sidebar;
	UserManagementPage usermanagement;
	UsersPage users;
	AddUserPage adduser;
	SalesCommissionAgentPage agent;
	AddSalesCommissionAgentPage addagent;
	UpdateAgentPage updateagent;
	DeleteAgentPage deleteagent;
	SoftAssert softAssert = new SoftAssert();
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description = "TC_029_Verify Edit sales agent details", priority = 29, enabled = true)
	public void verifyEditSalesCommissionAgent() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getNumeric(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		agent = usermanagement.clickOnSalesCommissionAgentPageSubMenu();
		addagent = agent.clickOnAddButton();
		addagent.enterPrefix(ExcelUtility.getString(3, 0, Constants.EXCELFILE, "newAgent"));
		addagent.enterFirstname(ExcelUtility.getString(3, 1, Constants.EXCELFILE, "newAgent"));
		addagent.enterLastname(ExcelUtility.getString(3, 2, Constants.EXCELFILE, "newAgent"));
		addagent.enterEmail(ExcelUtility.getString(3, 3, Constants.EXCELFILE, "newAgent"));
		addagent.enterContactNum(ExcelUtility.getNumeric(3, 4, Constants.EXCELFILE, "newAgent"));
		addagent.enterAddress(ExcelUtility.getString(3, 5, Constants.EXCELFILE, "newAgent"));
		addagent.entersalesCommisionPercent(ExcelUtility.getNumeric(3, 6, Constants.EXCELFILE, "newAgent"));
		agent = addagent.clickOnSaveButton();
		//PageUtility.HardWait();
		agent.iselementLoaded("Mrs reshma rajan");
		updateagent=agent.clickOnEditButton("Mrs reshma rajan");
		updateagent.editEmail("reshma2000@gmail.com");
		agent=updateagent.clickOnSaveButton();	
		agent.iselementLoaded("reshma2000@gmail.com");			
		List<ArrayList<String>> agentTable = agent.getSalesCommissionAgentTable();
		List<String> actualRow = agent.searchAgentInfo(agentTable, "Mrs reshma rajan");
		List<String> expectedRow = Arrays.asList("Mrs reshma rajan","reshma2000@gmail.com","3545667","calicut","56.00");
		softAssert.assertEquals(actualRow, expectedRow, "Agent data is not updated");		
		deleteagent=agent.clickOnDeleteButton("Mr alen many");
		agent=deleteagent.clickOnOkButton();
		//PageUtility.HardWait();
		//home.isUserMenuLoaded();
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}


}
