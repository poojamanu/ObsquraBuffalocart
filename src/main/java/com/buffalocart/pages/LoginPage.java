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

	private final String _invalidLoginMessage = "span.help-block";
	@FindBy(css = _invalidLoginMessage)
	private WebElement invalidLoginMessage;

	private final String _checkbox = "//input[@type='checkbox']";
	@FindBy(xpath = _checkbox)
	private WebElement rememberMecheckbox;
	
	private final String _forgotPassword = "a[class='btn btn-link']";
	@FindBy(css = _forgotPassword)
	private WebElement forgotPassword;
	
	private final String _panelHeading = "div.panel-heading";
	@FindBy(css = _panelHeading)
	private WebElement panelHeading;

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

	public HomePage clickOnLoginButton() {
		PageUtility.clickOnElement(loginButton);
		return new HomePage(driver);
	}

	public String getInvalidLoginMessage() {
		return PageUtility.getElementText(invalidLoginMessage);
	}

	public void clickOnCheckbox() {
		PageUtility.clickOnElement(rememberMecheckbox);
	}

	public boolean IsCheckboxSelected() {
		return PageUtility.isElementSelected(rememberMecheckbox);
	}
	
	public ForgotPasswordPage clickOnForgotPasswordLink() {
		PageUtility.clickOnElement(forgotPassword);
		return new ForgotPasswordPage(driver);
	}
	
	public String getLoginPanelHeading() {
		return PageUtility.getElementText(panelHeading);
	}

}
