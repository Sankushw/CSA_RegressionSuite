package rDM_US;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import generics.Screenshots;
import lib.Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Step02_Asssign_RDM_Role_US {


	private WebDriver driver;
	public String str2;
	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_rdm.xls";

	// Define the element 
	@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
	@FindBy ( id="btn_signin") private WebElement Signin_Button ;
	@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
	@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;

	@FindBy ( xpath = ".//*[@id='left-nav']/div/a[4]" ) private WebElement RDM_Request_tab ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/p[4]/input[1]" ) private WebElement Assign_Selected_Button ;

	@FindBy ( id="FLD_REQUEST_NUMBER" ) private WebElement Request_Num_Fld ;
	@FindBy ( name="btnGo" ) private WebElement Search_GO_btn ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[3]/a" ) private WebElement Request_Number ;


	@FindBy ( xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[1]//input[@name='CheckBoxGroup']" ) private WebElement Checkbox ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/p[4]/input[1]" ) private WebElement Assign_Selected_To_Button ;

	@FindBy ( xpath = ".//*[@id='FLD_WEB_ID']" ) private WebElement Assign_To_Dropdown ;
	@FindBy ( xpath = ".//*[@id='popup-footer']/div[2]/div/span[1]/input" ) private WebElement SaveAndContinue_Button ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[8]" ) private WebElement Assigned_Selected_LabelField ;
	@FindBy (xpath = ".//a[@class='delivery method-link' and @type='totp']") private WebElement Totp_Link;
	@FindBy(id = "otp")	private WebElement passcodeBox;
	@FindBy(id = "btn_submit")	private WebElement SubmitPasscode;

	public String sheet="Login";
	// Initialize the web elements 
	public Step02_Asssign_RDM_Role_US(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	// Function to login to the application

	public void login() throws InterruptedException
	{

		WebDriverWait wait00 = new WebDriverWait(driver, 180);
		wait00.until(ExpectedConditions.visibilityOf(loginToContractor_Link));
		
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_RDM_US();

		loginToContractor_Link.click();

		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(Signin_Button));

		Username_Box.clear();
		Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 23, 0));
		System.out.println(Excel.getCellValue(xlsFilePath, sheet, 23, 0));
		System.out.println(Excel.getCellValue(xlsFilePath, sheet, 23, 1));
		Password_Box.clear();
		Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 23, 1));
		Thread.sleep(2000);
		Signin_Button.click();
		
		try {
			Totp_Link.click();
			  String otpKeyStr = "47UORWOB2YBVMXQE"; // <- this 2FA secret key for csatestus1@c25a0161.toronto.ca.ibm.com
			//String otpKeyStr = "OMPZZ2UCFBFTGRHQ"; // <- this 2FA secret key for csatestus1@c25a0161.toronto.ca.ibm.com
			//String otpKeyStr = "YCX3MGYU7XF7P3NB"; // <- this 2FA secret key for bennett48@c25a0161.toronto.ca.ibm.com
			Totp totp = new Totp(otpKeyStr);
			String twoFactorCode = totp.now();
			WebDriverWait wait2 = new WebDriverWait(driver, 180);
			wait2.until(ExpectedConditions.visibilityOf(passcodeBox));
			passcodeBox.sendKeys(twoFactorCode);
			SubmitPasscode.click();
		}
	catch (Exception e) {
		System.out.println("no OTP screen");
		//e.printStackTrace();
	}

	}

	public void Assign_RDM() throws InterruptedException
	{

		WebDriverWait wait = new WebDriverWait(driver, 160);
		wait.until(ExpectedConditions.visibilityOf(RDM_Request_tab)); 

		RDM_Request_tab.click();

		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(Request_Num_Fld)); 

		Request_Num_Fld.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
		Search_GO_btn.click();

		WebDriverWait wait9 = new WebDriverWait(driver, 160);
		wait9.until(ExpectedConditions.visibilityOf(Request_Number)); 

		Checkbox.click();
		
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_RDM_US();
		
		Assign_Selected_To_Button.click();

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
				wait8.until(ExpectedConditions.visibilityOf(SaveAndContinue_Button));               

				Select SSlistbox = new Select(Assign_To_Dropdown);
				SSlistbox.selectByVisibleText("bennett 46/endicott/ibm@IBMUS");

				SaveAndContinue_Button.click();

			}		
		}		
		//Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);	

		WebDriverWait wait10 = new WebDriverWait(driver, 160);
		wait10.until(ExpectedConditions.visibilityOf(Assign_Selected_Button)); 
		
		Thread.sleep(3000);
		
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_RDM_US();

		
	}




}

