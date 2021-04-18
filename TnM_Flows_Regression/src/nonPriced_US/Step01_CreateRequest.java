package nonPriced_US;

import java.io.IOException;
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


public class Step01_CreateRequest {

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_nonPriced.xls";
	public String sheet="Login";

	// Define the element 
	@FindBy ( id="details-button") private WebElement Advanced ;
	@FindBy ( id="proceed-link") private WebElement AcceptSSL_Error ;

	@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
	
	@FindBy (xpath = "//div[@class='passwordless-options']/div[@id='credsDiv']") private WebElement Use_W3Id_Credentials_Link;
	@FindBy (xpath = "//span[contains(text(),'Windows Security')]") private WebElement WindowsSecurity_Option;

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
	@FindBy ( xpath= ".//*[@id='JRSS_SELECTION2']") private WebElement NonPriced_JRSS;
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

	@FindBy (xpath = ".//input[@id='btnAdd']" ) private WebElement Project_Coordinator_AddButton ;
	@FindBy ( xpath= "//input[@name='TEMP TECH COORD BUTTON']") private WebElement UseAsPC_Button ;
	@FindBy ( xpath= "//input[@id='btnAddRes']") private WebElement Response_Reviwer_AddButton ;

	@FindBy ( id="FLD_CLIENT") private WebElement Client ;
	@FindBy ( id="FLD_BRAND") private WebElement Brand ;
	@FindBy ( id="FLD_SECTOR") private WebElement Sector ;
	@FindBy ( id="FLD_INDUSTRY") private WebElement Industry ;
	@FindBy ( id="fldRegulatedAcc1") private WebElement FDA ;
	@FindBy ( id="fldRegulatedAcc2") private WebElement FFIEC ;
	@FindBy ( id="fldRegulatedAcc4") private WebElement NREG ;
	@FindBy ( xpath = " .//*[@value='I'] " ) private WebElement Accounting_Type ;

	//Skill detail Location
	@FindBy ( id="FLD_WRK_LOC_STATE" ) private WebElement State_Region_Province;
	@FindBy ( id="FLD_WRK_LOC_CITY" ) private WebElement City ;
	@FindBy ( id="FLD_WRK_LOC" ) private WebElement Work_Location ;
	@FindBy ( name="Continue" ) private WebElement Continue_2 ;
	@FindBy (id="FLD_ALT_WORK_LOC" ) private WebElement Alternate_Workloc ;
	@FindBy (id="FLD_FLOW_DOWN_TRMS" ) private WebElement FlowDown_Checkbox ;
	@FindBy ( xpath = "//*[@id='FLD_FLOW_DOWN_RESTRICTION1']") private WebElement Flowdown_NoRadio ;

	//Skill detail skill price
	@FindBy ( id="FLD_RQSTD_SKILL_LVL" ) private WebElement Skill_Level;
	@FindBy (id="FLD_RQSTD_PRICE_LVL" )  private WebElement Price_Level;
	@FindBy (id="FLD_QTY_SKILL_NEEDED" ) private WebElement Quantity ;
	@FindBy (id="FLD_ST" ) private WebElement ST_Rate ;
	@FindBy (id="FLD_OT" ) private WebElement OT_Rate ;


	// supplier selection page
	@FindBy ( xpath= "//input[@name='fldSelectSuppForReq' and @value='2']") private WebElement secondary_supp;
	@FindBy ( xpath= "//input[@name='fldSelectSuppForReq' and @value='2']") private WebElement Primary_supp;
	@FindBy ( xpath= "//a[contains(text(), 'Deselect all')]") private WebElement Deselect_All;
	@FindBy ( xpath= "//*[contains(text(), 'WOI US TEST VENDER 1-A')]//preceding-sibling::input[@name='fldSuppliers']") private WebElement RadioButton_UStestVendor;
	//@FindBy ( xpath= "//*[@id='fldSuppliers7']") private WebElement RadioButton_UStestVendor;


	@FindBy ( xpath= "//textarea[@id='FLD_SUPPWARN_SUPPSELJUST']") private WebElement SuppSelectionJustification;
	@FindBy ( xpath= "//*[@id='FLD_SUPPLIER_LIST1']") private WebElement Contract_selection;

	//Skill Summary Page
	@FindBy (xpath = ".//*[@value='Continue to request summary'] ") private WebElement ContinueToRequestSummary;

	//Review Skill request
	@FindBy (name = "Submit request") private WebElement SubmitRequest;

	//Request Created
	@FindBy ( xpath= ".//*[@id='content-main']/table[1]/tbody/tr/td[1]/h1 ") private WebElement RequestCreated_Header;
	@FindBy ( xpath= "//a[contains(text(),'Return to request')] ") private WebElement ReturnToRequest_Link;


