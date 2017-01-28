package com.selenium.datadriven.Datadriven_Framework_MVN;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.datadriven.Datadriver_Framework_util.Xls_Reader;


public class CreateKeyWords {
	
	WebDriver driver = null;
	Properties  prop = null;
	ExtentTest test = null;
	Xls_Reader xls = null;
	
	public CreateKeyWords(ExtentTest test, Xls_Reader xls) {
		this.test = test;
		this.xls = xls;
	prop = new Properties();
	FileInputStream fs;
	try {
		fs = new FileInputStream("C:\\Users\\370313\\Documents\\ZohotTesting\\ZohoTesting\\DataDriven_Framework\\Datadriven_Framework_MVN\\src\\test\\resources\\project.properties");
		prop.load(fs);
		System.out.println(prop);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		test.log(LogStatus.INFO, "Exception in property file");
	} 
	
	}
 
	public void openBrowser(String browser){

		if (browser.equals("Iexplorer")){
			System.setProperty("webdriver.ie.driver", "C:\\Drivers\\IEDriverServer.exe" );
			driver = new InternetExplorerDriver();	
		} else if(browser.equals("Chrome")) {  
			System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	} 
	
	public void navigateToURL(String environment){
		
		if (environment.equals("st"))
		driver.get(prop.getProperty("url_st"));
		else if (environment.equals("si"))
			driver.get(prop.getProperty("url_si"));
		else if (environment.equals("prod"))
		driver.get(prop.getProperty("url_prod"));
		test.log(LogStatus.INFO, "Navigated to URL");
	}
	
	public void clickAction(String locatorKey){	
	
		getElement(locatorKey).click();	
		test.log(LogStatus.INFO, "Clicked on "+locatorKey);
	
	}

	public void clearAction(String locatorKey){
		getElement(locatorKey).clear();
		test.log(LogStatus.INFO, "Cleared "+locatorKey);
		

	}
	
	public void sendKeysAction(String locatorKey, 	String inputValue){
		getElement(locatorKey).sendKeys(inputValue);
		test.log(LogStatus.INFO, "Entered value to "+locatorKey);
		
	}
	
	public String getTextAction(String locatorKey){
		
		String outputText =  getElement(locatorKey).getText();
		test.log(LogStatus.INFO, "Printed values for "+locatorKey);
		
		return outputText; 
		
	}
	
	
	
	public void getFlightNumbers(String paginationText){
		

		try {
			System.out.println(paginationText.length());
			String totalFlight = null;
			xls.removeColumn("FlightResults", 0);
			xls.addColumn("FlightResults","Flight#");
			if (paginationText.length()<=24){	
			totalFlight = paginationText.substring(0, 2);		
			}  else if (paginationText.length()>=24){	
			totalFlight = paginationText.substring(10, 12);		
				} else if (paginationText.isEmpty()){	
				    System.out.println("Zero flight results");		
					}	
			int totalFlightsReturned = Integer.parseInt(totalFlight);
			System.out.println("Total Number of Flights is "+totalFlightsReturned);	
			
			// Print flight numbers on page 1.
			String flightNumber = null;
			if(paginationText.length()>=24) {
			for (int i = 0;i<=19; i++) {
			flightNumber = driver.findElement(By.id("flightInfoContent_"+i+"_0")).getText();
			xls.setCellData("FlightResults", "Flight#", i+2,flightNumber);
			System.out.println(flightNumber);		
			}  
			
			} else if (paginationText.length()<24) {
				for (int i = 0;i<=totalFlightsReturned-1; i++) {
					flightNumber = driver.findElement(By.id("flightInfoContent_"+i+"_0")).getText();
					xls.setCellData("FlightResults","Flight#",i+2,flightNumber);
					System.out.println(flightNumber);
					
					} 
			}

			test.log(LogStatus.INFO, "Got all the flightNumbers");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Flight Numbers are not returned");
		}
		
		
	}
		
	//	------------------------------------------------Utility Functions-----------------------------------
		
		public WebElement getElement(String locatorKey) {
		
		WebElement e = null;
		try {
			if (locatorKey.endsWith("_id"))
			e = driver.findElement(By.id(prop.getProperty(locatorKey)));
			else if (locatorKey.endsWith("_xpath"))	
			e = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			else if (locatorKey.endsWith("_ClassName"))	
			e = driver.findElement(By.className(prop.getProperty(locatorKey)));
			else if (locatorKey.endsWith("_Name"))
			e = driver.findElement(By.name(prop.getProperty(locatorKey)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			Assert.fail("Element "+locatorKey+" was not found");
		}
		return e; 
		
	}
		
		public void verifyTitle(String locatorKey, String expectedTitle){
			
			String firstFlightSearchTitle = getElement(locatorKey).getText();
			Assert.assertEquals(expectedTitle, firstFlightSearchTitle);
			
		}
		
		
		public void reportFailure(String failureMessage){
		
		}

		
		
}
