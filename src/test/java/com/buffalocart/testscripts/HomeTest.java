package com.buffalocart.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buffalocart.automationcore.Base;
import com.buffalocart.pages.ApplicationTourPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;

public class HomeTest extends Base {
	LoginPage login;
	HomePage home;
	ApplicationTourPage tour;
	SignOutPage signout;
	
	@Test(description = "TC_006_Verify home page title", priority = 1, enabled = false)
	public void verifyHomePageTitle() {
		login=new LoginPage(driver);
		login.enterUsername("admin");
		login.enterPassword("admin");
		tour=login.clickOnLoginButton();
		home=tour.clickEndTourButton();
		String actualTitle=home.getHomePageTitle();
		String expectedTitle="Home - Reobeen HHC";
		Assert.assertEquals(actualTitle, expectedTitle,"invalid home page title");
	}
	
	@Test(description = "TC_007_Verify whether user is navigating to login page by clicking on Sign out button ", priority = 2, enabled = true)
	public void verifySignOut()  {
		login=new LoginPage(driver);
		login.enterUsername("admin");
		login.enterPassword("admin");
		tour=login.clickOnLoginButton();
		home=tour.clickEndTourButton();
		Boolean booleanStatus=home.verifyHomePageLogoDisplayed();	
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(booleanStatus, "Home page logo not displayed");
		signout=home.clickOnUserMenu();
		login=signout.clickOnSignoutButton();
		softAssert.assertAll();
	}

}
