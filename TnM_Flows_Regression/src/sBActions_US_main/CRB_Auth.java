package sBActions_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import lib.Excel;
import sBActions_US.Step07_CRB_Auth;


public class CRB_Auth
{
	// TestNG logger
	
				
		public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
		public String sheet="Login"; 
		public String url;
		public String id;
		public String paswd;
		
		WebDriver driver;
		
		
		@BeforeTest
	    public void setup()
		{
			 System.setProperty("webdriver.gecko.driver", "C:\\Users\\IBM_ADMIN\\Desktop\\Selenium\\Jar files for Selenium\\geckodriver-v0.19.1-win64\\geckodriver.exe");
				
				//ProfilesIni ini = new ProfilesIni();
		    	//FirefoxProfile profile = ini.getProfile("default");
		    	//WebDriver driver =  new FirefoxDriver(profile);
					
		    	  driver = new FirefoxDriver();
			
				//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
			    //driver = new ChromeDriver();
			    	
		        //id = Excel.getCellValue(xlsFilePath, sheet, 3, 0);
		   	    //paswd = Excel.getCellValue(xlsFilePath, sheet, 3, 1);
		     	url = Excel.getCellValue(xlsFilePath, sheet, 3, 2);
		    	
		    	//String url1 = "https://" +  id + ":" + paswd + "@" + url;
		    	      
		        driver.get(url);    
	  }	
		
		 // test to Login to the application as RIPC
		  
		 @Test(priority=0)
		  public void CRB_login() 
		  {
			  
			  Step07_CRB_Auth login = new Step07_CRB_Auth(driver);
			  login.login();
		  }
		  @Test(priority=1)
		  public void CRB_auth() 
		  {
			  
			  Step07_CRB_Auth crbauth = new Step07_CRB_Auth(driver);
			   crbauth.CRB_Auth();
		  }
		 
		 
			 
}
		  
	
