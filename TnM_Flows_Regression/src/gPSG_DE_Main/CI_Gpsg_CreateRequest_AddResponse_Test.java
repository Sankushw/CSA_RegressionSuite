package gPSG_DE_Main;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import gPSG_DE.Step01_CreateRequest_DE;
import gPSG_DE.Step02_AddResponse_DE_NewApp;
import lib.Excel;

@Listeners(generics.TestNG_Listeners.class)
public class CI_Gpsg_CreateRequest_AddResponse_Test
{

	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_gpsg.xls";
	public String sheet="Login"; 
	public String url;
	public String url1;
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
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")  +"\\geckodriver.exe");
		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(false);
		driver=new FirefoxDriver(op);
		//to display  browser logs in console
		//((RemoteWebDriver) driver).setLogLevel(Level.INFO);
        
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")  +"\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 12, 2);
		driver.get(url);   
		driver.manage().window().maximize();
		System.out.println("Starting execution of GPSG flow");
	}	

	// test to Login to the application as RIPC
	@Test(priority=0)
	public void T1_RIPC_Login() throws InterruptedException 
	{

		Step01_CreateRequest_DE login = new Step01_CreateRequest_DE(driver);
		login.login();
	}

	
	@Test(dependsOnMethods="T1_RIPC_Login")
	public void T2_SameCurr_Create_Req() 
	{

		Step01_CreateRequest_DE createreq = new Step01_CreateRequest_DE(driver);
		createreq.Create_New_Request();
	}
	@Test(dependsOnMethods="T2_SameCurr_Create_Req")
	public void T3_SameCurr_Select_ReqType() 
	{

		Step01_CreateRequest_DE reqType = new Step01_CreateRequest_DE(driver);
		reqType.Skill_Request();
	}
	@Test(dependsOnMethods="T3_SameCurr_Select_ReqType")
	public void T4_SameCurr_select_Requester() 
	{

		Step01_CreateRequest_DE selectreq = new Step01_CreateRequest_DE(driver);
		selectreq.Select_Requestor();
	}

	@Test(dependsOnMethods="T4_SameCurr_select_Requester")
	public void T5_SameCurr_select_jrss() 
	{

		Step01_CreateRequest_DE selectjr = new Step01_CreateRequest_DE(driver);
		selectjr.Select_JRSS();
	}

	@Test(dependsOnMethods="T5_SameCurr_select_jrss")
	public void T6_SameCurr_req_detail() 
	{
		String projectName=(Excel.getCellValue(xlsFilePath, "Request_creation", 4, 4));
		Step01_CreateRequest_DE reqdetail = new Step01_CreateRequest_DE(driver);
		reqdetail.Request_detailpage(projectName);
	}
	@Test(dependsOnMethods="T6_SameCurr_req_detail")
	public void T7_SameCurr_skill_loc() 
	{

		Step01_CreateRequest_DE skillloc = new Step01_CreateRequest_DE(driver);
		skillloc.Skill_detailLocationpage();
	}

	@Test(dependsOnMethods="T7_SameCurr_skill_loc")
	public void T8_SameCurr_skill_price() 
	{

		Step01_CreateRequest_DE skillprice = new Step01_CreateRequest_DE(driver);
		skillprice.Skill_detail_skillpricepage("EUR",  "IBA Group (CZ) € off-shore Rates");
	}
	@Test(dependsOnMethods="T8_SameCurr_skill_price")
	public void T9_SameCurr_supp() throws InterruptedException 
	{

		Step01_CreateRequest_DE supplier = new Step01_CreateRequest_DE(driver);
		String RequestNumber=supplier.SupplierSelectionPage();
		Excel.setCellValue(xlsFilePath, "Request_creation", 4, 15, RequestNumber );
	}
	
	@Test(dependsOnMethods="T9_SameCurr_supp")
	public void T10_ClickOnHomePage() 
	{
		Step01_CreateRequest_DE home = new Step01_CreateRequest_DE(driver);
		home.ClickHomePage();
	}
	
	@Test(dependsOnMethods="T10_ClickOnHomePage")
	public void T11_DiffCurr_Create_Req() 
	{

		Step01_CreateRequest_DE createreq = new Step01_CreateRequest_DE(driver);
		createreq.Create_New_Request();
	}
	@Test(dependsOnMethods="T11_DiffCurr_Create_Req")
	public void T12_DiffCurr_Select_ReqType() 
	{

		Step01_CreateRequest_DE reqType = new Step01_CreateRequest_DE(driver);
		reqType.Skill_Request();
	}
	@Test(dependsOnMethods="T12_DiffCurr_Select_ReqType")
	public void T13_DiffCurr_select_Requester() 
	{

		Step01_CreateRequest_DE selectreq = new Step01_CreateRequest_DE(driver);
		selectreq.Select_Requestor();
	}

	@Test(dependsOnMethods="T13_DiffCurr_select_Requester")
	public void T14_DiffCurr_select_jrss() 
	{

		Step01_CreateRequest_DE selectjr = new Step01_CreateRequest_DE(driver);
		selectjr.Select_JRSS();
	}

	@Test(dependsOnMethods="T14_DiffCurr_select_jrss")
	public void T15_DiffCurr_req_detail() 
	{
		String projectName=(Excel.getCellValue(xlsFilePath, "Request_creation", 8, 4));
		Step01_CreateRequest_DE reqdetail = new Step01_CreateRequest_DE(driver);
		reqdetail.Request_detailpage(projectName);
	}
	@Test(dependsOnMethods="T15_DiffCurr_req_detail")
	public void T16_DiffCurr_skill_loc() 
	{

		Step01_CreateRequest_DE skillloc = new Step01_CreateRequest_DE(driver);
		skillloc.Skill_detailLocationpage();
	}

	@Test(dependsOnMethods="T16_DiffCurr_skill_loc")
	public void T17_DiffCurr_skill_price() 
	{

		Step01_CreateRequest_DE skillprice = new Step01_CreateRequest_DE(driver);
		skillprice.Skill_detail_skillpricepage("USD",  "IBA Group (CZ) $ off-shore Rates");
	}
	@Test(dependsOnMethods="T17_DiffCurr_skill_price")
	public void T18_DiffCurr_supp() throws InterruptedException 
	{

		Step01_CreateRequest_DE supplier = new Step01_CreateRequest_DE(driver);
		String RequestNumber=supplier.SupplierSelectionPage();
		Excel.setCellValue(xlsFilePath, "Request_creation", 8, 15, RequestNumber );
	}
	@Test(dependsOnMethods="T18_DiffCurr_supp")
	public void T19_OpenNewGreen() 
	{

		url1 = Excel.getCellValue(xlsFilePath, sheet, 14, 2);
		driver.navigate().to(url1);
	}


	@Test(dependsOnMethods="T19_OpenNewGreen")
	public void T20_SupplierLogin()
	{

		Step02_AddResponse_DE_NewApp addresp = new Step02_AddResponse_DE_NewApp(driver); 
		addresp.login();
	}
	
	// Test to add response
	@Test(dependsOnMethods="T20_SupplierLogin")
	public void T21_add_response() throws InterruptedException
	{

		Step02_AddResponse_DE_NewApp addresp = new Step02_AddResponse_DE_NewApp(driver); 

		for (int i=1; i<3; i++)
		{
			String GpsgSameCurr_ReqId=Excel.getCellValue(xlsFilePath, "Request_creation", 4, 15);
			String GpsgDiffCurr_ReqId=Excel.getCellValue(xlsFilePath, "Request_creation", 8, 15);
		
			if(i==1)
			{
				addresp.openRequest(GpsgSameCurr_ReqId);
				addresp.SurveyPopUp_Check();
				addresp.FillResp_Details("GpsgSameCurr_R1", "10");
				addresp.Submit();
				addresp.MoveToDashboard();
			}  

			else
			{
				addresp.openRequest(GpsgDiffCurr_ReqId);
				addresp.FillResp_Details("GpsgDiffCurr_R1", "12");
				addresp.Submit();

			}
		}

}
	@AfterTest
	public void Close()
	{
		driver.quit();
	}
}

