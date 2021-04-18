package shakeDown_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import lib.Excel;
import shakeDown_US.Step01_CreateRequest;
import shakeDown_US.Step02_AddResponse_US_NewApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@Listeners(generics.TestNG_Listeners.class)
public class Shakedown_CreateReq_AddResp_Test
{

	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_shakeDown.xls";
	public String sheet="Login"; 
	public String url;
	public String url1;
	public String id;
	public String paswd;


	public WebDriver driver;

	@BeforeTest
	public void setup() throws ClassNotFoundException, SQLException
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"/geckodriver.exe");
		// to run Firefox in Headless mode
		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(false);
		driver=new FirefoxDriver(op);

		//here
		//((RemoteWebDriver) driver).setLogLevel(Level.INFO);

		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 4, 2);
		driver.get(url);
		driver.manage().window().maximize();
		System.out.println("Starting execution of Shakedown flow");
	}	

	// test to Login to the application as RIPC
	@Test
	public void T1_RIPC_Login() throws InterruptedException 
	{

		Step01_CreateRequest login = new Step01_CreateRequest(driver);
		
		try {
			login.login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	@Test(priority=0,dependsOnMethods="T3_skill_Req")
	public void T4_select_Req() 
	{

		Step01_CreateRequest selectreq = new Step01_CreateRequest(driver);
		try {
			selectreq.Select_Requestor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void T9_skill_price() throws InterruptedException 
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
	public void T11_OpenNewGreen() 
	{

		url1 = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		driver.navigate().to(url1);
	}
	@Test(dependsOnMethods="T11_OpenNewGreen")
	public void T12_SupplierLogin()
	{

		Step02_AddResponse_US_NewApp log = new Step02_AddResponse_US_NewApp(driver); 
		log.login();
		
	}
	@Test(dependsOnMethods="T12_SupplierLogin")
	public void T13_OpenTheRequest() throws IOException, InterruptedException
	{

		Step02_AddResponse_US_NewApp open = new Step02_AddResponse_US_NewApp(driver); 
		open.openRequest();
	}
	@Test(dependsOnMethods="T13_OpenTheRequest")
	public void T14_add_response() throws IOException, InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 

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

	@Test(dependsOnMethods="T14_add_response")
	public void T15_Withdraw() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.WithdrawResponse();

	}

	@Test(dependsOnMethods="T15_Withdraw")
	public void T16_Reactivate() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.ReactivateResponse();
	}

	@Test(dependsOnMethods="T16_Reactivate")
	public void T17_Resubmit() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.EditReactivatedResponse("20");
		addresp.Submit();
	}
	@Test(dependsOnMethods="T17_Resubmit")
	public void T18_Reprice() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.Reprice("30");

	}
	@AfterTest
	public void Close()
	{
		driver.quit();
	}

}


