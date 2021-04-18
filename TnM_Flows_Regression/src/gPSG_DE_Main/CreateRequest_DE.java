package gPSG_DE_Main;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import gPSG_DE.Step01_CreateRequest_DE;
import lib.Excel;


public class CreateRequest_DE
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
	public void setup()
	{
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")  +"\\geckodriver.exe");
		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(false);
		driver=new FirefoxDriver(op);
		//to display  browser logs in console
		((RemoteWebDriver) driver).setLogLevel(Level.INFO);
        
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")  +"\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 12, 2);
		driver.get(url);   
		driver.manage().window().maximize();
	}	

	// test to Login to the application as RIPC
	@Test(priority=0)
	public void RIPC_Login() throws InterruptedException 
	{

		Step01_CreateRequest_DE login = new Step01_CreateRequest_DE(driver);
		login.login();
	}

	//same currency request flow
	@Test(priority=1)
	public void SameCurr_Create_Req() 
	{

		Step01_CreateRequest_DE createreq = new Step01_CreateRequest_DE(driver);
		createreq.Create_New_Request();
	}
	@Test(priority=2)
	public void SameCurr_Select_ReqType() 
	{

		Step01_CreateRequest_DE reqType = new Step01_CreateRequest_DE(driver);
		reqType.Skill_Request();
	}
	@Test(priority=3)
	public void SameCurr_select_Requester() 
	{

		Step01_CreateRequest_DE selectreq = new Step01_CreateRequest_DE(driver);
		selectreq.Select_Requestor();
	}

	@Test(priority=4)
	public void SameCurr_select_jrss() 
	{

		Step01_CreateRequest_DE selectjr = new Step01_CreateRequest_DE(driver);
		selectjr.Select_JRSS();
	}

	@Test(priority=5)
	public void SameCurr_req_detail() 
	{
		String projectName=(Excel.getCellValue(xlsFilePath, "Request_creation", 4, 4));
		Step01_CreateRequest_DE reqdetail = new Step01_CreateRequest_DE(driver);
		reqdetail.Request_detailpage(projectName);
	}
	@Test(priority=6)
	public void SameCurr_skill_loc() 
	{

		Step01_CreateRequest_DE skillloc = new Step01_CreateRequest_DE(driver);
		skillloc.Skill_detailLocationpage();
	}

	@Test(priority=7)
	public void SameCurr_skill_price() 
	{

		Step01_CreateRequest_DE skillprice = new Step01_CreateRequest_DE(driver);
		skillprice.Skill_detail_skillpricepage("EUR",  "IBA Group (CZ) € off-shore Rates");
	}
	@Test(priority=9)
	public void SameCurr_supp() throws InterruptedException 
	{

		Step01_CreateRequest_DE supplier = new Step01_CreateRequest_DE(driver);
		String RequestNumber=supplier.SupplierSelectionPage();
		Excel.setCellValue(xlsFilePath, "Request_creation", 4, 15, RequestNumber );
	}
	
	@Test(priority=10)
	public void ClickOnHomePage() 
	{
		Step01_CreateRequest_DE home = new Step01_CreateRequest_DE(driver);
		home.ClickHomePage();
	}
	//Different currency request flow
	@Test(priority=11)
	public void DiffCurr_Create_Req() 
	{

		Step01_CreateRequest_DE createreq = new Step01_CreateRequest_DE(driver);
		createreq.Create_New_Request();
	}
	@Test(priority=12)
	public void DiffCurr_Select_ReqType() 
	{

		Step01_CreateRequest_DE reqType = new Step01_CreateRequest_DE(driver);
		reqType.Skill_Request();
	}
	@Test(priority=13)
	public void DiffCurr_select_Requester() 
	{

		Step01_CreateRequest_DE selectreq = new Step01_CreateRequest_DE(driver);
		selectreq.Select_Requestor();
	}

	@Test(priority=14)
	public void DiffCurr_select_jrss() 
	{

		Step01_CreateRequest_DE selectjr = new Step01_CreateRequest_DE(driver);
		selectjr.Select_JRSS();
	}

	@Test(priority=15)
	public void DiffCurr_req_detail() 
	{
		String projectName=(Excel.getCellValue(xlsFilePath, "Request_creation", 8, 4));
		Step01_CreateRequest_DE reqdetail = new Step01_CreateRequest_DE(driver);
		reqdetail.Request_detailpage(projectName);
	}
	@Test(priority=16)
	public void DiffCurr_skill_loc() 
	{

		Step01_CreateRequest_DE skillloc = new Step01_CreateRequest_DE(driver);
		skillloc.Skill_detailLocationpage();
	}

	@Test(priority=17)
	public void DiffCurr_skill_price() 
	{

		Step01_CreateRequest_DE skillprice = new Step01_CreateRequest_DE(driver);
		skillprice.Skill_detail_skillpricepage("USD",  "IBA Group (CZ) $ off-shore Rates");
	}
	@Test(priority=18)
	public void DiffCurr_supp() throws InterruptedException 
	{

		Step01_CreateRequest_DE supplier = new Step01_CreateRequest_DE(driver);
		String RequestNumber=supplier.SupplierSelectionPage();
		Excel.setCellValue(xlsFilePath, "Request_creation", 8, 15, RequestNumber );
	}

}

