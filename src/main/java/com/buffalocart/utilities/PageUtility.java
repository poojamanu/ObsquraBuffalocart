package com.buffalocart.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PageUtility {
	public static void clickOnElement(WebElement element) {
		element.click();
	}
	public static void enterText(WebElement element,String value) {
		element.sendKeys(value);
	}
	public static String getElementText(WebElement element) {
		return  element.getText();
		
	}
	public static String getAttributeValue(WebElement element,String attribute)
	{
		return element.getAttribute(attribute);
	}
	public void moveToWebElement(WebElement element, WebDriver driver) {
		Actions actions=new Actions(driver);
	    actions.moveToElement(element).build().perform();
		
	}
	public void doubleClickOnElement(WebElement element, WebDriver driver) {
		Actions actions=new Actions(driver);
	    actions.doubleClick(element).build().perform();
	}
	public void rightClickOnElement(WebElement element, WebDriver driver) {
		Actions actions=new Actions(driver);
	    actions.contextClick(element).build().perform();
	}
	public void clickAndHold(WebElement element, WebDriver driver) {
		Actions actions=new Actions(driver);
	    actions.clickAndHold(element).build().perform();
	}
	public void selectDropdownOption(WebElement element) {
		Select select=new Select(element);
		
	}
	
	public void handleAlert(WebDriver driver) {
		Alert alert=driver.switchTo().alert();
	}
	public void acceptAlert(WebDriver driver) {
		Alert alert=driver.switchTo().alert();
		alert.accept();
	}
	public void dismissAlert(WebDriver driver) {
		Alert alert=driver.switchTo().alert();
		alert.dismiss();
	}
	public void enterTextAlert(WebDriver driver,String value) {
		Alert alert=driver.switchTo().alert();
		alert.sendKeys(value);
	}
	public String getAlertText(WebDriver driver) {
		Alert alert=driver.switchTo().alert();
		return alert.getText();
	}
	public void multipleWindowHandling() {
		
	}
	public static Boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	public static Boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}
	public static Boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}
	public static String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	

}
