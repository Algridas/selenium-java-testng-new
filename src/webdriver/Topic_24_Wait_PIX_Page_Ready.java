package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Wait_PIX_Page_Ready {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 30);
		
	}

//	@Test
	public void TC_01_Orange_HRM_API() {
		driver.get("https://api.orangehrm.com/");
		
		//Wait cho icon loading biến mất
		//Vì khi nó biến mất thì cái trang nó sẽ load hết dữ liệu về thành công
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loader']/div[@class='spinner']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='project']//h1")).getText(), "OrangeHRM REST API Documentation");
		
	}

//	@Test
	public void TC_02_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com");
		
		driver.findElement(By.xpath("//input[@id='Email']")).clear();
		driver.findElement(By.xpath("//input[@id='Password']")).clear();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("admin@yourstore.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("admin");
		
		//CLick chuyển trang: Từ Login vào Dashboard
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		//Click chuyển trang: Từ Dashboard về Login
		driver.findElement(By.xpath("//a[text()='Logout']")).click();

		Assert.assertTrue(isPageLoadedSuccess());
		 
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
		
	}

	@Test
	public void TC_03_Blog_Test_Project() {
		driver.get("https://blog.testproject.io/");
		
		//Hover chuột vào 1 element bất kì tại page này để cho page ready
		action.moveToElement(driver.findElement(By.xpath("//h1[contains(@class,'main-heading')]"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		String keyword = "Selenium";
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys(keyword);
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postArticles = driver.findElements(By.xpath("//h3[@class='post-title']/a"));
		
		for (WebElement article : postArticles) {
			Assert.assertTrue(article.getText().contains(keyword));
		}

	}
	
	public boolean waitforAjaxBusyLoadingInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ajaxBusy']")));
	}
	
	
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}