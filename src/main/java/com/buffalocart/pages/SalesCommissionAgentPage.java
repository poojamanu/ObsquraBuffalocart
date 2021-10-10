package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

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
	
	
	/*** UserActionMethods ***/	
	
	
	public String getSalesCommissionAgentPageTitle() {
		return PageUtility.getPageTitle(driver);
	}
	
	public AddSalesCommissionAgentPage clickOnAddButton() {
		PageUtility.clickOnElement(AddButton);
		return new AddSalesCommissionAgentPage(driver);
	}

	
}
