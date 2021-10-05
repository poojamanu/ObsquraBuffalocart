package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class HomePage {
	WebDriver driver;

	/*** PageConstructor ***/
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	/*** WebElements ***/
	private final String _username = "//a[@class=\"dropdown-toggle\"]/span";
	@FindBy(xpath = _username)
	private WebElement username;
	
	private final String _homepageLogo ="span.logo-lg";
	@FindBy(css = _homepageLogo)
	private WebElement homepageLogo;
	
	private final String _userMenu ="//li[@class=\"dropdown user user-menu\"]";
	@FindBy(xpath = _userMenu)
	private WebElement userMenu;
	
	private final String _endTourButton = "button[class='btn btn-default btn-sm']";
	@FindBy(css = _endTourButton)
	private WebElement endTourButton;
	
	/*** UserActionMethods ***/
	
	public String getUsername() {
		return PageUtility.getElementText(username);
	}
	
	public Boolean verifyHomePageLogoDisplayed() {
		return PageUtility.isElementDisplayed(homepageLogo);
	}
	
	public String getHomePageTitle() {
		return PageUtility.getPageTitle(driver);
	}
	
	public void clickEndTourButton() {
		PageUtility.clickOnElement(endTourButton);
		
	}
	
	public SignOutPage clickOnUserMenu() {
		PageUtility.clickOnElement(userMenu);
		return new SignOutPage(driver);
		
	}

}
