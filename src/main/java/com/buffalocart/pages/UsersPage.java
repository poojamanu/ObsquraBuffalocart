package com.buffalocart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;
import com.buffalocart.utilities.TableUtility;

public class UsersPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public UsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/

	private final String _SearchBox = "input[type='search']";
	@FindBy(css= _SearchBox)
	private WebElement searchBox;
	
	private final String _SearchResultMessage = "users_table_info";
	@FindBy(id = _SearchResultMessage)
	private WebElement SearchResultMessage;
	
	private final String _addUser = "a[class='btn btn-block btn-primary']";
	@FindBy(css = _addUser)
	private WebElement addUserButton;
	
	private final String _usertable = "//table[@id='users_table']//tr//td";
	@FindBy(xpath = _usertable)
	private WebElement usertable;
	
	private final String _edit ="//table[@id='users_table']//tr[1]//td[5]//i[@class='glyphicon glyphicon-edit']";
	@FindBy(xpath = _edit)
	private WebElement edit;
	
	private final String _view ="//table[@id='users_table']//tr[1]//td[5]//a[@class='btn btn-xs btn-info']";
	@FindBy(xpath = _view)
	private WebElement view;
	
	private final String _delete ="//table[@id='users_table']//tr[1]//td[5]//button[contains(@class,'delete_user')]";
	@FindBy(xpath = _delete)
	private WebElement delete;
	
	private final String _rowItems="//table[@id='users_table']/tbody/tr";
	@FindBy(xpath = _rowItems)
	private List<WebElement> rowItems;
	
	private final String _columnItems="//table[@id='users_table']/tbody/tr/td";
	@FindBy(xpath = _columnItems)
	private List<WebElement> columnItems;
	

	/*** UserActionMethods ***/	
	
	public String getUsersPageTitle() {
		return PageUtility.getPageTitle(driver);
	}
	
	public void clickOnSearchBox() {
		PageUtility.clickOnElement(searchBox);
	}
	
	public void enterSearchKey(String key) {
		PageUtility.enterText(searchBox, key);
	}
	
	public String getSearchResultMessage() {
		return PageUtility.getElementText(SearchResultMessage);
	}
	
	public AddUserPage clickOnAddUserButton() {
		PageUtility.clickOnElement(addUserButton);
		return new AddUserPage(driver);
	}
	
	public List<ArrayList<String>> getAddedUsersTable() {
		return TableUtility.get_Dynamic_TwoDimension_TablElemnts(rowItems, columnItems);
		
	}
	
	public UpdateUserPage clickOnEditButton() {
		PageUtility.clickOnElement(edit);
		return new UpdateUserPage(driver);
	}
	
	public void verifyUserAdded(String value) {
		//PageUtility.searchForElementInTable(usertable,value);
	}
}
