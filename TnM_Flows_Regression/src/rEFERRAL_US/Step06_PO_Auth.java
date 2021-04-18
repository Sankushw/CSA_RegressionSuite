package rEFERRAL_US;

import generics.Screenshots;
import lib.Excel;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step06_PO_Auth {
 
  
  private WebDriver driver;
	
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
	
	 @FindAll({@FindBy(name="FLD_MULTI_LINE_ITEMS")}) private List<WebElement>  Select_Checkbox ;
	 
	@FindBy (name="btnSubmitSelCand") private WebElement Submit_Selected_Responses ;
	 @FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
	@FindBy (name="btnConfirmSubmit") private WebElement Confirm_submission ;
	
	
	
	public String sheet="Login";
	
	// Initialize the web elements 
	public Step06_PO_Auth(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	// Function to login to the application
	// Click on the link again as workaround 
	public void login(){
		
		loginToContractor_Link.click();
  
		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(Signin_Button));
		
		
		Username_Box.clear();
		Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 4, 0));
		Password_Box.clear();
		Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 4, 1));
		
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Referral_US();
		
  
		Signin_Button.click();
  }
	
	
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	
	
		
		//Function to click Create New Request button
        public void authorize_PO ()
        {
        	
        	WebDriverWait wait01 = new WebDriverWait(driver, 180);
			wait01.until(ExpectedConditions.visibilityOf(Awaiting_PO_Auth_link));
			
		    Awaiting_PO_Auth_link.click();
    		Candidates.click();
    		
    		Req_Num_Search.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
    		GO_reqnum.click();

    		WebDriverWait wait02 = new WebDriverWait(driver, 180);
			wait02.until(ExpectedConditions.visibilityOf(Project_Name));
    		
			/*Taking screenshot */
			Screenshots shot=new Screenshots(driver);
			shot.ScreenShot_Referral_US();
    		
    	Project_Name.click();
    	
    	WebDriverWait wait03 = new WebDriverWait(driver, 180);
		wait03.until(ExpectedConditions.visibilityOf(Submit_Selected_Responses));
    	

for (WebElement elt: Select_Checkbox){

	elt.click();

} 
	
		
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Referral_US();
		
    	Submit_Selected_Responses.click();
    	
    	WebDriverWait wait04 = new WebDriverWait(driver, 180);
		wait04.until(ExpectedConditions.visibilityOf(Confirm_submission));
		
		 Requesting_Organization.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1));
		 
		 /*Taking screenshot */
			Screenshots shot2=new Screenshots(driver);
			shot2.ScreenShot_Referral_US();
			
    	Confirm_submission.click();
    	
    	
    	        	
        }
	
}
