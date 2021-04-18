package gPSG_DE;

import generics.Screenshots;
import lib.Excel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Step02_AddResponse_DE_NewApp
{

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_gpsg.xls";
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
	@FindBy ( xpath = " //mat-table/mat-row[1]/mat-cell[1]" ) private WebElement Request_Number_link ;

	@FindBy ( xpath = "//select[@id='pagination-select-items-per-page-3']" ) private WebElement Navigation ;
	@FindBy ( xpath = "//a[contains(text(),'T232P8')]" ) private WebElement RequestId1 ;
	@FindBy ( xpath = "//a[contains(text(),'T232P9')]" ) private WebElement RequestId2 ;

	//Add Response and details
	@FindBy (xpath ="//button[contains(text(),'Add Response')]") private WebElement btn_Add_response; 
	@FindBy (xpath ="//span[@class='headerTitle' and contains(text(),'Attachments')]") private WebElement Attachments_Panel; 
	@FindBy (xpath ="//button[contains(@for,'file-uploader') and contains(text(),' Add Response File ')]") private WebElement Add_ResponseFile; 
	@FindBy (xpath ="//button[contains(@for,'file-uploader') and contains(text(),' Add Response File ')]//parent::div/input" ) private WebElement ResponseFile ;
	@FindBy (xpath ="//button[contains(@for,'file-uploader') and contains(text(),' Add File ')]//parent::div/input" ) private WebElement OtherFile ;

	@FindBy (xpath ="//button[@class='_hj-OO1S1__styles__openStateToggle']") private WebElement feedback_PopUp; 

	@FindBy (xpath ="//span[@class='headerTitle' and contains(text(),'Response Details')]") private WebElement ResponseDetails_Panel; 


	@FindBy ( xpath= "//input[@formcontrolname='firstName']" ) private WebElement First_Name ;
	@FindBy ( xpath= "//input[@formcontrolname='middleName']" ) private WebElement Middle_Name ;
	@FindBy ( xpath= "//input[@formcontrolname='lastName']" ) private WebElement LastName_Surname ;
	@FindBy (xpath= "//input[@formcontrolname='candRefCode']") private WebElement CandReference_Field;
	@FindBy ( id="dobPickerOverlay") private WebElement DOB_Field ;
	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[4]/td[3]") private WebElement DOB_InCalender;

	@FindBy ( xpath= "//*[@formcontrolname='flowDownTerm']/div/ibm-radio/input[@id='radio-10']" ) private WebElement Flowdown_No ;
	@FindBy (xpath= "//*[@class='bx--checkbox-wrapper bx--form-item ng-star-inserted' and @ng-reflect-id='noneOfAbove']") private WebElement PreviousClientEmp_None ;

	@FindBy ( xpath="//input[@id='citizenShip']") private WebElement Citizenship_Field ;
	@FindBy (xpath= "//ul[@class='bx--list-box__menu bx--multi-select']/li[1]/div[1]") private WebElement Citizenship_Country;

	@FindBy ( xpath="//span[contains(text(),'Requirement Attributes')]") private WebElement RequirementAttributes_Panel ;
	@FindBy ( xpath="//span[@class='attribute-field-title' and contains(text(),'Planned Start Date')]/parent::div/following-sibling::div/span") private WebElement Requested_FromDate ;
	@FindBy ( xpath="//span[@class='attribute-field-title' and contains(text(),'Planned End Date')]/parent::div/following-sibling::div/span") private WebElement Requested_EndDate ;
	@FindBy ( xpath="//input[@ng-reflect-name='expectedEndDate']") private WebElement EndDate_Field ;
	@FindBy (xpath= "//input[@ng-reflect-name='expectedStartDate']") private WebElement FromDate_field;

	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[2]/td[5]") private WebElement FromDate_InCalender;
	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[4]/td[5]") private WebElement EndDate_InCalender;



	@FindBy (xpath= "//button[@class='bx--list-box__field']") private WebElement Skill_Level;
	@FindBy (xpath= "//div[@class='bx--list-box__menu-item__option' and contains(text(),'Band 6')]") private WebElement Skill_Level_Value;

	@FindBy ( xpath="//span[@class='headerTitle' and contains(text(),'Response Pricing')]") private WebElement ResponsePricing_Panel ;
	@FindBy ( xpath="//input[@formcontrolname='straightTimeBillRate']") private WebElement ST_BillRate ;
	@FindBy ( xpath="//input[@formcontrolname='straightWages']") private WebElement ST_Wage ;	
	@FindBy ( xpath="//clapp-skill-response-detail[@class='ng-star-inserted']/div[1]/button[2]") private WebElement Save_Button ;


	//Submit Page
	@FindBy (xpath = "//input[@id='selectAll']/parent::label[@class='container']/span[@class='checkmark']") private WebElement SelectAll_Checkbox;
	@FindBy (xpath = "//input[@id='checkBoxId-001-0']/parent::label[@class='container']/span[@class='checkmark']") private WebElement Resp1_Checkbox;
	@FindBy (xpath = "//table[@class='custom-grid']/div[@class='ng-star-inserted']/tr[1]/td[2]/a") private WebElement Resp1_name;

	@FindBy (xpath = "//button[@ibmbutton='secondary' and contains(text(),'Submit')]") private WebElement Submit_Button;
	@FindAll({@FindBy(xpath = "//button[@class='mat-drawer-backdrop ng-star-inserted mat-drawer-shown']")}) private List<WebElement>  ResponseForm_OverLay ;
	@FindAll({@FindBy(xpath = "//button[@class='bx--modal bx--modal-tall is-visible']")}) private List<WebElement>  ActionWindow_OverLay ;
	@FindBy (xpath = "//button[@ibmbutton='primary' and contains(text(),'Confirm submit')]") private WebElement ConfirmSubmit_Button;
	@FindBy (xpath = ".//input[@value='Withdraw response']") private WebElement Withdraw_response;
	@FindBy (xpath = "//span[contains(text(),'Dashboard')]") private WebElement Dashboard;


	// Initialize the web elements 
	public Step02_AddResponse_DE_NewApp (WebDriver driver)
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
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 14, 0));

			ContinueButton.click();

			WebDriverWait wait9 = new WebDriverWait(driver, 160);
			wait9.until(ExpectedConditions.visibilityOf(SigninButton));

			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 14, 1));


			/*Taking screenshot */
			Screenshots shot=new Screenshots(driver);
			shot.ScreenShot_GPSGCore_DE();

			SigninButton.click();
			System.out.println("Sign in button clicked after entering credentials");
		} else
		{
			System.out.println("Continue button is not Present");  

			WebDriverWait wait2 = new WebDriverWait(driver, 160);
			wait2.until(ExpectedConditions.visibilityOf(UsernameField));

			UsernameField.clear();
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 14, 0));

			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 14, 1));

			/*Taking screenshot */
			Screenshots shot1=new Screenshots(driver);
			shot1.ScreenShot_GPSGCore_DE();
			SigninButton.click();
			System.out.println("Sign in button clicked after entering credentials");

		}  

	}

	public void openRequest(String Req_Id) throws InterruptedException
	{

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(search_tab));
		search_tab.click();
		waitForFormOverlayDisappear();
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Req_Num_field));

		Req_Num_field.sendKeys(Req_Id);
		Search_btn.click();
		waitForFormOverlayDisappear();
		
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(Request_Number_link));

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_GPSGCore_DE();
		Request_Number_link.click();

		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(btn_Add_response));

		System.out.println("searched and Opened the request= "+Req_Id);
	}	
	public void SurveyPopUp_Check()
	{

		if (feedback_PopUp.isDisplayed())
		{
			feedback_PopUp.click();
			System.out.println("feedback PopUp is Present and minimised");
		}
		else
		{System.out.println("feedback PopUp is not Present");

		}
	}


	//Add Response
	public void FillResp_Details(String reference_id, String rate) throws InterruptedException 
	{

		waitForFormOverlayDisappear();
		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.elementToBeClickable(btn_Add_response));

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_GPSGCore_DE();

		btn_Add_response.click();

		//Attachment Panel
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Attachments_Panel));

		Attachments_Panel.click();

		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(OtherFile));

		String AttachmentPath = System.getProperty("user.dir") + "\\src\\testdata\\Response attachment.txt";
		OtherFile.sendKeys(AttachmentPath);
		System.out.println("attachment uploaded--Filled info in Attachment Panel");

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_GPSGCore_DE();

		//response details panel
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ResponseDetails_Panel);
		CandReference_Field.sendKeys(reference_id);

		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_GPSGCore_DE();
		System.out.println("Filled info in response details Panel");

		//Requirements attribute panel
		executor.executeScript("arguments[0].click();", RequirementAttributes_Panel);
		executor.executeScript("arguments[0].click();", FromDate_field);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", FromDate_InCalender);
		executor.executeScript("arguments[0].click();", EndDate_Field);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", EndDate_InCalender);
		executor.executeScript("arguments[0].click();", Skill_Level);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", Skill_Level_Value);

		/*Taking screenshot */
		Screenshots shot3=new Screenshots(driver);
		shot3.ScreenShot_GPSGCore_DE();
		System.out.println("Filled info in Requirements attribute  Panel");

		//pricing panel
		executor.executeScript("arguments[0].click();", ResponsePricing_Panel);
		ST_BillRate.sendKeys(rate);

		/*Taking screenshot */
		Screenshots shot4=new Screenshots(driver);
		shot4.ScreenShot_GPSGCore_DE();
		System.out.println("Filled info in pricing  Panel");

		Save_Button.click();
		System.out.println("Response added and saved");
	}

	public void Submit() throws InterruptedException
	{
		waitForFormOverlayDisappear();
		isloadComplete(driver);
		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Resp1_Checkbox));

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Resp1_Checkbox);
		
		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.elementToBeClickable(Submit_Button));
		Thread.sleep(1000);
	
		executor.executeScript("arguments[0].click();", Submit_Button);

		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(ConfirmSubmit_Button));	

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_GPSGCore_DE();
		executor.executeScript("arguments[0].click();", ConfirmSubmit_Button);

		System.out.println("Response submitted to requester");
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_GPSGCore_DE();

	}

	public void MoveToDashboard() throws InterruptedException
	{
		waitForActionWindowOverlayDisappear();
		isloadComplete(driver);

		Actions act= new Actions(driver);
		act.sendKeys(Keys.PAGE_UP, Keys.PAGE_UP).build().perform();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Dashboard);
	
		System.out.println("Clicked on Dashboard to search new request");
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


