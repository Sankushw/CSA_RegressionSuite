package expOActions_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import expOActions_US.Step04_CRB_Auth;

import lib.Excel;


public class CRB_Auth
{

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;

	WebDriver driver;


	@BeforeTest
	public void setup()
	{
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\IBM_ADMIN\\Desktop\\Selenium\\Jar files for Selenium\\geckodriver-v0.19.1-win64\\geckodriver.exe");

		//ProfilesIni ini = new ProfilesIni();
		//FirefoxProfile profile = ini.getProfile("default");
		//WebDriver driver =  new FirefoxDriver(profile);

		//driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

		//id = Excel.getCellValue(xlsFilePath, sheet, 3, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 3, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 3, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url); 
		driver.manage().window().maximize();
	}	

	// test to Login to the application as RIPC

	@Test(priority=0)
	public void CRB_login() 
	{

		Step04_CRB_Auth login = new Step04_CRB_Auth(driver);
		login.login();
	}
	
	@Test(priority=1)
	public void Openreq() 
	{

		Step04_CRB_Auth open = new Step04_CRB_Auth(driver);
		open.OpenRequest();
	}
	
	/*@Test(priority=2)
	public void AllStatus() 
	{

		Step04_CRB_Auth status = new Step04_CRB_Auth(driver);
		status.ResponseStatus();
	}
	*/
	@Test(priority=3)
	public void CRB_auth() 
	{

		Step04_CRB_Auth crbauth = new Step04_CRB_Auth(driver);
		crbauth.AuthResponses();
	}



}


