package com.buffalocart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;
import com.buffalocart.utilities.WaitUtility;
import com.buffalocart.utilities.WaitUtility.LocatorType;

public class UpdateAgentPage {
	WebDriver driver;

	/*** PageConstructor ***/
	
	public UpdateAgentPage(WebDriver driver) {
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
	@FindBy(name = _email)
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

	public void editPrefix(String uPrefix) {
		PageUtility.clearText(prefix);
		PageUtility.enterText(prefix, uPrefix);
	}

	public void editFirstname(String ufirstName) {
		PageUtility.clearText(firstName);
		PageUtility.enterText(firstName, ufirstName);
	}

	public void editLastname(String uLast) {
		PageUtility.clearText(lastName);
		PageUtility.enterText(lastName, uLast);
	}

	public void editEmail(String mailid) throws InterruptedException  {
		WaitUtility.waitForElement(driver, _email, LocatorType.Name);
		PageUtility.clearText(email);
		//PageUtility.HardWait();
		PageUtility.enterText(email, mailid);
	}

	public void editContactNum(String num) {
		PageUtility.clearText(contactNumber);
		PageUtility.enterText(contactNumber, num);
	}

	public void editAddress(String agentaddress) {
		PageUtility.clearText(address);
		PageUtility.enterText(address, agentaddress);
	}

	public void editSalesCommisionPercent(String commission) {
		PageUtility.clearText(salesCommisionPercent);
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
