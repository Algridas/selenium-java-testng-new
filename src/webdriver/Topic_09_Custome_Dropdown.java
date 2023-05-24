package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custome_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_JQUERY() {
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//Muốn chọn item cho Speed dropdown
		
		selectItemInDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div[@role='option']", "Fast");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Fast");
		sleepInSecond(3);
		
		selectItemInDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div[@role='option']", "Slower");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Slower");
		sleepInSecond(3);
		
		selectItemInDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div[@role='option']", "Slow");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Slow");
		sleepInSecond(3);
		
		selectItemInDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div[@role='option']", "Faster");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Faster");
		sleepInSecond(3);	
		
		selectItemInDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//li[@class='ui-menu-item']/div[@role='option']", "Dr.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(), "Dr.");
		sleepInSecond(3);		
		
		
	}

//	@Test
	public void TC_02_ReactJS() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']//span[@class='text']", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");
		sleepInSecond(3);	
		
		selectItemInDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']//span[@class='text']", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
		sleepInSecond(3);	
		
		selectItemInDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']//span[@class='text']", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Justen Kitsune");
		sleepInSecond(3);	
		
	}

//	@Test
	public void TC_03_VueJS() {
		
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
		sleepInSecond(3);
		
		selectItemInDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
		sleepInSecond(3);	
	}
	@Test
	public void TC_04_Editable() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndSelectItemInDropdown("//input[@class='search']", "//div[@class='visible menu transition']//div[@role='option']", "Algeria");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Algeria");
		sleepInSecond(3);
		
		enterAndSelectItemInDropdown("//input[@class='search']", "//div[@class='visible menu transition']//div[@role='option']", "Andorra");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Andorra");
		sleepInSecond(3);
		
		enterAndSelectItemInDropdown("//input[@class='search']", "//div[@class='visible menu transition']//div[@role='option']", "Albania");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Albania");
		sleepInSecond(3);
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//Tránh lặp lại code nhiều lần thì chỉ cần gọi cái hàm ra để dùng
	//Đi kèm với tham số
	//Nếu truyền cứng 1 giá trị vào trong hàm = vô nghĩa
	// Nên define để dùng đi dùng lại nhiều lần
	public void selectItemInDropdown(String parentXpath, String allItemXpath, String expectedTextItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		//Locator phải lấy để đại diện cho tất cả các locator
		//Và phải lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		List<WebElement> speedDropdownItems=  driver.findElements(By.xpath(allItemXpath));
		
		//3 tìm xem có đúng cái đang cần hay ko
		for (WebElement tempItem : speedDropdownItems) {
			
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			//4 - KIểm tra cái text của item đúng với cái mình mong muốn
			if(itemText.trim().equals(expectedTextItem)) {
				//5 - Click vào item đó
//				System.out.println("Click vào item");
				tempItem.click(); 
				break;
			} 
		}
	}
	
	
	public void enterAndSelectItemInDropdown(String textboxXpath, String allItemXpath, String expectedTextItem) {
		driver.findElement(By.xpath(textboxXpath)).clear();
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		//Locator phải lấy để đại diện cho tất cả các locator
		//Và phải lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		List<WebElement> speedDropdownItems=  driver.findElements(By.xpath(allItemXpath));
		
		//3 tìm xem có đúng cái đang cần hay ko
		for (WebElement tempItem : speedDropdownItems) {
			
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			//4 - KIểm tra cái text của item đúng với cái mình mong muốn
			if(itemText.trim().equals(expectedTextItem)) {
				//5 - Click vào item đó
				sleepInSecond(1);
//				System.out.println("Click vào item");
				tempItem.click(); 
				break;
			} 
		}
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