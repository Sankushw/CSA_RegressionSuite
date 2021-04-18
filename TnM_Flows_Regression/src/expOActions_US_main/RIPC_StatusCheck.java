package expOActions_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import expOActions_US.Step06_RIPC_ResponseStatus;


import lib.Excel;


public class RIPC_StatusCheck
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
	  }	
		
		 // test to Login to the application as RIPC
		  @Test(priority=0)
		  public void RIPC_Login() 
		  {
			  
			  Step06_RIPC_ResponseStatus statuschk = new  Step06_RIPC_ResponseStatus(driver);
			  statuschk.status();
			 
			  
}
		  // Test to add response
	/*	 @Test(priority=1)
		  public void add_response()
		  {
		     driver.get(url2);
			  
			  log.debug("Inside open_CSA_Tab() function");
			  
			  Step02_AddResponse addresp = new Step02_AddResponse(driver); 
			  
			  for (int i=1; i<4; i++)
			  {
				  if(i==1)
				  {
				 addresp.login();
				  addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "40");
				  }  
				  else if(i==2)
				  {
				  //addresp.login();
				  addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "35");}
			  else
			  {
				  //addresp.login();
				  addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "38");
				  addresp.Submit();
			  }
			  }
			
		  }
		*/  
		 //Test for Skill Request page
	/*	  @Test(priority=0)
		  public void Finalize()
		  {
			 
			  Step03_Finalize respfinal = new Step03_Finalize(driver);
			  respfinal.loginAndOpenReq();
			  respfinal.FinaliseSubmit();
			  
				  
		  }*/
		  
		
}
