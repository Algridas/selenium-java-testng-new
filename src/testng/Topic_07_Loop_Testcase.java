package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_07_Loop_Testcase {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 5)
	public void Register()  {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Khabib");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Nurmagomedov");
		String emailAddress = "afc" + getRandomNumber() + "@hotmail.net";
		System.out.println("Email address = " + emailAddress);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123123");
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());

		
		// ....
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	public int getRandomNumber() {
		
		Random rand = new Random();
		return rand.nextInt(9999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
