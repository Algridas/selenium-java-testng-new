package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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

public class Topic_16_Popup_Part_3_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "testdemo" + getRandomNumber() + "@gmail.com";
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
	public void TC_01_Random_IN_DOM_Java_Code_Geeks() {
		
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(30);
		By lePopup = By.xpath("//div[@class='lepopup-popup-container']/div[not(contains(@style,'display:none'))]");
		
		//Vì nó luôn có trong DOM nên có thể dùng hàm isDisplayed() để kiểm tra được
		if(driver.findElement(lePopup).isDisplayed()) {
			//Nhập Email vào
			driver.findElement(By.xpath("//div[@class='lepopup-input']/input")).sendKeys(emailAddress);
			sleepInSecond(3);
			driver.findElement(By.xpath("//a[@data-label='Get the Books' or @data-label='OK']/span")).click();
			sleepInSecond(10);
			//Verify
			Assert.assertEquals(driver.findElement(By.xpath("//div[@class='lepopup-element-html-content']/h4")).getText(), "Thank you!");
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lepopup-element-html-content']/p")).getText().contains("Your sign-up request was successful. We will contact you shortly."));
		}
		sleepInSecond(30);
		driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("Agile Testing Explained");
		driver.findElement(By.xpath("//button[@id='search-submit']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='main']/header//input[@name='s']")).getAttribute("value"), "Agile Testing Explained");
	}

//	@Test
	public void TC_02_Random_IN_DOM() {
		
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);
		By Popup = By.xpath("//div[@id='tve_editor']");
		
		//hàm isDisplayed() chỉ check cho element có trong DOM
		//Ko có trong DOM thì ko có check dc -> Fail ngay đoạn findElement r
		
		if(driver.findElement(Popup).isDisplayed()) {
			//Close pop up này đi
			driver.findElement(By.xpath("//div[contains(@class,'tve_et_click')]")).click();
			sleepInSecond(3);
		}
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
		
	}

	@Test
	public void TC_03_Random_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		By Popup = By.xpath("//div[@class='popup-content']");
		
		//findElement -> Sẽ bị fail khi ko tìm thấy element -> Ném ra 1 ngoại lệ: NoSuchElementException
		//findElements -> Ko bị fail khi không tìm thấy element -> Trả về 1 list rỗng
		
		//isDisplayed()
		if(driver.findElements(Popup).size() > 0 && driver.findElements(Popup).get(0).isDisplayed()) {
			driver.findElement(By.xpath("//input[@id='popup-name']")).sendKeys("Abdullah");
			driver.findElement(By.xpath("//input[@id='popup-email']")).sendKeys(emailAddress);
			driver.findElement(By.xpath("//input[@id='popup-phone']")).sendKeys("0987654321");
			sleepInSecond(3);
			driver.findElement(By.xpath("//button[@class='close']")).click();
		}
		
		driver.findElement(By.xpath("//ul[contains(@class,'navbar-right')]//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@id='search-courses']")).sendKeys("Khóa học Thiết kế và Thi công Hệ thống BMS");
		driver.findElement(By.xpath("//button[@id='search-course-button']")).click();
		sleepInSecond(3);
		
		//Duy nhất 1 course hiển thị
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='course-info hachium']")).size(), 1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='course-content hachium']/h4")).getText(), "Khóa học Thiết kế và Thi công Hệ thống BMS");
		
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
	
	
	public int getRandomNumber() {
		return new Random().nextInt(9999);
	}
	
	
}