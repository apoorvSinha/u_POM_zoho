package com.apoorv.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

import com.apoorv.utilitiy.ExcelReader;
import com.apoorv.utilitiy.ExtentManager;
import com.apoorv.utilitiy.Utilities;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader("./src/test/resources/com/apoorv/excel/testdata.xlsx");
	public static WebDriverWait wait;
	public static ExtentManager extent;
	public static ExtentTest test;
	public static String browser;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	public static WebDriver driver;
	public static TopMenu menu;

	public Page() {

		if (driver == null) {

			// load configurations
			try {
				fis = new FileInputStream(".//src/test/resources/com/apoorv/properties/config.propeties");
				log.debug("Config file found");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.debug("Config file load error due to "+ e.getMessage());
			}
			try {
				config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e1) {
				e1.printStackTrace();
				log.debug("Config file load error not found due to "+ e1.getMessage());
			}

			// load Object repository
			try {
				fis = new FileInputStream(".//src/test/resources/com/apoorv/properties/OR.properties");
				log.debug("OBject Repository file found successfully");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.debug("Object repository file not found due to "+ e.getMessage());
			}
			try {
				OR.load(fis);
				log.debug("Object Repository file loaded");
			} catch (IOException e) {
				e.printStackTrace();
				log.debug("Object repository file load error not found due to "+ e.getMessage());
			}

			// jenkins setup browser
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
				log.debug("Browser value " + browser +" received from jenkins");
			} else {
				browser = config.getProperty("browser");
				log.debug("Browser value " + browser +"received from config file");
			}
			// if browser value comes from pipeline
			config.setProperty("browser", browser);
			log.debug("browser set as "+ browser);
			
			
			
			// choosing browser
			if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				// setting chrome options
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				driver = new ChromeDriver(options);
				log.debug("Chrome driver launched successfully");
			} else if (config.getProperty("browser").equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				log.debug("Edge browser launched successfully");
			} else if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug("Firefox browser launched successfully");
			}

			// managing window and timeouts
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);

			// initialize top menu here
			menu = new TopMenu(driver);
		}

	}
	
	
	// common interaction
	
	public static void quit() {
		driver.quit();
	}

	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();

		}else if(locator.endsWith("_linkText")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}

		test.log(Status.INFO, "Clicking on: " + locator);
		log.debug("Clicking on: " + locator);
	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_linkText")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(Status.INFO, "Typing in: " + locator + " entered value is: " + value);
		log.debug("Typing in: " + locator + " entered value is: " + value);
	}

	static WebElement dropDown;

	public static void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropDown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropDown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropDown = driver.findElement(By.id(OR.getProperty(locator)));
		}else if(locator.endsWith("_linkText")) {
			dropDown = driver.findElement(By.linkText(OR.getProperty(locator)));
		}
		Select selected = new Select(dropDown);
		selected.selectByVisibleText(value);

		test.log(Status.INFO, "Selecting from dropdown: " + locator + " value as: " + value);
		log.debug("Selecting from dropdown: " + locator + " value as: " + value);
	}

	public Boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void verifyEquals(String expecetd, String actual) {
		try {
			Assert.assertEquals(actual, expecetd);
		} catch (Throwable t) {
			Utilities.capturePrint();

			// Reportng
			Reporter.log("<br>" + "Verification failure: " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + " ><img src="
					+ Utilities.screenshotName + " height=1280 width =720></a>");
			// extent
			test.log(Status.FAIL, "Verification failure: " + t.getMessage());
			test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(Utilities.screenshotName));

		}
	}

}
