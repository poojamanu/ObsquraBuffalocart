package com.buffalocart.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.pages.AddSalesCommissionAgentPage;
import com.buffalocart.pages.AddUserPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SalesCommissionAgentPage;
import com.buffalocart.pages.SidebarPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UpdateAgentPage;
import com.buffalocart.pages.UserManagementPage;
import com.buffalocart.pages.UsersPage;
import com.buffalocart.utilities.ExcelUtility;

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
	SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_028_Verify Edit sales agent details", priority = 28, enabled = true)
	public void verifyAddNewSalesCommissionAgent() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		login.enterUsername(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "Login"));
		login.enterPassword(ExcelUtility.getString(1, 1, Constants.EXCELFILE, "Login"));
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		sidebar = home.clickOnSidebar();
		usermanagement = sidebar.clickOnUserManagement();
		agent = usermanagement.clickOnSalesCommissionAgentPageSubMenu();
		updateagent=agent.clickOnEditButton("Mr alen many");
		updateagent.editEmail("alen2020@gmail.com");
		agent=updateagent.clickOnSaveButton();	
		Thread.sleep(7000);
		List<ArrayList<String>> agentTable = agent.getSalesCommissionAgentTable();
		List<String> actualRow = agent.searchAgentInfo(agentTable, "Mr alen many");
		List<String> expectedRow = Arrays.asList("Mr alen many","alen2020@gmail.com","3545667","calicut","56.00%");
		softAssert.assertEquals(actualRow, expectedRow, "Agent data is not updated");		
		signout = home.clickOnUserMenu();
		login = signout.clickOnSignoutButton();
		softAssert.assertAll();
	}


}
