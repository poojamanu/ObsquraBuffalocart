package com.buffalocart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class SidebarPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public SidebarPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/
	private final String _sidebar = "//ul[@class='sidebar-menu']//li[@class='treeview ']";
	@FindBy(xpath = _sidebar)
	private List<WebElement> sidebar; // 12 elements

	/*** UserActionMethods ***/

	/*
	 * public List<WebElement> getSidebarMenu() { //return
	 * PageUtility.getDropdownOption(sidebar); }
	 */

	public UserManagementPage clickOnUserManagement() {
		PageUtility.SelectMenu(sidebar, "User Management");
		return new UserManagementPage(driver);
	}
	
	public ContactsPage clickOnContacts() {
		PageUtility.SelectMenu(sidebar, "Contacts");
		return new ContactsPage(driver);
	}
}
