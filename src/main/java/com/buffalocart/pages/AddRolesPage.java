package com.buffalocart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class AddRolesPage {
	WebDriver driver;

	/*** PageConstructor ***/
	public AddRolesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	/*** WebElements ***/
	
	
	private final String _roleName ="name";
	@FindBy(id = _roleName)
	private WebElement roleName;
	
	private final String _selectAll ="//div[@class='col-md-2']//input[@class='check_all input-icheck']";
	@FindBy(xpath = _selectAll)
	private List<WebElement> selectAll;
	
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
	
	
	/*** UserActionMethods ***/
	public String getAddRolesPageTitle() {
		return PageUtility.getPageTitle(driver);
	}	
	public void enterRoleName(String newRoleName) {
		PageUtility.enterText(roleName, newRoleName);
	}
	public void clickOnUserPermissionSelectAllCheckbox() {
		PageUtility.clickOnElement(UserSelectAll);
	}
	public void clickOnRolesPermissionSelectAllCheckbox() {
		PageUtility.clickOnElement(RolesSelectAll);
	}
	public void clickOnSupplierPermissionSelectAllCheckbox() {
		PageUtility.clickOnElement(supplierSelectAll);
	}
	public void clickOnCustomerPermissionSelectAllCheckbox() {
		PageUtility.clickOnElement(customerSelectAll);
	}
	public RolesPage clickOnSaveButton() {
		PageUtility.clickOnElement(SaveButton);
		return new RolesPage(driver);
	}
	
}
