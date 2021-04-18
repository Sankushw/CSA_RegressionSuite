package expOActions_US;


import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step04_CRB_Auth {
 
private WebDriver driver;	
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
public String sheet="Login";

// Define the element 
@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
@FindBy ( id="btn_signin") private WebElement Signin_Button ;
@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;

// Define all web elements under test displayed on home page
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Switch instance')]") private WebElement Switch_Instance;
@FindBy (id="FLD_SELECT_INSTANCE") private WebElement Instance ;
@FindBy (name="btnGo") private WebElement GO ;
@FindBy (xpath = ".//*[@value='Exit request'] ") private WebElement ExitRequest;
@FindBy (xpath = ".//*[@id='content-main']/form/table[1]/tbody/tr[1]/td[3]/a") private WebElement Skill_Req_hyperlink ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Awaiting client review board action')]") private WebElement Awaiting_CRB_auth_link ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Candidates')]") private WebElement Candidates ;
@FindBy (id="FLD_REQUEST_NUMBER") private WebElement Req_Num_Search ;
@FindBy (name="btnGo") private WebElement GO_reqnum ;
@FindBy (name = "btnAuthCandidate") private WebElement Auth_Link;
@FindBy (xpath =".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[3]/a" ) private WebElement Request_Number ;

@FindBy (xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[5]/a" ) private WebElement Skill_link ;
//@FindBy(name="tblcolCandId") private WebElement  Select_Checkbox ;
@FindBy (name="btnAuthSel") private WebElement Authorize_selected ;

@FindBy (xpath =".//*[@id='finalCandForm']/table[1]/tbody/tr[2]/td[9]/a") private WebElement Cand4_Status_awaitAuth_Link;
@FindBy (xpath =".//*[@id='finalCandForm']/table[1]/tbody/tr[3]/td[9]/a") private WebElement Cand3_Status_awaitAuth_Link;
@FindBy (xpath =".//*[@id='finalCandForm']/table[1]/tbody/tr[4]/td[9]/a") private WebElement Cand2_Status_awaitAuth_Link;
@FindBy (xpath =".//*[@id='finalCandForm']/table[1]/tbody/tr[5]/td[9]/a") private WebElement Cand1_Status_awaitAuth_Link;

@FindBy (xpath =".//*[@id='finalCandForm']/table[1]/tbody/tr[5]/td[2]/a") private WebElement Cand1_resp_Identification ;
@FindBy (xpath =".//*[@id='finalCandForm']/table[1]/tbody/tr[4]/td[2]/a") private WebElement Cand2_resp_Identification ;
@FindBy (xpath =".//*[@id='finalCandForm']/table[1]/tbody/tr[3]/td[2]/a") private WebElement Cand3_resp_Identification ;
@FindBy (xpath =".//*[@id='finalCandForm']/table[1]/tbody/tr[2]/td[2]/a") private WebElement Cand4_resp_Identification ;

@FindBy (xpath =".//*[@id='popup-footer']/div[2]/a") private WebElement Close_Child_Window;
@FindAll({@FindBy(name="tblcolCandId")}) private List<WebElement>  Select_Checkbox ;

@FindBy (xpath = ".//*[@id='finalCandForm']/table[3]/tbody/tr/td[2]/input") private WebElement Reject_sel;

//@FindBy(xpath = ".//*[@value='001~S23D2K~001~1000118974~IBMSAPGC0~AUTH~0'] ") private WebElement  Resp2_Checkbox ;
//@FindBy(xpath = ".//*[@value='002~S23D2K~001~1000118974~IBMSAPGC0~AUTH~0'] ") private WebElement  Resp3_Checkbox ;
//@FindBy(xpath = ".//*[@value='003~S23D2K~001~1000118974~IBMSAPGC0~AUTH~0'] ") private WebElement  Resp1_Checkbox ;



// Initialize the web elements 
public Step04_CRB_Auth(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}


// Function to login to the application

public void login()
{

	WebDriverWait wait00 = new WebDriverWait(driver, 180);
	wait00.until(ExpectedConditions.visibilityOf(loginToContractor_Link));

	loginToContractor_Link.click();

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Signin_Button));

	Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 3, 0));
	Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 3, 1));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Signin_Button.click();

}

