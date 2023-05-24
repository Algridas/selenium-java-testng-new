package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_Part_2 {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
	//đang chứa 12 số trong list này
		
		//1 -  CLick vào số 1 (source)
		action.clickAndHold(listNumber.get(0))
		// 2 -Vẫn giữ chuột/ chưa nhả ra
		// 3 - Di chuột tới số (target)
		.moveToElement(listNumber.get(7))
		//4 - Nhả chuột trái ra
		.release()
		
		//Execute
		.perform();
		
		sleepInSecond(2);
		
		List<WebElement> listSelectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(listSelectedNumber.size(), 8);
		
	}


	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
	//đang chứa 12 số trong list này
		
	//Nhấn CTRl xuống
		action.keyDown(Keys.CONTROL).perform();
	//Click chọn các số random	
		action.click(listNumber.get(0))
		.click(listNumber.get(5))
		.click(listNumber.get(7))
		.click(listNumber.get(10))
		.click(listNumber.get(8)).perform();
		//Nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
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