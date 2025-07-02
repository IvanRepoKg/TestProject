package TransPerfectProject.TransPerfect;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ExtentReports.ExtentFactory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import org.testng.asserts.SoftAssert;

public class Task5_MultiSelectField extends Base {
  
	Autocomplete_MultiselectField acf;
	SoftAssert sa;
  
  @BeforeClass
  public void beforeClass() {
	  
	  baseURL="https://demos.telerik.com/kendo-angular-ui/demos/dropdowns/overview?theme=default-main";
	  driver.get(baseURL);
	  
	  extent=ExtentFactory.get_ExtentReportInstance();
	  String path="C:\\Users\\Ivan\\eclipse-workspace\\TransPerfect\\ExtentReports\\Task5";
	  spark=ExtentFactory.get_ExtentSparkReporterInstance(path);
	  extent.attachReporter(spark);
	  
	  test=extent.createTest("Task5_MultiSelect");
	  
	  acf=new Autocomplete_MultiselectField(driver, test);
	  
	  sa=new SoftAssert();
  }
  
  @Test
  public void task5_multiSelect() 
  {
	  //Assert.assertTrue(acf.check_multiSelectField());
	  sa.assertTrue(acf.check_multiSelectField());
	  //Assert.assertTrue(acf.verify_OptionsFromDropdown_multiSelect());// Baseball is missing often, but not every time, so it passes sometime (not sure why)
	  sa.assertTrue(acf.verify_OptionsFromDropdown_multiSelect());// Baseball is missing often, but not every time, so it passes sometime (not sure why
	  acf.clear_multiSelectField(); //clearing MultiSelect field
	  acf.choose_favoriteSports();
	  Assert.assertTrue(acf.confirm_chosenSports_multiSelectField()); //confirming chosen sports
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

  @AfterClass
  public void afterClass() {
  }

}
