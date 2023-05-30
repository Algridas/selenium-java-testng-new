package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_and_Tab {
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
	}

//	@Test
	public void TC_01_ID() {
		
		//Parent Page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Lấy ra được ID của tab hiện tại
		String basicFormID = driver.getWindowHandle();
		
		//phần này ko cần lắm, vì nó chỉ cần coi cái ID là gì thôi
		System.out.println("Parent Page= " + basicFormID);
		
		//Click vào Google Link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		switchToWindowByID(basicFormID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Muhammad Ali");
		sleepInSecond(5);
		
		//Vì sao dùng hàm này lại -> lấy ra được ID của tab Google (Child): Vì đứng ở đâu, xài ở đó, nó lấy tab nào đang active 
		String googleWindowID = driver.getWindowHandle();
		
		switchToWindowByID(googleWindowID);

		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");

	}

//	@Test
	public void TC_02_Title() {
		
		//Parent Page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Click vào Google Link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		switchToWindowByPageTitle("Google");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Muhammad Ali");
		sleepInSecond(5);
	
		
		
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");

		//Click vào Facebook Link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		switchToWindowByPageTitle("Facebook – log in or sign up");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("abcd@yahoo.com");
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("abcd@yahoo.com");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		sleepInSecond(5);
		
		
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		//Click vào Tiki link để bật ra 1 tab mới
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("dildo");
		sleepInSecond(5);
		
	}

	@Test
	public void TC_03_Live_Guru() {
		
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		String parentID = driver.getWindowHandle();
		
		//Click vào XPeria - Compare
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		sleepInSecond(2);
		
		closeAllWindowWithoutParent(parentID);
		
		switchToWindowByPageTitle("Mobile");
		
		driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product IPhone has been added to comparison list.");
		sleepInSecond(2);
	}
	
	
	//Dùng được cho duy nhất 2 ID (Window/Tab)
	public void switchToWindowByID(String otherID) {
		//Lấy hết tất cả các ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(otherID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		}
	}
	
	//Dùng được cho  2 ID trở lên (Window/Tab)
	public void switchToWindowByPageTitle(String expectedPageTitle) {
		//Lấy hết tất cả các ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			//Switch từng ID trước
			driver.switchTo().window(id);
			
			//lấy ra title của page này
			String actualPageTitle = driver.getTitle();
			
			if(actualPageTitle.equals(expectedPageTitle)) {
				sleepInSecond(2);
				break;
			}
		}
	}
	
	
	public void closeAllWindowWithoutParent(String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
		}
		
		driver.switchTo().window(parentID);
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