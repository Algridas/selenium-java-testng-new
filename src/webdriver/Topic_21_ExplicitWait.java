package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	
	String JanibekSultanName = "djanibek-sultan-.jpg";
//	String CircassianNogaiCrimeanName = "De_Landschappen_der_Percoptize_en_Nogaize_Tartares,_Circassen,_P_Van_der_Aa_(Leiden,_1707).jpg";
	String AlikhanBukeikhanovName = "Alikhan_Bukeikhanov_in_1906.jpg";
	
	String JanibekSultanPath = projectPath + "\\upLoadFiles\\" + JanibekSultanName;
//	String CircassianNogaiCrimeanPath = projectPath + "\\upLoadFiles\\" + CircassianNogaiCrimeanName;
	String AlikhanBukeikhanovPath = projectPath + "\\upLoadFiles\\" + AlikhanBukeikhanovName;
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		
		//1 - Ảnh hưỡng trực tiếp tới 2 hàm: findElement/ findElements
		//2 - Ngoại lệ
		// Implicit Wait set ở đâu nó sẽ apply từ đó trở xuống
		// Nếu bị gán lại thì sẽ dùng cái giát trị mới/ ko dùng giá trị cũ
		
		//Apply 15s cho các điều kiện/ trạng thái cụ thể
		
		
//		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_Not_Enough() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait =  new WebDriverWait(driver, 3);
		
		//Click vào start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		
		//get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
	}

//	@Test
	public void TC_02_Enough() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait =  new WebDriverWait(driver, 3);
		
		//Click vào start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		
		//get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

//	@Test
	public void TC_03_More_Time() {		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait =  new WebDriverWait(driver, 50);
		
		//Click vào start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		
		//get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	
//	@Test
	public void TC_04_Invisible() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait =  new WebDriverWait(driver, 5);
		
		//Click vào start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		
		//get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	
//	@Test
	public void TC_05_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		 
		explicitWait =  new WebDriverWait(driver, 15);
		
		//Wait for Date Picker được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'RadCalendar')]")));
		
		
		//Verify cho Selected Dates là không có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='label']")).getText(), "No Selected Dates to display.");
		
		
		//Wait cho ngày 12 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='12']")));
		
		//CLick vào ngày 12
		driver.findElement(By.xpath("//a[text()='12']")).click();
		
		
		//Wait cho Ajax icon loading biến mất (invisible)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'RadCalendar1')]//div[@class='raDiv']")));
		
		//Wait cho ngày vừa được chọn là được phép click trở lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='12']")));
		
		//Verify cho cái text Selected Dates là có ngày được chọn "Sunday, June 11, 2023"
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='label']")).getText(), "Monday, June 12, 2023");
	}
	
	@Test
	public void TC_06_Upload_File() {
		driver.get("https://gofile.io/uploadFiles");

		explicitWait = new WebDriverWait(driver, 15);
		
		// Wait for Add files được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row justify-content-center']//button[contains(@class,'filesUploadButton')]")));
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(JanibekSultanPath + "\n" + AlikhanBukeikhanovPath );
		
		
		//Wait cho các loading icon của từng file biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[contains(@class,'mainUploadFilesListDetails')]//div[contains(@class,'progress position')]"))));
		
		
		//Wait cho Upload message thành công được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-auto text-center']//div[text()='Your files have been successfully uploaded']")));

//		Verify message này displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-auto text-center']//div[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		//Wait cho Link được clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-6 text-center']//a[@class='ajaxLink']")));
		
		//Click vào link
		driver.findElement(By.xpath("//div[@class='col-6 text-center']//a[@class='ajaxLink']")).click();
		
		//Wait + verify luôn: cho file name vs button download/ play hiển thị
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ JanibekSultanName +"']/parent::a/parent::div/following-sibling::div//a[@class='me-1']/button[contains(@class,'btn-outline-secondary')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ AlikhanBukeikhanovName +"']/parent::a/parent::div/following-sibling::div//a[@class='me-1']/button[contains(@class,'btn-outline-secondary')]"))).isDisplayed());
		
		//Wait + verify luôn: cho file name vs button download/ play hiển thị
				Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ JanibekSultanName +"']/parent::a/parent::div/following-sibling::div/button[contains(@class,'filesContentOptionPlay')]"))).isDisplayed());
				Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ AlikhanBukeikhanovName +"']/parent::a/parent::div/following-sibling::div/button[contains(@class,'filesContentOptionPlay')]"))).isDisplayed());
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}