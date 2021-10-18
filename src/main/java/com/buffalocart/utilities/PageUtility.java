package com.buffalocart.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PageUtility {
	public static void clickOnElement(WebElement element) {
		element.click();
	}

	public static void enterText(WebElement element, String value) {
		element.sendKeys(value);
	}

	public static String getElementText(WebElement element) {
		return element.getText();

	}

	public static void clearText(WebElement element) {
		element.clear();
	}

	public static String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	public void moveToWebElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();

	}

	public void doubleClickOnElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	public void rightClickOnElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).build().perform();
	}

	public void clickAndHoldOnElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.clickAndHold(element).build().perform();
	}

	public void handleAlert(WebDriver driver) {
		driver.switchTo().alert();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void enterTextAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
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

	public static void HardWait() throws InterruptedException {
		Thread.sleep(8000);
	}

	public static void clickUsingJavaScriptExecutor(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}

	public static void scrollToFindElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public static void fileUpload() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);

		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public static List<String> searchForElementInTable(List<ArrayList<String>> table, String value) {
		// boolean found=false;
		List<String> resultRow = new ArrayList<>();
		for (ArrayList<String> row : table) {
			if (row.contains(value)) {
				resultRow = row;
				// found=true;
			}

		}
		return resultRow;
	}

	public static boolean isElementFound(List<ArrayList<String>> table, String value) {
		boolean found = false;
		for (ArrayList<String> row : table) {
			if (row.contains(value)) {
				found = true;
				break;
			} else {
				found = false;
			}

		}
		return found;
	}

	public static List<String> convertWebElementListToString(List<WebElement> elements) {
		List<String> stringlist = new ArrayList<>();
		for (int i = 1; i < elements.size(); i++) {
			String element = elements.get(i).getText();
			if (!element.equals("")) {
				stringlist.add(element);
			}
		}
		return stringlist;
	}

	public static void SelectMenu(List<WebElement> element, String value) {
		for (int i = 0; i < element.size(); i++) {
			String item = element.get(i).getText();
			if (item.equals(value)) {
				element.get(i).click();
			}
		}
	}

	public static void selectDropdownbyText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

}
