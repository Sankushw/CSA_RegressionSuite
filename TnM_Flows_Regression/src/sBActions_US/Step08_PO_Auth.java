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

public class Step08_PO_Auth {

private WebDriver driver;
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
public String sheet="Login";

// Define the element 
@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
@FindBy ( id="btn_signin") private WebElement Signin_Button ;
@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Awaiting project office action')]") private WebElement Awaiting_PO_Auth_link ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Candidates')]") private WebElement Candidates ;
@FindBy (id="FLD_REQUEST_NUMBER") private WebElement Req_Num_Search ;
@FindBy (name="btnGo") private WebElement GO_reqnum ;
@FindBy (xpath =" .//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[3]/a " ) private WebElement Request_Number ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[4]/a" ) private WebElement Project_Name ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[5]/a" ) private WebElement Skill_link ;
@FindBy(name="FLD_MULTI_LINE_ITEMS") private WebElement  Select_Checkbox ;
@FindBy (name="btnSubmitSelCand") private WebElement Submit_Selected_Responses ;
@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
@FindBy (name="btnConfirmSubmit") private WebElement Confirm_submission ;
@FindBy ( xpath=".//input[@id='finalConfirmation']" ) private WebElement OK_button ;
@FindBy(xpath = ".//a[text()='Return to request header']") private WebElement  returnToRequest ;




// Initialize the web elements 
public Step08_PO_Auth(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}



// Function to login to the application
// Click on the link again as workaround 
public void login(){

	WebDriverWait wait00 = new WebDriverWait(driver, 180);
	wait00.until(ExpectedConditions.visibilityOf(loginToContractor_Link));

	loginToContractor_Link.click();

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Signin_Button));

	Username_Box.clear();
	Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 4, 0));
	Password_Box.clear();
	Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 4, 1));

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();

	Signin_Button.click();

}


//Function to click Create New Request button
public void authorize_PO ()
{

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Awaiting_PO_Auth_link));

	Awaiting_PO_Auth_link.click();

	WebDriverWait wait02 = new WebDriverWait(driver, 180);
	wait02.until(ExpectedConditions.visibilityOf(Candidates));

	Candidates.click();

	WebDriverWait wait03 = new WebDriverWait(driver, 180);
	wait03.until(ExpectedConditions.visibilityOf(Req_Num_Search));

	Req_Num_Search.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
	GO_reqnum.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 180);
	wait04.until(ExpectedConditions.visibilityOf(Project_Name));

	/*Taking screenshot */
	Screenshots shot=new Screenshots(driver);
	shot.ScreenShot_SBCore_US();

	Project_Name.click();

	WebDriverWait wait05 = new WebDriverWait(driver, 180);
	wait05.until(ExpectedConditions.visibilityOf(Submit_Selected_Responses));

	Select_Checkbox.click();

	/*Taking screenshot */
	Screenshots shot1=new Screenshots(driver);
	shot1.ScreenShot_SBCore_US();

	Submit_Selected_Responses.click();

	WebDriverWait wait06 = new WebDriverWait(driver, 180);
	wait06.until(ExpectedConditions.visibilityOf(Confirm_submission));

	String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1);  
	Select Rog = new Select(Requesting_Organization);
	Rog.selectByVisibleText(RO);

	/*Taking screenshot */
	Screenshots shot2=new Screenshots(driver);
	shot2.ScreenShot_SBCore_US();

	Confirm_submission.click();
	// Is pop present
	isAlertPresent();
	
	WebDriverWait wait10 = new WebDriverWait(driver, 160);
	wait10.until(ExpectedConditions.visibilityOf( OK_button));
	
	/*Taking screenshot */
	Screenshots shot3=new Screenshots(driver);
	shot3.ScreenShot_SBCore_US();

	OK_button.click();

	WebDriverWait wait7 = new WebDriverWait(driver, 160);
	wait7.until(ExpectedConditions.visibilityOf( returnToRequest));

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
