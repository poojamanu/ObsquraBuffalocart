package com.buffalocart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;
import com.buffalocart.utilities.TableUtility;

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

	private final String _rolesRow = "//table[@id='roles_table']//tbody//tr";
	@FindBy(xpath = _rolesRow)
	private List<WebElement> rolesRow;

	private final String _rolesColumn = "//table[@id='roles_table']//tbody//tr//td";
	@FindBy(xpath = _rolesColumn)
	private List<WebElement> rolesColumn;

	/*** UserActionMethods ***/

	public String getRolesPageTitle() {
		return PageUtility.getPageTitle(driver);
	}

	public AddRolesPage clickOnAddRoleButton() {
		PageUtility.clickOnElement(addRoleButton);
		return new AddRolesPage(driver);
	}

	public List<ArrayList<String>> getRolesTable() {
		return TableUtility.getGridData(rolesRow, rolesColumn);

	}

	public List<String> searchForRoles(List<ArrayList<String>> table, String value) {
		return PageUtility.searchForElementInTable(table, value);
	}
	
	public UpdateRolesPage clickOnEditRole(String role) {
		List<ArrayList<WebElement>> grid = TableUtility.getActionDataWebTable(rolesRow, rolesColumn);
		OUTER: for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				String data = grid.get(i).get(j).getText();

				if (data.equals(role)) {
					WebElement edit = driver.findElement(
							By.xpath("//table[@id='roles_table']//tbody//tr[" + (i + 1) + "]//td[2]//a"));
					PageUtility.clickOnElement(edit);
					break OUTER;
				}
			}
		}
		return new UpdateRolesPage(driver);
	}
	
	public DeleteRolesPage clickOnDeleteRole(String role) {
		List<ArrayList<WebElement>> grid = TableUtility.getActionDataWebTable(rolesRow, rolesColumn);
		OUTER: for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				String data = grid.get(i).get(j).getText();

				if (data.equals(role)) {
					WebElement delete = driver.findElement(
							By.xpath("//table[@id='roles_table']//tbody//tr[" + (i + 1) + "]//td[2]//button"));
					PageUtility.clickOnElement(delete);
					break OUTER;
				}
			}
		}
		return new DeleteRolesPage(driver);
	}

	public boolean isElementPresent(List<ArrayList<String>> table, String value) {
		return PageUtility.isElementFound(table, value);
	}


}
