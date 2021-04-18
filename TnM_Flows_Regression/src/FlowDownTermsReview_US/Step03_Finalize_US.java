package FlowDownTermsReview_US;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;


import lib.Excel;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step03_Finalize_US {

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";

	// Define the element 
	@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
	@FindBy ( id="btn_signin") private WebElement Signin_Button ;
	@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
	@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;
	@FindBy ( xpath = ".//*[@id='left-nav']//a[contains(text(), 'Search')]" ) private WebElement LHS_Search_tab ;
	@FindBy ( id="FLD_REQ_NUM_SEARCH" ) private WebElement Request_Num_Fld ;
	@FindBy ( name="GO" ) private WebElement Search_GO_btn ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[1]/a" ) private WebElement Request_Num_link ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/table[1]/tbody/tr[1]/td[3]/a" ) private WebElement Requested_Skill ;


	@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[2]/td[1]//input[@name='tblcolCandId']") private WebElement Resp1_Check_non_fin;
	@FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[3]/td[1]//input[@name='tblcolCandId']") private WebElement Resp2_Check_non_fin;
	@FindAll({@FindBy(name="tblcolCandId")}) private List<WebElement>  Select_Checkbox ;

	@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[2]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp1_Check_fin ;
	@FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[3]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp2_Check_fin ;
	@FindBy ( name="btnRejectSel" ) private WebElement Btn_rejected_Selected ;
	@FindBy ( xpath=".//*[@id='FLD_REJ_REASON~0']" ) private WebElement Reject_Reason ;
	@FindBy ( xpath=".//*[@id='content-main']/div/span/input" ) private WebElement Reject_again ;



	@FindBy(id="NSR1") private WebElement Justification;
	@FindBy ( name="btnAddSelFin" ) private WebElement Btn_AddSelectedToFinalists ;

	@FindBy ( name="btnSubmitSel" ) private WebElement Btn_SubmitSelected ;

	//Hiring Approval ///////////////////



	@FindBy (xpath=".//*[@id='content-main']/table[2]/tbody/tr[4]/td[3]/input") private WebElement Reporting_Manager_btn_Cand1;
	@FindBy (xpath=".//*[@id='content-main']/table[3]/tbody/tr[4]/td[3]/input") private WebElement Reporting_Manager_btn_Cand2;

	@FindBy (name="FLD_EMP_WEB_ID") private WebElement Email_Id;
	@FindBy (name="BTN_GO") private WebElement GO_Button;
	@FindBy (xpath = ".//*[@id='content-main']/table[3]/tbody/tr[2]/td[1]/a" ) private WebElement Name ;
	@FindBy (name="TEMP REPT MGR BUTTON") private WebElement Use_As_Manager;


	@FindBy (xpath= ".//*[@id='content-main']/table[2]/tbody/tr[8]/td[3]//textarea[@id='FLD_DESC_SOFTWARE_TECH']") private WebElement softTech_Access_Cand1;
	@FindBy (xpath= ".//*[@id='content-main']/table[3]/tbody/tr[8]/td[3]//textarea[@id='FLD_DESC_SOFTWARE_TECH']") private WebElement softTech_Access_Cand2;


	@FindBy (xpath= ".//*[@id='FLD_SENSITIVE_TECH00']") private WebElement sensitive_tech_cand1_N;
	@FindBy (xpath= ".//*[@id='FLD_SENSITIVE_TECH10']") private WebElement sensitive_tech_cand2_N;


	@FindBy (name="btnContinueSubmission") private WebElement Continue_Submission1;

	@FindBy ( id="FLD_NON_METRO_HIRING_REQUEST_NUM" ) private WebElement Non_Metro ;
	@FindBy (id="FLD_REASON_NOT_HAVING_METRO_NUM") private WebElement Reason ;
	@FindBy (id="ContinueSubmit") private WebElement Continue_Submission;
	@FindBy (name="Continue") private WebElement Continue;
	//Submit confirmation page
	@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
	@FindBy (name="btnConfirmSubmit") private WebElement Confirm_Submission;

	// UNSPSC page
	@FindBy ( xpath=".//input[@id='finalConfirmation']" ) private WebElement OK_button ;
	@FindBy(xpath = ".//a[text()='Return to request header']") private WebElement  returnToRequest ;


	public String sheet="Login";
	// Initialize the web elements 
	public Step03_Finalize_US(WebDriver driver)
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
		Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 12, 0));
		Password_Box.clear();
		Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 12, 1));

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Default Currency_DE\\");

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Multiple Currency_DE\\");

		Signin_Button.click();

	}


	//Finalising candidates for GPSG Default currency request-----------------------------------------------	

	public void FinaliseSubmitReq1()
	{

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(LHS_Search_tab)); 

		LHS_Search_tab.click();

		WebDriverWait wait = new WebDriverWait(driver, 160);
		wait.until(ExpectedConditions.visibilityOf(Request_Num_Fld)); 

		Request_Num_Fld.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 4, 15));

		Search_GO_btn.click();

		WebDriverWait wait9 = new WebDriverWait(driver, 160);
		wait9.until(ExpectedConditions.visibilityOf(Request_Num_link)); 

		Request_Num_link.click();

		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(Requested_Skill)); 

		Requested_Skill.click();

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(Btn_AddSelectedToFinalists)); 

		//Resp1_Check_non_fin.click();
		//Resp2_Check_non_fin.click();

		for (WebElement elt: Select_Checkbox){

			elt.click();
		} 

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).highlight(Btn_AddSelectedToFinalists).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Default Currency_DE\\");

		Btn_AddSelectedToFinalists.click();

		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Btn_SubmitSelected));

		//Resp1_Check_fin.click();
		//Resp2_Check_fin.click();

		for (WebElement elt: Select_Checkbox){

			elt.click();
		} 

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).highlight(Btn_SubmitSelected).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Default Currency_DE\\");

		Btn_SubmitSelected.click();

		Non_Metro.click();
		Reason.sendKeys("I use Metro, but can't for this situation and have a management-approved bypass");

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Default Currency_DE\\");

		Continue_Submission.click();

		WebDriverWait wait7 = new WebDriverWait(driver, 160);
		wait7.until(ExpectedConditions.visibilityOf(Continue_Submission1));

		// Cand_1//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

				Email_Id.sendKeys("csatestde1@c25a0161.toronto.ca.ibm.com");                                                                     
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

				WebDriverWait wait32 = new WebDriverWait(driver, 160);
				wait32.until(ExpectedConditions.visibilityOf(Email_Id));

				Email_Id.sendKeys("csatestde1@c25a0161.toronto.ca.ibm.com");                                                                     
				GO_Button.click();
				WebDriverWait wait07 = new WebDriverWait(driver, 180);
				wait07.until(ExpectedConditions.visibilityOf(Name));
				Name.click();
				Use_As_Manager.click();

			}		
		}		
		//Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow2);	

		// code to capture screenshot //
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Default Currency_DE\\");

		Continue_Submission1.click();

		WebDriverWait wait8 = new WebDriverWait(driver, 160);
		wait8.until(ExpectedConditions.visibilityOf(Confirm_Submission));

		//submit confirmation page

		String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 4, 1);
		Select Rorg = new Select(Requesting_Organization);
		Rorg.selectByVisibleText(RO);

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Default Currency_DE\\");

		Confirm_Submission.click();
		isAlertPresent();

		WebDriverWait wait10 = new WebDriverWait(driver, 160);
		wait10.until(ExpectedConditions.visibilityOf( OK_button));

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Default Currency_DE\\");

		OK_button.click();

		WebDriverWait wait11 = new WebDriverWait(driver, 160);
		wait11.until(ExpectedConditions.visibilityOf( returnToRequest));

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Default Currency_DE\\");

	}



	//Finalising candidates for GPSG Multiple currency request-----------------------------------------------	


	public void FinaliseSubmitReq2()
	{

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(LHS_Search_tab)); 

		LHS_Search_tab.click();

		WebDriverWait wait = new WebDriverWait(driver, 160);
		wait.until(ExpectedConditions.visibilityOf(Request_Num_Fld)); 

		Request_Num_Fld.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 8, 15));

		Search_GO_btn.click();

		WebDriverWait wait9 = new WebDriverWait(driver, 160);
		wait9.until(ExpectedConditions.visibilityOf(Request_Num_link)); 

		Request_Num_link.click();	
		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(Requested_Skill)); 

		Requested_Skill.click();

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(Btn_AddSelectedToFinalists)); 

		//Resp1_Check_non_fin.click();
		//Resp2_Check_non_fin.click();

		for (WebElement elt: Select_Checkbox){

			elt.click();
		} 
		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).highlight(Btn_AddSelectedToFinalists).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Multiple Currency_DE\\");

		Btn_AddSelectedToFinalists.click();

		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Btn_SubmitSelected));

		//Resp1_Check_fin.click();
		//Resp2_Check_fin.click();

		for (WebElement elt: Select_Checkbox){

			elt.click();
		} 

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).highlight(Btn_SubmitSelected).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Multiple Currency_DE\\");

		Btn_SubmitSelected.click();

		Non_Metro.click();
		Reason.sendKeys("I use Metro, but can't for this situation and have a management-approved bypass");

		// code to capture screenshot //
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Multiple Currency_DE\\");

		Continue_Submission.click();

		WebDriverWait wait7 = new WebDriverWait(driver, 160);
		wait7.until(ExpectedConditions.visibilityOf(Continue_Submission1));


		// Cand_1//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

				Email_Id.sendKeys("csatestde1@c25a0161.toronto.ca.ibm.com");                                                                     
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

				WebDriverWait wait32 = new WebDriverWait(driver, 160);
				wait32.until(ExpectedConditions.visibilityOf(Email_Id));

				Email_Id.sendKeys("csatestde1@c25a0161.toronto.ca.ibm.com");                                                                     
				GO_Button.click();
				WebDriverWait wait07 = new WebDriverWait(driver, 180);
				wait07.until(ExpectedConditions.visibilityOf(Name));
				Name.click();
				Use_As_Manager.click();

			}		
		}		
		//Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow2);	

		// code to capture screenshot //
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Multiple Currency_DE\\");

		Continue_Submission1.click();

		WebDriverWait wait8 = new WebDriverWait(driver, 160);
		wait8.until(ExpectedConditions.visibilityOf(Confirm_Submission));

		//submit confirmation page


		String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 8, 1);
		Select Rorg = new Select(Requesting_Organization);
		Rorg.selectByVisibleText(RO);

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Multiple Currency_DE\\");

		Confirm_Submission.click();
		isAlertPresent();

		WebDriverWait wait10 = new WebDriverWait(driver, 160);
		wait10.until(ExpectedConditions.visibilityOf( OK_button));

		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Multiple Currency_DE\\");

		OK_button.click();

		WebDriverWait wait11 = new WebDriverWait(driver, 160);
		wait11.until(ExpectedConditions.visibilityOf( returnToRequest));

		/* code to capture screenshot */

		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\GPSG Flow\\DE\\Multiple Currency_DE\\");

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

