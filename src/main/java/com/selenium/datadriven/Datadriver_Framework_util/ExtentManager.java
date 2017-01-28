package com.selenium.datadriven.Datadriver_Framework_util;

import java.io.File;


import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;


public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		
		if(extent==null){
			
			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports("C:\\Users\\370313\\Documents\\ZohotTesting\\ZohoTesting\\DataDriven_Framework\\Datadriven_Framework_MVN\\Reports\\"+fileName, true, DisplayOrder.NEWEST_FIRST);
			extent.loadConfig(new File("C:\\Users\\370313\\Documents\\ZohotTesting\\ZohoTesting\\DataDriven_Framework\\Datadriven_Framework_MVN\\ReportsConfig.xml"));
			
			//Optional
			extent.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo("Environment", "QA");
			
		}
		
		return extent;		
		
	}
	
}
