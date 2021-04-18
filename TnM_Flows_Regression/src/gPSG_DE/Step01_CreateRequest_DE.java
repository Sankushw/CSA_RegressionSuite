package gPSG_DE;


import java.util.Iterator;
import java.util.Set;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import generics.Screenshots;
import lib.Excel;

public class Step01_CreateRequest_DE {

	private WebDriver driver;

	//Define the element 
	@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
	@FindBy (xpath = ".//*[@id='site-home']") private WebElement CSA_Homepage_Link;

	@FindBy (xpath = "//span[contains(text(),'Windows Security')]") private WebElement WindowsSecurity_Option;
	@FindBy (xpath = "//div[@class='passwordless-options']/div[@id='credsDiv']") private WebElement Use_W3Id_Credentials_Link;

	@FindBy (xpath = "//input[@name='username']") private WebElement Email_Box;
	@FindBy (xpath = "//input[@name='password']") private WebElement Pwd_Box;
	@FindBy (xpath = "//button[@id='login-button']") private WebElement SignIn_ButtonNewUI;

	@FindBy (xpath = "//label[@id='totp_label']") private WebElement Authenticator_App_option;

	@FindBy(xpath = ".//input[@placeholder='One-time password']")	private WebElement passcodeBox;
	@FindBy(xpath = ".//button[starts-with(@id,'submit')]")	private WebElement SubmitPasscode;

	@FindBy (xpath = ".//*[@id='content-main']/form[1]/div/span/input") private WebElement Create_New_Request;

	//Skill Request Page	
	@FindBy (id ="FLD_REQ_TYPE") private WebElement New_Request ;
	@FindBy ( id="FLD_NORMAL_TYPE1") private WebElement Time_Materials ;
	@FindBy (name = "btnContinue") private WebElement Continue ;

	//Select Requestor
	@FindBy  (id = "FLD_REQST_CO") private WebElement  Requesting_Company ;
	@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;

	// Job Role/Skill
	@FindBy ( xpath= ".//*[@id='JRSS_SELECTION1']") private WebElement Priced_JRSS;
	@FindBy ( id = "FLD_JOB_ROLE" ) private WebElement Select_JobRole ;
	@FindBy ( id = "FLD_SKILL_SET") private WebElement Select_SkillSet;


	//Project Creation
	@FindBy ( id="FLD_PROJ_NAME") private WebElement Project_Name;
	@FindBy ( id="FLD_CONTACT_NAME") private WebElement Customer_Name;
	@FindBy (id="FLD_IS_GLOBAL_RESOURCE0") private WebElement GlobalResource_No ;
	@FindBy ( id="FLD_CONTACT_NAME") private WebElement CustomerName_Refernce ;
	@FindBy ( xpath = ".//*[@id='FLD_IS_FED_CONTRACT']")  private WebElement Govt_FederalContract ;
	@FindBy ( xpath = ".//*[@name='btnAddContingentMgr']")  private WebElement Project_Task_Manager_AddButton ;
	@FindBy ( xpath = ".//*[@id='FLD_IS_TIME_APPROVAL_TASK1']")  private WebElement Will_Manager_perform_TimeApproval_Yes ;
	@FindBy ( xpath = ".//*[@id='FLD_IS_TIME_APPROVAL_TASK0']")  private WebElement Will_Manager_perform_TimeApproval_No ;
	@FindBy ( xpath = ".//*[@name='btnAddTimeApprovalMgr']")  private WebElement TimeApproverID_AddButton ;

	@FindBy ( xpath = ".//*[@name='FLD_EMP_WEB_ID']")  private WebElement Email_Id_TextBox ;
	@FindBy ( xpath = ".//*[@name='BTN_GO']")  private WebElement GoButton ;
	@FindBy (xpath = ".//*[@id='content-main']/table[4]/tbody/tr[2]/td[1]/a" ) private WebElement Name ;
	@FindBy ( xpath = ".//input[@name='TEMP TIME APPROVER BUTTON']")  private WebElement UseAsTimeApprover_Button ;
	@FindBy ( xpath = ".//input[@name='TEMP MANAGER FOR CR BUTTON']")  private WebElement UseAsManager_Button ;
	@FindBy ( id="FLD_CLIENT") private WebElement Client ;
	@FindBy ( id="FLD_BRAND") private WebElement Brand ;
	@FindBy ( id="FLD_SECTOR") private WebElement Sector ;
	@FindBy ( id="FLD_INDUSTRY") private WebElement Industry ;
	@FindBy ( id="fldRegulatedAcc1") private WebElement FDA ;
	@FindBy ( id="fldRegulatedAcc2") private WebElement FFIEC ;
	@FindBy ( id="fldRegulatedAcc4") private WebElement NREG ;
	@FindBy ( xpath = " .//*[@value='I'] " ) private WebElement Accounting_Type_Internal ;

