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

public class Step02_AddResponse
{

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login";	

	// Define the element 
	@FindBy (xpath = ".//*[@id='ibm-content-main']//a[contains(text(),'Sign in to CSA.')]"   ) private WebElement Sign_in_to_CSA ;
	@FindBy ( xpath = ".//button[@id='continue-button']" ) private WebElement ContinueButton ;
	@FindBy ( id = "username" ) private WebElement UsernameField ;
	@FindBy ( id = "password" ) private WebElement PasswordField ;
	@FindBy ( id = "signinbutton" ) private WebElement SigninButton ;
	@FindBy (xpath = ".//*[@id='ibm-primary-links']/li[5]/a[contains(text(), 'CSA search')] " ) private WebElement CSA_search_tab ;
	@FindBy ( xpath= "//input[@id='FLD_REQ_NUM_SEARCH']" ) private WebElement Req_Num_field ;
	@FindBy (xpath =".//*[@id='ibm-content-main']//input[@class='ibm-btn-arrow-pri'and @title='Search'] ") private WebElement Search_btn ; 
	@FindBy ( xpath = " .//*[@id='ibm-content-main']/div[2]/div/table/tbody/tr[2]/td[1]/a" ) private WebElement Request_Number_link ;

	//Add Response and details
	@FindBy (xpath =".//*[@id='ibm-content-main']//input[@class='ibm-btn-arrow-pri' and @title='Add response']") private WebElement btn_Add_response; 
	@FindBy ( id="FLD_CAND_FIRST_NM") private WebElement First_Name ;
	@FindBy ( id="FLD_CAND_MDL_NM") private WebElement Middle_Name;
	@FindBy ( id="FLD_CAND_LAST_NM_SURNM") private WebElement LastName_Surname ;
	@FindBy ( id="FLD_FLOW_DOWN_RESTRICTION1") private WebElement Flowdown_No ;
	@FindBy ( name="FLD_NOA_EMP") private WebElement PreviousClientEmp_No ;
	@FindBy ( id="FLD_CAND_DOB") private WebElement DOB_Field ;

	@FindBy ( id="FLD_CITIZENSHIP") private WebElement Citizenship ;
	@FindBy ( id="FLD_CAND_RES") private WebElement Resp_Attachment ;
	@FindBy (xpath= ".//*[@id='ibm-content-main']//div[4]//tr[2]//td[3]") private WebElement lbl_StartDate;
	@FindBy (xpath= ".//*[@id='ibm-content-main']//div[4]//tr[3]//td[3]") private WebElement lbl_EndDate;
	@FindBy (xpath= ".//*[@id='ibm-content-main']//div[4]//tr[4]//td[3]") private WebElement lbl_Skill_Level;
	@FindBy ( id="FLD_START_DT") private WebElement txt_StartDate ;
	@FindBy ( id="FLD_END_DT" ) private WebElement txt_EndDate ;
	@FindBy (name="FLD_SKILL_LVL") private WebElement  DD_SkillLevel ;
	@FindBy (xpath = ".//*[@id='ibm-content-main']//input[@class='ibm-btn-arrow-pri' and @title='Continue']" ) private WebElement Continue ;
	@FindBy (xpath = ".//*[@id='ibm-content-main']/form[1]/div/div/div[1]/div/table/tbody/tr[2]/td[2]/a") private WebElement Resp_Identification;

	//pricing
	@FindBy ( id="FLD_ST_BILL_RT" ) private WebElement ST_rate ;
	@FindBy ( id="FLD_OT_BILL_RATE" ) private WebElement OT_rate ;
	@FindBy ( id="FLD_ST_WAGE" ) private WebElement ST_wage ;
	@FindBy (xpath =".//*[@id='FLD_JUSTIFICATION']") private WebElement  Justification ;

	//Summary Page
	@FindBy (xpath = ".//*[@id='ibm-content-main']//input[@class='ibm-btn-arrow-sec' and @title='Edit Response details']") private WebElement Edit_Response_details;

	//Submit Page
	@FindBy (xpath = ".//*[@id='ibm-content-main']//input[@class='ibm-btn-arrow-pri' and @title='Submit responses' ]" ) private WebElement Submit_responses ;
	@FindBy (xpath = ".//input[@value='Reject all skills' and @type='submit']") private WebElement Reject_all_skills;
	@FindBy (xpath = ".//input[@value='Withdraw response']") private WebElement Withdraw_response;


	// Initialize the web elements 
	public Step02_AddResponse (WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	// Function to login to the application
	public void login(){

		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Sign_in_to_CSA));

		Sign_in_to_CSA.click();

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(UsernameField));

		if (ContinueButton.isDisplayed())
		{
			System.out.println("continue button is Present");  

			UsernameField.clear();
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

	public void openRequest()
	{

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(CSA_search_tab));
		CSA_search_tab.click();

		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Req_Num_field));
		
		Req_Num_field.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
		Search_btn.click();
	System.out.println("clicked");
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(Request_Number_link));
		System.out.println("waited");
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_SBCore_US();
		System.out.println("took");
		Request_Number_link.click();
		System.out.println("clickedreq");

	}


	//Add Response and details
	public void FillRespdetails(String First_Name_temp , String Middle_Name_temp , String LastName_Surname_temp, String rate, String overtime) throws IOException 
	{

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(btn_Add_response));

		btn_Add_response.click();

		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Continue));

		First_Name.sendKeys(First_Name_temp);
		Middle_Name.sendKeys(Middle_Name_temp);
		LastName_Surname.sendKeys(LastName_Surname_temp);
		DOB_Field.sendKeys("03/24");
		//Flowdown_No.click();

		Select ct = new Select(Citizenship);
		ct.selectByVisibleText("United States");

		PreviousClientEmp_No.click();

		String startDate = lbl_StartDate.getText().trim();
		txt_StartDate.sendKeys(startDate); 

		String endDate = lbl_EndDate.getText().trim();
		txt_EndDate.sendKeys(endDate); 

		String Skill = lbl_Skill_Level.getText().trim();
		Select sel = new Select(DD_SkillLevel);
		sel.selectByVisibleText(Skill); 

		Resp_Attachment.sendKeys("C:\\Users\\SanjayKushwaha\\Desktop\\Response attachment.txt");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("attachment Uploaded");

		Continue.click();

		//pricing page
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(ST_rate));

		ST_rate.clear();
		ST_rate.sendKeys(rate);


		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		OT_rate.clear();
		OT_rate.sendKeys(overtime);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ST_wage.clear();
		//driver.switchTo().alert().accept();
		ST_wage.sendKeys(rate);

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_SBCore_US();

		Continue.click();

		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(Justification));

		// Above matrix rate Justification  page 

		Justification.sendKeys("test for above matrix rate");

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_SBCore_US();

		Continue.click();

		//summary page

		WebDriverWait wait7 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(Edit_Response_details));

		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_SBCore_US();

		Continue.click();

		//main page

		WebDriverWait wait8 = new WebDriverWait(driver, 160);
		wait7.until(ExpectedConditions.visibilityOf(Submit_responses));

		/*Taking screenshot */
		Screenshots shot3=new Screenshots(driver);
		shot3.ScreenShot_SBCore_US();

	}

	public void Submit()
	{
		Submit_responses.click();

		WebDriverWait wait8 = new WebDriverWait(driver, 160);
		wait8.until(ExpectedConditions.visibilityOf(Withdraw_response));

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_SBCore_US();

	}
}
