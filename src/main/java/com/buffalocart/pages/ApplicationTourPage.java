package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class ApplicationTourPage {
	WebDriver driver;

	/*** PageConstructor ***/
	public ApplicationTourPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _endTourButton = "button[class='btn btn-default btn-sm']";
	@FindBy(css = _endTourButton)
	private WebElement endTourButton;

	/*** UserActionMethods ***/

	public HomePage clickEndTourButton() {
		PageUtility.clickOnElement(endTourButton);
		return new HomePage(driver);
	}
}
