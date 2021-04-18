package sBActions_US;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import generics.Screenshots;
import lib.Excel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Step04_BA_Auth
{
	
private WebDriver driver;
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
public String sheet="Login";

//Define Login element
@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
@FindBy ( id="btn_signin") private WebElement Signin_Button ;
@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;

//Define all web elements under test displayed on home page
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Awaiting Business Approver action')]") private WebElement Awaiting_BA_Action ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'New/Renewal Approval')]") private WebElement New_Renewal_Approval ;

@FindBy (id="FLD_REQUEST_NUMBER") private WebElement Req_Num_Search ;
@FindBy (name="btnGo") private WebElement GO_reqnum ;

@FindBy (xpath =" .//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[2]/a" ) private WebElement Request_Number ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[5]/a" ) private WebElement Skill_link ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[5]/a" ) private WebElement Resp_identification1 ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[3]/td[5]/a" ) private WebElement Resp_identification2 ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[4]/td[5]/a" ) private WebElement Resp_identification3 ;

@FindBy (name="btnAuthCandidate") private WebElement Authorize ;
@FindBy (name="FLD_JUSTIFICATION_COMMENTS") private WebElement Justification ;

@FindBy (name="FLD_CMTS_TO_REQSTR") private WebElement Auth_comments ;
@FindBy (name="btnAuthSel") private WebElement Auth_Selected_Button ;
@FindBy (id="content-main']/form/div[2]/span/span/input") private WebElement ExitReq;


// Initialize the web elements 
public Step04_BA_Auth (WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}


// Function to login to the application
public void login()
{

	WebDriverWait wait = new WebDriverWait(driver, 180);
	wait.until(ExpectedConditions.visibilityOf(loginToContractor_Link));

	loginToContractor_Link.click();

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Signin_Button));

	Username_Box.clear();
	Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 17, 0));
	Password_Box.clear();
	Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 17, 1));

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();

	Signin_Button.click();
}

public void BA_open_req_US()
{

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Awaiting_BA_Action));

	Awaiting_BA_Action.click();
	WebDriverWait wait02 = new WebDriverWait(driver, 180);
	wait02.until(ExpectedConditions.visibilityOf(New_Renewal_Approval));
	New_Renewal_Approval.click();

	WebDriverWait wait03 = new WebDriverWait(driver, 180);
	wait03.until(ExpectedConditions.visibilityOf(Req_Num_Search));
	
	Req_Num_Search.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
	GO_reqnum.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Request_Number)); 

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();

	Request_Number.click();

}

//auth 1st response

public void BA_auth1_US()
{

	WebDriverWait wait03 = new WebDriverWait(driver, 160);
	wait03.until(ExpectedConditions.visibilityOf(Resp_identification1)); 

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();

	Resp_identification1.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Authorize)); 

	Select st = new Select(Justification);
	st.selectByVisibleText("Approved acceptable business cost");

	/*Taking screenshot */
	Screenshots shot1=new Screenshots(driver);
	shot1.ScreenShot_SBCore_US();

	Authorize.click();

	WebDriverWait wait05 = new WebDriverWait(driver, 160);
	wait05.until(ExpectedConditions.visibilityOf(Auth_Selected_Button)); 

	Auth_comments.sendKeys("Approved");

	/*Taking screenshot */
	Screenshots shot2=new Screenshots(driver);
	shot2.ScreenShot_SBCore_US();

	Auth_Selected_Button.click();

}

//auth resp 2

public void BA_auth2_US()
{


	WebDriverWait wait06 = new WebDriverWait(driver, 160);
	wait06.until(ExpectedConditions.visibilityOf(Resp_identification2));

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();

	Resp_identification2.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Authorize)); 

	Select st = new Select(Justification);
	st.selectByVisibleText("Approved acceptable business cost");

	/*Taking screenshot */
	Screenshots shot1=new Screenshots(driver);
	shot1.ScreenShot_SBCore_US();
	Authorize.click();

	WebDriverWait wait05 = new WebDriverWait(driver, 160);
	wait05.until(ExpectedConditions.visibilityOf(Auth_Selected_Button)); 

	Auth_comments.sendKeys("Approved");

	/*Taking screenshot */
	Screenshots shot2=new Screenshots(driver);
	shot2.ScreenShot_SBCore_US();

	Auth_Selected_Button.click();
	
}

//auth 3rd response

public void BA_auth3_US()
{

	WebDriverWait wait03 = new WebDriverWait(driver, 160);
	wait03.until(ExpectedConditions.visibilityOf(Resp_identification3)); 

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();
	
	Resp_identification3.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Authorize)); 

	Select st = new Select(Justification);
	st.selectByVisibleText("Approved acceptable business cost");

	/*Taking screenshot */
	Screenshots shot1=new Screenshots(driver);
	shot1.ScreenShot_SBCore_US();
	Authorize.click();

	WebDriverWait wait05 = new WebDriverWait(driver, 160);
	wait05.until(ExpectedConditions.visibilityOf(Auth_Selected_Button)); 

	Auth_comments.sendKeys("Approved");

	/*Taking screenshot */
	Screenshots shot2=new Screenshots(driver);
	shot2.ScreenShot_SBCore_US();

	Auth_Selected_Button.click();

}
}
