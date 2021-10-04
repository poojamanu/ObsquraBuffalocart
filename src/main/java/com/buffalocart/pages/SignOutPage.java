package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class SignOutPage {
	WebDriver driver;

	public SignOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*** WebElements ***/

	private final String _signOut = "//div[@class=\"pull-right\"]/a";
	@FindBy(xpath = _signOut)
	private WebElement SignoutButton;
	
	/*** UserActionMethods ***/
	
	public LoginPage clickOnSignoutButton() {
		PageUtility.clickOnElement(SignoutButton);
		return new LoginPage(driver);
	}
}
