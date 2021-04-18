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

public class Step05_CRB_Auth {
 
  
  private WebDriver driver;
	
	// Define the element 
	@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
	@FindBy ( id="btn_signin") private WebElement Signin_Button ;
	@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
	@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;
	
	// Define all web elements under test displayed on home page
		@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Switch instance')]") private WebElement Switch_Instance;
		@FindBy (id="FLD_SELECT_INSTANCE") private WebElement Instance ;
		@FindBy (name="btnGo") private WebElement GO ;
		@FindBy (xpath = ".//*[@value='Exit request'] ") private WebElement ExitRequest;
		@FindBy (xpath = ".//*[@id='content-main']/form/table[1]/tbody/tr[1]/td[3]/a") private WebElement Skill_Req_hyperlink ;
		@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Awaiting client review board action')]") private WebElement Awaiting_CRB_auth_link ;
		@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Candidates')]") private WebElement Candidates ;
		@FindBy (id="FLD_REQUEST_NUMBER") private WebElement Req_Num_Search ;
		@FindBy (name="btnGo") private WebElement GO_reqnum ;
		@FindBy (name = "btnAuthCandidate") private WebElement Auth_Link;
		@FindBy (xpath =".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[3]/a" ) private WebElement Request_Number ;
		
		@FindBy (xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[5]/a" ) private WebElement Skill_link ;
		//@FindBy(name="tblcolCandId") private WebElement  Select_Checkbox ;
		@FindBy (name="btnAuthSel") private WebElement Authorize_selected ;
		
		@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[5]/td[4]/a") private WebElement Cand4_Status_awaitAuth_Link;
		@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[4]/td[4]/a") private WebElement Cand3_Status_awaitAuth_Link;
		@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[3]/td[4]/a") private WebElement Cand2_Status_awaitAuth_Link;
		@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[4]/a") private WebElement Cand1_Status_awaitAuth_Link;
		
		@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[5]/a") private WebElement Cand1_resp_Identification ;
		@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[3]/td[5]/a") private WebElement Cand2_resp_Identification ;
		@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[4]/td[5]/a") private WebElement Cand3_resp_Identification ;
		@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[5]/td[5]/a") private WebElement Cand4_resp_Identification ;
		
	
		
		@FindBy (xpath =".//*[@id='popup-footer']/div[2]/a") private WebElement Close_Child_Window;
		 @FindAll({@FindBy(name="tblcolCandId")}) private List<WebElement>  Select_Checkbox ;
		
		
		@FindBy (xpath = ".//*[@id='finalCandForm']/table[3]/tbody/tr/td[2]/input") private WebElement Reject_sel;
		
		@FindBy(xpath = ".//*[@value='001~S23D2K~001~1000118974~IBMSAPGC0~AUTH~0'] ") private WebElement  Resp2_Checkbox ;
		@FindBy(xpath = ".//*[@value='002~S23D2K~001~1000118974~IBMSAPGC0~AUTH~0'] ") private WebElement  Resp3_Checkbox ;
		@FindBy(xpath = ".//*[@value='003~S23D2K~001~1000118974~IBMSAPGC0~AUTH~0'] ") private WebElement  Resp1_Checkbox ;
		
	
		public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
		public String sheet="Login";
	// Initialize the web elements 
	public Step05_CRB_Auth(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// Function to login to the application
	
	public void login()
	{
		
loginToContractor_Link.click();
		
	
		
		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(Signin_Button));
		
		
		Username_Box.clear();
		Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 3, 0));
		Password_Box.clear();
		Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 3, 1));
		
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Referral_US();
  
		Signin_Button.click();
  
    }

	//Function to click Create New Request button
    public void CRB_Auth()
    {
    	
		WebDriverWait wait02 = new WebDriverWait(driver, 180);
		wait02.until(ExpectedConditions.visibilityOf(Awaiting_CRB_auth_link));
		
		
		Awaiting_CRB_auth_link.click();
		
		WebDriverWait wait03 = new WebDriverWait(driver, 180);
		wait03.until(ExpectedConditions.visibilityOf(Candidates));
		
		Candidates.click();
		
		Req_Num_Search.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
		GO_reqnum.click();

		String Req_Num = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15) ;
		
		 WebDriverWait wait04 = new WebDriverWait(driver, 160);
		wait04.until(ExpectedConditions.visibilityOf(Skill_link)); 
		
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Referral_US();
				
		Request_Number.click();
		
		 WebDriverWait wait09 = new WebDriverWait(driver, 160);
			wait09.until(ExpectedConditions.visibilityOf(Skill_Req_hyperlink)); 
		
			Skill_Req_hyperlink.click();
			
			
			//Resp Selection Page
	    WebDriverWait wait05 = new WebDriverWait(driver, 160);
		wait05.until(ExpectedConditions.visibilityOf(Authorize_selected));
		


for (WebElement elt: Select_Checkbox){

	elt.click();

} 
		
/*Taking screenshot */
Screenshots shot1=new Screenshots(driver);
shot1.ScreenShot_Referral_US();		
		
	Authorize_selected.click();

	//CRB Comments Page
	WebDriverWait wait06 = new WebDriverWait(driver, 160);
	wait06.until(ExpectedConditions.visibilityOf(Authorize_selected));
	
	/*Taking screenshot */
	Screenshots shot2=new Screenshots(driver);
	shot2.ScreenShot_Referral_US();
	
	Authorize_selected.click();


	/*Taking screenshot */
	Screenshots shot3=new Screenshots(driver);
	shot3.ScreenShot_Referral_US();
    }
}