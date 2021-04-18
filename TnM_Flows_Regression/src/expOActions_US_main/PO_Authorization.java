package expOActions_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import expOActions_US.Step07_PO_Auth;
import lib.Excel;


public class PO_Authorization {


	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;

	WebDriver driver;

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

		//id = Excel.getCellValue(xlsFilePath, sheet, 4, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 4, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 4, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);
		driver.manage().window().maximize();   
	}	

	// test to Login to the application as RIPC
	@Test(priority=0)
	public void PO_Login() 
	{

		Step07_PO_Auth login = new Step07_PO_Auth(driver);
		login.login();
	}


	// test to authorize request from PO
	@Test(priority=1)
	public void authorize_PO() 
	{

		Step07_PO_Auth auth_PO = new Step07_PO_Auth(driver);
		auth_PO.authorize_PO();
	}


}


