package fEEDBACK_US;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import generics.Screenshots;
import lib.Excel;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step04_submit_feedback_New_app {
	
	private WebDriver driver;
	public String url;
	public String sheet="Login";
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata_feedback.xls";

	// Define the element 
			@FindBy (xpath = "//button[contains(text(),'Log in')]") private WebElement Login_Button ;
			@FindBy ( xpath = ".//button[@id='continue-button']" ) private WebElement ContinueButton ;
			@FindBy ( id = "username" ) private WebElement UsernameField ;
			@FindBy ( id = "password" ) private WebElement PasswordField ;
			@FindBy ( id = "signinbutton" ) private WebElement SigninButton ;
			@FindBy (xpath = "//button[@name='search']" ) private WebElement search_tab ;
			@FindBy ( xpath= "//input[@placeholder='Request No']" ) private WebElement Req_Num_field ;
			@FindBy (xpath ="//*[@touranchor='search-form']/div/div[1]/div/button[2] ") private WebElement Search_btn ; 
			@FindBy ( xpath = "//mat-table/mat-row/mat-cell[1]" ) private WebElement Request_Number_link ;
			@FindBy (xpath ="//button[@class='_hj-OO1S1__styles__openStateToggle']") private WebElement feedback_PopUp;
			@FindBy (xpath ="//button[contains(text(),'Add Response')]") private WebElement btn_Add_response;
			@FindAll({@FindBy(xpath = "//div[@class='mat-drawer-backdrop ng-star-inserted mat-drawer-shown']")}) private List<WebElement>  ResponseForm_OverLay ;
			@FindAll({@FindBy(xpath = "//section[@class='bx--modal bx--modal-tall is-visible']")}) private List<WebElement>  ActionWindow_OverLay ;
	// Define the Feedback page element 	
			@FindBy (xpath = "//button[@class='bx--btn bx--btn--tertiary bx--btn--sm']") private WebElement Feedback_Button;
			@FindBy (xpath ="//button[@class='bx--btn bx--btn--primary ng-star-inserted' and contains (text(),'Submit feedback')]") private WebElement SubmitFeedback_Button;
			@FindBy (xpath ="//label[@class='bx--checkbox-label']/span[contains(text(),' Incorrect job role skill set (JRSS)')]") private WebElement Incorrectjobrole_JRSS;
			@FindBy (xpath ="//span[contains(text(),'Incorrect job role skill set (JRSS)')]//ancestor::div[@class='bx--row ng-star-inserted']/div[2]/input") private WebElement Incorrect_JRSS;
			@FindBy (xpath ="//label[@class='bx--checkbox-label']/span[contains(text(),'Incorrect skill level')]") private WebElement Incorrectskill_Level;
			@FindBy (xpath ="//span[contains(text(),'Incorrect skill level')]//ancestor::div[@class='bx--row ng-star-inserted']/div[2]/input") private WebElement Incorrect_skill;
			@FindBy (xpath ="//p[@class='bx--inline-notification__subtitle']") private WebElement NotificationText;
			
	public Step04_submit_feedback_New_app(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
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
			shot.ScreenShot_Feedback_US();

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
			shot1.ScreenShot_Feedback_US();
			SigninButton.click();
		}  

	}
	
	public void openRequest()
	{

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(search_tab));
		search_tab.click();

		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Req_Num_field));

		Req_Num_field.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
		Search_btn.click();
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Feedback_US();
		System.out.println("took");
		Request_Number_link.click();
		
		WebDriverWait wait6 = new WebDriverWait(driver, 160);
        wait6.until(ExpectedConditions.visibilityOf(Feedback_Button));
        
        System.out.println("Searched and Opened the request " + Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
        if (feedback_PopUp.isDisplayed())
		{
			feedback_PopUp.click();
			System.out.println("feedback PopUp is Present and minimised");
		}
        
	}
	
	public void addfeedbackdetails() throws InterruptedException
	
	{
		isloadComplete(driver);
		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(Feedback_Button));
		Feedback_Button.click();
		
		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(SubmitFeedback_Button));
		
		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(Incorrectjobrole_JRSS));
		Incorrectjobrole_JRSS.click();
		Thread.sleep(1000);
		Incorrect_JRSS.sendKeys("Wrong JRSS");
		
		Incorrectskill_Level.click();
		Thread.sleep(1000);
		Incorrect_skill.sendKeys("Wrong Skill");
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Feedback_US();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", SubmitFeedback_Button);
		waitForActionWindowOverlayDisappear();
		String actualMessage = NotificationText.getText();
		System.out.println("Notification Message=" + actualMessage);
		assertTrue(actualMessage.contains("successfully submitted feedback"));
		System.out.println("Feedback Submitted");
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
