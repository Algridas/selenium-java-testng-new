package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://demo.nopcommerce.com/register");
	}

	@Test
	public void TC_01_ID() {
		
		driver.findElement(By.id("FirstName")).sendKeys("Hala Madrid");
	}

	@Test
	public void TC_02_Class(){
		driver.get("https://demo.nopcommerce.com/search");
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
		
	}

	@Test
	public void TC_03_Name() {
		//Click vào Advance search checkbox
		driver.findElement(By.name("advs")).click();
		
	}
	@Test
	public void TC_04_TagName() {
		// tìm ra bao nhiêu cái thẻ input trên màn hình hiện tại
		System.out.println(driver.findElement(By.tagName("input")).getSize());
		
		
	}
	@Test
	public void TC_05_LinkText() {
		//click vào dường link Addresses(tuyệt đối)
		driver.findElement(By.linkText("Addresses")).click();
	}
	@Test
	public void TC_06_PartialLinkText() {
		//click vào dường link Apply for vendor account(tuong đối)
		driver.findElement(By.partialLinkText("vendor account")).click();
		
	}
	
	@Test
	public void TC_07_Css() {
		// Mở lại trang register
		driver.get("https://demo.nopcommerce.com/register");
		//1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		//2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
	}
	@Test
	public void TC_08_XPath() {
		
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}