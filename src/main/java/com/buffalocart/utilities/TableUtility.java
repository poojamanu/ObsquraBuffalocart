package com.buffalocart.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TableUtility {
	static String celtext;
	public static String get_Dynamic_TwoDimension_TablElemntsAsString(List<WebElement> rowItems,
			List<WebElement> columnItems) {
		
		int rows_count = rowItems.size();
		for (int row = 0; row < rows_count; row++) {
			int columns_count = columnItems.size();
			for (int column = 0; column < columns_count; column++) {
				celtext = columnItems.get(column).getText();
			}
		}
		return celtext;

	}

	public List<String> getTable(List<WebElement> table) throws IOException {
		List<String> actualValues = new ArrayList<String>();
		for (int i = 0; i < table.size(); i++) {
			actualValues.add(table.get(i).getText());
		}
		return actualValues;

	}

	public static List<ArrayList<String>> get_Dynamic_TwoDimension_TablElemnts(List<WebElement> rowItems,
			List<WebElement> columnItems) {

		// List<WebElement> rowItems =
		// driver.findElements(By.xpath("//div[@class='su-table
		// su-table-alternate']//tr"));
		// List<WebElement> coloumnItems =
		// driver.findElements(By.xpath("//div[@class='su-table
		// su-table-alternate']//tr//td"));
		String[] columnList = new String[columnItems.size() / rowItems.size()];
		List<ArrayList<String>> gridData = new ArrayList<ArrayList<String>>();
		int x = 0;
		for (int i = 0; i < rowItems.size(); i++) {
			for (int j = 1; j < columnList.length; j++) {
				columnList[j] = columnItems.get(i).getText();
				// gridData.addAll(coloumnList[j]);
			}
			x++;
			gridData.add(new ArrayList<String>(Arrays.asList(columnList)));

		}
		return gridData;
	}

}