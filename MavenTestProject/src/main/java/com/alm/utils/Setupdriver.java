package com.alm.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Setupdriver {
	
	ReadProperty propreader = new ReadProperty("src/main/resources/Config.properties");
	public WebDriver driver;
	String app_url = propreader.getString("app_url");
	String driverType = propreader.getString("browserType");
	
	static String chromepath = "/src/test/resources/Drivers/chromedriver.exe";
	static String firebasepath = "src/test/resources/Drivers/geckodriver";
	static String iepath = "write_path_here";
	
	public WebDriver init_webDriver(String driverType, String app_url) {
		if("chrome".equalsIgnoreCase(driverType)) {
			System.setProperty("webdriver.chrome.driver", chromepath);
			driver = new ChromeDriver();
			driver.get(app_url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		}else if("firefox".equalsIgnoreCase(driverType)) {
			System.setProperty("webdriver.gecko.driver", firebasepath);
			driver = new FirefoxDriver();
			driver.get(app_url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		}
		else if("IE32".equalsIgnoreCase(driverType)) {
			System.setProperty("webdriver.ie.driver", iepath);
			driver = new InternetExplorerDriver();
			driver.get(app_url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		}
//		else if("htmlunit".equalsIgnoreCase(driverType)) {
//			driver = new HtmlUnitDriver();
//			driver.get(app_url);
//			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//		}
		return driver;

	}
	

}
