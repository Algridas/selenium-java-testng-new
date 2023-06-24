package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_ImplicitWait {
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
		
		//1 - Ảnh hưỡng trực tiếp tới 2 hàm: findElement/ findElements
		//2 - Ngoại lệ
		// Implicit Wait set ở đâu nó sẽ apply từ đó trở xuống
		// Nếu bị gán lại thì sẽ dùng cái giát trị mới/ ko dùng giá trị cũ
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Not_Enough() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vào start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		
		
		//get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
	}

	@Test
	public void TC_02_Enough() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		
		
		//Click vào start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
	}

	@Test
	public void TC_03_More_Time() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vào start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		
		//get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}