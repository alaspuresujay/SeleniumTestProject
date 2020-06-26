package com.alm.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.alm.utils.ReadProperty;

public class JtsLogin {
	ReadProperty prop = new ReadProperty("src/main/resources/Config.properties");
	By userField = By.xpath(prop.getString("userField"));
	By passField = By.xpath(prop.getString("passField"));
	By loginbtn = By.xpath(prop.getString("loginbtn"));
	String asserturl = "Add_url_here";
	
	WebDriver driver;
	
	public JtsLogin(WebDriver driver) {
		this.driver=driver;
	}
	
	public void inputUserId() {
		Reporter.log("Providing User Id");
		driver.findElement(userField).sendKeys(prop.getString("tid"));
		
	}
	
	public void inputPassword() {
		Reporter.log("Providing Password");
		driver.findElement(passField).sendKeys(prop.getString("Password"));
	}
	
	public void signIn() {
		Reporter.log("Click on Sign in Button");
		driver.findElement(loginbtn).click();
	}
	public void assertLogin() {
		Assert.assertTrue(driver.getCurrentUrl().contains("asserturl"));
		Reporter.log("you have Successfully logged in to application "+driver.getTitle());
		
	}
	
	
	
	
	
	
	
	

}
