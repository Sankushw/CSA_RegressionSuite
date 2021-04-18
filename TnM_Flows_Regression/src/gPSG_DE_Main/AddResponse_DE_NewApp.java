package gPSG_DE_Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import gPSG_DE.Step02_AddResponse_DE_NewApp;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import lib.Excel;


public class AddResponse_DE_NewApp
{

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata_gpsg.xls";
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
	public void setup() throws AWTException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")  +"\\geckodriver.exe");
		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(false);
		driver=new FirefoxDriver(op);
		//to display  browser logs in console
		((RemoteWebDriver) driver).setLogLevel(Level.INFO);
        
		//System.setProperty("webdriver.chrome.driver", "System.getProperty("user.dir")  +"\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 14, 2);
		driver.get(url);  
		driver.manage().window().maximize();
	
	}	
	@Test(priority=0)
	public void SupplierLogin()
	{

		Step02_AddResponse_DE_NewApp addresp = new Step02_AddResponse_DE_NewApp(driver); 
		addresp.login();
	}
	
	// Test to add response
	@Test(priority=1)
	public void add_response_Default() throws InterruptedException
	{

		Step02_AddResponse_DE_NewApp addresp = new Step02_AddResponse_DE_NewApp(driver); 

		for (int i=1; i<3; i++)
		{
			String GpsgSameCurr_ReqId=Excel.getCellValue(xlsFilePath, "Request_creation", 4, 15);
			String GpsgDiffCurr_ReqId=Excel.getCellValue(xlsFilePath, "Request_creation", 8, 15);
		
			if(i==1)
			{
				addresp.openRequest(GpsgSameCurr_ReqId);
				addresp.FillResp_Details("GpsgSameCurr_R1"+i, "10");
				addresp.MoveToDashboard();
			}  

			else
			{
				addresp.openRequest(GpsgDiffCurr_ReqId);
				addresp.FillResp_Details("GpsgDiffCurr_R1"+i, "12");
				addresp.Submit();

			}
		}



	

	}
}