//Function to click Create New Request button
public void OpenRequest()
{

	WebDriverWait wait02 = new WebDriverWait(driver, 180);
	wait02.until(ExpectedConditions.visibilityOf(Awaiting_CRB_auth_link));

	Awaiting_CRB_auth_link.click();

	WebDriverWait wait03 = new WebDriverWait(driver, 180);
	wait03.until(ExpectedConditions.visibilityOf(Candidates));

	Candidates.click();

	Req_Num_Search.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
	GO_reqnum.click();

	String Req_Num = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15) ;

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Skill_link)); 

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Request_Number.click();

	WebDriverWait wait09 = new WebDriverWait(driver, 160);
	wait09.until(ExpectedConditions.visibilityOf(Skill_Req_hyperlink)); 

	Skill_Req_hyperlink.click();

	//Resp Selection Page
	WebDriverWait wait05 = new WebDriverWait(driver, 160);
	wait05.until(ExpectedConditions.visibilityOf(Authorize_selected));
}

	////Checking awaithing auth status of candidate1////////////////////////////////////////////////////////////////////////

public void ResponseStatus()
{
	Cand1_Status_awaitAuth_Link.click();

	// To handle all new opened window.
	String MainWindow=driver.getWindowHandle();

	Set<String> s1=driver.getWindowHandles();		
	Iterator<String> i1=s1.iterator();		

	while(i1.hasNext())			
	{		
		String ChildWindow=i1.next();		

		if(!MainWindow.equalsIgnoreCase(ChildWindow))			
		{    		

			// Switching to Child window
			driver.switchTo().window(ChildWindow);	

			WebDriverWait wait8 = new WebDriverWait(driver, 160);
			wait8.until(ExpectedConditions.visibilityOf(Close_Child_Window));               

			/* code to capture screenshot */
			Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

			Close_Child_Window.click();
		}		
	}		
	// Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow);	



	////Checking awaithing auth status of candidate2////////////////////////////////////////////////////////////////////////

	Cand2_Status_awaitAuth_Link.click();

	// To handle all new opened window.
	String MainWindow2=driver.getWindowHandle();

	Set<String> s2=driver.getWindowHandles();		
	Iterator<String> i2=s2.iterator();		

	while(i2.hasNext())			
	{		
		String ChildWindow=i2.next();		

		if(!MainWindow2.equalsIgnoreCase(ChildWindow))			
		{    		

			// Switching to Child window
			driver.switchTo().window(ChildWindow);	

			WebDriverWait wait8 = new WebDriverWait(driver, 160);
			wait8.until(ExpectedConditions.visibilityOf(Close_Child_Window));               

			/* code to capture screenshot */
			Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

			Close_Child_Window.click();
		}		
	}		
	//Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow2);	


	////Checking awaithing auth status of candidate2////////////////////////////////////////////////////////////////////////

	Cand3_Status_awaitAuth_Link.click();

	// To handle all new opened window.
	String MainWindow3=driver.getWindowHandle();

	Set<String> s3=driver.getWindowHandles();		
	Iterator<String> i3=s3.iterator();		

	while(i3.hasNext())			
	{		
		String ChildWindow=i3.next();		

		if(!MainWindow3.equalsIgnoreCase(ChildWindow))			
		{    		

			// Switching to Child window
			driver.switchTo().window(ChildWindow);	

			WebDriverWait wait8 = new WebDriverWait(driver, 160);
			wait8.until(ExpectedConditions.visibilityOf(Close_Child_Window));               

			/* code to capture screenshot */
			Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

			Close_Child_Window.click();
		}		
	}		
	//Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow3);	


	////Checking awaithing auth status of candidate4////////////////////////////////////////////////////////////////////////

	Cand4_Status_awaitAuth_Link.click();

	// To handle all new opened window.
	String MainWindow4=driver.getWindowHandle();

	Set<String> s4=driver.getWindowHandles();		
	Iterator<String> i4=s4.iterator();		

	while(i4.hasNext())			
	{		
		String ChildWindow=i4.next();		

		if(!MainWindow4.equalsIgnoreCase(ChildWindow))			
		{    		

			// Switching to Child window
			driver.switchTo().window(ChildWindow);	

			WebDriverWait wait8 = new WebDriverWait(driver, 160);
			wait8.until(ExpectedConditions.visibilityOf(Close_Child_Window));               

			/* code to capture screenshot */
			Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

			Close_Child_Window.click();
		}		
	}		
	//Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow4);	

}

public void AuthResponses()
{
	
	for (WebElement elt: Select_Checkbox){

		elt.click();
	} 

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");
	Authorize_selected.click();

	//CRB Comments Page
	WebDriverWait wait06 = new WebDriverWait(driver, 160);
	wait06.until(ExpectedConditions.visibilityOf(Authorize_selected));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Authorize_selected.click();

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");
}
}