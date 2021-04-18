package generics;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

public class Screenshots {
	public WebDriver driver; 

	// the driver information will be given by selenium test case 
	public Screenshots(WebDriver driver){
		this.driver = driver; 
	}
	public void ScreenShot_EXPCore_US(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	public void ScreenShot_Shakedown_US(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	public void ScreenShot_AdvSearch_US(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	public void ScreenShot_NP_US(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	public void ScreenShot_GPSGCore_DE(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	public void ScreenShot_FlowDownReview_US(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	
	public void ScreenShot_Referral_US(){

		/*Taking screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	public void ScreenShot_RDM_US(){

		/*Taking screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	public void ScreenShot_SBCore_US(){

		/*Taking screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	public void ScreenShot_Feedback_US(){

		/*Taking screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}
	
	 public void TakeSnapOnFailure(String className,String methodName) {
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in project location with test class and method name 
                try {
            	FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\test-output\\snaps\\"+className+"___"+methodName+".png"));
				System.out.println("***Placed failed screenshot in \\test-output\\snaps folder with name "+className+"____"+methodName+".png"+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
}