	//Skill detail Location
	@FindBy ( id="FLD_WRK_LOC_CTRY" ) private WebElement Country_territory;
	@FindBy ( id="FLD_WRK_LOC_CITY" ) private WebElement City ;
	@FindBy ( id="FLD_WRK_LOC" ) private WebElement Work_Location ;
	@FindBy (xpath = ".//*[@id='FLD_ABOVE_MATRIX_RATES']") private WebElement Above_Matrix_Rate;

	@FindBy ( name="Continue" ) private WebElement Continue_2 ;
	@FindBy (id="FLD_ALT_WORK_LOC" ) private WebElement Alternate_Workloc ;
	@FindBy (id="FLD_FLOW_DOWN_TRMS" ) private WebElement FlowDown_Checkbox ;
	@FindBy ( xpath = "//*[@id='FLD_FLOW_DOWN_RESTRICTION1']") private WebElement Flowdown_NoRadio ;
	//Skill detail skill price
	@FindBy ( id="FLD_RQSTD_SKILL_LVL" ) private WebElement Skill_Level;
	@FindBy (id="FLD_RQSTD_PRICE_LVL" )  private WebElement Price_Level;
	@FindBy (id="FLD_QTY_SKILL_NEEDED" ) private WebElement Quantity ;
	@FindBy (name="FLD_ENGAGEMENT_RATE" ) private WebElement engagement_rates ;



	@FindBy ( xpath= ".//*[@id='content-main']/form/table[5]/tbody/tr[1]/td[3]//input[@name='FLD_EXPENSE']") private WebElement Other_Expense_Field;
	@FindBy ( xpath= ".//*[@id='content-main']/form/table[5]/tbody/tr[2]/td[3]//input[@name='FLD_EXPENSE']") private WebElement Travel_Expense_Field;


	@FindBy ( xpath= ".//*[@id='FLD_CURRENCY']") private WebElement Currency_field;

	//Supplier page
	@FindBy ( xpath= "//input[@name='fldSelectSuppForReq' and @value='2']") private WebElement secondary_supp;
	@FindBy ( xpath= "//input[@name='fldSelectSuppForReq' and @value='2']") private WebElement Primary_supp;
	@FindBy ( xpath= "//a[contains(text(), 'Deselect all')]") private WebElement Deselect_All;
	@FindBy ( xpath= "//*[contains(text(), 'WOI US TEST VENDER 1-A')]//preceding-sibling::input[@name='fldSuppliers']") private WebElement RadioButton_UStestVendor;
	@FindBy ( xpath= "//*[contains(text(), 'EUROPA TECHNICAL SOLUTIONS LTD')]//preceding-sibling::input[@name='fldSuppliers']") private WebElement RadioButton_EUROPA_TECHNICAL_SOLUTIONS;
	@FindBy ( xpath= "//textarea[@id='FLD_SUPPWARN_SUPPSELJUST']") private WebElement SuppSelectionJustification;
	@FindBy ( id="FLD_SUPPWARN_SUPPSELJUST") private WebElement Supp_JustificationText ;

	//Skill Summary Page
	@FindBy (xpath = ".//*[@value='Continue to request summary'] ") private WebElement ContinueToRequestSummary;

	//Review Skill request
	@FindBy (name = "Submit request") private WebElement SubmitRequest;

	//Request Created
	@FindBy ( xpath= ".//*[@id='content-main']/table[1]/tbody/tr/td[1]/h1 ") private WebElement RequestCreated_Header;
	@FindBy ( xpath= "//a[contains(text(),'Return to request')] ") private WebElement ReturnToRequest_Link;

	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_gpsg.xls";
	public String sheet="Login";

	// Initialize the web elements 
	public Step01_CreateRequest_DE(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	// Function to login to the application
	// Click on the link again as workaround 
	public void login() throws InterruptedException
	{

		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.visibilityOf(loginToContractor_Link));

		loginToContractor_Link.click();

		System.out.println("clicked on loginToContractor_Link");
		WebDriverWait wait00 = new WebDriverWait(driver, 180);
		wait00.until(ExpectedConditions.visibilityOf(WindowsSecurity_Option));
		Use_W3Id_Credentials_Link.click();
		System.out.println("clicked on Use_W3Id_Credentials_Link");

		//new login changes
		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(SignIn_ButtonNewUI));
		wait01.until(ExpectedConditions.elementToBeClickable(SignIn_ButtonNewUI));

