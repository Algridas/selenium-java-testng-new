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

public class Topic_13_Action_Part_1 {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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

//	@Test
	public void TC_01_Tooltip() {
		
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
		
	}

//	@Test
	public void TC_02_Myntra() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[@href='/kids-home-bath']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/kids-home-bath");
	}

	@Test
	public void TC_03_Fahasa() {
		
		driver.get("https://www.fahasa.com/");
		//CHưa học bài handle popup - manual click cho nó tắt popup đi trước khi hover
		sleepInSecond(10);
		
		//Hover 1 lần
		action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
		sleepInSecond(3);
		
		//Hover lần 2
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
		sleepInSecond(3);
		
		//Click vào
		driver.findElement(By.xpath("//div[contains(@class, 'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());
		
		
	}

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