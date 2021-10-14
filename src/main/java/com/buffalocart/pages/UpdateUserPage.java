package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class UpdateUserPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public UpdateUserPage(WebDriver driver) {
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
	
	private final String _update = "submit_user_button";
	@FindBy(id = _update)
	private WebElement update;
	
	/*** UserActionMethods ***/	
	
	public String getUpdateUserPageTitle() {
		return PageUtility.getPageTitle(driver);
	}
	public void editPrefix(String uPrefix) {
		PageUtility.enterText(prefix, uPrefix);
	}
	public void editFirstname(String ufirstName) {
		PageUtility.enterText(firstName, ufirstName);
	}
	public void editLastname(String uLast) {
		PageUtility.enterText(lastName, uLast);
	}
	public void editEmail(String mailid) {
		PageUtility.enterText(email, mailid);
	}
	public void editRole(String role) {
		PageUtility.selectDropdownbyText(roleDropdown,role );
	}
	public void editUsername(String uname) {
		PageUtility.enterText(username, uname);
	}
	public void editPassword(String pword) {
		PageUtility.enterText(password, pword);
	}
	public void editconfirmPassword(String confirmPwd) {
		PageUtility.enterText(confirmPassword, confirmPwd);
	}
	public void editSalesCommissionPercentage(String percent) {
		PageUtility.enterText(salesCommisionPercent, percent);
	}
	public void clearField() {
		PageUtility.clearText(email);
	}
	public UsersPage clickOnUpdateButton() {
		PageUtility.clickOnElement(update);
		//PageUtility.clickOnElementUsingJavaScriptExecutor(driver, saveButton);
		return new UsersPage(driver);
	}
	

}
