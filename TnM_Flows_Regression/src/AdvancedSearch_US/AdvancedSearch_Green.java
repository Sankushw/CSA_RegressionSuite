package AdvancedSearch_US;

import generics.Screenshots;
import lib.Excel;

import java.util.List;

import org.apache.poi.ss.formula.functions.Dec2Bin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdvancedSearch_Green
{

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "/src/testdata/testdata_advancedSearch.xls";
	public String sheet="Login";	

	// Define the element 
	@FindBy (xpath = "//button[contains(text(),'Log in')]") private WebElement Login_Button ;
	@FindBy ( xpath = ".//button[@id='continue-button']" ) private WebElement ContinueButton ;
	@FindBy ( id = "username" ) private WebElement UsernameField ;
	@FindBy ( id = "password" ) private WebElement PasswordField ;
	@FindBy ( id = "signinbutton" ) private WebElement SigninButton ;
	@FindBy (xpath = "//button[@name='search']" ) private WebElement search_tab ;
	@FindAll({@FindBy(xpath = "//div[@class='mat-drawer-backdrop ng-star-inserted mat-drawer-shown']")}) private List<WebElement>  SearchForm_OverLay ;

	@FindBy ( xpath= "//input[@placeholder='Request No']" ) private WebElement Req_Num_field ;
	@FindBy (xpath ="//*[@touranchor='search-form']/div/div[1]/div/button[2]") private WebElement Search_btn ; 
	@FindBy ( xpath = "//mat-table/mat-row[1]/mat-cell[1]" ) private WebElement Request_Number_link ;
	@FindBy ( xpath = "//span[@class='advancedSearch']" ) private WebElement OpenSearchPanel ;
	@FindBy ( xpath = "//input[@placeholder='PO Number']" ) private WebElement PONumber_Field ;
	@FindBy ( xpath = "//input[@formcontrolname='candidateId']" ) private WebElement CandidateId_Field ;
	@FindBy ( xpath = "//input[@formcontrolname='referenceId']" ) private WebElement ReferenceId_Field ;
	@FindBy ( xpath = "//input[@formcontrolname='fName']" ) private WebElement FirstName_Field ;
	@FindBy ( xpath = "//input[@formcontrolname='lName']" ) private WebElement LastName_Field ;
	@FindBy ( xpath = "//input[@formcontrolname='projectName']" ) private WebElement ProjectName_Field ;
	@FindBy ( xpath = "//input[@class='bx--date-picker__input flatpickr-input ng-star-inserted']" ) private WebElement StartDueDate_Field ;
	@FindBy ( xpath = "//input[@class='bx--date-picker__input ng-star-inserted']" ) private WebElement EndDueDate_Field ;

	@FindBy (xpath= "//div[@class='dayContainer']/span[1]") private WebElement StartDate_InCalender;
	@FindBy (xpath= "//div[@class='dayContainer']/span[12]") private WebElement EndDate_InCalender;

	// Initialize the web elements 
	public AdvancedSearch_Green (WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	// Function to login to the application
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
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));

			ContinueButton.click();

			WebDriverWait wait9 = new WebDriverWait(driver, 160);
			wait9.until(ExpectedConditions.visibilityOf(SigninButton));

			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));


			/*Taking screenshot */
			Screenshots shot=new Screenshots(driver);
			shot.ScreenShot_AdvSearch_US();

			SigninButton.click();
			System.out.println("Sign in button clicked after entering credentials");
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
			shot1.ScreenShot_AdvSearch_US();;
			SigninButton.click();
			System.out.println("Sign in button clicked after entering credentials");
		}  

	}

	public void OpenSearchTab()
	{

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(search_tab));
		search_tab.click();
	}
	public void SearchByReqId() throws InterruptedException
	{

		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOf(Req_Num_field));
		Req_Num_field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 0));
	
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_AdvSearch_US();
		Search_btn.click();
		
		waitForSearchOverlayDisappear();
		WebDriverWait wait3 = new WebDriverWait(driver, 60);
		wait3.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		
		System.out.println("Search results are shown for Request ID= "+Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 0));
	}
	public void SearchByPO() throws InterruptedException
	{
		OpenSearchPanel.click();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOf(PONumber_Field));

		PONumber_Field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 1));
	
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		Search_btn.click();
		waitForSearchOverlayDisappear();
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_AdvSearch_US();
		System.out.println("Search results are shown for PO number= "+Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 1));

	}
	public void SearchByFirstName() throws InterruptedException
	{
		OpenSearchPanel.click();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOf(FirstName_Field));
		FirstName_Field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 2));
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		Search_btn.click();
		waitForSearchOverlayDisappear();
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_AdvSearch_US();
		System.out.println("Search results are shown for FirstName= "+Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 2));

	}
	public void SearchByLastName() throws InterruptedException
	{
		OpenSearchPanel.click();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOf(LastName_Field));
		LastName_Field.sendKeys(Excel.getCellValue(xlsFilePath,"AdvancedSearch", 1, 3));
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		Search_btn.click();
		waitForSearchOverlayDisappear();
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_AdvSearch_US();
		System.out.println("Search results are shown for LastName= "+Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 3));

	}
	public void SearchByProjectName() throws InterruptedException
	{
		OpenSearchPanel.click();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOf(ProjectName_Field));
		ProjectName_Field.sendKeys(Excel.getCellValue(xlsFilePath,"AdvancedSearch", 1, 4));
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		Search_btn.click();
		waitForSearchOverlayDisappear();
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_AdvSearch_US();
		System.out.println("Search results are shown for projectName= "+Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 4));

	}
	public void SearchByStartDuedate() throws InterruptedException
	{
		OpenSearchPanel.click();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOf(StartDueDate_Field));
		/*StartDueDate_Field.click();
		Thread.sleep(1000);
		StartDate_InCalender.click();*/
		StartDueDate_Field.sendKeys("04 Dec 2020");
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		Thread.sleep(5000);
		Search_btn.click();
		waitForSearchOverlayDisappear();
		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_AdvSearch_US();
		System.out.println("Search results are shown for StartDueDate");

	}

	public void SearchBy_StartnEndDuedate_Range() throws InterruptedException
	{
		OpenSearchPanel.click();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOf(StartDueDate_Field));
		StartDueDate_Field.click();
		Thread.sleep(1000);
		StartDate_InCalender.click();
		Thread.sleep(500);
		EndDueDate_Field.click();
		Thread.sleep(1000);
		EndDate_InCalender.click();
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		Search_btn.click();
		waitForSearchOverlayDisappear();
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_AdvSearch_US();
		System.out.println("Search results are shown for Start&EndDueDates");

	}
	
	public void SearchBy_Req_Fname_Lname_ProjName_PO() throws InterruptedException
	{
		OpenSearchPanel.click();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOf(Req_Num_field));
		Req_Num_field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 7));
		FirstName_Field.sendKeys(Excel.getCellValue(xlsFilePath,"AdvancedSearch", 1, 2));
		LastName_Field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 3));
		ProjectName_Field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 4));
		PONumber_Field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 1));
		
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		Search_btn.click();
		waitForSearchOverlayDisappear();
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_AdvSearch_US();
		String ReqNo=Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 7);
		String FirstName=Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 2);
		String LastName=Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 3);
		String ProjectName=Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 4);
		String PONumber=Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 1);
		System.out.println("Search results are shown for RequestID= "+ReqNo+" FirstName= "+FirstName+" LastName= "+LastName+" ProjectName= "+ProjectName+" PO Number= "+PONumber);

	}
	public void SearchBy_Req_Fname_ProjName() throws InterruptedException
	{
		OpenSearchPanel.click();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOf(Req_Num_field));
		Req_Num_field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 7));
		FirstName_Field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 2));
		ProjectName_Field.sendKeys(Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 4));
		
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_AdvSearch_US();
		Search_btn.click();
		waitForSearchOverlayDisappear();
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOf(Request_Number_link));
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_AdvSearch_US();
		String ReqNo=Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 7);
		String FirstName=Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 2);
		String ProjectName=Excel.getCellValue(xlsFilePath, "AdvancedSearch", 1, 4);
		
		System.out.println("Search results are shown for RequestID= "+ReqNo+" FirstName= "+FirstName+" ProjectName= "+ProjectName);

	}
	
	public void waitForSearchOverlayDisappear() throws InterruptedException
	{
		int count=0;
		while(SearchForm_OverLay.size()!=0 && count<20)
		{
			Thread.sleep(1000);
			count++;
		}}
}
