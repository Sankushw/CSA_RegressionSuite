package fEEDBACK_US_Main;

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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import fEEDBACK_US.Step01_CreateRequest_US;
import fEEDBACK_US.Step04_submit_feedback_New_app;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lib.Excel;

@Listeners(generics.TestNG_Listeners.class)
public class Createreq_Feedback_Submit_Test
{
	// TestNG logger

	
	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_feedback.xls";
	public String sheet="Login"; 
	public String url;
	public String url1;
	public String id;
	public String paswd;


	public WebDriver driver;


	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\UdayKotipalli\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");

		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(false);
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
	
	@Test
	public void T1_RIPC_Login() throws InterruptedException 
	{

		Step01_CreateRequest_US login = new Step01_CreateRequest_US(driver);
		login.login();
	}


	@Test(dependsOnMethods="T1_RIPC_Login")
	public void T2_Create_Req() 
	{

		Step01_CreateRequest_US createreq = new Step01_CreateRequest_US(driver);
		createreq.Create_New_Request();
	}
	@Test(dependsOnMethods="T2_Create_Req")
	public void T3_skill_Req() 
	{

		Step01_CreateRequest_US skillreq = new Step01_CreateRequest_US(driver);
		skillreq.Skill_Request();
	}
	@Test(dependsOnMethods="T3_skill_Req")
	public void T4_select_Req() 
	{

		Step01_CreateRequest_US selectreq = new Step01_CreateRequest_US(driver);
		selectreq.Select_Requestor();
	}

	@Test(dependsOnMethods="T4_select_Req")
	public void T5_select_jrss() throws InterruptedException 
	{

		Step01_CreateRequest_US selectreq = new Step01_CreateRequest_US(driver);
		selectreq.Select_JRSS();
	}

	@Test(dependsOnMethods="T5_select_jrss")
	public void T6_req_detail() 
	{

		Step01_CreateRequest_US reqdetail = new Step01_CreateRequest_US(driver);
		reqdetail.Request_detailpage();
	}
	@Test(dependsOnMethods="T6_req_detail")
	public void T7_skill_loc() 
	{

		Step01_CreateRequest_US skillloc = new Step01_CreateRequest_US(driver);
		skillloc.Skill_detailLocationpage();
	}
	@Test(dependsOnMethods="T7_skill_loc")
	public void T8_alert_check() 
	{

		Step01_CreateRequest_US alertchk = new Step01_CreateRequest_US(driver);
		alertchk.isAlertPresent();
	}
	@Test(dependsOnMethods="T8_alert_check")
	public void T9_skill_price() 
	{

		Step01_CreateRequest_US skillprice = new Step01_CreateRequest_US(driver);
		skillprice.Skill_detail_skillpricepage();
	}
	@Test(dependsOnMethods="T9_skill_price")
	public void T10_SupllierSelection() 
	{

		Step01_CreateRequest_US Suppliersub = new Step01_CreateRequest_US(driver);
		Suppliersub.SupplierSelectionPage();
	}   
	
	@Test(dependsOnMethods="T10_SupllierSelection")
	public void T11_SubmitReq() 
	{

		Step01_CreateRequest_US subreq = new Step01_CreateRequest_US(driver);
		subreq.SubmitRequest();
	}   
	
		
	@Test(dependsOnMethods="T11_SubmitReq")
	public void T12_OpenNewGreen()
	{
		url1 = Excel.getCellValue(xlsFilePath, sheet, 26, 2);
		driver.navigate().to(url1);
	}

	@Test(dependsOnMethods="T12_OpenNewGreen")
	public void T13_supplierlogin() throws IOException, InterruptedException
	{

		Step04_submit_feedback_New_app Suplogin = new Step04_submit_feedback_New_app(driver); 
		
		Suplogin.login();
		
						
  }
	
	@Test(dependsOnMethods="T13_supplierlogin")
	public void T14_requestopening() 
	
	{
		Step04_submit_feedback_New_app Opreq = new Step04_submit_feedback_New_app(driver); 
		Opreq.openRequest();
	}
	
	@Test(dependsOnMethods="T14_requestopening")
	public void T15_Feedback() throws InterruptedException
	{
		Step04_submit_feedback_New_app addfeedback = new Step04_submit_feedback_New_app(driver);
		addfeedback.addfeedbackdetails();
		
	}
		
}





