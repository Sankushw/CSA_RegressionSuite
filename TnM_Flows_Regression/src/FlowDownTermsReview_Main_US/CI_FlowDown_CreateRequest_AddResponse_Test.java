package FlowDownTermsReview_Main_US;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FlowDownTermsReview_US.Step01_CreateRequest_US;
import FlowDownTermsReview_US.Step02_AddResponse_US_NewApp;
import gPSG_DE.Step02_AddResponse_DE_NewApp;
import lib.Excel;

@Listeners(generics.TestNG_Listeners.class)
public class CI_FlowDown_CreateRequest_AddResponse_Test
{

	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_flowDown.xls";
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

		//System.setProperty("webdriver.chrome.driver", "System.getProperty("user.dir")  +"\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		driver.get(url);   
		driver.manage().window().maximize();

	}	

	// test to Login to the application as RIPC
	@Test(priority=0)
	public void T1_RIPC_Login() throws InterruptedException 
	{

		Step01_CreateRequest_US login = new Step01_CreateRequest_US(driver);
		login.login();
	}


	@Test(dependsOnMethods="T1_RIPC_Login")
	public void T2_FlowDown_Y_Create_Req() 
	{

		Step01_CreateRequest_US createreq = new Step01_CreateRequest_US(driver);
		createreq.Create_New_Request();
	}
	@Test(dependsOnMethods="T2_FlowDown_Y_Create_Req")
	public void T3_FlowDown_Y_Select_ReqType() 
	{

		Step01_CreateRequest_US reqType = new Step01_CreateRequest_US(driver);
		reqType.Skill_Request();
	}
	@Test(dependsOnMethods="T3_FlowDown_Y_Select_ReqType")
	public void T4_FlowDown_Y_select_Requester() 
	{

		Step01_CreateRequest_US selectreq = new Step01_CreateRequest_US(driver);
		selectreq.Select_Requestor();
	}

	@Test(dependsOnMethods="T4_FlowDown_Y_select_Requester")
	public void T5_FlowDown_Y_select_jrss() 
	{

		Step01_CreateRequest_US selectjr = new Step01_CreateRequest_US(driver);
		selectjr.Select_JRSS();
	}

	@Test(dependsOnMethods="T5_FlowDown_Y_select_jrss")
	public void T6_FlowDown_Y_req_detail() 
	{
		String projectName=(Excel.getCellValue(xlsFilePath, "Request_creation", 2, 4));
		Step01_CreateRequest_US reqdetail = new Step01_CreateRequest_US(driver);
		reqdetail.Request_detailpage(projectName);
	}
	@Test(dependsOnMethods="T6_FlowDown_Y_req_detail")
	public void T7_FlowDown_Y_skill_loc() 
	{

		Step01_CreateRequest_US skillloc = new Step01_CreateRequest_US(driver);
		skillloc.Skill_detailLocationpage("Yes");
	}

	@Test(dependsOnMethods="T7_FlowDown_Y_skill_loc")
	public void T8_FlowDown_Y_skill_price() 
	{

		Step01_CreateRequest_US skillprice = new Step01_CreateRequest_US(driver);
		skillprice.Skill_detail_skillpricepage();
	}
	@Test(dependsOnMethods="T8_FlowDown_Y_skill_price")
	public void T9_FlowDown_Y_supp() throws InterruptedException 
	{

		Step01_CreateRequest_US supplier = new Step01_CreateRequest_US(driver);
		String RequestNumber=supplier.SupplierSelectionPage();
		Excel.setCellValue(xlsFilePath, "Request_creation", 2, 15, RequestNumber );
	}

	@Test(dependsOnMethods="T9_FlowDown_Y_supp")
	public void T10_ClickOnHomePage() 
	{
		Step01_CreateRequest_US home = new Step01_CreateRequest_US(driver);
		home.ClickHomePage();
	}

	@Test(dependsOnMethods="T10_ClickOnHomePage")
	public void T11_FlowDown_N_Create_Req() 
	{

		Step01_CreateRequest_US createreq = new Step01_CreateRequest_US(driver);
		createreq.Create_New_Request();
	}
	@Test(dependsOnMethods="T11_FlowDown_N_Create_Req")
	public void T12_FlowDown_N_Select_ReqType() 
	{

		Step01_CreateRequest_US reqType = new Step01_CreateRequest_US(driver);
		reqType.Skill_Request();
	}
	@Test(dependsOnMethods="T12_FlowDown_N_Select_ReqType")
	public void T13_FlowDown_N_select_Requester() 
	{

		Step01_CreateRequest_US selectreq = new Step01_CreateRequest_US(driver);
		selectreq.Select_Requestor();
	}

	@Test(dependsOnMethods="T13_FlowDown_N_select_Requester")
	public void T14_FlowDown_N_select_jrss() 
	{

		Step01_CreateRequest_US selectjr = new Step01_CreateRequest_US(driver);
		selectjr.Select_JRSS();
	}

	@Test(dependsOnMethods="T14_FlowDown_N_select_jrss")
	public void T15_FlowDown_N_req_detail() 
	{
		String projectName=(Excel.getCellValue(xlsFilePath, "Request_creation", 6, 4));
		Step01_CreateRequest_US reqdetail = new Step01_CreateRequest_US(driver);
		reqdetail.Request_detailpage(projectName);
	}
	@Test(dependsOnMethods="T15_FlowDown_N_req_detail")
	public void T16_FlowDown_N_skill_loc() 
	{

		Step01_CreateRequest_US skillloc = new Step01_CreateRequest_US(driver);
		skillloc.Skill_detailLocationpage("No");
	}

	@Test(dependsOnMethods="T16_FlowDown_N_skill_loc")
	public void T17_FlowDown_N_skill_price() 
	{

		Step01_CreateRequest_US skillprice = new Step01_CreateRequest_US(driver);
		skillprice.Skill_detail_skillpricepage();
	}
	@Test(dependsOnMethods="T17_FlowDown_N_skill_price")
	public void T18_FlowDown_N_supp() throws InterruptedException 
	{

		Step01_CreateRequest_US supplier = new Step01_CreateRequest_US(driver);
		String RequestNumber=supplier.SupplierSelectionPage();
		Excel.setCellValue(xlsFilePath, "Request_creation", 6, 15, RequestNumber );
	}
	@Test(dependsOnMethods="T18_FlowDown_N_supp")
	public void T19_OpenNewGreen() 
	{

		url1 = Excel.getCellValue(xlsFilePath, sheet, 2, 2);
		driver.navigate().to(url1);
	}


	@Test(dependsOnMethods="T19_OpenNewGreen")
	public void T20_SupplierLogin()
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.login();
	}
	@Test(dependsOnMethods="T20_SupplierLogin")
	public void T21_OpenRequest_FlowDown_Yes_Case() throws InterruptedException
	{
		String FlowDown_Y_ReqId=Excel.getCellValue(xlsFilePath, "Request_creation", 2, 15);
		Step02_AddResponse_US_NewApp open = new Step02_AddResponse_US_NewApp(driver); 
		open.openRequest(FlowDown_Y_ReqId);
	}
	@Test(dependsOnMethods="T21_OpenRequest_FlowDown_Yes_Case")
	public void T22_MinimizeSurveyPopUp() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp survey = new Step02_AddResponse_US_NewApp(driver); 
		survey.SurveyPopUp_Check();
	}
	// Test to add response
	@Test(dependsOnMethods="T22_MinimizeSurveyPopUp")
	public void T23_add_responses_FlowDown_Yes_Case() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		for (int i=1; i<3; i++)
		{
			if(i==1)
			{
				System.out.println("adding 1st candidate response");
				addresp.FillResp("Fname_"+i, "Lname_"+i, "United States", "Yes", "20", "20");
			}  
			else
			{
				System.out.println("adding 2nd candidate response");
				addresp.FillResp("Fname_"+i, "Lname_"+i, "United States", "No", "20", "20");
			}
		}
	}
	@Test(dependsOnMethods="T23_add_responses_FlowDown_Yes_Case")
	public void T24_SubmitResponses_FlowDown_Yes_Case() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp sub = new Step02_AddResponse_US_NewApp(driver); 
		sub.Submit();
	}
	@Test(dependsOnMethods="T24_SubmitResponses_FlowDown_Yes_Case")
	public void T25_MoveToDashBoardPage() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp dash = new Step02_AddResponse_US_NewApp(driver); 
		dash.MoveToDashboard();
	}
	@Test(dependsOnMethods="T25_MoveToDashBoardPage")
	public void T26_OpenRequest_FlowDown_No_Case() throws InterruptedException
	{
		String FlowDown_N_ReqId=Excel.getCellValue(xlsFilePath, "Request_creation", 6, 15);
		Step02_AddResponse_US_NewApp open = new Step02_AddResponse_US_NewApp(driver); 
		open.openRequest(FlowDown_N_ReqId);
	}
	@Test(dependsOnMethods="T26_OpenRequest_FlowDown_No_Case")
	public void T27_add_responses_FlowDown_No_Case() throws InterruptedException
	{
		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		for (int i=1; i<3; i++)
		{
			if(i==1)
			{
				System.out.println("adding 1st candidate response");
				addresp.FillResp("Fname_"+i, "Lname_"+i, "United States", "Yes", "20", "20");
			}  
			else
			{
				System.out.println("adding 2nd candidate response");
				addresp.FillResp("Fname_"+i, "Lname_"+i, "United States", "No", "20", "20");
			}
		}
	}
	@Test(dependsOnMethods="T27_add_responses_FlowDown_No_Case")
	public void T28_SubmitResponses_FlowDown_No_Case() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp sub = new Step02_AddResponse_US_NewApp(driver); 
		sub.Submit();
	}
	@AfterTest
	public void Close()
	{
		driver.quit();
	}
}

