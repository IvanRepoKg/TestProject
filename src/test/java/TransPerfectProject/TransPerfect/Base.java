package TransPerfectProject.TransPerfect;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {
	
	public WebDriver driver;
	public String baseURL;
	public ExtentReports extent;
	public ExtentSparkReporter spark;
	public ExtentTest test;
 
 
  @BeforeClass
  public void SetUp() {
	  
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	  
	  /*baseURL="https://demos.telerik.com/kendo-angular-ui/demos/dialogs/preview?theme=default-main";
	  driver.get(baseURL);*/
	  
	  
  }

  @AfterClass
  public void CleanUp() throws InterruptedException {
	  
	  Thread.sleep(3000);
	  extent.flush();
	  driver.quit();
  }


}
