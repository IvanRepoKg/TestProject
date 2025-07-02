package TransPerfectProject.TransPerfect;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Autocomplete_MultiselectField {
	
	private WebDriver driver;
	private WebElement element=null;
	private ExtentTest test;
	
	public WebDriverWait wait;
	
	
	public Autocomplete_MultiselectField(WebDriver driver, ExtentTest test) 
	{
		this.driver=driver;
		this.test=test;
	}
	
	public WebElement autoComleteField() 
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Your favorite sport']")));
		return element;
	}
	
	public void choose_Sport(String sport)
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=autoComleteField();
		element.sendKeys(sport);
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@role='listbox']/li[@index='4']/span[contains(text(),\"Football\")]")));
		element.click();
		test.log(Status.INFO, "The sport is chosen:" + sport);
	}
	
	public boolean verifyChosenSport(String sport)
	{
		String value_AutoComplete=autoComleteField().getAttribute("value");
		
		if(sport.equalsIgnoreCase(value_AutoComplete)) 
		{
			test.log(Status.PASS, "Verification successfull: AutoComplete field is showing correct sport: "+value_AutoComplete);
			return true;
		}
		else{
			test.log(Status.FAIL, "Verification is not successfull: AutoComplete field is NOT showing correct sport: "+value_AutoComplete);
			return false;
		}	
	}
	
	public void clear_xButton_on_autoComplete() 
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//kendo-autocomplete//span[@role='button' and @title='clear']")));
		element.click();
		test.log(Status.INFO, "AutoComplete field is cleared");
	}
	
	public WebElement multiSelect() 
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Your favorite sports']")));
		return element;
	}
	
	public boolean verify_OptionsFromDropdown_multiSelect()
	{
		List<String> expectedListOfSports=new ArrayList<String>();
		expectedListOfSports.add("Baseball");
		expectedListOfSports.add("Basketball");
		expectedListOfSports.add("Cricket");
		expectedListOfSports.add("Field Hockey");
		expectedListOfSports.add("Football");
		expectedListOfSports.add("Table Tennis");
		expectedListOfSports.add("Tennis");
		expectedListOfSports.add("Volleyball");
		
		List<String> actualListOfSports=new ArrayList<String>();
		actualListOfSports=this.multiSelect_ListBox();
		
		if(expectedListOfSports.equals(actualListOfSports))
		{
			test.log(Status.PASS, "Expected list of sports and actual list are the same");
			return true;
		}
		else 
		{
			test.log(Status.FAIL, "Expected list of sports and actual list are NOT the same");
			return false;
		}
		
	}
	
	public WebElement multiSelect_comboBox() 
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='k-input-values']/input")));
		return element;
	}
	
	public boolean check_multiSelectField() 
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='k-input-values']/kendo-taglist")));
		
		if(element.getText().equalsIgnoreCase("")) 
		{
			test.log(Status.PASS, "MultiSelect field is empty as expected");
			return true;
		}
		else 
		{
			test.log(Status.FAIL, "MultiSelect field is NOT empty as expected. It contains pre-selected items");
			return false;
		}
	}
	
	public List<String> multiSelect_ListBox() //this is just to catch the list, I will continue later
	{
		element=multiSelect_comboBox();
		element.click();
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@role='listbox']")));//finding ul element
		
		List<WebElement> li_Elements=element.findElements(By.tagName("li")); //finding all li elements
		List<String> actualListOfSports=new ArrayList<String>();
		for(WebElement element:li_Elements) 
		{
			System.out.println(element.getText());
			actualListOfSports.add(element.getText());
		}
		
		return actualListOfSports;

	}
	
	public void clear_multiSelectField()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//kendo-multiselect//span[@role='button']")));
		element.click();
		test.log(Status.INFO, "MultiSelect field is cleared");
	}
	
	public void choose_favoriteSports()
	{
		this.multiSelect_comboBox().click();
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),\"Football\")]")));//selecting Football
		element.click();
		test.log(Status.INFO, "Selected football");
		
		this.multiSelect_comboBox().click();
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"Tennis\"]"))); //selecting Tennis
		element.click();
		test.log(Status.INFO, "Selected tennis");
	}
	
	public boolean confirm_chosenSports_multiSelectField()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		//element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='k-input-values']/kendo-taglist")));
		element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='k-input-values']")));
		
		String wholeText=element.getText();
		 
		if(wholeText.contains("Football") && wholeText.contains("Tennis"))
		{
			test.log(Status.PASS, "MultiSelect field contains Football and Tennis as expected");
			return true;
		}
		else
		{
			test.log(Status.FAIL, "MultiSelect field does NOT contain Football and Tennis as expected");
			return false;
		}
	}

}
