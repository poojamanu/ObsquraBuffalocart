package com.buffalocart.utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class HelperMethodUtility {
	

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
	 
	public static List<String> searchRow(List<ArrayList<String>> table, String value) {
		List<String> resultRow = new ArrayList<>();
		for (ArrayList<String> row : table) {
			if (row.contains(value)) {
				resultRow = row;
			}

		}
		return resultRow;
	}
	
	public static void SelectMenu(List<WebElement> element, String value) {
		for (int i = 0; i < element.size(); i++) {
			String item = element.get(i).getText();
			if (item.equals(value)) {
				element.get(i).click();
			}
		}
	}
	
	public static String generateUsername() {
		Faker faker = new Faker();
		return faker.name().username();
	}

}
