package gPSG_DE_Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import gPSG_DE.Step03_FinalizeSubmit2Bond_DE;


import lib.Excel;


public class FinalizeSubmit2Bond_DE
{

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
		System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\IBM_ADMIN\\Desktop\\Selenium\\Jar files for Selenium\\geckodriver-v0.19.1-win64\\geckodriver.exe");

		//ProfilesIni ini = new ProfilesIni();
		//FirefoxProfile profile = ini.getProfile("default");
		//WebDriver driver =  new FirefoxDriver(profile);

		// driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		//id = Excel.getCellValue(xlsFilePath, sheet, 12, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 12, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 12, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);   
		driver.manage().window().maximize();
	}	


	//Test for Skill Request page
	
	@Test(priority=0)
	public void login()
	{

		Step03_FinalizeSubmit2Bond_DE log = new Step03_FinalizeSubmit2Bond_DE(driver);
		log.loginAndOpenReq();
	}	
	/*	
	@Test(priority=1)
	public void FinalizeReq1()
	{

		Step03_FinalizeSubmit2Bond_DE respfinal1 = new Step03_FinalizeSubmit2Bond_DE(driver);
		respfinal1.FinaliseSubmitReq1();

	} */

	@Test(priority=2)
	public void FinalizeReq2()
	{

		Step03_FinalizeSubmit2Bond_DE respfinal2 = new Step03_FinalizeSubmit2Bond_DE(driver);

		respfinal2.FinaliseSubmitReq2();


	}

}
