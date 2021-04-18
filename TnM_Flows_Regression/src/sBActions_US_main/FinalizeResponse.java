package sBActions_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lib.Excel;
import sBActions_US.Step01_CreateRequest;
import sBActions_US.Step02_AddResponse;
import sBActions_US.Step03_Finalize;


public class FinalizeResponse
{
	// TestNG logger

	
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
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
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\IBM_ADMIN\\Desktop\\Selenium\\Jar files for Selenium\\geckodriver-v0.19.1-win64\\geckodriver.exe");

		//ProfilesIni ini = new ProfilesIni();
		//FirefoxProfile profile = ini.getProfile("default");
		//WebDriver driver =  new FirefoxDriver(profile);

		driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		//id = Excel.getCellValue(xlsFilePath, sheet, 1, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 1, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);    
		driver.manage().window().maximize();   
	}	


	//Test for Skill Request page
	@Test(priority=0)
	public void login()
	{

		Step03_Finalize log = new Step03_Finalize(driver);
		log.loginAndOpenReq();

	}
	
	@Test(priority=1)
	public void Finalize()
	{

		Step03_Finalize respfinal = new Step03_Finalize(driver);
		respfinal.FinaliseSubmit();

	}

}
