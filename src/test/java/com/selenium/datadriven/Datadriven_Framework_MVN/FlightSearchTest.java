package com.selenium.datadriven.Datadriven_Framework_MVN;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.datadriven.Datadriver_Framework_util.Xls_Reader;

import junit.framework.Assert;


public class FlightSearchTest {
	
	WebDriver driver = null;
	ExtentReports rep = null;
	ExtentTest test = null;
	Xls_Reader xls = new Xls_Reader("C:\\temp\\Airline_Hybrid_MVN\\Data\\TESTSUITE1.xlsx");
	String testName = null;
	
	@BeforeMethod
	public void before(){
		rep = com.selenium.datadriven.Datadriver_Framework_util.ExtentManager.getInstance();
		test = rep.startTest("Starting Fliht SearchTest");		
	}
	
	@AfterMethod
	public void after(){
		rep.endTest(test);
		rep.flush();
	}
	
	
	@Test(dataProvider = "getData")
	public void deltaFlightSearch (String runmode, String testCase, String environment, String browser) throws InterruptedException{
		test.log(LogStatus.INFO, "Inside deltaFlightSearch");
		System.out.println("this is environment value" +environment);
		
		if (runmode.equals("N")){
			test.log(LogStatus.INFO, "Skipping the test");
			throw new SkipException("Skipping the test with has No runmode");
			
		}
		
		testName = testCase;
		ExecuteKeywords ex = new ExecuteKeywords(test);
		System.out.println("about to go to doFlightSearch funcation");
		ex.doFlightSearch(testName,xls,environment,browser);		
		
	}
	
	
	@DataProvider
	public Object[][] getData(){

		String testSuite = "Flight Search";
		String sheetName = "FlightData";
		
		// Step1: First Find Out from which row my test data is starting.
		int testStartRowNum=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testSuite)){
			testStartRowNum++;
		}
		System.out.println("Test starts from row - "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		// Step 2: After finding row start number we can find out how many rows of data we have
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
		System.out.println("Total rows are  - "+rows );
		
		//Step 3: After that we need to find out how many columns of data we have. 
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total cols are  - "+cols );
		
		//Step 4: once we find out total rows and columns of actual data we can create an object array and size accordingly.
		Object[][] data = new Object[rows][cols];
		
		
		//Step 5: Read those columns and rows data using for loop inside another for loop. 
		int dataRow=0;

		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
	
			for(int cNum=0;cNum<cols;cNum++){
				String data1=xls.getCellData(sheetName,cNum,rNum);
				System.out.println(data1);
				data[dataRow][cNum] = data1;
				// 0,0 0,1 0,2
				//1,0 1,1, 1,2
			}
			
			dataRow++;
	
		}
		return data;
		
		
		
	/*	Object data[][] = new Object[1][4];
		
		data[0][0] = "ATL";
		data[0][1] = "HNL";
		data[0][2] = "01/30/2017";
		data[0][3] = "02/03/2017";  
		
		return data;  */
	}
	

}

	

