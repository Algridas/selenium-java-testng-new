package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
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
	public void TC_01_Button() {
		
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//li[contains(@class,'popup-login-tab-login')]")).click();
		By loginButton = By.xpath("//button[@class='fhs-btn-login']");
		
		//Verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0909800427");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("1223456789");
		sleepInSecond(2);
		//Verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		loginButtonBackground=driver.findElement(loginButton).getCssValue("background-color");
		
		Color loginButtonBackgroundColour = Color.fromString(loginButtonBackground);
		Assert.assertEquals(loginButtonBackgroundColour.asHex(), "#c92127");
		
		System.out.println(loginButtonBackground);
		
		sleepInSecond(5);
	}

//	@Test
	public void TC_02_Default_Checkbox_Radio() {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// CLick chọn 1 checkbox/
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		sleepInSecond(5);
		
		//CLick chọn 1 radio
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		sleepInSecond(5);
		
		//Verify các checkbox/radio đã được chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		
		//Checkbox có thể tự bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		sleepInSecond(5);
		//Verify checkbox đã được bỏ chọn rồi
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		
		//Radio ko thể tự bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		
		//Verify radio vẫn được chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		sleepInSecond(5);
	}

//	@Test
	public void TC_03_Default_Checkbox_Multiple() {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCHeckboxes = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
		
//		for (WebElement checkbox : allCHeckboxes) {
//			checkbox.click();
//			sleepInSecond(1);
//		}
//		
//		//Verify tất cả các checkbox được chọn thành công
//		for (WebElement checkbox : allCHeckboxes) {
//			Assert.assertTrue(checkbox.isSelected());
//			
//		}
		
		//Nếu như gặp 1 checkbox có tên là X thì ms click
		for (WebElement checkbox : allCHeckboxes) {
			
			if(checkbox.getAttribute("value").equals("Emotional Disorder")) {
				checkbox.click();
				sleepInSecond(1);
			}
			
		}
		
	}
	
	@Test
	public void TC_04_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		
		//CHọn nó
		
		if (!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
			sleepInSecond(5);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		sleepInSecond(5);
		
		//Bỏ Chọn
		
		if (driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
			sleepInSecond(5);
		}
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		sleepInSecond(5);
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