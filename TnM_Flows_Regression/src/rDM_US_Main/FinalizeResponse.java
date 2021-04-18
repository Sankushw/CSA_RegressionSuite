package rDM_US_Main;

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
import rDM_US.Step01_CreateRequest_US;
import rDM_US.Step03_AddResponse;
import rDM_US.Step04_Finalize;


public class FinalizeResponse
{
	// TestNG logger
	
				
		public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
		public String sheet="Login"; 
		public String url;
		public String id;
		public String paswd;
		public String id_green;
		public String paswd_green;
		public String url_green;
		public String url2;
		
		public WebDriver driver;
		
		
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
			    	
		        //id = Excel.getCellValue(xlsFilePath, sheet, 23, 0);
		   	    //paswd = Excel.getCellValue(xlsFilePath, sheet, 23, 1);
		     	url = Excel.getCellValue(xlsFilePath, sheet, 3, 2);
		    	
		    	//String url1 = "https://" +  id + ":" + paswd + "@" + url;
		    	      
		        driver.get(url);      
	  }	
		
		
        //Test for Skill Request page
		  @Test(priority=0)
		  public void login()
		  {
			 
			  Step04_Finalize log = new Step04_Finalize(driver);
			  log.loginAndOpenReq();
			  
			  
				  
		  }
		  @Test(priority=1)
		  public void Finalize()
		  {
			 
			  Step04_Finalize respfinal = new Step04_Finalize(driver);
		
			  respfinal.FinaliseSubmit();
			  
				  
		  }
		
}
