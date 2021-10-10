package com.buffalocart.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.pages.ForgotPasswordPage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.utilities.ExcelUtility;

public class ForgotPasswordTest extends Base {
	LoginPage login;
	ForgotPasswordPage forgotpassword;
	@Test(description = "TC_005_Verify error message displyed on  Reset Password page with invalid email id", priority = 5, enabled = false)
	public void verifyResetPasswordWithInvalidEmailid() throws IOException, InterruptedException {
		login = new LoginPage(driver);
		forgotpassword = login.clickOnForgotPasswordLink();
		forgotpassword.enterEmail(ExcelUtility.getString(1, 0, Constants.EXCELFILE, "UserEmail"));
		forgotpassword.ClickOnSendPasswordResetLinkButton();
		Thread.sleep(3000);
		String actualMessage = forgotpassword.getInvalidUserEmailMessage();
		String expectedMessage = "We can't find a user with that e-mail address.";
		Assert.assertEquals(actualMessage, expectedMessage, "user email not registered");
	}

}
