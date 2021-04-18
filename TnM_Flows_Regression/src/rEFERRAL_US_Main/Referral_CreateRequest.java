package rEFERRAL_US_Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import lib.Excel;
import rEFERRAL_US.Step01_CreateRequest_US;


public class Referral_CreateRequest
{
	// TestNG logger

	
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;


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
		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);    
	}	

	// test to Login to the application as RIPC
	
	@Test(priority=0)
	public void RIPC_Login() throws InterruptedException 
	{

		Step01_CreateRequest_US login = new Step01_CreateRequest_US(driver);
		login.login();
	}


	@Test(priority=1)
	public void Create_Req() 
	{

		Step01_CreateRequest_US createreq = new Step01_CreateRequest_US(driver);
		createreq.Create_Referral_Request();
	}
	@Test(priority=2)
	public void skill_Req() 
	{

		Step01_CreateRequest_US skillreq = new Step01_CreateRequest_US(driver);
		skillreq.Skill_Request();
	}
	@Test(priority=3)
	public void select_Req() 
	{

		Step01_CreateRequest_US selectreq = new Step01_CreateRequest_US(driver);
		selectreq.Select_Requestor();
	}

	@Test(priority=4)
	public void select_jrss() throws InterruptedException 
	{

		Step01_CreateRequest_US selectreq = new Step01_CreateRequest_US(driver);
		selectreq.Select_JRSS();
	}

	@Test(priority=5)
	public void req_detail() 
	{

		Step01_CreateRequest_US reqdetail = new Step01_CreateRequest_US(driver);
		reqdetail.Request_detailpage();
	}
	@Test(priority=6)
	public void skill_loc() 
	{

		Step01_CreateRequest_US skillloc = new Step01_CreateRequest_US(driver);
		skillloc.Skill_detailLocationpage();
	}
	@Test(priority=7)
	public void alert_check() 
	{

		Step01_CreateRequest_US alertchk = new Step01_CreateRequest_US(driver);
		alertchk.isAlertPresent();
	}
	@Test(priority=8)
	public void skill_price() 
	{

		Step01_CreateRequest_US skillprice = new Step01_CreateRequest_US(driver);
		skillprice.Skill_detail_skillpricepage();
	}
	
	@Test(priority=10)
	public void candidate_name()
	{
		Step01_CreateRequest_US candidate_name = new Step01_CreateRequest_US(driver);
		candidate_name.Candidate_Details();
	}
	
	@Test(priority=11)
	public void submit() 
	{

		Step01_CreateRequest_US sub = new Step01_CreateRequest_US(driver);
		sub.SupplierSelectionPage();
		sub.SubmitRequest();
		
	}   
	


		
	}





