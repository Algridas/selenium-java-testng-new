package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_Part_I {
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
		driver.get("https://alada.vn/");
	}

	@Test
	public void TC_01_register_with_empty_data() {
		
		
		//home page
		
		
		
		//Open login page
		
		driver.findElement(By.xpath("//a[@class='btn-anis-effect']")).click();
		
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		//driver.findElement(By.xpath("//div[@class='field_btn']//button")).click();
		driver.findElement(By.xpath("//button[@class= 'btn_pink_sm fs16']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
		
		
		
		
		
		
		sleepInSecond(3);
	}
	
	@Test
	public void TC_02_register_with_invalid_email() {
		
		//home page
		
		
		
		//Open login page
		
		driver.findElement(By.xpath("//a[@class='btn-anis-effect']")).click();
		
		
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kramer");
		driver.findElement(By.id("txtEmail")).sendKeys("123@45@678");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@45@678");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0908222700");
		driver.findElement(By.xpath("//div[@class='field_btn']//button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
		
		
		
		sleepInSecond(3);
		
	
	}
	
	@Test
	public void TC_03_register_with_invalid_confirm_email() {
		
		
		//home page
		
	
		
		//Open login page
		
		driver.findElement(By.xpath("//a[@class='btn-anis-effect']")).click();
		
		
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kramer");
		driver.findElement(By.id("txtEmail")).sendKeys("johnkramer123@yahoo.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnkramer1213@yahoo.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0908222700");
		driver.findElement(By.xpath("//div[@class='field_btn']//button")).click();
		
		
	
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
		sleepInSecond(3);
		
		
	}
	
	
	@Test
	public void TC_04_register_with_password_less_than_6_characters() {
		
		//home page
		

		
		//Open login page
		
		driver.findElement(By.xpath("//a[@class='btn-anis-effect']")).click();
		
		

		driver.findElement(By.id("txtFirstname")).sendKeys("John Kramer");
		driver.findElement(By.id("txtEmail")).sendKeys("johnkramer123@yahoo.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnkramer123@yahoo.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0908222700");
		driver.findElement(By.xpath("//div[@class='field_btn']//button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
		sleepInSecond(3);
		
		
	}
	
	@Test
	public void TC_05_register_with_invalid_confirm_password() {
		
		
		//home page
		

		
		//Open login page
		
		driver.findElement(By.xpath("//a[@class='btn-anis-effect']")).click();
		
		
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kramer");
		driver.findElement(By.id("txtEmail")).sendKeys("johnkramer123@yahoo.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnkramer123@yahoo.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0908222700");
		driver.findElement(By.xpath("//div[@class='field_btn']//button")).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
		
		
		
		sleepInSecond(3);
		
	}
	
	
	@Test
	public void TC_06_register_with_invalid_phone_number() {
		
	
		//Open login page
		//Action 1
		driver.findElement(By.xpath("//a[@class='btn-anis-effect']")).click();
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kramer");
		driver.findElement(By.id("txtEmail")).sendKeys("johnkramer123@yahoo.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnkramer123@yahoo.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("09082227002313");
		driver.findElement(By.xpath("//div[@class='field_btn']//button")).click();
		
		// Verify 1
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Action 2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("0908");
		driver.findElement(By.xpath("//div[@class='field_btn']//button")).click();
		
		// Verify 2
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");

		
		//Action 3
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("909800427");
		driver.findElement(By.xpath("//div[@class='field_btn']//button")).click();
		
		// Verify 3
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

		
		sleepInSecond(3);
		
		
	}
	
	
//	
	@AfterClass
	public void afterClass() {
//		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
//ss
		driver.close();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}