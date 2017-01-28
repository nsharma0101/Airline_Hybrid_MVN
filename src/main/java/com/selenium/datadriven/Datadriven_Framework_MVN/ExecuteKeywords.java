package com.selenium.datadriven.Datadriven_Framework_MVN;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.datadriven.Datadriver_Framework_util.Xls_Reader;



public class ExecuteKeywords {
	
	ExtentTest test = null;
	Xls_Reader xls = new Xls_Reader("C:\\Users\\370313\\Documents\\ZohotTesting\\ZohoTesting\\DataDriven_Framework\\Datadriven_Framework_MVN\\Data\\TESTSUITE1.xlsx");
	
	public ExecuteKeywords(ExtentTest test) {
		this.test = test;

	}
	

	public void doFlightSearch(String testUnderProgress, Xls_Reader xl, String environment, String browser){
		System.out.println("inside do Flight Search function");
		
		test.log(LogStatus.INFO, "Inside do Flight search functions");
		
		CreateKeyWords app = new CreateKeyWords(test, xls);
	
		int rows = xls.getRowCount("Keywords");
		
		for (int rNum=2; rNum<=rows; rNum++){
			
			String paginationText = null;
			//System.out.println("able to go inside for loop");
			String tcid = xls.getCellData("Keywords", "TCID", rNum);
		
		if(tcid.equals(testUnderProgress)) {
			String description = xls.getCellData("Keywords", "Description", rNum);
			System.out.println(description);
			String keyWord = xls.getCellData("Keywords", "Keyword", rNum);
			String object = xls.getCellData("Keywords", "Object", rNum);
			String data = xls.getCellData("Keywords", "Data", rNum);
		   // System.out.println(tcid +" "+description+" "+keyWord+" "+object+" "+data);		
	
		if(keyWord.equals("openBrowser")){
			app.openBrowser(browser);
		} else if (keyWord.equals("navigateToURL")){
			app.navigateToURL(environment);
		}else if (keyWord.equals("clearAction")) {
			app.clearAction(object);
		}else if (keyWord.equals("sendKeysAction")) {
			app.sendKeysAction(object, data);
		 }else if(keyWord.equals("clickAction")) {
			 app.clickAction(object);
		} else if(keyWord.equals("getTextAction")) {
			 app.getTextAction(object);
		}  else if(keyWord.equals("getTextAction")) {
			paginationText =  app.getTextAction(object);
		} else if(keyWord == "getFlightNumbers") {	
			 app.getFlightNumbers(paginationText);
		} else  if(keyWord == "verifyTitle") {	
			 app.verifyTitle(object, data);
		
		}
		
	/*	app.openBrowser();
		app.navigateToURL("url");
		app.clearAction("originCity_id");
		app.sendKeysAction("originCity_id", originCity);
		app.clearAction("destinationCity_id");
		app.sendKeysAction("destinationCity_id", destinationCity);
		app.clearAction("departureDate_id");
		app.sendKeysAction("departureDate_id", departureDate);
		app.clickAction("CalenderClose_xpath");
		app.clearAction("returnDate_id");
		app.sendKeysAction("returnDate_id", returnDate);
		app.clickAction("CalenderClose_xpath");
		app.sendKeysAction("paxCount_id", passenger);
		app.clickAction("findFlightsSubmit_id"); 
		app.getTextAction("paginationText_xpath");  
		app.getFlightNumbers(paginationText);   */
		
		
		
		
	}
	
} 
		} 
	}
