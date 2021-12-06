package com.buffalocart.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;

public class TableUtility {

	public static List<ArrayList<String>> getGridData(List<WebElement> rowItems, List<WebElement> columnItems) {
		int rowSize = rowItems.size();
		int columnSize = columnItems.size();
		int columnListSize = (columnSize / rowSize) - 1; // -1 for excluding the last column user actions
		String[] columnList = new String[columnListSize];
		List<ArrayList<String>> gridData = new ArrayList<ArrayList<String>>();
		int x = 0;
		for (int i = 0; i < rowItems.size(); i++) {
			for (int j = 0; j < columnList.length; j++) {
				columnList[j] = columnItems.get(x).getText();
				x++;
			}
			x++;
			gridData.add(new ArrayList<String>(Arrays.asList(columnList)));

		}
		return gridData;
	}

	public static List<ArrayList<WebElement>> getActionDataWebTable(List<WebElement> rowItems, List<WebElement> columnItems) {
	    int rSize= rowItems.size();
	    int cSize = columnItems.size();
	    int clistSize = (columnItems.size() / rowItems.size())  ;
	    WebElement[] columnList = new WebElement[clistSize]; 
	    List<ArrayList<WebElement>> gridData = new ArrayList<ArrayList<WebElement>>();
	    int x = 0;
	    int s = columnList.length;
	    for (int i = 0; i < rowItems.size(); i++) {

	        for (int j = 0; j < columnList.length; j++) {

	            columnList[j] = columnItems.get(x);

	            x++;
	        }
	        gridData.add(new ArrayList<WebElement>(Arrays.asList(columnList)));
	    }
	    return gridData;
	}

}