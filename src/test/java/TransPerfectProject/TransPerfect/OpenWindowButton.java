package TransPerfectProject.TransPerfect;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class OpenWindowButton {
	
	private WebDriver driver;
	private WebElement element=null;
	private ExtentTest test;
	
	public WebDriverWait wait;

	
	//konstruktor
	public OpenWindowButton(WebDriver driver, ExtentTest test) 
	{
		this.driver=driver;
		this.test=test;
	}
	
	public WebElement openWindowButton()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(3));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='example-wrapper']/button[@dir='ltr'][2]")));
		return element;
	}
	
	public void clickOn_openWindowButton() 
	{
		element=openWindowButton();
		element.click();
		test.log(Status.INFO, "Clicked on the Open Window button");
	}
	
	public WebElement windowTitle()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(3));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//kendo-window-titlebar/span"))); // I cannot use id because it is changing dynamically
		return element;
	}
	
	
	public boolean assertWindowTitle(String name) 
	{
		if(windowTitle().getText().equalsIgnoreCase(name)) 
		{
			test.log(Status.PASS, "Name of the window is correct");
			return true;
		}
		else 
		{
			test.log(Status.FAIL, "Name of the window is NOT correct");
			return false;
		}
			
	}
	
	public WebElement minimizeBtn() 
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(3));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Minimize']")));
		return element;
	}
	
	
	public WebElement maximizeBtn()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(3));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Maximize']")));
		return element;
	}
	
	public WebElement xBtn()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(3));
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Close']")));
		return element;
	}
	
	public boolean isElement_present(String webElement) 
	{
		if(webElement.equalsIgnoreCase("minimizeBtn")) 
		{
			if(minimizeBtn().isDisplayed())
			{
				test.log(Status.PASS, "Minimize button is displayed");
				return true;
			}
			else 
			{
				test.log(Status.FAIL, "Minimize button is displayed");
				return false;
			}
		}
		else if(webElement.equalsIgnoreCase("maximizeBtn")) 
		{
			if(maximizeBtn().isDisplayed())
			{
				test.log(Status.PASS, "Maximize button is displayed");
				return true;
			}
			else 
			{
				test.log(Status.FAIL, "Maximize button is displayed");
				return false;
			}
		}
		else if(webElement.equalsIgnoreCase("xBtn")) 
		{
			if(xBtn().isDisplayed())
			{
				test.log(Status.PASS, "X button is displayed");
				return true;
			}
			else 
			{
				test.log(Status.FAIL, "X button is displayed");
				return false;
			}
		}
		else 
		{
			test.log(Status.FAIL, "Provided element does not exist");
			System.out.println("Provided element does not exist");
			return false;
		}
	}
	
	
	public WebElement windowText()
	{
		element=driver.findElement(By.xpath("//div[@class='k-window-content']/p")); //I could use only "p" tag, but I prefer it like this
		return element;
	}
	
	public boolean assertTextOnWindow(String text)
	{
		if(windowText().getText().equalsIgnoreCase(text))
		{
			test.log(Status.PASS, "Text on the window is displayed correctly: "+windowText().getText());
			return true;
		}
		else 
		{
			test.log(Status.FAIL, "Text on the window is NOT displayed correctly: "+windowText().getText());
			return false;
		}
	}
	
	public void clickOn_maximizeWindow() 
	{
		element=maximizeBtn();
		element.click();
		test.log(Status.INFO, "Clicked on the Maximize button");
	}
	
	public boolean maximizedButton_OnMaximizedWindow() 
	{
		try
		{
			element=driver.findElement(By.xpath("//div[@class='k-window-titlebar-actions']/button[2]"));
		}
		catch(NoSuchElementException e)
		{
			System.out.println(e.getMessage());
		}
		
		if(element.getAttribute("style").equalsIgnoreCase("display: none;")) 
		{
			test.log(Status.PASS, "Maximize button is not visible on already maximized window");
			return true;
		}
		else 
		{
			test.log(Status.FAIL, "Maximize button is visible on already maximized window");
			return false;
		}
	}
	
	
	public void closeWindow() 
	{
		this.xBtn().click();
		test.log(Status.INFO, "The window is closed by clicking on the X button");
	}
	
	
	public boolean isWindowClosed() 
	{	
		
		if(openWindowButton().isDisplayed()) 	
		{
			test.log(Status.PASS, "The window is closed");
			return true;
		}
		else 
		{
			test.log(Status.FAIL, "The window is NOT closed");
			return false;
		}
	}
	
}
