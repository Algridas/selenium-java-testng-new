package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_dowpdown {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, emailNumber = String.valueOf(rand.nextInt(9999));
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		  firstName = "Muhammad";
		  lastName = "Chagaev";
		  emailAddress = "MuhammadChagaev" + emailNumber + "@gmail.com";
		  password= "abcd123!!";
		  companyName= "Axon";
	}

	@Test
	public void TC_01_() {
		
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
		
//	       WebElement Daydown= driver.findElement(By.name("DateOfBirthDay"));
//	       WebElement Monthdown= driver.findElement(By.name("DateOfBirthMonth"));
//	       WebElement Yeardown= driver.findElement(By.name("DateOfBirthYear"));
//	       
//	       Select selectDay = new Select(Daydown);
//	       Select selectMonth = new Select(Monthdown);
//	       Select selectYear = new Select(Yeardown);
//	       selectDay.selectByVisibleText("1");
//	       selectMonth.selectByVisibleText("May");
//	       selectYear.selectByVisibleText("1980");
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("1");
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("May");
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText("1980");
	       driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
	       driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(companyName);
	       driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
	       driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
	       driver.findElement(By.xpath("//button[@id='register-button']")).click();
	       Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
	       sleepInSecond(5);
	       driver.findElement(By.xpath("//div[@class='footer-block my-account']//a[text()='My account']")).click();
	       driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
	       driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
	       driver.findElement(By.xpath("//button[text()='Log in']")).click();
	       
			Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);
			Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);
			
			Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),"1");
			Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), "May");
			Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),"1980");
			Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAddress);
			Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Company']")).getAttribute("value"), companyName);
	       sleepInSecond(3);
		
	}

	@Test
	public void TC_02_() {
	}

	@Test
	public void TC_03_() {
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