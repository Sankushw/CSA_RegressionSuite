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
import org.openqa.selenium.NoAlertPresentException;
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

public class Step03_Finalize {

private WebDriver driver;
public String str2;
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata_expActions.xls";
public String sheet="Login";

// Define the element 
@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
@FindBy ( id="btn_signin") private WebElement Signin_Button ;
@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;

@FindBy ( xpath = ".//*[@id='left-nav']/div/a[8]" ) private WebElement LHS_Search_tab ;
@FindBy ( id="FLD_REQ_NUM_SEARCH" ) private WebElement Request_Num_Fld ;
@FindBy ( name="GO" ) private WebElement Search_GO_btn ;
@FindBy ( xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[1]/a" ) private WebElement Request_Num_link ;

@FindBy ( xpath = ".//*[@id='content-main']/form/table[1]/tbody/tr[1]/td[3]/a" ) private WebElement Requested_Skill ;
//@FindBy(name="tblcolCandId") private WebElement  Resp1_Checkbox ;


@FindBy ( name="btnRejectSel" ) private WebElement Btn_Reject_Selected ;
@FindBy ( xpath=".//*[@id='content-main']/div/span/input" ) private WebElement Reject_Selected ;
@FindBy ( xpath=".//*[@id='FLD_REJ_REASON~0']" ) private WebElement Reject_Reason ;


@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[2]/td[1]//input[@name='tblcolCandId']") private WebElement Resp1_Checkbox;
@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[3]/td[1]//input[@name='tblcolCandId']") private WebElement Resp2_Checkbox;
@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[4]/td[1]//input[@name='tblcolCandId']") private WebElement Resp3_Checkbox;
@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[5]/td[1]//input[@name='tblcolCandId']") private WebElement Resp4_Checkbox;

@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[2]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp1_Check ;
@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[3]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp2_Check ;
@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[4]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp3_Check ;
@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[5]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp4_Check ;


@FindBy(id="NSR1") private WebElement Justification;
@FindBy ( name="btnAddSelFin" ) private WebElement Btn_AddSelectedToFinalists ;

@FindBy ( name="btnSubmitSel" ) private WebElement Btn_SubmitSelected ;
//Hiring Approval 

@FindBy (xpath =".//*[@id='skillReqSubmitForm']/table[1]/tbody/tr[2]/td[1]//input[@id='FLD_SENSITIVE_QUESTION' and  @value='1']") private WebElement  Sensitive_Material_Cand4_Y ;
@FindBy (xpath =".//*[@id='skillReqSubmitForm']/table[1]/tbody/tr[3]/td[1]//input[@id='FLD_SENSITIVE_QUESTION' and  @value='0']") private WebElement  Sensitive_Material_Cand3_N ;
@FindBy (xpath =".//*[@id='skillReqSubmitForm']/table[1]/tbody/tr[4]/td[1]//input[@id='FLD_SENSITIVE_QUESTION' and  @value='0']") private WebElement  Sensitive_Material_Cand2_N ;
@FindBy (xpath =".//*[@id='skillReqSubmitForm']/table[1]/tbody/tr[5]/td[1]//input[@id='FLD_SENSITIVE_QUESTION' and  @value='0']") private WebElement  Sensitive_Material_Cand1_N ;

@FindBy ( id="FLD_NON_METRO_HIRING_REQUEST_NUM" ) private WebElement Non_Metro ;
@FindBy (id="FLD_REASON_NOT_HAVING_METRO_NUM") private WebElement Reason ;
@FindBy (id="ContinueSubmit") private WebElement Continue_Submission;
@FindBy (name="btnContinueSubmission") private WebElement Continue_Submission1;

@FindBy (xpath=".//*[@id='content-main']/table[2]/tbody/tr[4]/td[3]/input") private WebElement Reporting_Manager_btn_Cand1;
@FindBy (xpath=".//*[@id='content-main']/table[3]/tbody/tr[4]/td[3]/input") private WebElement Reporting_Manager_btn_Cand2;
@FindBy (xpath=".//*[@id='content-main']/table[4]/tbody/tr[4]/td[3]/input") private WebElement Reporting_Manager_btn_Cand3;
@FindBy (xpath=".//*[@id='content-main']/table[5]/tbody/tr[4]/td[3]/input") private WebElement Reporting_Manager_btn_Cand4;


@FindBy (name="FLD_EMP_WEB_ID") private WebElement Email_Id;
@FindBy (name="BTN_GO") private WebElement GO_Button;
@FindBy (xpath = ".//*[@id='content-main']/table[3]/tbody/tr[2]/td[1]/a" ) private WebElement Name ;
@FindBy (name="TEMP REPT MGR BUTTON") private WebElement Use_As_Manager;


@FindBy (xpath= ".//*[@id='content-main']/table[2]/tbody/tr[7]/td[3]//textarea[@id='FLD_DETAIL_JOB_SCOPE']") private WebElement job_Scope_Cand1;
@FindBy (xpath= ".//*[@id='content-main']/table[3]/tbody/tr[7]/td[3]//textarea[@id='FLD_DETAIL_JOB_SCOPE']") private WebElement job_Scope_Cand2;
@FindBy (xpath= ".//*[@id='content-main']/table[4]/tbody/tr[7]/td[3]//textarea[@id='FLD_DETAIL_JOB_SCOPE']") private WebElement job_Scope_Cand3;
@FindBy (xpath= ".//*[@id='content-main']/table[5]/tbody/tr[7]/td[3]//textarea[@id='FLD_DETAIL_JOB_SCOPE']") private WebElement job_Scope_Cand4;

@FindBy (xpath= ".//*[@id='content-main']/table[2]/tbody/tr[9]/td[3]//textarea[@id='FLD_DESC_SOFTWARE_TECH']") private WebElement softTech_Access_Cand1;
@FindBy (xpath= ".//*[@id='content-main']/table[3]/tbody/tr[9]/td[3]//textarea[@id='FLD_DESC_SOFTWARE_TECH']") private WebElement softTech_Access_Cand2;
@FindBy (xpath= ".//*[@id='content-main']/table[4]/tbody/tr[9]/td[3]//textarea[@id='FLD_DESC_SOFTWARE_TECH']") private WebElement softTech_Access_Cand3;
@FindBy (xpath= ".//*[@id='content-main']/table[5]/tbody/tr[9]/td[3]//textarea[@id='FLD_DESC_SOFTWARE_TECH']") private WebElement softTech_Access_Cand4;

@FindBy (xpath= ".//*[@id='FLD_SENSITIVE_TECH00']") private WebElement sensitive_tech_cand1_N;
@FindBy (xpath= ".//*[@id='FLD_SENSITIVE_TECH10']") private WebElement sensitive_tech_cand2_N;
@FindBy (xpath= ".//*[@id='FLD_SENSITIVE_TECH20']") private WebElement sensitive_tech_cand3_N;
@FindBy (xpath= ".//*[@id='FLD_SENSITIVE_TECH3']") private WebElement sensitive_tech_cand4_Y;

@FindBy (name="Continue") private WebElement Continue;
//Submit confirmation page
@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
@FindBy (name="btnConfirmSubmit") private WebElement Confirm_Submission;
@FindBy(xpath = ".//a[text()='Return to request header']") private WebElement  returnToRequest ;

// Initialize the web elements 
public Step03_Finalize(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}


// Function to login to the application

public void loginAndOpenReq()
{

	WebDriverWait wait00 = new WebDriverWait(driver, 180);
	wait00.until(ExpectedConditions.visibilityOf(loginToContractor_Link));
	
	loginToContractor_Link.click();

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Signin_Button));

