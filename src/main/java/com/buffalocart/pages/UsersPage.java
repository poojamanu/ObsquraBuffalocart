package com.buffalocart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.buffalocart.utilities.HelperMethodUtility;
import com.buffalocart.utilities.PageUtility;
import com.buffalocart.utilities.TableUtility;
import com.buffalocart.utilities.WaitUtility;
import com.buffalocart.utilities.WaitUtility.LocatorType;

public class UsersPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public UsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _SearchBox = "input[type='search']";
	@FindBy(css = _SearchBox)
	private WebElement searchBox;

	private final String _addUser = "a[class='btn btn-block btn-primary']";
	@FindBy(css = _addUser)
	private WebElement addUserButton;
	
	

	
	/*
	 * private final String _usertable = "//table[@id='users_table']//tr//td";
	 * 
	 * @FindBy(xpath = _usertable) private List<WebElement> usertable;
	 */
	
	private final String _usertable = "//table[@id='users_table']";
	@FindBy(xpath = _usertable)
	private WebElement usertable;

	private final String _rowItems = "//table[@id='users_table']//tbody//tr";
	@FindBy(xpath = _rowItems)
	private List<WebElement> rowItems;

	private final String _columnItems = "//table[@id='users_table']/tbody//tr//td";
	@FindBy(xpath = _columnItems)
	private List<WebElement> columnItems;

	/*** UserActionMethods ***/

	public String getUsersPageTitle() {
		return PageUtility.getPageTitle(driver);
	}

	public void clickOnSearchBox() {
		WaitUtility.waitForElementToBeClickable(driver, searchBox);
		PageUtility.clickOnElement(searchBox);
	}

	public void enterSearchKey(String key) {
		PageUtility.enterText(searchBox, key);
	}

	public AddUserPage clickOnAddUserButton() {
		PageUtility.clickOnElement(addUserButton);
		return new AddUserPage(driver);
	}

	public List<ArrayList<String>> getUserTable() throws InterruptedException {
		WaitUtility.waitForElement(driver, _usertable, LocatorType.Xpath);
		return TableUtility.getGridData(rowItems, columnItems);

	}

	public List<String> searchUserInfo(List<ArrayList<String>> table, String value) {
		return HelperMethodUtility.searchRow(table, value);
	}
	
	public boolean isElementPresent(List<ArrayList<String>> table, String value) {
		return HelperMethodUtility.isElementFound(table, value);
	}
	
	public void applyHardWait() throws InterruptedException {
		PageUtility.HardWait();
	}
	
	
	public void iselementLoaded(String value) {
		WaitUtility.waitForElementToBePresent(driver, usertable, value);
		
	} 
	
	public void ScrollDown() {
		PageUtility.ScrollBy(driver);
	}
	
	

	public UpdateUserPage clickOnEditButton(String user) throws InterruptedException {
		//WaitUtility.waitForElement(driver, _usertable, LocatorType.Xpath);
		List<ArrayList<WebElement>> grid = TableUtility.getActionDataWebTable(rowItems, columnItems);
		OUTER: for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				String data = grid.get(i).get(j).getText();

				if (data.equals(user)) {
					WebElement edit = driver.findElement(
							By.xpath("//table[@id='users_table']//tbody//tr[" + (i + 1) + "]//td[5]//a[1]"));
					PageUtility.clickOnElement(edit);
					break OUTER;
				}
			}
		}
		return new UpdateUserPage(driver);
	}

	public ViewUserPage clickOnViewButton(String user) {
		WaitUtility.waitForElement(driver, _usertable, LocatorType.Xpath);
		List<ArrayList<WebElement>> grid = TableUtility.getActionDataWebTable(rowItems, columnItems);
		OUTER:for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				String data = grid.get(i).get(j).getText();
				
				if (data.equals(user)) {
					WebElement view = driver.findElement(
							By.xpath("//table[@id='users_table']//tbody//tr[" + (i + 1) + "]//td[5]//a[2]"));
					PageUtility.clickOnElement(view);
					break OUTER;
				}
			}

		}
		return new ViewUserPage(driver);
	}
	
	public DeleteUserPage clickOnDeleteButton(String user) {
		WaitUtility.waitForElement(driver, _usertable, LocatorType.Xpath);
		List<ArrayList<WebElement>> grid = TableUtility.getActionDataWebTable(rowItems, columnItems);
		OUTER:for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				String data = grid.get(i).get(j).getText();
				
				if (data.equals(user)) {
					WebElement delete = driver.findElement(By.xpath("//table[@id='users_table']//tbody//tr["+(i+1)+"]//td[5]//button"));
					//PageUtility.scrollToFindElement(driver, delete);
					PageUtility.clickOnElement(delete);
					break OUTER;
				}
			}

		}
		return new DeleteUserPage(driver);
	}

}
