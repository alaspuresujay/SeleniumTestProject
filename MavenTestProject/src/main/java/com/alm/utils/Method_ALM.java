package com.alm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Method_ALM {
	public static WebDriver driver;
	static String chromepath = "/usr/bin/chromedriver";
	static String firebasepath = "/home/sujay/Academics/Coding/Java_Programs/geckodriver";
	static String iepath = "write path here";
	
	public static Properties readconfig(String Filepath) {
		Properties prop = new Properties();
		
		  File file = new File(Filepath);
		  						//ENTER LOCALTION OF "Config.properties" FILE

			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found !!");
			}

			try {
				prop.load(fileInput);
			} catch (IOException e) {
				System.out.println("File Not Loaded Properly !!");
			}
			return prop;

		}	
	

	
	//******************** setting Webdrivers
	/** setup the Webdriver and open url
	 * 
	 * @param browser chrome, firefox, IE32, htmlunit
	 * @param Url url of the site
	 */
	public static void setUpDriver(String browser, String Url) {
		if("chrome".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.chrome.driver", chromepath);
			driver = new ChromeDriver();
			driver.get(Url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		}else if("firefox".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.gecko.driver", firebasepath);
			driver = new FirefoxDriver();
			driver.get(Url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		}
		else if("IE32".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.ie.driver", iepath);
			driver = new InternetExplorerDriver();
			driver.get(Url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		}
//		else if("htmlunit".equalsIgnoreCase(browser)) {
//			driver = new HtmlUnitDriver();
//			driver.get(Url);
//			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//		}

	}
	
	
	//**********************************END setting webdrivers
	/** Send the string to input box as string
	 * 
	 * @param locator [String]
	 * @param locatorPath [String]
	 * @param data [String]
	 */
	
	public static void send(String locator, String locatorPath, String data){
		if(locator.contentEquals("id")){
			driver.findElement(By.id(locatorPath)).sendKeys(data);
		}
		else if(locator.contentEquals("class")){
			driver.findElement(By.className(locatorPath)).sendKeys(data);
		}
		else if(locator.contentEquals("css")){
			driver.findElement(By.cssSelector(locatorPath)).sendKeys(data);
		}
		else if(locator.contentEquals("name")){
			driver.findElement(By.name(locatorPath)).sendKeys(data);
		}
		else if(locator.contentEquals("xpath")){
			driver.findElement(By.xpath(locatorPath)).sendKeys(data);
		}
	}
	
	public static void selectOption(String dropdownID, String optionText) {
	       // Get the dropdown button
	       WebElement dropdownButton = driver.findElement(By.id(dropdownID));

	       // Click on the dropdown button, this will make the selection menu visible
	       dropdownButton.click();

	       // Get the dropdown selection menu, since it is now visible you can select from it
	       WebElement dropdownMenu = driver.findElement(By.id(dropdownID));

	       // Verify selection menu is visible
	       if(dropdownMenu.isDisplayed()) {
	             List<WebElement> menuItems = dropdownMenu.findElements(By.tagName("a"));
	             for(WebElement menuItem : menuItems) {
	                if(menuItem.getText().trim().toLowerCase().equalsIgnoreCase(optionText.trim().toLowerCase())) {
	                       menuItem.click();
	                       break;
	                }
	            }  
	       }        
	}
	
	/** Click on element
	 * @param locator id, xpath, name, link, class [String]
	 * @param locatorPath path of element [String]
	 */
	
	public static void click(String locator, String locatorPath){
		if(locator.contentEquals("id")){
			driver.findElement(By.id(locatorPath)).click();
		}
		else if(locator.contentEquals("class")){
			driver.findElement(By.className(locatorPath)).click();
		}
		else if(locator.contentEquals("css")){
			driver.findElement(By.cssSelector(locatorPath)).click();
		}
		else if(locator.contentEquals("name")){
			driver.findElement(By.name(locatorPath)).click();
		}
		else if(locator.contentEquals("xpath")){
			driver.findElement(By.xpath(locatorPath)).click();
		}
		else if(locator.contentEquals("link")){
			driver.findElement(By.linkText(locatorPath)).click();
		}
	}
	/** Adds implicitlyWait function
	 * @param time in Seconds [int]
	*/
	public static void IWait(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	/** Closes the current active window
	*/
	public static void Close(){
		driver.close();
	}
	/** Closes the all window that are opened by Selenium script
	*/
	public static void Quit(){
		driver.quit();
	}
	/** Switch to current active window
	*/
	public static void swit(){
		String currentTab = driver.getWindowHandle();
		for (String tab : driver.getWindowHandles()) {
		    if (!tab.equals(currentTab)) {
		        driver.switchTo().window(tab); 
		    }       
		}
	}
	public static void freeze(int time) throws InterruptedException{
		Thread.sleep(time);
	}
	/** Get the Value of element
	 * @param locator xpath, name, class, id [String]
	 * @param locatorPath - path of the element [String]
	*/
	public static String getValue(String locator, String locatorPath){
		if(locator.contentEquals("id")){
			WebElement b4=driver.findElement(By.id(locatorPath));
			return b4.getText();
		}
		else if(locator.contentEquals("class")){
			WebElement b4=driver.findElement(By.className(locatorPath));
			return b4.getText();
		}
		else if(locator.contentEquals("css")){
			WebElement b4=driver.findElement(By.cssSelector(locatorPath));
			return b4.getText();

		}
		else if(locator.contentEquals("name")){
			WebElement b4=driver.findElement(By.name(locatorPath));
			return b4.getText();
		}
		else if(locator.contentEquals("xpath")){
			WebElement b4=driver.findElement(By.xpath(locatorPath));
			return b4.getText();
		}
		else if(locator.contentEquals("link")){
			WebElement b4=driver.findElement(By.linkText(locatorPath));
			return b4.getText();
		}
		return null;
	}
	/**
	 * 
	 * @param locator [String]
	 * @param locatorPath [String]
	 * @param data [String]
	 */

	public static void DropdownByValue(String locator, String locatorPath, String data){

		if(locator.contentEquals("id")){
			Select leave = new Select(driver.findElement(By.id(locatorPath)));
			  leave.selectByValue(data);
		}
		else if(locator.contentEquals("class")){
			Select leave = new Select(driver.findElement(By.className(locatorPath)));
			  leave.selectByValue(data);
		}
		else if(locator.contentEquals("css")){
			Select leave = new Select(driver.findElement(By.cssSelector(locatorPath)));
			  leave.selectByValue(data);
		}
		else if(locator.contentEquals("name")){
			Select leave = new Select(driver.findElement(By.name(locatorPath)));
			  leave.selectByValue(data);
		}
		else if(locator.contentEquals("xpath")){
			Select leave = new Select(driver.findElement(By.xpath(locatorPath)));
			  leave.selectByValue(data);
		}
	}
	/**
	 * 
	 * @param locator [String]
	 * @param locatorPath [String]
	 * @param data [String]
	 */
	
	public static void DropdownByText(String locator, String locatorPath, String data){

		if(locator.contentEquals("id")){
			Select leave = new Select(driver.findElement(By.id(locatorPath)));
			  leave.selectByVisibleText(data);
		}
		else if(locator.contentEquals("class")){
			Select leave = new Select(driver.findElement(By.className(locatorPath)));
			  leave.selectByVisibleText(data);
		}
		else if(locator.contentEquals("css")){
			Select leave = new Select(driver.findElement(By.cssSelector(locatorPath)));
			  leave.selectByVisibleText(data);
		}
		else if(locator.contentEquals("name")){
			Select leave = new Select(driver.findElement(By.name(locatorPath)));
			  leave.selectByVisibleText(data);
		}
		else if(locator.contentEquals("xpath")){
			Select leave = new Select(driver.findElement(By.xpath(locatorPath)));
			  leave.selectByVisibleText(data);
		}
	}
	public static void DropdownByIndex(String locator, String locatorPath, int data){

		if(locator.contentEquals("id")){
			Select leave = new Select(driver.findElement(By.id(locatorPath)));
			  leave.selectByIndex(data);
		}
		else if(locator.contentEquals("class")){
			Select leave = new Select(driver.findElement(By.className(locatorPath)));
			  leave.selectByIndex(data);
		}
		else if(locator.contentEquals("css")){
			Select leave = new Select(driver.findElement(By.cssSelector(locatorPath)));
			  leave.selectByIndex(data);
		}
		else if(locator.contentEquals("name")){
			Select leave = new Select(driver.findElement(By.name(locatorPath)));
			  leave.selectByIndex(data);
		}
		else if(locator.contentEquals("xpath")){
			Select leave = new Select(driver.findElement(By.xpath(locatorPath)));
			  leave.selectByIndex(data);
		}
	}
	
	
	public static void isEnable(String locator, String locatorPath){

		if(locator.contentEquals("id")){
			Boolean btn = driver.findElement(By.id(locatorPath)).isEnabled();
			 if(btn)
		        {
		            System.out.println("Button Enabled");
		        }
		        else{
		            System.out.println("Button Disabled");
		        }
		}
		else if(locator.contentEquals("class")){
			Boolean btn = driver.findElement(By.className(locatorPath)).isEnabled();
			 if(btn)
		        {
		            System.out.println("Button Enabled");
		        }
		        else{
		            System.out.println("Button Disabled");
		        }
		}
		else if(locator.contentEquals("css")){
			Boolean btn = driver.findElement(By.cssSelector(locatorPath)).isEnabled();
			 if(btn)
		        {
		            System.out.println("Button Enabled");
		        }
		        else{
		            System.out.println("Button Disabled");
		        }
		}
		else if(locator.contentEquals("name")){
			Boolean btn = driver.findElement(By.name(locatorPath)).isEnabled();
			 if(btn)
		        {
		            System.out.println("Button Enabled");
		        }
		        else{
		            System.out.println("Button Disabled");
		        }
		}
		else if(locator.contentEquals("xpath")){
			Boolean btn = driver.findElement(By.xpath(locatorPath)).isEnabled();
			 if(btn)
		        {
		            System.out.println("Button Enabled");
		        }
		        else{
		            System.out.println("Button Disabled");
		        }
		}
	}
	public static void MoveMouseOver(String locator, String locatorPath){

		Actions actions = new Actions(driver);

		if(locator.contentEquals("id")){
			WebElement target = driver.findElement(By.id(locatorPath));
			actions.moveToElement(target).perform();
		}
		else if(locator.contentEquals("class")){
			WebElement target = driver.findElement(By.className(locatorPath));
			actions.moveToElement(target).perform();
		}
		else if(locator.contentEquals("css")){
			WebElement target = driver.findElement(By.cssSelector(locatorPath));
			actions.moveToElement(target).perform();
		}
		else if(locator.contentEquals("name")){
			WebElement target = driver.findElement(By.name(locatorPath));
			actions.moveToElement(target).perform();
		}
		else if(locator.contentEquals("xpath")){
			WebElement target = driver.findElement(By.xpath(locatorPath));
			actions.moveToElement(target).perform();
		}
		else if(locator.contentEquals("link")){
			WebElement target = driver.findElement(By.linkText(locatorPath));
			actions.moveToElement(target).perform();
		}
	} 
	/** perform the action refresh, back or forward
	 * 
	 * @param here forward, back, refresh or url(http) [String]
	 */
	public static void navigate(String here){
		if(here.contentEquals("refresh")){
			driver.navigate().refresh();
		}
		else if(here.contentEquals("back")){
			driver.navigate().back();
		}
		else if(here.contentEquals("forward")){
			driver.navigate().forward();
			driver.navigate().to(here);
		}
		else if(here.startsWith("http")){
			driver.navigate().to(here);
		}
		

	} 
	/** to check element present or not
	 * 
	 * @param locator
	 * @param locatorPath
	 * @return boolean
	 */
	public static boolean present(String locator, String locatorPath){
		if(locator.contentEquals("id")){
			try{
				WebElement b1 = driver.findElement(By.id(locatorPath));
//				System.out.println(b1.getText()+ " Element is Present");
	            return true;
	        }
	        catch(Exception e){
//	        	System.out.println(" Element is Absent");
	            return false;
	        }

		}
		else if(locator.contentEquals("class")){
			try{
				WebElement b1 = driver.findElement(By.className(locatorPath));
//				System.out.println(b1.getText()+ " Element is Present");
	            return true;
	        }
	        catch(Exception e){
//	        	System.out.println(" Element is Absent");
	            return false;
	        }
		}
		else if(locator.contentEquals("css")){
			try{
				WebElement b1 = driver.findElement(By.cssSelector(locatorPath));
//				System.out.println(b1.getText()+ " Element is Present");
	            return true;
	        }
	        catch(NoSuchElementException e){
//	        	System.out.println(" Element is Absent");
	            return false;
	        }
			}
		else if(locator.contentEquals("name")){
			try{
				WebElement b1 = driver.findElement(By.name(locatorPath));
//				System.out.println(b1.getText()+ " Element is Present");
	            return true;
	        }
	        catch(NoSuchElementException e){
//	        	System.out.println(" Element is Absent");
	            return false;
	        }
		}
		else if(locator.contentEquals("xpath")){
			try{
				WebElement b1 = driver.findElement(By.xpath(locatorPath));
//				System.out.println(b1.getText()+ " Element is Present");
	            return true;
	        }
	        catch(NoSuchElementException e){
//	        	System.out.println(" Element is Absent");
	            return false;
	        }
		}
		else if(locator.contentEquals("link")){
			try{
				WebElement b1 = driver.findElement(By.linkText(locatorPath));
//				System.out.println(b1.getText()+ " Element is Present");
	            return true;
	        }
	        catch(NoSuchElementException e){
//	        	System.out.println(" Element is Absent");
	            return false;
	        }
		}
		return false;
	} 
	
	public static String getColorofLink(String locator,String locatorpath){
		String b = null;
		if(locator.contentEquals("id")){
			 b=driver.findElement(By.id(locatorpath)).getCssValue("color");

//			System.out.println("color of the element is:"+b);

		}
		else if(locator.contentEquals("class")){
			 b=driver.findElement(By.className(locatorpath)).getCssValue("color");

//			System.out.println("color of the element is"+b);
		}
		else if(locator.contentEquals("css")){
			b=driver.findElement(By.cssSelector(locatorpath)).getCssValue("color");

//			System.out.println("color of the element is"+b);
		}
		else if(locator.contentEquals ("name")){
			 b=driver.findElement(By.name(locatorpath)).getCssValue("color");

//			System.out.println("color of the element is"+b);
		}
		else if(locator.contentEquals("xpath")){
			 b=driver.findElement(By.name(locatorpath)).getCssValue("color");

//			System.out.println("color of the element is"+b);
		}
		return b;
	}  
	
	public static String PageTitleCheck()
	{
		String t=driver.getTitle();
		if(t!=null)
			{
			    System.out.println("title is present"+t);
			}
		else
		{
			System.out.println("title not present");
		}
		return t;
	}

}
