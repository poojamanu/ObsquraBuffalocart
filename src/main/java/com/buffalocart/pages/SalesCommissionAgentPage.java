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

public class SalesCommissionAgentPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public SalesCommissionAgentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	/*** WebElements ***/
	
	private final String _AddButton = "button[class='btn btn-primary btn-modal pull-right']";
	@FindBy(css= _AddButton)
	private WebElement AddButton;
	
	private final String _AgentTable = "sales_commission_agent_table";
	@FindBy(id= _AgentTable)
	private WebElement AgentTable;
	
	private final String _rowItems = "//table[@id='sales_commission_agent_table']//tbody//tr";
	@FindBy(xpath = _rowItems)
	private List<WebElement> rowItems;

	private final String _columnItems = "//table[@id='sales_commission_agent_table']//tbody//tr//td";
	@FindBy(xpath = _columnItems)
	private List<WebElement> columnItems;
	
	/*** UserActionMethods ***/	
	
	
	public String getSalesCommissionAgentPageTitle() {
		return PageUtility.getPageTitle(driver);
	}
	
	public AddSalesCommissionAgentPage clickOnAddButton() {
		PageUtility.clickOnElement(AddButton);
		return new AddSalesCommissionAgentPage(driver);
	}
	public List<ArrayList<String>> getSalesCommissionAgentTable() {
		return TableUtility.getGridData(rowItems, columnItems);

	}

	public List<String> searchAgentInfo(List<ArrayList<String>> table, String value) {
		return PageUtility.searchForElementInTable(table, value);
	}
	public UpdateAgentPage clickOnEditButton(String user) throws InterruptedException {
		List<ArrayList<WebElement>> grid = TableUtility.getActionDataWebTable(rowItems, columnItems);
		OUTER: for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				String data = grid.get(i).get(j).getText();

				if (data.equals(user)) {
					WebElement edit = driver.findElement(
							By.xpath("//table[@id='sales_commission_agent_table']//tbody//tr[" + (i + 1) + "]//td[6]//i[contains(@class,'edit')]"));
					PageUtility.clickOnElement(edit);
					break OUTER;
				}
			}
		}
		return new UpdateAgentPage(driver);
	}

	public DeleteAgentPage clickOnDeleteButton(String user) throws InterruptedException {
		List<ArrayList<WebElement>> grid = TableUtility.getActionDataWebTable(rowItems, columnItems);
		OUTER:for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				String data = grid.get(i).get(j).getText();
				
				if (data.equals(user)) {
					WebElement delete = driver.findElement(
							By.xpath("//table[@id='sales_commission_agent_table']//tbody//tr[\" + (i + 1) + \"]//td[6]//button[contains(@class,' delete')]"));
				
					PageUtility.clickOnElement(delete);
					break OUTER;
				}
			}

		}
		return new DeleteAgentPage(driver);
	}
	
	public boolean isElementPresent(List<ArrayList<String>> table, String value) {
		return PageUtility.isElementFound(table, value);
	}
	
}
