package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class LoginPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _username = "username";
	@FindBy(id = _username)
	private WebElement username;

	private final String _password = "password";
	@FindBy(id = _password)
	private WebElement password;

	private final String _loginButton = "button[class='btn btn-primary']";
	@FindBy(css = _loginButton)
	private WebElement loginButton;

	/*** UserActionMethods ***/

	public String getLoginPageTitle() {
		return PageUtility.getPageTitle(driver);
	}

	public void enterUsername(String uName) {
		PageUtility.enterText(username, uName);
	}

	public void enterPassword(String pword) {
		PageUtility.enterText(password, pword);
	}

	public ApplicationTourPage clickOnLoginButton() {
		PageUtility.clickOnElement(loginButton);
		return new ApplicationTourPage(driver);
	}
}
