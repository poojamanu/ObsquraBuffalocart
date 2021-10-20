package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class ForgotPasswordPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/
	private final String _email = "email";
	@FindBy(id = _email)
	private WebElement emailId;

	private final String _passwordResetButton = "button[class='btn btn-primary']";
	@FindBy(css = _passwordResetButton)
	private WebElement passwordResetButton;

	private final String _errorMessage = "//span[@class='help-block']/strong";
	@FindBy(xpath = _errorMessage)
	private WebElement errorMessage;

	/*** UserActionMethods ***/

	public void enterEmail(String emailAddress) {
		PageUtility.enterText(emailId, emailAddress);
	}

	public void ClickOnSendPasswordResetLinkButton() {
		PageUtility.clickOnElement(passwordResetButton);

	}

	public String getInvalidUserEmailMessage() throws InterruptedException {
		PageUtility.HardWait();
		return PageUtility.getElementText(errorMessage);
	}

}
