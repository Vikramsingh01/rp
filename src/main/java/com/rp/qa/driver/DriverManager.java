package com.rp.qa.driver;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import com.rp.qa.configurations.Configuration;
import org.testng.annotations.Optional;

/**
 * DriverManager class contains methods to initialise browser driver & launch browser before test execution
 * and quit the browser after test execution. Browser type is passed as parameter via testng.xml file.
 *
 */
public class DriverManager {
	
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	static Logger log = LogManager.getLogger(DriverManager.class);
	
    private DriverManager()	{
		// To prevent external instantiation of this class
    }
	
	/**
	 * This method is used to retrieve the driver and does not take any parameters.
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return driver.get();
	}
	

	/**
	 * This method initializes the driver and launches browser. It maximizes the browser window.
	 * It is called before each test.
	 * 
	 * @param browser
	 */
	public static void initialize(@Optional("chrome") String browser)  {
		driver.set(getDriver(browser));

		log.info(browser.toUpperCase() + " is configured");

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	/**
	 * This method is used to retrieve the driver based on the browser parameter.
	 * Supported browsers - Chrome, Chrome-headless Firefox, Firefox-headless, Safari and Edge.
	 * 
	 * @param browserName
	 * @return
	 */
	public static WebDriver getDriver(String browserName) {
		WebDriver driver = null;

		switch (browserName) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "chrome-headless":
			driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "firefox-headless":
			driver = new FirefoxDriver(new FirefoxOptions().addArguments("-headless"));
			break;
		case "edge":
			if (Configuration.PLATFORM_NAME.toLowerCase().contains("mac")) {
				throw new WebDriverException("Your operating system does not support the requested browser");
			} else {
				driver = new EdgeDriver();
			}
			break;
		case "safari":
			if (Configuration.PLATFORM_NAME.toLowerCase().contains("windows")) {
				throw new WebDriverException("Your operating system does not support the requested browser");
			} else {
				driver = new SafariDriver();
			}
			break;
		default:
			System.out.println("No driver found for:" + browserName);
		}
		return driver;
	}

	/**
	 * quit() method is called after every test. It closes the browser
	 * 
	 */
	public static void quit() {
		getDriver().manage().deleteAllCookies();
		getDriver().close();
	}

	/**
	 * terminate() method is called after every class. It removes the ThreadLocal driver.
	 */
	public static void terminate() {
		driver.remove();
	}

	/*
	 * Use below method when browser is passed as parameter in config.properties
	 * 
	 * public void initialize() { 
	 * String browserName = properties.getProperty("browser"); 
	 * driver.set(getDriver(browserName));
	 * 
	 * log.info(browserName + " is configured");
	 * 
	 * getDriver().manage().window().maximize();
	 * getDriver().manage().deleteAllCookies();
	 * 
	 * getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	 * 
	 * }
	 */

}
