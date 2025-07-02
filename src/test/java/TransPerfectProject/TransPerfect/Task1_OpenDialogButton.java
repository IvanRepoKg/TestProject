package TransPerfectProject.TransPerfect;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ExtentReports.ExtentFactory;

public class Task1_OpenDialogButton extends Base{
  
  OpenDialogButton odb;

  @BeforeClass
  public void get_extentReport() {
	  
	  baseURL="https://demos.telerik.com/kendo-angular-ui/demos/dialogs/preview?theme=default-main";
	  driver.get(baseURL);
	  extent=ExtentFactory.get_ExtentReportInstance();
	  String path="C:\\Users\\Ivan\\eclipse-workspace\\TransPerfect\\ExtentReports\\Task1";
	  spark=ExtentFactory.get_ExtentSparkReporterInstance(path);
	  extent.attachReporter(spark);
	  
	  test=extent.createTest("Task1_openDialogButton");
	  
	  odb=new OpenDialogButton(driver, test);
  }
  
  @Test
  public void task1_openDialog() throws InterruptedException {
	  
	  odb.clickOn_openDialogButton();
	  Assert.assertTrue(odb.verify_dialogBox_present());// I am using hard Assert here (I could use Soft Assert but, I will show it in other tasks)
	  Assert.assertTrue(odb.verify_dialogBoxMessage_present());
	  Assert.assertTrue(odb.clickOn_dialogBoxButton_NO());
	  odb.clickOn_openDialogButton();
	  Assert.assertTrue(odb.clickOn_dialogBoxButton_YES());// this one is failing as expected
	  Assert.assertTrue(odb.backgroundColor_YesButton());// this one is failing as expected
	  odb.getfocusOn_X_button_onDialogBox();
	  odb.closeDialogBox();
	  Assert.assertTrue(odb.isDialogBox_closed());
  }
  
  @AfterMethod
  public void testFailure(ITestResult testResult) {
	  if(testResult.getStatus()==ITestResult.FAILURE) 
		{
			System.out.println("Failed" + testResult.getMethod().getMethodName());
			test.log(Status.FAIL, "Test failed");
		}
	  
  }

}
