 package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement {
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

	@Test
	public void TC_01_WebElement() {
		WebElement element = driver.findElement(By.className(""));
		
		//DÙng cho các textbox/ textarea/ dropdown (Editable)
		//Xóa dữ liệu đi trước khi nhập text
		element.clear(); //*
		
		
		//DÙng cho các textbox/ textarea/ dropdown (Editable)
		//Nhập liệu
		element.sendKeys(""); //**
		
		//Click vào các button/ link/ checkbox/ radio/ image/...
		element.click(); //**
		
		String searchAttribute = element.getAttribute("placeholder"); //**
		String emailTextboxAttribute = element.getAttribute("value");
		//Search store
		
		//GUI: Font/ Size/ Color/ Location/ Position/..
		element.getCssValue("background-color"); //*
		
		// Chụp hình khi testcase fail
		element.getScreenshotAs(OutputType.FILE); //*
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		// Lấy text từ Error message/success message/ label/ header/..
		element.getText(); //**
		
		
		// Khi nào dùng getText - getAttribute
		//Khi mà cái value mình cần lấy nó nằm bên ngoài -> getText
		//Khi cái value mình cần lấy nó nằm bên trong -> getAttribute
		
		//Dùng để verify xem 1 elenment hiển thị hoặc ko
		//Phạm vi: Tất cả các element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		//Dùng để verify xem 1 element được chọn hay chưa
		//Phạm vi: CHeckbox/ Radio
		Assert.assertTrue(element.isSelected()); //*
		
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