		Email_Box.clear();
		Email_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 12, 0));
		Thread.sleep(1000);
		Pwd_Box.clear();
		Pwd_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 12, 1));

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Shakedown_US();
		Thread.sleep(1000);

		SignIn_ButtonNewUI.click();
		System.out.println("entered W3_Id credentials and clicked on Sign in Button");

		try{
			WebDriverWait wait1 = new WebDriverWait(driver, 50);
			wait1.until(ExpectedConditions.visibilityOf(Authenticator_App_option));

			Authenticator_App_option.click();
			System.out.println("Authenticator option is selected for authentication");
		}catch(NoSuchElementException | TimeoutException e)
		{
			System.out.println("Page to select Authenticator app option is not displayed");
			e.printStackTrace();
		}

		try {
			String otpKeyStr = "4G23MEFZC3BQY7RR"; // <- this 2FA secret key for csatestde1@c25a0161.toronto.ca.ibm.com

			Totp totp = new Totp(otpKeyStr);
			String twoFactorCode = totp.now();

			passcodeBox.sendKeys(twoFactorCode);
			SubmitPasscode.click();
			System.out.println("Entered OTP and clicked on Submit button");

			WebDriverWait wait02 = new WebDriverWait(driver, 100);
			wait02.until(ExpectedConditions.visibilityOf(Create_New_Request));
			System.out.println("Title of page= "+driver.getTitle());
		}
		catch (NoSuchElementException e) {
			System.out.println("OTP screen is not displayed");
			e.printStackTrace();
			WebDriverWait wait02 = new WebDriverWait(driver, 100);
			wait02.until(ExpectedConditions.visibilityOf(Create_New_Request));
			System.out.println("Title of page= "+driver.getTitle());
		}
	}

	public void Create_New_Request()
	{
		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(Create_New_Request));

		Create_New_Request.click();

	}
	//Skill Request Page
	public void Skill_Request(){
		WebDriverWait wait02 = new WebDriverWait(driver, 180);
		wait02.until(ExpectedConditions.visibilityOf(New_Request));

		New_Request.click();
		Time_Materials.click();

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_GPSGCore_DE();

		Continue.click();
	}
	//Select Requestor Page
	public void Select_Requestor(){

		WebDriverWait wait = new WebDriverWait(driver, 160);
		wait.until(ExpectedConditions.visibilityOf(Requesting_Company)); 

		String RC = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 0);
		String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 1);  

		Select Rcom = new Select(Requesting_Company);
		Rcom.selectByVisibleText(RC);
		Select Rog = new Select(Requesting_Organization);
		Rog.selectByVisibleText(RO);

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_GPSGCore_DE();

		Continue.click();		
	}

	//JRSS
	public void Select_JRSS(){

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(Priced_JRSS)); 

		Priced_JRSS.click();	

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(Select_JobRole));

		String JR = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 2);
		String SS = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 3); 

		Select JRlistbox = new Select(Select_JobRole);
		JRlistbox.selectByVisibleText(JR);

		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(Select_SkillSet));

		Select SSlistbox = new Select(Select_SkillSet);
		SSlistbox.selectByVisibleText(SS);

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_GPSGCore_DE();

		Continue.click();
	}

	//request details
	public void Request_detailpage(String ProjectName){
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Project_Name)); 

		Project_Name.sendKeys(ProjectName);
		CustomerName_Refernce.sendKeys("test123");
		GlobalResource_No.click();
		Govt_FederalContract.click();

		Project_Task_Manager_AddButton.click();
		///////////////// adding Project_Task_Manager Id.
		// To handle all new opened window.

		String MainWindow1=driver.getWindowHandle();
		Set<String> s1=driver.getWindowHandles();		
		Iterator<String> i1=s1.iterator();		

		while(i1.hasNext())			
		{		
			String ChildWindow=i1.next();		

			if(!MainWindow1.equalsIgnoreCase(ChildWindow))			
			{    		
				System.out.println("Window handler Id of Parent window= "+MainWindow1);
				System.out.println("Window handler Id of Child window= "+ChildWindow);

				// Switching to Child window
				driver.switchTo().window(ChildWindow);	

				WebDriverWait wait8 = new WebDriverWait(driver, 160);
				wait8.until(ExpectedConditions.visibilityOf(Email_Id_TextBox));               

				Email_Id_TextBox.sendKeys("csatestgb1@c25a0161.toronto.ca.ibm.com");                                                                     
				GoButton.click();
				WebDriverWait wait07 = new WebDriverWait(driver, 180);
				wait07.until(ExpectedConditions.visibilityOf(Name));
				Name.click();
				WebDriverWait wait08 = new WebDriverWait(driver, 180);
				wait08.until(ExpectedConditions.visibilityOf(UseAsManager_Button));

				/*Taking screenshot */
				Screenshots shot=new Screenshots(driver);
				shot.ScreenShot_GPSGCore_DE();

				UseAsManager_Button.click();

			}		
		}		
		//Switching to Parent window i.e Main Window.z
		driver.switchTo().window(MainWindow1);	

		WebDriverWait wait10 = new WebDriverWait(driver, 160);
		wait10.until(ExpectedConditions.visibilityOf(Continue)); 

		String clientValue = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 5);
		String brandValue = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 6); 
		String sectorValue = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 7);
		String industryValue = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 8); 

		Select clientdropdown = new Select(Client);
		clientdropdown.selectByVisibleText(clientValue);

		Select branddropdown = new Select(Brand);
		branddropdown.selectByVisibleText(brandValue);

		Select sectordropdown = new Select(Sector);
		sectordropdown.selectByVisibleText(sectorValue);

		Select industrydropdown = new Select(Industry);
		industrydropdown.selectByVisibleText(industryValue);

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_GPSGCore_DE();

		Continue.click();
	}

	public void Skill_detailLocationpage()
	{

		WebDriverWait wait22 = new WebDriverWait(driver, 160);
		wait22.until(ExpectedConditions.visibilityOf(Country_territory));

		Select st = new Select(Country_territory);
		st.selectByVisibleText("Germany");

		Select ct = new Select(City);
		ct.selectByVisibleText("AACHEN");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(FlowDown_Checkbox.isSelected())
		{
			Flowdown_NoRadio.click();
		}
		else if(!FlowDown_Checkbox.isSelected())
		{
			FlowDown_Checkbox.click();
			WebDriverWait wait13 = new WebDriverWait(driver, 160);
			wait13.until(ExpectedConditions.visibilityOf( Flowdown_NoRadio));
			Flowdown_NoRadio.click();

		}

		Continue_2.click();
		//checking pop up
		isAlertPresent();
	}
	//Skill detail skill price
	public void Skill_detail_skillpricepage(String Currency, String EngagementRates)

	{
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(Skill_Level));

		String SL_Value = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 12); 
		String PL_Value = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 13); 

		Select SLDropdown = new Select(Skill_Level);
		SLDropdown.selectByVisibleText(SL_Value);

		Select PLDropdown = new Select(Price_Level);
		PLDropdown.selectByVisibleText(PL_Value);

		Quantity.clear();
		isAlertPresent();
		Quantity.sendKeys("1");

		Select CurrDropdown = new Select(Currency_field);
		CurrDropdown.selectByVisibleText(Currency);

		Select enag_rates = new Select(engagement_rates);
		enag_rates.selectByVisibleText(EngagementRates);
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_GPSGCore_DE();
		Continue_2.click();

	}
	//Supplier Selection Page
	public String SupplierSelectionPage() throws InterruptedException
	{

		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(secondary_supp));

		secondary_supp.click();
		Thread.sleep(1000);
		Deselect_All.click();
		Thread.sleep(1000);
		RadioButton_EUROPA_TECHNICAL_SOLUTIONS.click();

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_GPSGCore_DE();
		Continue.click();

		WebDriverWait wait9 = new WebDriverWait(driver, 160);
		wait9.until(ExpectedConditions.visibilityOf(Supp_JustificationText)); 

		Supp_JustificationText.sendKeys("Test");

		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_GPSGCore_DE();

		Continue.click();

		//Summary Page
		WebDriverWait wait7 = new WebDriverWait(driver, 160);
		wait7.until(ExpectedConditions.visibilityOf(ContinueToRequestSummary));

		/*Taking screenshot */
		Screenshots shot3=new Screenshots(driver);
		shot3.ScreenShot_GPSGCore_DE();

		ContinueToRequestSummary.click();

		WebDriverWait wait8 = new WebDriverWait(driver, 160);
		wait8.until(ExpectedConditions.visibilityOf(SubmitRequest));

		/*Taking screenshot */
		Screenshots shot4=new Screenshots(driver);
		shot4.ScreenShot_GPSGCore_DE();

		SubmitRequest.click();

		WebDriverWait wait11 = new WebDriverWait(driver, 160);
		wait11.until(ExpectedConditions.visibilityOf(ReturnToRequest_Link));

		String header=RequestCreated_Header.getText();
		System.out.println("header ="+header);
		System.out.println("header length ="+header.length());

		String RequestNumber = header.substring((header.length()-7),(header.length()-1) );
		System.out.println("REQUEST Number ="+RequestNumber);

		/*Taking screenshot */
		Screenshots shot5=new Screenshots(driver);
		shot5.ScreenShot_GPSGCore_DE();
		return RequestNumber;
	}
	public void ClickHomePage()
	{
		CSA_Homepage_Link.click();
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

	}
	public static boolean isloadComplete(WebDriver driver)
	{
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
				|| ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}

}




