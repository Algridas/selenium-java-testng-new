package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Condition_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitwait;
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitwait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		
	}

//	@Test
	public void TC_01_Visible_Displayed_Visibility() {
		
		driver.get("https://www.facebook.com/"); 
		
		//1. Có trên UI (bắt buộc)
		//1. Có trong HTML (bắt buôc)
		
		//  wait cho email address textbox hiển thị
		
		//CHờ cho email address textbox hiển thị trong vòng 10s
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		driver.findElement(By.id("email")).sendKeys("laixi123@yahoo.com");
		
	}

//	@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		// 2. Không có trên UI (Bắt buộc)
		// 1. Có trong HTML
		driver.get("https://www.facebook.com/"); 
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
//	@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		//2. Không có trên UI (bắt buộc)
		//2. Không có trong HMTL
		driver.get("https://www.facebook.com/"); 
		
		
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	

//	@Test
	public void TC_03_Presence_I() {
		
		driver.get("https://www.facebook.com/"); 
		
		//1. Có trên UI (bắt buộc)
		//1. Có trong HTML (bắt buôc)
		
		//  wait cho email address textbox hiển thị
		
		//CHờ cho email address textbox presence trong HTML trong vòng 10s
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		
	}
	
//	@Test
	public void TC_03_Presence_II() {
		// 2. Không có trên UI (Bắt buộc)
		// 1. Có trong HTML
		driver.get("https://www.facebook.com/"); 
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
	
	@Test
	public void TC_04_Staleness() {
		// 2. Không có trên UI (Bắt buộc)
		// 1. Không có trong HTML
		driver.get("https://www.facebook.com/"); 
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Phase 1: Element có trong cây HTML
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		//Thao tác vs element khác làm cho element re-enter email ko còn trong DOM nữa
		//...
		
		//Close pop up đi
		driver.findElement(By.xpath("//img[@class='_8idr img']")).click();
		
		
		//Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitwait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}