package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_iframe {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

//		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		
		
		//tắt notification trên firefox
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Kyna_Iframe() {
		
		driver.get("https://skills.kynaenglish.vn/");
		
		//Verify Facebook iframe đã hiển thị
		// Facebook iframe vẫn là 1 element của trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		//Cần phải switch vào đúng cái thẻ iframe chứa các element đó
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(facebookLike, "165K followers");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		
		//click vào chat iframe
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[contains(@class,'button_bar')]")).click();
		driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys("John Ken");
		driver.findElement(By.xpath("//input[contains(@class,'input_phone')]")).sendKeys("0987654321");
		new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
//		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//select[@id='serviceSelect']")));
		sleepInSecond(5);
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Xin Chúa hãy ban cho con sự nghiệp thăng tiến, cuộc sống hạnh phúc bên người con yêu ạ");
		sleepInSecond(5);
		
		//Từ chat quay về trang main
		driver.switchTo().defaultContent();
		
		//Search vs từ khóa Excel
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		
		List<WebElement> courseName = driver.findElements(By.xpath("//div[@class='content']/h4"));
		
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		} 
		
		sleepInSecond(5);
		
	}

	@Test
	public void TC_02_HDFC_Bank_Frame() {
		
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		//switch qua Frame chứ Login textbox
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("Abdol123");
		driver.findElement(By.xpath("//a[contains(@class,'login-btn')]")).click();
		sleepInSecond(5);
		//Từ chat quay về trang main
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='keyboard']")).isDisplayed());
		driver.findElement(By.xpath("//input[@id='keyboard']")).sendKeys("Abdol123");
		sleepInSecond(5);
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