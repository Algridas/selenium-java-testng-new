package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_1_Fixed_in_DOM {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		
		
		//tắt notification trên Chrome
		Map<String, Integer> prefs = new HashMap<String, Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//tắt notification trên firefox
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(new FirefoxProfile());
//		options.addPreference("dom.webnotifications.enabled", false);
//		driver = new FirefoxDriver(options);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Fixed_IN_DOM_NgoaiNgu() {
		
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.xpath("//div[@id='modal-login-v1']//div[@class='modal-content']");
		
		
		//Verify popup is undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		//CLick vào button login
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		sleepInSecond(3);
	}

	@Test
	public void TC_02_Kyna() {
		
		driver.get("https://skills.kynaenglish.vn/");
		
		By loginPopup = By.xpath("//div[@id='k-popup-account-login']");
		
		//Verify popup is undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//a[@class='login-btn']")).click();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("123456");
//		sleepInSecond(10);
		driver.findElement(By.xpath("//form[@id='login-form']//button[@type='submit']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
//		sleepInSecond(10);
		
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