package com.buffalocart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class RolesPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public RolesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _addRoleButton = "a[class='btn btn-block btn-primary']";
	@FindBy(css = _addRoleButton)
	private WebElement addRoleButton;
	
	
	/*** UserActionMethods ***/

	public String getRolesPageTitle() {
		return PageUtility.getPageTitle(driver);
	}
	
	public AddRolesPage clickOnAddRoleButton() {
		PageUtility.clickOnElement(addRoleButton);
		return new AddRolesPage(driver);
	}

	public HomePage navigateToHome() {
		return new HomePage(driver);
	}

}
