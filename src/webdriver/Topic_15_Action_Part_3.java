package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Action_Part_3 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Scroll đến element đó
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		
		sleepInSecond(1);

		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
		
	}
	
	
//	@Test
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[contains(@class,'context-menu-one')]"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
		sleepInSecond(5);
		
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit context-menu-hover context-menu-visible')]")).isDisplayed());
		action.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		sleepInSecond(3);
		driver.switchTo().alert().accept();
		Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
		sleepInSecond(3);
		
	}
	
	
	
	@Test
	public void TC_02_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		
		WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		
		//Verify Text
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget']")).getText(), "You did great!");
		
		//Verify Background color
		 String bigCircleBackgroundColor = bigCircle.getCssValue("background-color");
		 System.out.println(bigCircleBackgroundColor);
		 // Convert qua mã HEXA cho nó đồng nhất
		 Assert.assertEquals(Color.fromString(bigCircleBackgroundColor).asHex().toUpperCase(), "#03A9F4");
		
		sleepInSecond(3);
	}
	
	

	@AfterClass
	public void sleepInSecond() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	
}