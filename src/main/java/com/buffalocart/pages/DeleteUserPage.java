package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class DeleteUserPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public DeleteUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _OK = "//button[contains(@class,'confirm swal-button--danger')]";
	@FindBy(xpath = _OK)
	private WebElement OKbutton;

	/*** UserActionMethods  ***/

	public UsersPage clickOnOkButton() {
		PageUtility.clickOnElement(OKbutton);
		return new UsersPage(driver);
	}
	


}
