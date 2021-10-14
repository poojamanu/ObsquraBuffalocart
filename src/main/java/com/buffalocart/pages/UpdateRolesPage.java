package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class UpdateRolesPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public UpdateRolesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _UserselectAll ="//*[@id=\"role_add_form\"]/div[3]/div[2]/div/label";
	@FindBy(xpath = _UserselectAll)
	private WebElement UserSelectAll;
	

	private final String _RolesSelectAll ="//*[@id=\"role_add_form\"]/div[4]/div[2]/div/label";
	@FindBy(xpath = _RolesSelectAll)
	private WebElement RolesSelectAll;
	
	private final String _supplierSelectAll ="//*[@id=\"role_add_form\"]/div[5]/div[2]/div/label";
	@FindBy(xpath = _supplierSelectAll)
	private WebElement supplierSelectAll;
	

	private final String _customerSelectAll ="//*[@id=\"role_add_form\"]/div[6]/div[2]/div/label";
	@FindBy(xpath = _customerSelectAll)
	private WebElement customerSelectAll;
	
	private final String _SaveButton ="button[class='btn btn-primary pull-right']";
	@FindBy(css = _SaveButton)
	private WebElement SaveButton;
	
	private final String _roleName ="name";
	@FindBy(id = _roleName)
	private WebElement roleName;

	/*** UserActionMethods ***/
	
	public String getUpdateRolesPageTitle() {
		return PageUtility.getPageTitle(driver);
	}
	
	
	public void editRoleName(String newRoleName) {
		PageUtility.enterText(roleName, newRoleName);
	}
	public void editUserPermissionSelectAllCheckbox() {
		PageUtility.clickOnElement(UserSelectAll);
	}
	public void editRolesPermissionSelectAllCheckbox() {
		PageUtility.clickOnElement(RolesSelectAll);
	}
	public void editSupplierPermissionSelectAllCheckbox() {
		PageUtility.clickOnElement(supplierSelectAll);
	}
	public void editCustomerPermissionSelectAllCheckbox() {
		PageUtility.clickOnElement(customerSelectAll);
	}
	public RolesPage clickOnUpdateButton() {
		PageUtility.clickOnElement(SaveButton);
		return new RolesPage(driver);
	}

}
