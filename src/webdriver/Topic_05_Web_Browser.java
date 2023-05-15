package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
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
		
		//Tương tác với Browser thì sẽ thông qua biến WebDriver driver
		//Tương tác với Element thì sẽ thông qua biến WebElement element
		
	}

	@Test
	public void TC_01_() {
		//Java Document(cách sử dụng hàm này như thế nào)
		
		
		// >=2: Nó sẽ đóng tab/ window mà nó đang đứng
		// =1: Nó cũng đóng Browser
		driver.close();
		
		//Ko quan tâm bao nhiêu tab/ window -> Browser
		driver.quit();
		
		// - Có thể lưu nó vào 1 biến để sử dụng cho các step sau -> dùng lại nhiều lần 
		WebElement emailTextbox = driver.findElement(By.xpath(""));
		
		//Tìm nhiều element 
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		//Mở ra 1 Url nào đó
		driver.get("https://en.wikipedia.org/wiki/Battle_of_Ghazdewan");
		
		//Click vào link: wiki tiếng Việt
		
		//Trả về Url của page hiện tại
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://uz.wikipedia.org/wiki/G%CA%BBijduvon_jangi");
		
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		
		//Trả về source code HTML của page hiện tại
		//Verify tương đối
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ với mọi người trong cuộc sống của bạn"));
		Assert.assertTrue(driver.getPageSource().contains("trong cuộc sống của bạn"));
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ "));
		
		// Trả về title của page hiện tại
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//Lấy ra được ID của Window/ Tab mà driver đang đứng (active)
		String loginWindowID = driver.getWindowHandle();
		
		// Lấy ra ID của tất cả Window/ Tab
		Set<String> allIDs = driver.getWindowHandles();
		
		// Cookie/ Cache
		Options opt = driver.manage();
		
		//Login thanh cong -> Luu lai
		opt.getCookies();
		
		//Testcase khác -> Set cookie vào lại -> Ko cần phải login nữa

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
}