package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_2_Fixed_in_not_in_DOM {
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
		
		
		//tắt notification trên Chrome
//		Map<String, Integer> prefs = new HashMap<String, Integer>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//tắt notification trên firefox
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Fixed_NOT_IN_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		
		
		//By: Nó chưa có đi tìm element
		By loginPopup = By.xpath("//div[contains(@class,'ReactModal__Content ')]");
		
		
		// Verify nó chưa hiển thị khi chưa click vào Login button
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		//CLick cho bật login popup
		driver.findElement(By.xpath("//div[contains(@data-view-id,'header_account')]")).click();
		sleepInSecond(5);
		
		//Verify nó hiển thị
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("0987654321");
		sleepInSecond(2);
		
		//close popup
		driver.findElement(By.xpath("//img[@class='close-img']")).click();
		
		// Verify nó ko còn hiển thị 
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		driver.findElement(By.xpath("//div[contains(@data-view-id,'header_account')]")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());
		sleepInSecond(2);
		
		//close popup
				driver.findElement(By.xpath("//img[@class='close-img']")).click();
				
				// Verify nó ko còn hiển thị 
				Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}

	@Test
	public void Fixed_NOT_IN_DOM_Facebook() {
		
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//div[@class='_6ltg']//a[@role='button']")).click();
		
		//By: Nó chưa có đi tìm element
		By loginPopup = By.xpath("//div[@class='_8ien']");
		
		
		//Verify nó hiển thị
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		sleepInSecond(5);
		
		
		driver.findElement(By.name("firstname")).sendKeys("Automation");
		driver.findElement(By.name("lastname")).sendKeys("FC");
		driver.findElement(By.name("reg_email__")).sendKeys("0987654321");
		driver.findElement(By.name("reg_passwd__")).sendKeys("abcd123");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("18");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Feb");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1990");
		driver.findElement(By.xpath("//label[text()='Male']")).click();
		
		//close popup
	    driver.findElement(By.xpath("//img[@class='_8idr img']")).click();
	    sleepInSecond(2);
		
		// Verify nó ko còn hiển thị 
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	    
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