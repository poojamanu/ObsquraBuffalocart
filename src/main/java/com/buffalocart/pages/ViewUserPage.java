package com.buffalocart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buffalocart.utilities.PageUtility;

public class ViewUserPage {
	WebDriver driver;

	/*** PageConstructor ***/

	public ViewUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*** WebElements ***/
	
	private final String _profileName = "//div[@class='col-md-12']/h3";
	@FindBy(xpath = _profileName)
	private WebElement profileName;
	
	private final String _Email = "//div[@class='row']/div[2]/p[1]";
	@FindBy(xpath = _Email)
	private WebElement Email;
	
	private final String _role = "//div[@class='row']/div[2]/p[2]";
	@FindBy(xpath = _role)
	private WebElement role;
	
	private final String _username = "//div[@class='row']/div[2]/p[3]";
	@FindBy(xpath = _username)
	private WebElement username;
	
	private final String _commission = "//div[@class='row']/div[3]/p[1]";
	@FindBy(xpath = _commission)
	private WebElement commissionPercent;
	
	
	
	/*** UserActionMethods ***/

	public String getProfileName() {
		return PageUtility.getElementText(profileName);
	}
	
	public String getEmail() {
		String s=PageUtility.getElementText(Email);
		return s.substring(7);
	}
	
	public String getRole() {
		 String s=PageUtility.getElementText(role);
		 return s.substring(6);
	}
	
	public String getUserName() {
		String s= PageUtility.getElementText(username);
		return s.substring(10);
	}
	
	public String getCommissionPercentage() {
		String s=  PageUtility.getElementText(commissionPercent);
		return s.substring(0);
	}
	
	public void ScrollDown() {
		PageUtility.ScrollBy(driver);
	}
	public UsersPage navigateToBackPage() {
		PageUtility.navigateToBack(driver);
		return new UsersPage(driver);
	}
	
	

}
