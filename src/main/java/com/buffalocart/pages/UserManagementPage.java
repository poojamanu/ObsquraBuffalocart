package com.buffalocart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class UserManagementPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public UserManagementPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _userManagementSubmenu = "//li[@class='treeview  active']//i[contains(@class,'fa fa')]//following-sibling::span[@class='title']";
	@FindBy(xpath = _userManagementSubmenu)
	private List<WebElement> userManagementSubmenu;

	/*** UserActionMethods ***/

	public List<String> getUserManagementSubmenu() {
		return PageUtility.convertWebElementListToString(userManagementSubmenu);
	}
	

	public UsersPage clickOnUsersSubMenu() {
		PageUtility.SelectMenu(userManagementSubmenu, "Users");
		return new UsersPage(driver);
	}
	
	public RolesPage clickOnRoleSubMenu() {
		PageUtility.SelectMenu(userManagementSubmenu, "Roles");
		return new RolesPage(driver);
	}
	
	public SalesCommissionAgentPage clickOnSalesCommissionAgentPageSubMenu() {
		PageUtility.SelectMenu(userManagementSubmenu, "Sales Commission Agents");
		return new SalesCommissionAgentPage(driver);
	}

}
