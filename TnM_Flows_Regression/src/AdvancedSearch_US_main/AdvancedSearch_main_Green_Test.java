
package AdvancedSearch_US_main;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import AdvancedSearch_US.AdvancedSearch_Green;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import lib.Excel;

@Listeners(generics.TestNG_Listeners.class)
public class AdvancedSearch_main_Green_Test
{


	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_advancedSearch.xls";
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
		// ((RemoteWebDriver) driver).setLogLevel(Level.INFO);

		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		driver.get(url);   
		driver.manage().window().maximize();
		System.out.println("Starting execution of Advanced search flow");
	}	


	@Test(priority=0)
	public void T1_SupplierLogin() throws IOException, InterruptedException
	{
		AdvancedSearch_Green log = new AdvancedSearch_Green(driver); 
		log.login();
	}
	@Test(dependsOnMethods="T1_SupplierLogin" )
	public void T2_OpenSearchTab() throws IOException, InterruptedException
	{
		AdvancedSearch_Green open = new AdvancedSearch_Green(driver); 
		open.OpenSearchTab();


	}
	@Test (dependsOnMethods="T2_OpenSearchTab" )
	public void T3_SearchByReq() throws IOException, InterruptedException
	{
		AdvancedSearch_Green req = new AdvancedSearch_Green(driver); 
		req.SearchByReqId();

	}
	@Test(dependsOnMethods="T2_OpenSearchTab" )
	public void T4_SearchByPONumber() throws IOException, InterruptedException
	{
		AdvancedSearch_Green po = new AdvancedSearch_Green(driver); 
		po.SearchByPO();
	}
	@Test(dependsOnMethods="T2_OpenSearchTab" )
	public void T5_SearchByFirstName() throws IOException, InterruptedException
	{
		AdvancedSearch_Green fname = new AdvancedSearch_Green(driver); 
		fname.SearchByFirstName();


	}
	@Test(dependsOnMethods="T2_OpenSearchTab" )
	public void T6_SearchByLastName() throws IOException, InterruptedException
	{
		AdvancedSearch_Green lname = new AdvancedSearch_Green(driver); 
		lname.SearchByLastName();

	}
	@Test(dependsOnMethods="T2_OpenSearchTab" )
	public void T7_SearchByProjectName() throws IOException, InterruptedException
	{
		AdvancedSearch_Green project = new AdvancedSearch_Green(driver); 
		project.SearchByProjectName();

	}

	@Test(dependsOnMethods="T2_OpenSearchTab" )
	public void T8_SearchByReqId_FName_ProjectName() throws IOException, InterruptedException
	{
		AdvancedSearch_Green multi2 = new AdvancedSearch_Green(driver); 
		multi2.SearchBy_Req_Fname_ProjName();
	}
	
	@Test(dependsOnMethods="T2_OpenSearchTab" )
	public void T9_SearchByReqId_FName_LName_ProjectName_PONumber() throws IOException, InterruptedException
	{
		AdvancedSearch_Green multi1 = new AdvancedSearch_Green(driver); 
		multi1.SearchBy_Req_Fname_Lname_ProjName_PO();

	}


	@AfterTest
	public void Close()
	{
		driver.quit();
	}
}