	Username_Box.clear();
	Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));
	Password_Box.clear();
	Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Signin_Button.click();

	WebDriverWait wait1 = new WebDriverWait(driver, 160);
	wait1.until(ExpectedConditions.visibilityOf(LHS_Search_tab)); 

	LHS_Search_tab.click();

	WebDriverWait wait = new WebDriverWait(driver, 160);
	wait.until(ExpectedConditions.visibilityOf(Request_Num_Fld)); 

	Request_Num_Fld.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));

	Search_GO_btn.click();

	WebDriverWait wait9 = new WebDriverWait(driver, 160);
	wait9.until(ExpectedConditions.visibilityOf(Request_Num_link)); 

	Request_Num_link.click();
}


public void FinaliseSubmit()
{

	WebDriverWait wait2 = new WebDriverWait(driver, 160);
	wait2.until(ExpectedConditions.visibilityOf(Requested_Skill)); 

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Requested_Skill.click();
	str2 = Requested_Skill.getText();
	System.out.println(str2);

	WebDriverWait wait3 = new WebDriverWait(driver, 160);
	wait3.until(ExpectedConditions.visibilityOf(Btn_AddSelectedToFinalists)); 

	Resp1_Checkbox.click();
	Resp2_Checkbox.click();
	Resp3_Checkbox.click();
	Resp4_Checkbox.click();

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Btn_AddSelectedToFinalists.click();

	WebDriverWait wait4 = new WebDriverWait(driver, 160);
	wait4.until(ExpectedConditions.visibilityOf(Resp4_Check));

	Resp1_Check.click();
	Resp2_Check.click();
	Resp3_Check.click();
	Resp4_Check.click();

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Btn_SubmitSelected.click();

	//Hiring Approval page
	WebDriverWait wait6 = new WebDriverWait(driver, 160);
	wait6.until(ExpectedConditions.visibilityOf( Sensitive_Material_Cand4_Y));

	Sensitive_Material_Cand4_Y.click();
	Sensitive_Material_Cand3_N.click();
	Sensitive_Material_Cand2_N.click();
	Sensitive_Material_Cand1_N.click();

	Non_Metro.click();

	Select reason = new Select(Reason);
	reason.selectByVisibleText("I use Metro, but can't for this situation and have a management-approved bypass");

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Continue_Submission.click();

	WebDriverWait wait7 = new WebDriverWait(driver, 160);
	wait7.until(ExpectedConditions.visibilityOf(Continue_Submission1));

	// Cand_1//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	job_Scope_Cand1.sendKeys("Test");
	softTech_Access_Cand1.sendKeys("Test");
	sensitive_tech_cand1_N.click();

	Reporting_Manager_btn_Cand1.click();


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
			wait8.until(ExpectedConditions.visibilityOf(Email_Id));               

			Email_Id.sendKeys("csatestus1@c25a0161.toronto.ca.ibm.com");                                                                     
			GO_Button.click();
			WebDriverWait wait07 = new WebDriverWait(driver, 180);
			wait07.until(ExpectedConditions.visibilityOf(Name));
			Name.click();
			Use_As_Manager.click();

		}		
	}		
	//Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow);	



	//Cand_2//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	job_Scope_Cand2.sendKeys("Test");
	softTech_Access_Cand2.sendKeys("Test");
	sensitive_tech_cand2_N.click();

	Reporting_Manager_btn_Cand2.click();


	//To handle all new opened window.
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

			WebDriverWait wait9 = new WebDriverWait(driver, 160);
			wait9.until(ExpectedConditions.visibilityOf(Email_Id));

			Email_Id.sendKeys("csatestus1@c25a0161.toronto.ca.ibm.com");                                                                     
			GO_Button.click();
			WebDriverWait wait07 = new WebDriverWait(driver, 180);
			wait07.until(ExpectedConditions.visibilityOf(Name));
			Name.click();
			Use_As_Manager.click();

		}		
	}		
	//Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow2);	



	//Cand_3//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	job_Scope_Cand3.sendKeys("Test");
	softTech_Access_Cand3.sendKeys("Test");
	sensitive_tech_cand3_N.click();

	Reporting_Manager_btn_Cand3.click();


	//To handle all new opened window.
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
			wait8.until(ExpectedConditions.visibilityOf(Email_Id));

			Email_Id.sendKeys("csatestus1@c25a0161.toronto.ca.ibm.com");                                                                     
			GO_Button.click();
			WebDriverWait wait07 = new WebDriverWait(driver, 180);
			wait07.until(ExpectedConditions.visibilityOf(Name));
			Name.click();
			Use_As_Manager.click();

		}		
	}		
	//Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow3);	




	//Cand_4//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	job_Scope_Cand4.sendKeys("Test");
	softTech_Access_Cand4.sendKeys("Test");
	sensitive_tech_cand4_Y.click();

	Reporting_Manager_btn_Cand4.click();


	//To handle all new opened window.
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
			wait8.until(ExpectedConditions.visibilityOf(Email_Id));

			Email_Id.sendKeys("csatestus1@c25a0161.toronto.ca.ibm.com");                                                                     
			GO_Button.click();
			WebDriverWait wait07 = new WebDriverWait(driver, 180);
			wait07.until(ExpectedConditions.visibilityOf(Name));
			Name.click();
			Use_As_Manager.click();

		}		
	}		
	//Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow4);	

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Continue_Submission1.click();

	//submit confirmation page

	WebDriverWait wait8 = new WebDriverWait(driver, 160);
	wait8.until(ExpectedConditions.visibilityOf(Confirm_Submission));

	String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1);  
	Select Rog = new Select(Requesting_Organization);
	Rog.selectByVisibleText(RO);

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Confirm_Submission.click();
	
	Step03_Finalize popup = new Step03_Finalize(driver);
	popup.isAlertPresent();

	WebDriverWait wait9 = new WebDriverWait(driver, 160);
	wait9.until(ExpectedConditions.visibilityOf( returnToRequest));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

}

public boolean isAlertPresent() 
{ 
	try 
	{ 
		driver.switchTo().alert().accept();
		return true;


	}   // try 
	catch (NoAlertPresentException Ex) 
	{ 
		return false; 
	}   // catch 
	//driver.switchTo().alert().accept();
}

}
