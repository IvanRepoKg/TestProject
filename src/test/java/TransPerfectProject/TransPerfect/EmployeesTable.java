package TransPerfectProject.TransPerfect;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class EmployeesTable {
	
	private WebDriver driver;
	private WebElement element=null;
	private ExtentTest test;
	
	public WebDriverWait wait;
	
	private XSSFWorkbook ExcelWBook;
	private XSSFSheet ExcelWSheet;
	
	//konstruktor
	public EmployeesTable(WebDriver driver, ExtentTest test) 
	{
		this.driver=driver;
		this.test=test;
	}
	
	public WebElement filterCountry() 
	{
		element=driver.findElement(By.xpath("//a[@title='Country Column Menu']"));
		return element;
	}
	
	public void filterCountriesByName(String countryName) 
	{
		element=this.filterCountry();
		element.click();
		driver.findElement(By.xpath("//kendo-grid-columnmenu-item[@icon='filter']//div[@role='button']")).click(); //xpath could be better...I will take a look later
		driver.findElement(By.xpath("//kendo-dropdownlist[@aria-label='Country Filter Operators']")).click();
		driver.findElement(By.xpath("//span[@class='k-list-item-text ng-star-inserted' and contains(text(), 'Is equal to')]")).click();
		driver.findElement(By.xpath("//kendo-grid-filter-menu-input-wrapper//kendo-textbox/input[@class='k-input-inner']")).sendKeys(countryName);
		//the xpath in the upper line of code could be better, I will check it later
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//test.log(Status.INFO, "Employees sorted by country");
		test.log(com.aventstack.extentreports.Status.INFO, "Employees sorted by country");
	}
	
	public void printEmployeeData() 
	{
		
		List<WebElement> nameList=driver.findElements(By.xpath("//div[@class='customer-name']"));
		List<WebElement> jobTitle=driver.findElements(By.xpath("//td[@aria-colindex='3']"));
		List<WebElement> phoneNumber=driver.findElements(By.xpath("//td[@aria-colindex='9']"));
		List<WebElement> address=driver.findElements(By.xpath("//td[@aria-colindex='10']"));
		int sizeOfList=nameList.size();
		
		for(int i=0; i<sizeOfList; i++)
		{
			System.out.println("Name: "+nameList.get(i).getText());
			System.out.println("Job Title: "+jobTitle.get(i).getText());
			System.out.println("Phone number: "+phoneNumber.get(i).getText());
			System.out.println("Adress: "+address.get(i).getText());
			System.out.println("\n");
			
		}
		test.log(Status.INFO, "Employees sorted by country and  printed out in the console");
	}
		
		
		// my idea here was to use method for printing out employees (it is basically the same logic
		//but I have issues with this task which are more important and I can't spend more time at this moment
		// I will revisit this if I have enough time
		public List<Employee> actualEmployees() 
		{
			List<Employee>realEmployees= new ArrayList<Employee>();
			
			List<WebElement> nameList=driver.findElements(By.xpath("//div[@class='customer-name']"));
			List<WebElement> jobTitle=driver.findElements(By.xpath("//td[@aria-colindex='3']"));
			List<WebElement> phoneNumber=driver.findElements(By.xpath("//td[@aria-colindex='9']"));
			List<WebElement> address=driver.findElements(By.xpath("//td[@aria-colindex='10']"));
			int sizeOfList=nameList.size();
			
			for(int i=0; i<sizeOfList; i++)
			{
				String name=nameList.get(i).getText();
				String job=jobTitle.get(i).getText();
				String phone=phoneNumber.get(i).getText();
				String addr=address.get(i).getText();
				
				realEmployees.add(new Employee(name, job, phone, addr));
			}
		
		test.log(Status.INFO, "List of employees in the page");
		
		return realEmployees;
	}
	
	public WebElement filterStatus() 
	{
		element=driver.findElement(By.xpath("//a[@title='Status Column Menu']"));
		return element;
	}
	
	public void filterByStatus(String status) 
	{
		element=this.filterStatus();
		element.click();
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Status Column Menu']//kendo-grid-columnmenu-item[@icon='filter']//div[@role='button']")));
		element.click();
		if(status.equalsIgnoreCase("Online")) 
		{
			driver.findElement(By.xpath("//label[ text()='Is True']//preceding-sibling::kendo-radiobutton/input")).click();
		}
		else
		{
			driver.findElement(By.xpath("//label[ text()='Is False']//preceding-sibling::kendo-radiobutton/input")).click();
		}
		driver.findElement(By.xpath("//button[@type='submit']")).click(); //I will have to check xpath...it is the same as in method for countries
		//test.log(Status.INFO, "Employee list is sorted by status");
		test.log(com.aventstack.extentreports.Status.INFO, "Employee list is sorted by status");
	}
	
	public void clickOn_exportTo_Excel() 
	{
		element=driver.findElement(By.xpath("//span[text()='Export to Excel']//parent::button[@type='button']"));
		element.click();
		//test.log(Status.INFO, "The list of employees is exported to Excel file");
		test.log(com.aventstack.extentreports.Status.INFO, "The list of employees is exported to Excel file");
	}
	
	public boolean verifyExportedExcelFile()
	{
		  List<Employee>excelEmployees= new ArrayList<Employee>();
		  
		  try(FileInputStream ExcelFile = new FileInputStream("c:\\Users\\Ivan\\Downloads\\Employees (1).xlsx"))
		  {
			  ExcelWBook = new XSSFWorkbook(ExcelFile);
			  ExcelWSheet=ExcelWBook.getSheet("Sheet1");
			  
			  for(Row row:ExcelWSheet) 				  		  
			  {
				  
				  int rowIndex=row.getRowNum();
				  
				  if(rowIndex>1)
				  {
					  String name = row.getCell(0).getStringCellValue();
					  String jobTitle = row.getCell(1).getStringCellValue();
					  String phoneNumber = row.getCell(7).getStringCellValue();
	                  String address = row.getCell(8).getStringCellValue();  
	                  
	                  excelEmployees.add(new Employee(name, jobTitle, phoneNumber, address));
				  }
				  
			  }
			  
			  
			  List<Employee>expectedEmployees=new ArrayList<Employee>();
			  expectedEmployees=actualEmployees();

	          if (expectedEmployees.equals(excelEmployees))
	          {
	             System.out.println("Verification successful: The exported employees match the expected data.");
	             test.log(Status.PASS, "Verification successful: The exported employees match the expected data");
	             return true;
	          } 
	          else
	          {  
	             System.out.println("Verification failed: The exported employees do not match the expected data.");
	             test.log(Status.FAIL, "Verification failed: The exported employees do not match the expected data");
	             return false;
	          }
		  }
		  catch(IOException e) 
		  {
			  e.printStackTrace();
			  return false;
		  }

	}

	
}
