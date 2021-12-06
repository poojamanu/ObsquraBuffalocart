package com.buffalocart.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.buffalocart.utilities.DateUtility;
import com.buffalocart.utilities.PageUtility;
import com.buffalocart.utilities.WaitUtility;
import com.buffalocart.utilities.WaitUtility.LocatorType;

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

	private final String _homepageLogo = "span.logo-lg";
	@FindBy(css = _homepageLogo)
	private WebElement homepageLogo;

	private final String _userMenu = "//li[@class=\"dropdown user user-menu\"]";
	@FindBy(xpath = _userMenu)
	private WebElement userMenu;

	private final String _endTourButton = "button[class='btn btn-default btn-sm']";
	@FindBy(css = _endTourButton)
	private WebElement endTourButton;

	private final String _sidebar = "ul.sidebar-menu";
	@FindBy(css = _sidebar)
	private WebElement sidebar;

	private final String _date = "//div[@class='m-8 pull-left mt-15 hidden-xs']/strong";
	@FindBy(xpath = _date)
	private WebElement date;
	
	private final String _toaster = "toast-container";
	@FindBy(id = _toaster)
	private WebElement toasterMessage;

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

	public void isUserMenuLoaded() {
		
		WaitUtility.waitForElementToBeClickable(driver, userMenu);			
		
		
	}
	
	public SignOutPage clickOnUserMenu() {		
		WaitUtility.waitForElementInvisible(driver, _toaster, LocatorType.Id);
		PageUtility.scrollToFindElement(driver, userMenu);
		PageUtility.clickOnElement(userMenu);
		return new SignOutPage(driver);
	}

	
	public SidebarPage clickOnSidebar() {
		PageUtility.clickOnElement(sidebar);
		return new SidebarPage(driver);
	}

	public String getDateDisplayed() {
		return PageUtility.getElementText(date);
	}

}
