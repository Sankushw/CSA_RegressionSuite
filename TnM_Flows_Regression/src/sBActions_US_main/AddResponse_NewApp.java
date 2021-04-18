
package sBActions_US_main;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.java.swing.plaf.windows.resources.windows_zh_TW;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import lib.Excel;
import sBActions_US.Step02_AddResponse;
import sBActions_US.Step02_AddResponse_NewApp;
import sBActions_US.Step03_Finalize;


public class AddResponse_NewApp
{
	// TestNG logger

	
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	//public static String xlsFilePath ="C:\\Users\\UdayKotipalli\\Downloads\\SBActions_core\\SBActions_core\\src\\testdata\\testdata.xls";
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
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\UdayKotipalli\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");

		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(true);
		driver=new FirefoxDriver(op);
		
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\UdayKotipalli\\Downloads\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		
		url = Excel.getCellValue(xlsFilePath ,sheet, 21, 2);
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);  
		driver.manage().window().maximize();

	}	



	// Test to add response
	@Test(priority=0)
	public void add_response() throws IOException, InterruptedException
	{
		
		Step02_AddResponse_NewApp addresp = new Step02_AddResponse_NewApp(driver); 
		
		addresp.login();
		
		addresp.openRequest();
		
		
		for (int i=1; i<4; i++)
		{
			if(i==1)
			{  

				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "100", "10");
			}  
			else if(i==2)
			{
				//addresp.login();
				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "120", "10");
			}
			else
			{
				//addresp.login();
				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "150", "10");

			}
		}
		addresp.Submit();
	}
}




