package TransPerfectProject.TransPerfect;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ExtentReports.ExtentFactory;

public class Task3_Employees extends Base {
 
  //private WebElement element;
	
	EmployeesTable et;

  @BeforeClass
  public void beforeClass() {
	  
	  baseURL="https://demos.telerik.com/kendo-angular-ui/demos/grid/filter-all-columns?theme=default-main";
	  driver.get(baseURL);
	  extent=ExtentFactory.get_ExtentReportInstance();
	  String path="C:\\Users\\Ivan\\eclipse-workspace\\TransPerfect\\ExtentReports\\Task3";
	  spark=ExtentFactory.get_ExtentSparkReporterInstance(path);
	  extent.attachReporter(spark);
	  
	  test=extent.createTest("Task3_Employees");
	  
	  et=new EmployeesTable(driver, test);
	  
  }
  
  @Test
  public void task3_Employees() {
	  //this.getData();
	  
	  et.filterCountriesByName("us");
	  et.printEmployeeData();
	  et.filterByStatus("Online");
	  et.clickOn_exportTo_Excel();
	  Assert.assertTrue(et.verifyExportedExcelFile());
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
