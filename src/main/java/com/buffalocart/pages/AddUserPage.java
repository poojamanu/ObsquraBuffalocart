package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class AddUserPage {
	WebDriver driver;

	/*** PageConstructor ***/
	public AddUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	/*** WebElements ***/
	
	
	private final String _prefix ="surname";
	@FindBy(id = _prefix)
	private WebElement prefix;
	
	private final String _firstName ="first_name";
	@FindBy(id = _firstName)
	private WebElement firstName;
	
	private final String _lastName ="last_name";
	@FindBy(id = _lastName)
	private WebElement lastName;
	
	private final String _email ="email";
	@FindBy(id = _email)
	private WebElement email;
	
	private final String _roleDropdown = "role";
	@FindBy(id = _roleDropdown)
	private WebElement roleDropdown;
	
	private final String _username = "username";
	@FindBy(id = _username)
	private WebElement username;
	
	private final String _password = "password";
	@FindBy(id = _password)
	private WebElement password;
	
	private final String _confirm_password = "confirm_password";
	@FindBy(id = _confirm_password)
	private WebElement confirmPassword;
	
	private final String _commisionPercent = "cmmsn_percent";
	@FindBy(id = _commisionPercent)
	private WebElement salesCommisionPercent;
	
	private final String _save = "submit_user_button";
	@FindBy(id = _save)
	private WebElement saveButton;
	
	private final String _firstNameError ="first_name-error";
	@FindBy(id = _firstNameError)
	private WebElement firstNameError;
	
	private final String _emailError ="email-error";
	@FindBy(id = _emailError)
	private WebElement emailError;
	
	private final String _passwordError ="password-error";
	@FindBy(id = _passwordError)
	private WebElement passwordError;
	
	private final String _confirmPasswordError ="confirm_password-error";
	@FindBy(id = _confirmPasswordError)
	private WebElement confirmPasswordError;
	
	/*** UserActionMethods ***/
	public String getAddUserPageTitle() {
		return PageUtility.getPageTitle(driver);
	}	
	public void enterPrefix(String uPrefix) {
		PageUtility.enterText(prefix, uPrefix);
	}
	public void enterFirstname(String ufirstName) {
		PageUtility.enterText(firstName, ufirstName);
	}
	public void enterLastname(String uLast) {
		PageUtility.enterText(lastName, uLast);
	}
	public void enterEmail(String mailid) {
		PageUtility.enterText(email, mailid);
	}
	public void selectRole(String role) {
		PageUtility.selectDropdownbyText(roleDropdown,role );
	}
	public void enterUsername(String uname) {
		PageUtility.enterText(username, uname);
	}
	public void enterPassword(String pword) {
		PageUtility.enterText(password, pword);
	}
	public void enterconfirmPassword(String confirmPwd) {
		PageUtility.enterText(confirmPassword, confirmPwd);
	}
	public void enterSalesCommissionPercentage(String percent) {
		PageUtility.enterText(salesCommisionPercent, percent);
	}
	public UsersPage clickOnSaveButton() {
		//PageUtility.clickOnElement(saveButton);
		PageUtility.clickUsingJavaScriptExecutor(driver, saveButton);
		return new UsersPage(driver);
	}
	
	public Boolean isFirstnameErrorDisplayed() {
		return PageUtility.isElementDisplayed(firstNameError);
	}
	public Boolean isEmailErrorDisplayed() {
		return PageUtility.isElementDisplayed(emailError);
	}
	public Boolean isPasswordErrorDisplayed() {
		return PageUtility.isElementDisplayed(passwordError);
	}
	public Boolean isConfirmPasswordErrorDisplayed() {
		return PageUtility.isElementDisplayed(confirmPasswordError);
	}
	
	

}
