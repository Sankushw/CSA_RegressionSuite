package expOActions_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import expOActions_US.Step01_CreateRequest;
import expOActions_US.Step02_AddResponse_NewApp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import lib.Excel;

@Listeners(generics.TestNG_Listeners.class)
public class CI_Exp_CreateReq_AddResponses_Test
{

	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_expActions.xls";
	public String sheet="Login"; 
	public String url;
	public String url1;
	public String id;
	public String paswd;

	public WebDriver driver;


	@BeforeTest
	public void setup()
	{

		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")  +"\\geckodriver.exe");
		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(false);
		driver=new FirefoxDriver(op);
		//to display  browser logs in console
		//((RemoteWebDriver) driver).setLogLevel(Level.INFO);

		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		driver.get(url);   
		driver.manage().window().maximize();
		System.out.println("Starting execution of EXP flow");
	}	

	// test to Login to the application as RIPC
	@Test(priority=0)
	public void T1_RIPC_Login() throws InterruptedException 
	{

		Step01_CreateRequest login = new Step01_CreateRequest(driver);
		login.login();
	}
	@Test(dependsOnMethods="T1_RIPC_Login")
	public void T2_Create_Req() 
	{

		Step01_CreateRequest createreq = new Step01_CreateRequest(driver);
		createreq.Create_New_Request();
	}
	@Test(dependsOnMethods="T2_Create_Req")
	public void T3_skill_Req() 
	{

		Step01_CreateRequest skillreq = new Step01_CreateRequest(driver);
		skillreq.Skill_Request();
	}
	@Test(dependsOnMethods="T3_skill_Req")
	public void T4_select_Req() 
	{

		Step01_CreateRequest selectreq = new Step01_CreateRequest(driver);
		selectreq.Select_Requestor();
	}

	@Test(dependsOnMethods="T4_select_Req")
	public void T5_select_jrss() 
	{

		Step01_CreateRequest selectreq = new Step01_CreateRequest(driver);
		selectreq.Select_JRSS();
	}

	@Test(dependsOnMethods="T5_select_jrss")
	public void T6_req_detail() 
	{

		Step01_CreateRequest reqdetail = new Step01_CreateRequest(driver);
		reqdetail.Request_detailpage();
	}
	@Test(dependsOnMethods="T6_req_detail")
	public void T7_skill_loc() 
	{

		Step01_CreateRequest skillloc = new Step01_CreateRequest(driver);
		skillloc.Skill_detailLocationpage();
	}
	@Test(dependsOnMethods="T7_skill_loc")
	public void T8_alert_check() 
	{

		Step01_CreateRequest alertchk = new Step01_CreateRequest(driver);
		alertchk.isAlertPresent();
	}
	@Test(dependsOnMethods="T8_alert_check")
	public void T9_skill_price() 
	{

		Step01_CreateRequest skillprice = new Step01_CreateRequest(driver);
		skillprice.Skill_detail_skillpricepage();
	}
	@Test(dependsOnMethods="T9_skill_price")
	public void T10_supp() throws InterruptedException 
	{

		Step01_CreateRequest supplier = new Step01_CreateRequest(driver);
		supplier.SupplierSelectionPage();
	}

	@Test(dependsOnMethods="T10_supp")
	public void T11_OpenNewGreenApp() 
	{

		url1 = Excel.getCellValue(xlsFilePath, sheet, 23, 2);
		driver.navigate().to(url1);
	}

	@Test(dependsOnMethods="T11_OpenNewGreenApp")
	public void T12_Login_Green() 
	{

		Step02_AddResponse_NewApp log = new Step02_AddResponse_NewApp(driver); 
		log.login();

	}
	@Test(dependsOnMethods="T12_Login_Green")
	public void T13_OpenRequest() throws InterruptedException
	{

		Step02_AddResponse_NewApp openReq = new Step02_AddResponse_NewApp(driver); 
		openReq.openRequest();

	}
	// Test to add response
	@Test(dependsOnMethods="T13_OpenRequest")
	public void T14_add_response() throws IOException, InterruptedException
	{

		Step02_AddResponse_NewApp addresp = new Step02_AddResponse_NewApp(driver); 

		for (int i=1; i<4; i++)
		{
			if(i==1)
			{

				addresp.FillRespdetails("fResp"+i , "Canada" , "lResp"+i , "Canada" , "30", "30");
			}  
			else if(i==2)
			{

				addresp.FillRespdetails("fResp"+i ,  "Cuba" , "lResp"+i , "Cuba" ,  "30", "30");
			}
			else if(i==3)
			{

				addresp.FillRespdetails("fResp"+i , "Armenia" , "lResp"+i, "Armenia" , "30", "30");

			}
			else
			{

				addresp.FillRespdetails("fResp"+i , "Canada" , "lResp"+i, "Canada" , "30", "30");


			}
		}


	}

	@Test(dependsOnMethods="T14_add_response")
	public void T15_SubmitResponseToRequester() throws InterruptedException
	{

		Step02_AddResponse_NewApp sub = new Step02_AddResponse_NewApp(driver); 
		sub.Submit();

	}

	@AfterTest
	public void Close()
	{
		driver.quit();
	}
}






