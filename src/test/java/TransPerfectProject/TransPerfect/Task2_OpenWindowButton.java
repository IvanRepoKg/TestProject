package TransPerfectProject.TransPerfect;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ExtentReports.ExtentFactory;

public class Task2_OpenWindowButton extends Base{
 
 OpenWindowButton owb;

  @BeforeClass
  public void beforeClass() {
	  
	  baseURL="https://demos.telerik.com/kendo-angular-ui/demos/dialogs/preview?theme=default-main";
	  driver.get(baseURL);
	  extent=ExtentFactory.get_ExtentReportInstance();
	  String path="C:\\Users\\Ivan\\eclipse-workspace\\TransPerfect\\ExtentReports\\Task2";
	  spark=ExtentFactory.get_ExtentSparkReporterInstance(path);
	  extent.attachReporter(spark);
	  
	  test=extent.createTest("Task2_openWindowButton");
	  
	  owb=new OpenWindowButton(driver, test);
  }
  
  @Test
  public void task2_openWindow() {
	  
	  owb.clickOn_openWindowButton();
	  Assert.assertTrue(owb.assertWindowTitle("About"));
	  Assert.assertTrue(owb.isElement_present("minimizeBtn"));
	  Assert.assertTrue(owb.isElement_present("maximizeBtn"));	  
	  Assert.assertTrue(owb.isElement_present("xBtn"));
	  Assert.assertTrue(owb.assertTextOnWindow("Additional info"));
	  owb.clickOn_maximizeWindow();
	  Assert.assertTrue(owb.maximizedButton_OnMaximizedWindow());
	  owb.closeWindow();
	  Assert.assertTrue(owb.isWindowClosed());
	  
  }

  @AfterClass
  public void afterClass() {
  }
  
  @BeforeMethod
  public void testFailure(ITestResult testResult) {
		  if(testResult.getStatus()==ITestResult.FAILURE) 
			{
				System.out.println("Failed" + testResult.getMethod().getMethodName());
				test.log(Status.FAIL, "Test failed");
			}
		  
	  }

}
