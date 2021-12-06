package com.buffalocart.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.ForgotPasswordPage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.utilities.ExcelUtility;
import com.buffalocart.utilities.PageUtility;

public class ForgotPasswordTest extends Base {
	LoginPage login;
	ForgotPasswordPage forgotpassword;
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	
	@Test(description = "TC_005_Verify error message displyed on  Reset Password page with invalid email id", priority = 5, enabled = true)
	public void verifyResetPasswordWithInvalidEmailid() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		forgotpassword = login.clickOnForgotPasswordLink();
		forgotpassword.enterEmail(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "UserEmail"));
		forgotpassword.ClickOnSendPasswordResetLinkButton();
		//PageUtility.HardWait();
		String actualMessage = forgotpassword.getInvalidUserEmailMessage();
		String expectedMessage = "We can't find a user with that e-mail address.";
		Assert.assertEquals(actualMessage, expectedMessage, "user email not registered");
	}

}
