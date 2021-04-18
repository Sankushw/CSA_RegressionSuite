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
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step02_AddResponse_NewApp
{

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_sbActions.xls";
	//public static String xlsFilePath="C:\\Users\\UdayKotipalli\\Downloads\\SBActions_core\\SBActions_core\\src\\testdata\\testdata.xls";
	public String sheet="Login";	

	// Define the element 
	@FindBy (xpath = "//button[contains(text(),'Log in')]") private WebElement Login_Button ;
	@FindBy ( xpath = ".//button[@id='continue-button']" ) private WebElement ContinueButton ;
	@FindBy ( id = "username" ) private WebElement UsernameField ;
	@FindBy ( id = "password" ) private WebElement PasswordField ;
	@FindBy ( id = "signinbutton" ) private WebElement SigninButton ;
	@FindBy (xpath = "//button[@name='search']" ) private WebElement search_tab ;
	@FindBy ( xpath= "//input[@placeholder='Request No']" ) private WebElement Req_Num_field ;
	@FindBy (xpath ="//*[@touranchor='search-form']/div/div[1]/div/button[2] ") private WebElement Search_btn ; 
	@FindBy ( xpath = "//mat-table/mat-row/mat-cell[1]" ) private WebElement Request_Number_link ;
	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[4]/td[3]") private WebElement DOB_InCalender;
	@FindBy (xpath="//input[@ng-reflect-name='expectedEndDate']") private WebElement EndDate_Field ;
	@FindBy (xpath= "//input[@ng-reflect-name='expectedStartDate']") private WebElement FromDate_field;
	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[2]/td[5]") private WebElement FromDate_InCalender;
	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[4]/td[5]") private WebElement EndDate_InCalender;
	@FindBy ( xpath = "//select[@id='pagination-select-items-per-page-3']" ) private WebElement Navigation ;
	
	//Add Response and details
	@FindBy (xpath ="//button[contains(text(),'Add Response')]") private WebElement btn_Add_response; 
	@FindBy (xpath ="//span[@class='headerTitle' and contains(text(),'Attachments')]") private WebElement Attachments_Panel; 
	@FindBy (xpath ="//button[contains(@for,'file-uploader') and contains(text(),' Add Response File ')]") private WebElement Add_ResponseFile; 
	@FindBy (xpath ="//button[@class='_hj-OO1S1__styles__openStateToggle']") private WebElement feedback_PopUp; 

	@FindBy (xpath ="//span[@class='headerTitle' and contains(text(),'Response Details')]") private WebElement ResponseDetails_Panel; 
	@FindBy (xpath = "//button[contains(@for,'file-uploader') and contains(text(),' Add Response File ')]//parent::div/input" ) private WebElement ResponseFile ;

	@FindBy ( xpath= "//input[@formcontrolname='firstName']" ) private WebElement First_Name ;
	@FindBy ( xpath= "//input[@formcontrolname='middleName']" ) private WebElement Middle_Name ;
	@FindBy ( xpath= "//input[@formcontrolname='lastName']" ) private WebElement LastName_Surname ;
	@FindBy ( id="dobPickerOverlay") private WebElement DOB_Field ;

	@FindBy ( xpath= "//*[@formcontrolname='flowDownTerm']/div/ibm-radio/input[@id='radio-10']" ) private WebElement Flowdown_No ;
	@FindBy (xpath= "//*[@class='bx--checkbox-wrapper bx--form-item ng-star-inserted' and @ng-reflect-id='noneOfAbove']") private WebElement PreviousClientEmp_None ;

	@FindBy ( xpath="//input[@id='citizenShip']") private WebElement Citizenship_Field ;
	@FindBy (xpath= "//ul[@class='bx--list-box__menu bx--multi-select']/li[1]/div[1]") private WebElement Citizenship_Country;
	@FindBy ( xpath="//span[contains(text(),'Requirement Attributes')]") private WebElement RequirementAttributes_Panel ;
	@FindBy ( xpath="//input[@formcontrolname='expectedEndDate']") private WebElement ToDate_Field ;


	@FindBy (xpath= "//button[@class='bx--list-box__field']") private WebElement Skill_Level;
	@FindBy (xpath= "//div[@class='bx--list-box__menu-item__option' and contains(text(),'Band 6')]") private WebElement Skill_Level_Value;
	
	@FindBy ( xpath="//span[@class='headerTitle' and contains(text(),'Response Pricing')]") private WebElement ResponsePricing_Panel ;
	@FindBy ( xpath="//input[@formcontrolname='straightTimeBillRate']") private WebElement ST_BillRate ;
	@FindBy ( xpath="//input[@formcontrolname='straightWages']") private WebElement ST_Wage ;	
	//@FindBy ( xpath="//clapp-skill-response-detail[@class='ng-star-inserted']/div[3]/button[2]") private WebElement Save_Button ;
	@FindBy ( xpath="//clapp-skill-response-detail[@class='ng-star-inserted']/div[1]/button[2]") private WebElement Save_Button ;
	

	//Submit Page
	@FindBy (xpath = ".//*[@id='ibm-content-main']//input[@class='ibm-btn-arrow-pri' and @title='Submit responses' ]" ) private WebElement Submit_responses ;
	@FindBy (xpath = ".//input[@value='Withdraw response']") private WebElement Withdraw_response;
	@FindBy (xpath = "//input[@id='selectAll']/parent::label[@class='container']/span[@class='checkmark']") private WebElement SelectAll_Checkbox;
	@FindBy (xpath = "//button[@ibmbutton='secondary' and contains(text(),'Submit')]") private WebElement Submit_Button;
	@FindBy (xpath = "//button[@ibmbutton='primary' and contains(text(),'Confirm submit')]") private WebElement ConfirmSubmit_Button;
	@FindBy (xpath = "//input[@id='checkBoxId-001-0']/parent::label[@class='container']/span[@class='checkmark']") private WebElement Resp1_Checkbox;
	@FindBy (xpath = "//table[@class='custom-grid']/div[@class='ng-star-inserted']/tr[1]/td[2]/a") private WebElement Resp1_name;
	@FindAll({@FindBy(xpath = "//div[@class='mat-drawer-backdrop ng-star-inserted mat-drawer-shown']")}) private List<WebElement>  ResponseForm_OverLay ;
	@FindAll({@FindBy(xpath = "//section[@class='bx--modal bx--modal-tall is-visible']")}) private List<WebElement>  ActionWindow_OverLay ;
	
	// Initialize the web elements 
	public Step02_AddResponse_NewApp (WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	// Function to login to the application
	public void login(){

		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Login_Button));

		Login_Button.click();

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(UsernameField));

		if (ContinueButton.isDisplayed())
		{
			System.out.println("continue button is Present");  

			//UsernameField.clear();
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 2, 0));

			ContinueButton.click();

			WebDriverWait wait9 = new WebDriverWait(driver, 160);
			wait9.until(ExpectedConditions.visibilityOf(SigninButton));

			System.out.println("login button there");
			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 2, 1));
			System.out.println("pw entered");

			/*Taking screenshot */
			Screenshots shot=new Screenshots(driver);
			shot.ScreenShot_SBCore_US();

			SigninButton.click();
			System.out.println("login button clicked");
		} else
		{
			System.out.println("Continue button is not Present");  

			WebDriverWait wait2 = new WebDriverWait(driver, 160);
			wait2.until(ExpectedConditions.visibilityOf(UsernameField));

			UsernameField.clear();
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 2, 0));

			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 2, 1));

			/*Taking screenshot */
			Screenshots shot1=new Screenshots(driver);
			shot1.ScreenShot_SBCore_US();
			SigninButton.click();
		}  

	}

	public void openRequest() throws InterruptedException
	{

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(search_tab));
		search_tab.click();

		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Req_Num_field));

		Req_Num_field.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
		Search_btn.click();
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_SBCore_US();
		System.out.println("took");
		Request_Number_link.click();
		
		WebDriverWait wait6 = new WebDriverWait(driver, 160);
        wait6.until(ExpectedConditions.visibilityOf(btn_Add_response));
        
        System.out.println("Searched and Opened the request " + Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15)); 
        if (feedback_PopUp.isDisplayed())
		{
			feedback_PopUp.click();
			System.out.println("feedback PopUp is Present and minimised");
		}
        
	}


	//Add Response and details
	public void FillRespdetails(String First_Name_temp , String Middle_Name_temp , String LastName_Surname_temp, String rate, String overtime) throws IOException, InterruptedException
	{
		waitForFormOverlayDisappear();
		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.elementToBeClickable(btn_Add_response));
		//Thread.sleep(10000);
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_SBCore_US();
		btn_Add_response.click();
		System.out.println("Add Response Button Clicked");
		//Attachment Panel
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Attachments_Panel));
		Actions act= new Actions(driver);
		act.sendKeys(Keys.PAGE_DOWN).build().perform();
		Attachments_Panel.click();
		System.out.println("Attachment Panel Clicked");
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(ResponseFile));
		
		String AttachmentPath = System.getProperty("user.dir") + "\\src\\testdata\\Response attachment.txt";
		ResponseFile.sendKeys(AttachmentPath);
		System.out.println("Attached Response File");
		
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_SBCore_US();
		//response details panel
		ResponseDetails_Panel.click();
		System.out.println("Response Panel Clicked");
		First_Name.sendKeys(First_Name_temp);
		System.out.println("Entered First Name");
		//Middle_Name.sendKeys(Middle_Name_temp);
		LastName_Surname.sendKeys(LastName_Surname_temp);
		System.out.println("Entered Last Name");
		DOB_Field.click();
		System.out.println("DOB field clicked");
		Thread.sleep(1000);
		DOB_InCalender.click();
		System.out.println("DOB Entered");
		WebDriverWait wait14 = new WebDriverWait(driver, 160);
		wait14.until(ExpectedConditions.elementToBeClickable(Citizenship_Field));
		Citizenship_Field.click();
		System.out.println("Citizenship field clicked");
		Thread.sleep(1000);
		Citizenship_Field.sendKeys("united states");
		System.out.println("Country Selected");
		//Citizenship_Field.sendKeys("test");
		Thread.sleep(2000);
		//Citizenship_Country.click();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Citizenship_Country);
		PreviousClientEmp_None.click();
		System.out.println("PreviousClientEmp None Selected");
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_SBCore_US();
		//Requirements attribute panel
		executor.executeScript("arguments[0].click();", RequirementAttributes_Panel);
		System.out.println("RequirementAttributes Panel Clicked");
		executor.executeScript("arguments[0].click();", FromDate_field);
		System.out.println("FromDate Field Selected");
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", FromDate_InCalender);
		System.out.println("FromDate Incalender Selected");
		executor.executeScript("arguments[0].click();", EndDate_Field);
		System.out.println("EndDate Field Selected");
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", EndDate_InCalender);
		System.out.println("EndDate Incalender Selected");
		executor.executeScript("arguments[0].click();", Skill_Level);
		System.out.println("Skill_Level Clicked");
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", Skill_Level_Value);
		System.out.println("Skill_Level Value Selected");
		/*Taking screenshot */
		Screenshots shot3=new Screenshots(driver);
		shot3.ScreenShot_SBCore_US();
		//pricing panel
		executor.executeScript("arguments[0].click();", ResponsePricing_Panel);
		System.out.println("ResponsePricing Panel Clicked");
		ST_BillRate.sendKeys("30");
		System.out.println("ST Bill Value Entered");
		ST_Wage.sendKeys("30");
		System.out.println("ST Wage Value Entered");
		/*Taking screenshot */
		Screenshots shot4=new Screenshots(driver);
		shot4.ScreenShot_SBCore_US();
		Save_Button.click();
		System.out.println("Response added and saved");
	}

	public void Submit() throws InterruptedException
	{
		waitForFormOverlayDisappear();
		isloadComplete(driver);
		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Resp1_Checkbox));
				
		SelectAll_Checkbox.click();
		Thread.sleep(500);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Submit_Button);
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(ConfirmSubmit_Button));	
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_SBCore_US();
		executor.executeScript("arguments[0].click();", ConfirmSubmit_Button);
		System.out.println("Response submitted");
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_SBCore_US();

	}

	 
	public static boolean isloadComplete(WebDriver driver)
	{
	    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
	            || ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}
	
	public void waitForFormOverlayDisappear() throws InterruptedException
	{
		int count=0;
		while(ResponseForm_OverLay.size()!=0 && count<20)
		{
			Thread.sleep(1000);
			count++;
		}}
	public void waitForActionWindowOverlayDisappear() throws InterruptedException
	{
		int count=0;
		while(ActionWindow_OverLay.size()!=0 && count<20)
		{
			Thread.sleep(1000);
			count++;
		}}
}
