package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _username = "username";
	@FindBy(id = _username)
	private WebElement username;

}
