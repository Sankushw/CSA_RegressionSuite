package expOActions_US;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

public class Step07_PO_Auth {
 
private WebDriver driver;
public String sheet="Login";
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";

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
@FindBy(xpath=".//*[@id='content-main']/form/table[3]/tbody/tr[5]/td[1]//input[@id='FLD_MULTI_LINE_ITEMS1']") private WebElement  Select_Checkbox_Cand2 ;
@FindAll({@FindBy(id="FLD_MULTI_LINE_ITEMS1")}) private List<WebElement>  Select_Checkbox ;
@FindBy (name="btnSubmitSelCand") private WebElement Submit_Selected_Responses ;
@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
@FindBy (name="btnConfirmSubmit") private WebElement Confirm_submission ;
@FindBy ( xpath=".//input[@id='finalConfirmation']" ) private WebElement OK_button ;
@FindBy(xpath = ".//a[text()='Return to request header']") private WebElement  returnToRequest ;


// Initialize the web elements 
public Step07_PO_Auth(WebDriver driver)
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

	Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 4, 0));
	Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 4, 1));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Signin_Button.click();

}

//Function to click Create New Request button
public void authorize_PO ()
{

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Awaiting_PO_Auth_link));

	Awaiting_PO_Auth_link.click();
	WebDriverWait wait31 = new WebDriverWait(driver, 180);
	wait31.until(ExpectedConditions.visibilityOf(Candidates));

	Candidates.click();

	Req_Num_Search.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
	GO_reqnum.click();

	WebDriverWait wait02 = new WebDriverWait(driver, 180);
	wait02.until(ExpectedConditions.visibilityOf(Project_Name));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Project_Name.click();

	WebDriverWait wait03 = new WebDriverWait(driver, 180);
	wait03.until(ExpectedConditions.visibilityOf(Submit_Selected_Responses));

	//Select_Checkbox_Cand2.click();
	for (WebElement elt: Select_Checkbox){

		elt.click();
	} 

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

	Submit_Selected_Responses.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 180);
	wait04.until(ExpectedConditions.visibilityOf(Confirm_submission));

	String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1);  
	Select Rog = new Select(Requesting_Organization);
	Rog.selectByVisibleText(RO);

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");
	
	Confirm_submission.click();

	Step03_Finalize popup = new Step03_Finalize(driver);
	popup.isAlertPresent();
	
	WebDriverWait wait10 = new WebDriverWait(driver, 160);
	wait10.until(ExpectedConditions.visibilityOf( OK_button));
	
	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");
	
	OK_button.click();
	
	WebDriverWait wait11 = new WebDriverWait(driver, 160);
	wait11.until(ExpectedConditions.visibilityOf( returnToRequest));

		/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");

}

}
