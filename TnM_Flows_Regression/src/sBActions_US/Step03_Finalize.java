package sBActions_US;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import generics.Screenshots;
import lib.Excel;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	
//Define the element 
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
@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[2]/td[1]//input[@name='tblcolCandId']") private WebElement Resp1_Checkbox;
@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[3]/td[1]//input[@name='tblcolCandId']") private WebElement Resp2_Checkbox;
@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[4]/td[1]//input[@name='tblcolCandId']") private WebElement Resp3_Checkbox;


@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[2]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp1_Check ;
@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[3]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp2_Check ;
@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[4]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp3_Check ;

@FindAll({@FindBy(name="tblcolCandId")}) private List<WebElement>  Select_Checkbox ;

@FindBy(id="NSR1") private WebElement Justification;
@FindBy ( name="btnAddSelFin" ) private WebElement Btn_AddSelectedToFinalists ;

@FindBy ( name="btnSubmitSel" ) private WebElement Btn_SubmitSelected ;
//Hiring Approval 
@FindBy ( id="FLD_NON_METRO_HIRING_REQUEST_NUM" ) private WebElement Non_Metro ;
@FindBy (id="FLD_REASON_NOT_HAVING_METRO_NUM") private WebElement Reason ;
@FindBy (id="ContinueSubmit") private WebElement Continue_Submission;
@FindBy (name="Continue") private WebElement Continue;
//Submit confirmation page
@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
@FindBy (name="btnConfirmSubmit") private WebElement Confirm_Submission;
@FindBy(xpath = ".//a[text()='Return to request header']") private WebElement  returnToRequest ;


public String sheet="Login";
// Initialize the web elements 
public Step03_Finalize(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}


// Function to login to the application

public void loginAndOpenReq()
{

	WebDriverWait wait = new WebDriverWait(driver, 180);
	wait.until(ExpectedConditions.visibilityOf(loginToContractor_Link));

	loginToContractor_Link.click();

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Signin_Button));

	Username_Box.clear();
	Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));
	Password_Box.clear();
	Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();

	Signin_Button.click();
	
	WebDriverWait wait11 = new WebDriverWait(driver, 180);
	wait11.until(ExpectedConditions.visibilityOf(LHS_Search_tab));

	LHS_Search_tab.click();

	WebDriverWait wait1 = new WebDriverWait(driver, 160);
	wait1.until(ExpectedConditions.visibilityOf(Request_Num_Fld)); 

	Request_Num_Fld.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
	Search_GO_btn.click();

	WebDriverWait wait2 = new WebDriverWait(driver, 160);
	wait2.until(ExpectedConditions.visibilityOf(Request_Num_link)); 

	Request_Num_link.click();
}


public void FinaliseSubmit()
{

	WebDriverWait wait2 = new WebDriverWait(driver, 160);
	wait2.until(ExpectedConditions.visibilityOf(Requested_Skill)); 

	Requested_Skill.click();

	WebDriverWait wait3 = new WebDriverWait(driver, 160);
	wait3.until(ExpectedConditions.visibilityOf(Btn_AddSelectedToFinalists)); 

	for (WebElement elt: Select_Checkbox){

		elt.click();
	} 

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();

	Btn_AddSelectedToFinalists.click();

	WebDriverWait wait4 = new WebDriverWait(driver, 160);
	wait4.until(ExpectedConditions.visibilityOf(Resp3_Check));
	
	for (WebElement elt: Select_Checkbox)
	{
		elt.click();

	} 

	/*Taking screenshot */
	Screenshots shot1=new Screenshots(driver);
	shot1.ScreenShot_SBCore_US();


	Btn_SubmitSelected.click();

	//Hiring Approval page
	WebDriverWait wait6 = new WebDriverWait(driver, 160);
	wait6.until(ExpectedConditions.visibilityOf(Continue_Submission));

	Non_Metro.click();

	Select reason = new Select(Reason);
	reason.selectByVisibleText("I use Metro, but can't for this situation and have a management-approved bypass");

	/*Taking screenshot */
	Screenshots shot2=new Screenshots(driver);
	shot2.ScreenShot_SBCore_US();

	Continue_Submission.click();

	//submit confirmation page

	WebDriverWait wait7 = new WebDriverWait(driver, 160);
	wait7.until(ExpectedConditions.visibilityOf(Confirm_Submission));

	String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1);  
	Select Rog = new Select(Requesting_Organization);
	Rog.selectByVisibleText(RO);

	/*Taking screenshot */
	Screenshots shot3=new Screenshots(driver);
	shot3.ScreenShot_SBCore_US();

	Confirm_Submission.click();

	Step03_Finalize popup = new Step03_Finalize(driver);
	popup.isAlertPresent();

	WebDriverWait wait9 = new WebDriverWait(driver, 160);
	wait9.until(ExpectedConditions.visibilityOf( returnToRequest));
	
	/*Taking screenshot */
	Screenshots shot4=new Screenshots(driver);
	shot4.ScreenShot_SBCore_US();

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

