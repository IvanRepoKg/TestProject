package TransPerfectProject.TransPerfect;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ExtentReports.ExtentFactory;

public class Task4_AutoCompleteField extends Base {
  
  Autocomplete_MultiselectField acf;

  @BeforeClass
  public void beforeClass() {
	  
	  baseURL="https://demos.telerik.com/kendo-angular-ui/demos/dropdowns/overview?theme=default-main";
	  driver.get(baseURL);
	  
	  extent=ExtentFactory.get_ExtentReportInstance();
	  String path="C:\\Users\\Ivan\\eclipse-workspace\\TransPerfect\\ExtentReports\\Task4";
	  spark=ExtentFactory.get_ExtentSparkReporterInstance(path);
	  extent.attachReporter(spark);
	  
	  test=extent.createTest("Task4_Autocomplete");
	  
	  acf=new Autocomplete_MultiselectField(driver, test);
  }
  
  
  @Test
  public void Task4_autoCompleteField() {
	  
	  acf.choose_Sport("Football");
	  Assert.assertTrue(acf.verifyChosenSport("Football"));
	  acf.clear_xButton_on_autoComplete();  
  }
  
  
  @AfterMethod
  public void testFailure(ITestResult testResult)
  {
	
		  if(testResult.getStatus()==ITestResult.FAILURE) 
			{
				System.out.println("Failed" + testResult.getMethod().getMethodName());
				test.log(Status.FAIL, "Test failed");
			}
		  
  }

}
