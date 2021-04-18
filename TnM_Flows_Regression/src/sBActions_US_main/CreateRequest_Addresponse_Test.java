package sBActions_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import lib.Excel;
import sBActions_US.Step01_CreateRequest;
import sBActions_US.Step02_AddResponse_NewApp;

@Listeners(generics.TestNG_Listeners.class)
public class CreateRequest_Addresponse_Test
{
	// TestNG logger

	//public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_sbActions.xls";
	public String sheet="Login"; 
	public String url;
	public String url1;
	public String id;
	public String paswd;
	public WebDriver driver;


	@BeforeTest
	public void setup() throws ClassNotFoundException, SQLException
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\UdayKotipalli\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		
		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(true);
		driver=new FirefoxDriver(op);
		
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);  
		driver.manage().window().maximize();
		}	
	
	@Test
	public void T1_RIPC_Login() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
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
	@Test(dependsOnMethods="T3_skill_Req")
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
	public void T5_select_jrss() throws InterruptedException 
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
	public void T10_supp() 
	{

		Step01_CreateRequest supplier = new Step01_CreateRequest(driver);
		supplier.SupplierSelectionPage();
	} 
	
	@Test(dependsOnMethods="T10_supp")
	public void T11_OpenNewGreen()
	{
		url1 = Excel.getCellValue(xlsFilePath, sheet, 21, 2);
		driver.navigate().to(url1);
	}

	
	// Test to add response
		@Test(dependsOnMethods="T11_OpenNewGreen")
		public void T12_add_response() throws IOException, InterruptedException
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


