package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String username = "admin"; //Reading thing this from external CSV or XLS
	String password = "admin";//Reading thing this from external CSV or XLS

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Accept_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		Assert.assertEquals(driver.switchTo().alert().getText(), "I am a JS Alert");
		sleepInSecond(2);
		driver.switchTo().alert().accept();
		sleepInSecond(5);
	    String resultContent = driver.findElement(By.id("result")).getText();
	    Assert.assertEquals(resultContent, "You clicked an alert successfully");
	}

//	@Test
	public void TC_02_Confirm_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		Assert.assertEquals(driver.switchTo().alert().getText(), "I am a JS Confirm");
		sleepInSecond(2);
		driver.switchTo().alert().dismiss();
		sleepInSecond(5);
	    String resultContent = driver.findElement(By.id("result")).getText();
	    Assert.assertEquals(resultContent, "You clicked: Cancel");
		
	}

//	@Test
	public void TC_03_Prompt_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		Assert.assertEquals(driver.switchTo().alert().getText(), "I am a JS prompt");
        driver.switchTo().alert().sendKeys("hello");
        sleepInSecond(2);
        driver.switchTo().alert().accept();
        sleepInSecond(5);
        String resultContent = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(resultContent, "You entered: hello");
	}
	
//	@Test
	public void TC_03_Authentication_Alert() {
		//Cách 1: viết thẳng username và password trên cái đường link đó luôn
//		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
//		sleepInSecond(2);
		//Cách 2: đặt biến
		driver.get("http://"+username+":"+password+"@the-internet.herokuapp.com/basic_auth");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), ' Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
//Sẽ có TC4 liên quan đến prompt, mình sẽ viết hàm
	@AfterClass
	public void afterClass() {
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