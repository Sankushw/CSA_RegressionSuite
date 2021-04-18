package rDM_US_Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lib.Excel;
import rDM_US.Step01_CreateRequest_US;
import rDM_US.Step02_Asssign_RDM_Role_US;
import rDM_US.Step03_AddResponse;
import rDM_US.Step03_AddResponse_New_app;
import rDM_US.Step04_Finalize;


public class Add_reponse_New_app
{
	
	    // TestNG logger

		

		public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata_rdm.xls";
		public String sheet="Login"; 
		public String url;
		public String id;
		public String paswd;


		public WebDriver driver;


		@BeforeTest
		public void setup()
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\geckodriver.exe");
			FirefoxOptions op=new FirefoxOptions();
			op.setHeadless(true);
			driver=new FirefoxDriver(op);
			
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\chromedriver.exe");
			//driver = new ChromeDriver();

			
			url = Excel.getCellValue(xlsFilePath, sheet, 26, 2);

			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(url);    
		}	

		


		@Test(priority=11)
		public void add_response() throws IOException, InterruptedException
		{

			

			Step03_AddResponse_New_app addresp = new Step03_AddResponse_New_app(driver); 
			
			addresp.login();
			
			addresp.openRequest();
						
			for (int i=1; i<2; i++)
			{
				if(i==1)
				{

					addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i , "United States" , "30", "10");
					addresp.Submit();
				}  


				else
					
				{

					addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "United States" , "40", "10");

				}
			}
		
	  }
		
}