	// Initialize the web elements 
	public Step01_CreateRequest(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	// Function to login to the application
	public void login() throws IOException, InterruptedException{

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
		Email_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 4, 0));
		Thread.sleep(1000);
		Pwd_Box.clear();
		Pwd_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 4, 1));

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
			//String otpKeyStr = "47UORWOB2YBVMXQE";  // <- this 2FA secret key for csatestus2@c25a0161.toronto.ca.ibm.com
			String otpKeyStr = "WEIBVLBVWMVAZZPZ";  // <- this 2FA secret key for csatestus1@c25a0161.toronto.ca.ibm.com
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
	//Create Request
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
		Continue.click();

	}

	//Select Requestor Page
	public void Select_Requestor() throws IOException{

		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(Requesting_Company));

		String RC = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 0);
		String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1);  

		Select Rcom = new Select(Requesting_Company);
		Rcom.selectByVisibleText(RC);

		Select Rog = new Select(Requesting_Organization);
		Rog.selectByVisibleText(RO);

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_NP_US();

		Continue.click();		
	}

	//JRSS
	public void Select_JRSS()

	{

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(NonPriced_JRSS)); 

		NonPriced_JRSS.click();	

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(Select_JobRole));	

		String JR = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 2);
		String SS = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 3); 

		Select JRlistbox = new Select(Select_JobRole);
		JRlistbox.selectByVisibleText(JR);

		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(Select_SkillSet));

		Select SSlistbox = new Select(Select_SkillSet);
		SSlistbox.selectByVisibleText(SS);

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_NP_US();
		Continue.click();

	}

	//request details
	public void Request_detailpage()

	{

		WebDriverWait wait22 = new WebDriverWait(driver, 160);
		wait22.until(ExpectedConditions.visibilityOf(Project_Name));

		Project_Name.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 4));
		Customer_Name.sendKeys("Test_CustName123&@#");
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

				Email_Id_TextBox.sendKeys("csatestus1@c25a0161.toronto.ca.ibm.com");                                                                     
				GoButton.click();
				WebDriverWait wait07 = new WebDriverWait(driver, 180);
				wait07.until(ExpectedConditions.visibilityOf(Name));
				Name.click();
				WebDriverWait wait08 = new WebDriverWait(driver, 180);
				wait08.until(ExpectedConditions.visibilityOf(UseAsManager_Button));

				/*Taking screenshot */
				Screenshots shot1=new Screenshots(driver);
				shot1.ScreenShot_NP_US();

				UseAsManager_Button.click();

			}		
		}		
		//Switching to Parent window i.e Main Window.z
		driver.switchTo().window(MainWindow1);	

		WebDriverWait wait10 = new WebDriverWait(driver, 160);
		wait10.until(ExpectedConditions.visibilityOf(Continue)); 


		String clientValue = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 5);
		String brandValue = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 6); 
		String sectorValue = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 7);
		String industryValue = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 8); 

		Select clientdropdown = new Select(Client);
		clientdropdown.selectByVisibleText(clientValue);

		Select branddropdown = new Select(Brand);
		branddropdown.selectByVisibleText(brandValue);

		Select sectordropdown = new Select(Sector);
		sectordropdown.selectByVisibleText(sectorValue);

		Select industrydropdown = new Select(Industry);
		industrydropdown.selectByVisibleText(industryValue);

		FDA.click();
		FFIEC.click();
		NREG.click();
		Accounting_Type.click();

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_NP_US();

		Continue.click();
	}

	//skill detail Page
	public void Skill_detailLocationpage()

	{
		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(State_Region_Province));

		Select st = new Select(State_Region_Province);
		st.selectByVisibleText("Vermont");

		Select ct = new Select(City);
		ct.selectByVisibleText("ESSEX JUNCTION");
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
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_NP_US();

		Continue_2.click();
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

	//Skill detail skill price
	public void Skill_detail_skillpricepage() throws InterruptedException

	{

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(Skill_Level));

		String SL_Value = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 12); 
		//String PL_Value = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 13); 

		Select SLDropdown = new Select(Skill_Level);
		SLDropdown.selectByVisibleText(SL_Value);

		/*Select PLDropdown = new Select(Price_Level);
		PLDropdown.selectByVisibleText(PL_Value);*/

		Quantity.clear();
		isAlertPresent();
		Quantity.sendKeys("1");
		ST_Rate.clear();
		isAlertPresent();
		ST_Rate.sendKeys("3000");
		OT_Rate.clear();
		isAlertPresent();
		OT_Rate.sendKeys("300");

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_NP_US();

		Continue_2.click();

	}
	//Supplier Selection Page
	public void SupplierSelectionPage() throws InterruptedException
	{
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(secondary_supp));

		secondary_supp.click();
		Thread.sleep(1000);
		Deselect_All.click();
		Thread.sleep(1000);
		RadioButton_UStestVendor.click();

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_NP_US();

		Continue.click();

		//Skill detail Skill request
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(Contract_selection));

		Continue.click();

		//supplier warning page
		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(SuppSelectionJustification));

		SuppSelectionJustification.sendKeys("test");

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_NP_US();
		Continue.click();

		//Summary Page
		WebDriverWait wait7 = new WebDriverWait(driver, 160);
		wait7.until(ExpectedConditions.visibilityOf(ContinueToRequestSummary));

		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_NP_US();

		ContinueToRequestSummary.click();

		WebDriverWait wait8 = new WebDriverWait(driver, 160);
		wait8.until(ExpectedConditions.visibilityOf(SubmitRequest));

		/*Taking screenshot */
		Screenshots shot3=new Screenshots(driver);
		shot3.ScreenShot_NP_US();
		SubmitRequest.click();

		isloadComplete(driver);
		WebDriverWait wait9 = new WebDriverWait(driver, 160);
		wait9.until(ExpectedConditions.visibilityOf(ReturnToRequest_Link));
		String header=RequestCreated_Header.getText();
		System.out.println("header ="+header);
		System.out.println("header length ="+header.length());

		String RequestNumber = header.substring((header.length()-7),(header.length()-1) );
		System.out.println("REQUEST Number ="+RequestNumber);
		Excel.setCellValue(xlsFilePath, "Request_creation", 1, 15, RequestNumber );

		/*Taking screenshot */
		Screenshots shot4=new Screenshots(driver);
		shot4.ScreenShot_NP_US();
	}
	public static boolean isloadComplete(WebDriver driver)
	{
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
				|| ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}
}
