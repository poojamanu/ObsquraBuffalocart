package com.buffalocart.automationcore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.buffalocart.constants.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.buffalocart.utilities.WaitUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	public static Properties prop;
	FileInputStream fs;
	ExtentTest extentTest;
	public Base() {
		prop = new Properties();		
		try {
			fs = new FileInputStream(System.getProperty("user.dir") + Constants.CONFIGfILE);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			prop.load(fs);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	void testInitialize(String browserName, String url) throws Exception {

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else {
			throw new Exception("Invalid Name Exception");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(WaitUtility.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(WaitUtility.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
	}


	@BeforeMethod
	@Parameters({"browser"})
	public void setUp(String browser) throws Exception {
		//String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		testInitialize(browser, url);
		
	}


	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakesScreenshot takescreenshot = (TakesScreenshot) driver;
			File screenshot = takescreenshot.getScreenshotAs(OutputType.FILE);
			//String imagePath="./Screenshots/" ;
			//extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(imagePath ));
			FileUtils.copyFile(screenshot, new File("./Screenshots/"+ result.getName() + ".png"));
		}
		driver.close();

	}
	
	


}
