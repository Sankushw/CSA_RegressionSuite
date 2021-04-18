package expOActions_US;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

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
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step06_RIPC_ResponseStatus {
 

  private WebDriver driver;
  
  public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	
	// Define the element 
	@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
	@FindBy ( id="btn_signin") private WebElement Signin_Button ;
	@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
	@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;
	@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Search')]") private WebElement Search ;
	@FindBy ( id="FLD_REQ_NUM_SEARCH") private WebElement Request_Num ;
	@FindBy ( name="GO") private WebElement Go_btn ;
	@FindBy  ( xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[1]/a") private WebElement ReqNumlink ;
	@FindBy ( name="btnSubmitSelCand") private WebElement sub_sel_Response_button ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/table[1]/tbody/tr[11]/td[3]/a") private WebElement Supplink ;
	
	public String sheet="Login";
	
	
	// Initialize the web elements 
	public Step06_RIPC_ResponseStatus(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	// Function to login to the application
	// Click on the link again as workaround 
	public void status(){
		
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.visibilityOf(loginToContractor_Link));
		loginToContractor_Link.click();
		
		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(Signin_Button));
		
		Username_Box.clear();
		Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));
		Password_Box.clear();
		Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));
		
		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");
		  
		Signin_Button.click();
		
		WebDriverWait wait12 = new WebDriverWait(driver, 180);
		wait12.until(ExpectedConditions.visibilityOf(Search));
  
		Search.click();
		
		WebDriverWait wait13 = new WebDriverWait(driver, 180);
		wait13.until(ExpectedConditions.visibilityOf(Request_Num));
		
		Request_Num.sendKeys(Excel.getCellValue(xlsFilePath,"Request_creation", 1, 15));
		Go_btn.click();
		
		WebDriverWait wait14 = new WebDriverWait(driver, 180);
		wait14.until(ExpectedConditions.visibilityOf(ReqNumlink));
		
		ReqNumlink.click();
		System.out.println("Hello1");
	
		WebDriverWait wait04 = new WebDriverWait(driver, 300);
		wait04.until(ExpectedConditions.visibilityOf(sub_sel_Response_button)); 
			
		/* code to capture screenshot */
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\EO\\US\\");
	}
		
		
	
  }

