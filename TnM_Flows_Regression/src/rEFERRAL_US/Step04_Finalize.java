package rEFERRAL_US;

import generics.Screenshots;
import lib.Excel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step04_Finalize {
 
  
  private WebDriver driver;
  public String str2;
  public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	
	// Define the element 
	@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
	@FindBy ( id="btn_signin") private WebElement Signin_Button ;
	@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
	@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;
	
	@FindBy ( xpath = ".//*[@id='left-nav']/div/a[4]" ) private WebElement RDM_Request_tab ;
	@FindBy ( xpath = ".//*[@id='left-nav']/div/a[8]" ) private WebElement LHS_Search_tab ;
	@FindBy ( id="FLD_REQUEST_NUMBER" ) private WebElement Request_Num_Fld ;
	@FindBy ( name="btnGo" ) private WebElement Search_GO_btn ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[3]/a" ) private WebElement Request_Num_link ;


	 @FindBy ( xpath = ".//*[@id='content-main']/form/table[1]/tbody/tr[1]/td[3]/a" ) private WebElement Skill_Hyperlink ;
	 //@FindBy(name="tblcolCandId") private WebElement  Resp1_Checkbox ;

	 @FindBy (xpath = ".//*[@id='nonFinalCandForm']/table[1]/tbody/tr[2]/td[1]//input[@name='tblcolCandId']") private WebElement Resp1_Check_non_fin;

	 

	
	 @FindBy(xpath = ".//*[@id='finalCandForm']/table[1]/tbody/tr[2]/td[1]//input[@name='tblcolCandId']") private WebElement  Resp1_Check_fin ;

	 
	// @FindBy ( name="btnRejectSel" ) private WebElement Btn_rejected_Selected ;
	 
	 @FindBy(id="NSR1") private WebElement Justification;
	 @FindBy ( name="btnAddSelFin" ) private WebElement Btn_AddSelectedToFinalists ;
	 
	 @FindBy ( name="btnSubmitSel" ) private WebElement Btn_SubmitSelected ;
	 //Hiring Approval 
	 

	 
	 @FindBy ( id="FLD_NON_METRO_HIRING_REQUEST_NUM" ) private WebElement Non_Metro ;
	 @FindBy (id="FLD_REASON_NOT_HAVING_METRO_NUM") private WebElement Reason ;
	 @FindBy (id="ContinueSubmit") private WebElement Continue_Submission;
	 @FindBy (name="btnContinueSubmission") private WebElement Continue_Submission1;
	 
	 
	
	 
	 
	 @FindBy (name="Continue") private WebElement Continue;
	 //Submit confirmation page
	 @FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
	 @FindBy (name="btnConfirmSubmit") private WebElement Confirm_Submission;
	 @FindBy (linkText="Return to request header") private WebElement ReturnToRequest_header;
	 
	 public String sheet="Login";
	// Initialize the web elements 
	public Step04_Finalize(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	// Function to login to the application
	
	public void loginAndOpenReq()
	{
		
        loginToContractor_Link.click();
		
	
		
		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(Signin_Button));
		
		
		Username_Box.clear();
		Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 23, 0));
		Password_Box.clear();
		Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 23, 1));
		
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Referral_US();
  
		Signin_Button.click();
		
	}
		public void FinaliseSubmit()
		{
			

			WebDriverWait wait = new WebDriverWait(driver, 160);
			wait.until(ExpectedConditions.visibilityOf(RDM_Request_tab)); 
				
				
			RDM_Request_tab.click();
				
				WebDriverWait wait2 = new WebDriverWait(driver, 160);
				wait2.until(ExpectedConditions.visibilityOf(Request_Num_Fld)); 
				
				
				Request_Num_Fld.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
				
				
				Search_GO_btn.click();
				
				WebDriverWait wait9 = new WebDriverWait(driver, 160);
				wait9.until(ExpectedConditions.visibilityOf(Request_Num_link)); 
				

				/*Taking screenshot */
				Screenshots shot=new Screenshots(driver);
				shot.ScreenShot_Referral_US();
				
				Request_Num_link.click();
				
				WebDriverWait wait10 = new WebDriverWait(driver, 160);
				wait10.until(ExpectedConditions.visibilityOf(Skill_Hyperlink)); 
				
                Skill_Hyperlink.click();
				
			
			
			 WebDriverWait wait3 = new WebDriverWait(driver, 160);
		     wait3.until(ExpectedConditions.visibilityOf(Btn_AddSelectedToFinalists)); 
		     
		     Resp1_Check_non_fin.click();
		     
		     /*Taking screenshot */
				Screenshots shot1=new Screenshots(driver);
				shot1.ScreenShot_Referral_US();		   
		     
		     Btn_AddSelectedToFinalists.click();
		     
		  /*   try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		     
			 WebDriverWait wait4 = new WebDriverWait(driver, 160);
		     wait4.until(ExpectedConditions.visibilityOf(Btn_SubmitSelected));*/
		     

		      WebDriverWait wait11 = new WebDriverWait(driver,120);
		      wait11.until(ExpectedConditions.elementToBeClickable(Resp1_Check_fin));
		      

		     Resp1_Check_fin.click();
		
		     /*Taking screenshot */
				Screenshots shot2=new Screenshots(driver);
				shot2.ScreenShot_Referral_US(); 	
		    		  
		 		Btn_SubmitSelected.click();
			
			
			
			//Hiring Approval page
		
				 WebDriverWait wait23 = new WebDriverWait(driver, 160);
			     wait23.until(ExpectedConditions.visibilityOf(Continue_Submission));
			     
			Non_Metro.click();
			
			    Select  hiringReason= new Select(Reason);
				hiringReason.selectByVisibleText("I use Metro, but can't for this situation and have a management-approved bypass");
				
			//Reason.sendKeys("I use Metro, but can't for this situation and have a management-approved bypass");
			
				/*Taking screenshot */
				Screenshots shot3=new Screenshots(driver);
				shot3.ScreenShot_Referral_US();
				
			Continue_Submission.click();
			
			
			
			
			 WebDriverWait wait8 = new WebDriverWait(driver, 160);
			 wait8.until(ExpectedConditions.visibilityOf(Confirm_Submission));
			 
			 //submit confirmation page
			
			 String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1);  
			 Select Rorg = new Select(Requesting_Organization);
				Rorg.selectByVisibleText(RO);
			 
			// Requesting_Organization.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1));
			 
				/*Taking screenshot */
				Screenshots shot4=new Screenshots(driver);
				shot4.ScreenShot_Referral_US();
			 
			 Confirm_Submission.click();
			 
	             	//Pop up handling
			   
			       driver.switchTo().alert().accept();
			        
			    
			       WebDriverWait wait12 = new WebDriverWait(driver, 160);
					 wait12.until(ExpectedConditions.visibilityOf(ReturnToRequest_header));
			    
					 /*Taking screenshot */
						Screenshots shot5=new Screenshots(driver);
						shot5.ScreenShot_Referral_US();
			
		}}
	 
	  
	  

