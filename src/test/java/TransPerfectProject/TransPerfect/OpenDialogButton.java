package TransPerfectProject.TransPerfect;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class OpenDialogButton {
	
	private WebDriver driver;
	private WebElement element=null;
	private ExtentTest test;
	
	public WebDriverWait wait;
	
	//konstruktor
	public OpenDialogButton(WebDriver driver, ExtentTest test) 
	{
		this.driver=driver;
		this.test=test;
	}
	
	
	public WebElement openDilog_Button()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@dir='ltr'][1]")));
		return element;
	}
	
	public void clickOn_openDialogButton() 
	{
		element=openDilog_Button();
		element.click();
		test.log(Status.INFO, "Clicked on the Open dialog button");
	}
	
	public WebElement dialogBox()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));
		return element;
	}
	
	public boolean verify_dialogBox_present() 
	{
		if(this.dialogBox().isDisplayed())
		{
			test.log(Status.PASS, "Dialog box is present");
			return true;
		} 
		else 
		{
			test.log(Status.FAIL, "Dialog box is NOT present");
			return false;
		}
	}
	
	public WebElement dialogBoxMessage()
	{
		element=driver.findElement(By.xpath("//p[contains(text(),'Are you sure you want to continue?')]"));
		return element;
	}
	
	public boolean verify_dialogBoxMessage_present() 
	{
		if(this.dialogBoxMessage().isDisplayed())
		{
			test.log(Status.PASS, "Dialog box message is present");
			return true;
		} 
		else 
		{
			test.log(Status.FAIL, "Dialog box message is NOT present");
			return false;
		}
	}
	
	public WebElement dialogBoxButton_NO()
	{
		element=driver.findElement(By.xpath("//kendo-dialog-actions/button[1]"));
		return element;
	}
	
	public WebElement dialogBoxButton_YES()
	{
		
		element=driver.findElement(By.xpath("//kendo-dialog-actions/button[2]"));
		return element;
	}
	
	public boolean clickOn_dialogBoxButton_NO()
	{
		element=dialogBoxButton_NO();
		element.click();
		wait=new WebDriverWait(driver, Duration.ofSeconds(2));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@dir='ltr'][1]")));
		if(element.isDisplayed()) 
		{
			test.log(Status.PASS, "NO button works as expected");
			return true;
		}
		else {
			test.log(Status.FAIL, "NO button is not working as expected");
			return false;
		}
	}
	
	public boolean clickOn_dialogBoxButton_YES()
	{
		element=dialogBoxButton_YES();
		element.click();
		wait=new WebDriverWait(driver, Duration.ofSeconds(2));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@dir='ltr'][1]")));
		if(element.isDisplayed()) 
		{
			test.log(Status.FAIL, "YES button is not working as expected - it is closing the dialog box and returns us one step back");
			return false;
		}
		else {
			test.log(Status.PASS, "YES button is working as expected");
			return true;
			//kliktanje na dugme YES bi trebalo da nas odvede dalje na neku novu stranicu, ali nema definisane koja bi to stranica bila
		}
	}
	
	
	public boolean backgroundColor_YesButton() 
	{
		String expectedColor="#008000"; //this is green color...for example
		String rgbaActualColor=dialogBoxButton_YES().getCssValue("background-color");
		Color color=Color.fromString(rgbaActualColor);
		String actualColor=color.asHex();
		
		if(expectedColor.equalsIgnoreCase(actualColor)) 
		{
			test.log(Status.PASS, "Background color of YES button is green");
			return true;
		}
		else 
		{
			test.log(Status.FAIL, "Background color of YES button is not green. The color in hex code is: "+actualColor);
			return false;
		}
	}
	
	public WebElement X_button_onDialogBox() 
	{
		//this.clickOn_openDialogButton();
		//element=driver.findElement(By.xpath("//button[@icon='close']"));
		wait=new WebDriverWait(driver, Duration.ofSeconds(3));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@icon='close']")));
		return element;
	}
	
	public void clickOn_X_button_dialogBox()//ovo mi ne treba
	{
		element=X_button_onDialogBox();
		element.click();
	}
	
	public void getfocusOn_X_button_onDialogBox() throws InterruptedException
	{
		//ovde mogu da koristim JavascriptExecutor, ali nije naglaseno
		//clickOn_openDialogButton();
		element=X_button_onDialogBox();
		element.sendKeys(Keys.TAB);
		test.log(Status.INFO, "Focus is on X button on the dialog box");
		Thread.sleep(4000);
	}
	
	public void closeDialogBox() 
	{
		this.X_button_onDialogBox().sendKeys(Keys.ENTER);
		test.log(Status.INFO, "Dialog box is closed by sending ENTER on X button");
	}
	
	public boolean isDialogBox_closed() 
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(2));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@dir='ltr'][1]")));
		if(element.isDisplayed()) 
		{
			test.log(Status.PASS, "Dialog box is closed");
			return true;
		}
		else {
			test.log(Status.FAIL, "Dialog box is NOT closed");
			return false;
		}
	}

}
