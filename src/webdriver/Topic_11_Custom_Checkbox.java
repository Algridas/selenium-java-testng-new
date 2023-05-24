package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		
		
		//Luôn khởi tạo sau biến driver này
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_() {
		
		driver.get("https://material.angular.io/components/radio/examples");
		
		/* CASE 1  */
		//The input bị che nên ko thao tác được
		//Thẻ input lại dùng để verify được -. vì hàm isSelected() nó chỉ work vs thẻ input 
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}

//	@Test
	public void TC_02_() {
		
		/* CASE 2  */
		//Thẻ khác với input để click (span/div/label/...) -> đang hiển thị là được
		//Thẻ này lại dùng để verify được -. vì hàm isSelected() nó chỉ work vs thẻ input 
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).isSelected());
		
		//Thẻ span/ div/ label luôn luôn trả vể false
		sleepInSecond(3);
	}

//	@Test
	public void TC_03_() {
		
		/* CASE 3 */
		//Thẻ khác với input để click (span/div/label/...) -> đang hiển thị là được
		//Thẻ input lại dùng để verify được -. vì hàm isSelected() nó chỉ work vs thẻ input
		
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
		sleepInSecond(3);
		
		//demo, basic oke, framework ko oke
	}
	
//	@Test
	public void TC_04_() {
		
		/* CASE 4 */
		//Thẻ input bị ẩn nhưng vẫn dùng để click
		//Hàm click() của WebElemnt nó sẽ ko tháo tác vào element bị ẩn được
		// Nên sẽ dùng 1 hàm click() của Javascript để click(click vào element bị ẩn được)
		//Thẻ input lại dùng để verify được -. vì hàm isSelected() nó chỉ work vs thẻ input
		
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		//Selenium nó cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào kịch bản test được -> JavascriptExecutor 
		
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		sleepInSecond(3);
		
		//demo, basic oke, framework ko oke
	}
	
	
	@Test
	public void TC_05_() {
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		//Selenium nó cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào kịch bản test được -> JavascriptExecutor 
		
		By radioButton = By.xpath("//div[@aria-label='Hà Nội']");
		By checkBox = By.xpath("//div[@aria-label='Quảng Nam']");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkBox));
		sleepInSecond(3);
		
		//Verify chọn thành công
		//Cách 1
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @aria-checked='true' ]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true' ]")).isDisplayed());
		
		//Cách 2
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkBox).getAttribute("aria-checked"), "true");
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