package shakeDown_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lib.Excel;
import shakeDown_US.Step01_CreateRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;




public class CreateRequest
{
	// TestNG logger

	//public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata_shakeDown.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;


	public WebDriver driver;


	@BeforeClass
	public void setup() throws ClassNotFoundException, SQLException
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 4, 2);
		driver.get(url);
		driver.manage().window().maximize();
	}	

	// test to Login to the application as RIPC
	@Test(priority=0)
	public void RIPC_Login() throws InterruptedException 
	{
			Step01_CreateRequest login = new Step01_CreateRequest(driver);
		try {
			login.login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority=1)
	public void Create_Req() 
	{

		Step01_CreateRequest createreq = new Step01_CreateRequest(driver);
		createreq.Create_New_Request();
	}
	@Test(priority=2)
	public void skill_Req() 
	{

		Step01_CreateRequest skillreq = new Step01_CreateRequest(driver);
		skillreq.Skill_Request();
	}
	@Test(priority=3)
	public void select_Req() 
	{

		Step01_CreateRequest selectreq = new Step01_CreateRequest(driver);
		try {
			selectreq.Select_Requestor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority=4)
	public void select_jrss() 
	{

		Step01_CreateRequest selectreq = new Step01_CreateRequest(driver);
		selectreq.Select_JRSS();
	}

	@Test(priority=5)
	public void req_detail() 
	{

		Step01_CreateRequest reqdetail = new Step01_CreateRequest(driver);
		reqdetail.Request_detailpage();
	}
	@Test(priority=6)
	public void skill_loc() 
	{

		Step01_CreateRequest skillloc = new Step01_CreateRequest(driver);
		skillloc.Skill_detailLocationpage();
	}
	@Test(priority=7)
	public void alert_check() 
	{

		Step01_CreateRequest alertchk = new Step01_CreateRequest(driver);
		alertchk.isAlertPresent();
	}
	@Test(priority=8)
	public void skill_price() throws InterruptedException 
	{

		Step01_CreateRequest skillprice = new Step01_CreateRequest(driver);
		skillprice.Skill_detail_skillpricepage();
	}
	@Test(priority=9)
	public void supp() throws InterruptedException 
	{

		Step01_CreateRequest supplier = new Step01_CreateRequest(driver);
		supplier.SupplierSelectionPage();
	}
	
	@AfterClass
	public void Quit()
	{
		driver.close();
	}
	
}


