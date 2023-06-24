package webdriver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_23_FluentWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	
	
	long allTinme = 10; //Second
	long pollingTime = 1000; //Milisecond
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
	}

//	@Test
	public void TC_01_Fluent() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		findElement("//div[@id='start']/button").click();
		
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
		
	}
		@Test
	public void TC_02_Fluent() {
		
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTime = findElement("//div[@id='javascript_countdown_time']");
		
		fluentElement = new FluentWait<WebElement>(countdownTime);
		
		fluentElement.withTimeout(Duration.ofSeconds(allTinme))
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(org.openqa.selenium.NoSuchElementException.class);
		
		//Apply điều kiện
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
			
		});
		
	}

	@Test
	public void TC_03_() {
	}
	public WebElement findElement(String xpathLocator) {
		//Dùng Fluent Wait
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		//set tổng thời gian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(allTinme))
		// 1/3 giay6 check 1 lan
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(org.openqa.selenium.NoSuchElementException.class);
		
		//Apply điều kiện
		return fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
		
		
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}