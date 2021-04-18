package rDM_US_Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lib.Excel;
import rDM_US.Step01_CreateRequest_US;
import rDM_US.Step02_Asssign_RDM_Role_US;
import rDM_US.Step03_AddResponse;
import rDM_US.Step04_Finalize;


public class Step1_2
{
	// TestNG logger

	
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata_rdm.xls";
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
		createreq.Create_New_Request();
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
	@Test(priority=9)
	public void submit() 
	{

		Step01_CreateRequest_US sub = new Step01_CreateRequest_US(driver);
		sub.SupplierSelectionPage();
		sub.SubmitRequest();
		sub.SignOut();

	}   
	@Test(priority=10)
	public void Assign_RDM() throws InterruptedException 
	{
		Step02_Asssign_RDM_Role_US rdm = new Step02_Asssign_RDM_Role_US(driver);
		rdm.login();
		rdm.Assign_RDM();


	}


		
	}





