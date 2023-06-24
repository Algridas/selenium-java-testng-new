package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_file {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
	
	String JanibekSultanName = "djanibek-sultan-.jpg";
	String CircassianNogaiCrimeanName = "De_Landschappen_der_Percoptize_en_Nogaize_Tartares,_Circassen,_P_Van_der_Aa_(Leiden,_1707).jpg";
	String AlikhanBukeikhanovName = "Alikhan_Bukeikhanov_in_1906.jpg";
	
	String JanibekSultanPath = projectPath + "\\upLoadFiles\\" + JanibekSultanName;
	String CircassianNogaiCrimeanPath = projectPath + "\\upLoadFiles\\" + CircassianNogaiCrimeanName;
	String AlikhanBukeikhanovPath = projectPath + "\\upLoadFiles\\" + AlikhanBukeikhanovName;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_One_File_Per_Time() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		
		//Load file lên
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(JanibekSultanPath);
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(CircassianNogaiCrimeanPath);
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(AlikhanBukeikhanovPath);
		sleepInSecond(3);
		
		//Verify file được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + AlikhanBukeikhanovName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + CircassianNogaiCrimeanName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + JanibekSultanName + "']")).isDisplayed());
		
		// CLick upload
		List<WebElement> buttonUpload =  driver.findElements(By.xpath("//table[@class='table table-striped']//button[contains(@class,'primary start')]"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(2);
		}
		
		//Verify upload thành công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + AlikhanBukeikhanovName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + CircassianNogaiCrimeanName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + JanibekSultanName + "']")).isDisplayed());
		
		//Verify upload thành công (image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + AlikhanBukeikhanovName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'Tartares')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + JanibekSultanName + "')]"));
	}

//	@Test
	public void TC_02_Multiple_File_Per_Time() {
		
driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		
		//Load file lên
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(JanibekSultanPath + "\n" + AlikhanBukeikhanovPath + "\n" + CircassianNogaiCrimeanPath);
		sleepInSecond(3);

		//Verify file được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + AlikhanBukeikhanovName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + CircassianNogaiCrimeanName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + JanibekSultanName + "']")).isDisplayed());
		
		// CLick upload
		List<WebElement> buttonUpload =  driver.findElements(By.xpath("//table[@class='table table-striped']//button[contains(@class,'primary start')]"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(2);
		}
		
		//Verify upload thành công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + AlikhanBukeikhanovName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + CircassianNogaiCrimeanName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + JanibekSultanName + "']")).isDisplayed());
		
		//Verify upload thành công (image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + AlikhanBukeikhanovName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'Tartares')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + JanibekSultanName + "')]"));
		
	}

	@Test
	public void TC_03_() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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