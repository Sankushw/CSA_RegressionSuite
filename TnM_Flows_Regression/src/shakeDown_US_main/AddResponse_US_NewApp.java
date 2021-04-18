
package shakeDown_US_main;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import lib.Excel;
import shakeDown_US.Step02_AddResponse_US_NewApp;


public class AddResponse_US_NewApp
{
	// TestNG logger


	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_shakeDown.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;
	public String id_green;
	public String paswd_green;
	public String url_green;
	public String url2;

	public WebDriver driver;


	@BeforeClass
	public void setup() throws AWTException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		//to display  browser logs in console
        ((RemoteWebDriver) driver).setLogLevel(Level.INFO);
        
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		driver.get(url);  
		driver.manage().window().maximize();
	
	}	


	// Test to add response
	@Test(priority=0)
	public void add_response() throws IOException, InterruptedException
	{


		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.login();
		addresp.openRequest();
		

		for (int i=1; i<2; i++)
		{
			if(i==1)
			{

				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "10", "10");
			}  
			else if(i==2)
			{

				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "10", "10");
			}
			else
			{

				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "10", "10");

			}
		}
		addresp.Submit();
	}

	@Test(priority=1)
	public void Withdraw() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.WithdrawResponse();

	}

	@Test(priority=2)
	public void Reactivate() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.ReactivateResponse();
	}
	
	@Test(priority=3)
	public void Resubmit() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.EditReactivatedResponse("20");
		addresp.Submit();
	}
	@Test(priority=4)
	public void Reprice() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.Reprice("30");
		
	}
	
	@AfterClass
	public void Quit()
	{
		driver.close();
	}
}


