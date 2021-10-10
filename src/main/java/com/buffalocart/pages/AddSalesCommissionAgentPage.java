package com.buffalocart.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class AddSalesCommissionAgentPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public AddSalesCommissionAgentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/
	private final String _prefix = "surname";
	@FindBy(id = _prefix)
	private WebElement prefix;

	private final String _firstName = "first_name";
	@FindBy(id = _firstName)
	private WebElement firstName;

	private final String _lastName = "last_name";
	@FindBy(id = _lastName)
	private WebElement lastName;

	private final String _email = "email";
	@FindBy(id = _email)
	private WebElement email;

	private final String _contactNumber = "contact_no";
	@FindBy(id = _contactNumber)
	private WebElement contactNumber;

	private final String _address = "address";
	@FindBy(id = _address)
	private WebElement address;

	private final String _commisionPercent = "cmmsn_percent";
	@FindBy(id = _commisionPercent)
	private WebElement salesCommisionPercent;

	private final String _save = "button[class='btn btn-primary']";
	@FindBy(css = _save)
	private WebElement saveButton;

	private final String _close = "//form[@id='sale_commission_agent_form']/div[@class='modal-footer']//button[@class='btn btn-default']";
	@FindBy(xpath = _close)
	private WebElement closeButton;

	/*** UserActionMethods ***/

	public void enterPrefix(String uPrefix) {
		PageUtility.enterText(prefix, uPrefix);
	}

	public void enterFirstname(String ufirstName) {
		PageUtility.enterText(firstName, ufirstName);
	}

	public void enterLastname(String uLast) {
		PageUtility.enterText(lastName, uLast);
	}

	public void enterEmail(String mailid) {
		PageUtility.enterText(email, mailid);
	}

	public void enterContactNum(String num) {
		PageUtility.enterText(contactNumber, num);
	}

	public void enterAddress(String agentaddress) {
		PageUtility.enterText(address, agentaddress);
	}

	public void entersalesCommisionPercent(String commission) {
		PageUtility.enterText(salesCommisionPercent, commission);
	}

	public SalesCommissionAgentPage clickOnSaveButton() {
		PageUtility.clickOnElement(saveButton);
		return new SalesCommissionAgentPage(driver);
	}
	
	public SalesCommissionAgentPage clickOnCloseButton() {
		PageUtility.clickOnElement(closeButton);
		return new SalesCommissionAgentPage(driver);
	}

}
