package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.id("under_18");
	By educationTextArea = By.id("edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.xpath("//input[@id='disable_password']");
	By biographyTextArea = By.xpath("//textarea[@id='bio']");
	By jobrole1 = By.xpath("//select[@id='job1']");
	By developmentCHeckBox = By.xpath("//input[@id='development']");
	By javaCheckbox = By.xpath("//input[@id='java']");
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

//	@Test
	public void TC_01_Displayed() {
		
		//Textbox/ Textarea nếu có hiển thị thì nhập text vào và in ra console
		//Textbox
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Selenium WebDriver");
			System.out.println("Email textbox is dispalyed");
		} else {
			System.out.println("Email textbox is not dispalyed");
		}
		sleepInSecond(5);
		//Textarea
		
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium WebDriver");
			System.out.println("Education Area is dispalyed");
		} else {
			System.out.println("Education Area is not dispalyed");
		}
		sleepInSecond(5);
		//Radio
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Under 18 radio is dispalyed");
		} else {
			System.out.println("Under 18 radio  is not dispalyed");
		}
		sleepInSecond(5);
		
		
		//user 5
		if (driver.findElement(nameUser5Text).isDisplayed()) {
			
			System.out.println("nameUser5Text is dispalyed");
		} else {
			System.out.println("nameUser5Text  is not dispalyed");
		}
		sleepInSecond(5);
		
	}

//	@Test
	public void TC_02_Enable() {
		
		if (driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password is enable");
			
		} else {
			System.out.println("Password is disable");
		}
		
		if (driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("biography is enable");
			
		} else {
			System.out.println("biography is disable");
		}
		
		if (driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("biography is enable");
			
		} else {
			System.out.println("biography is disable");
		}
		
		if (driver.findElement(jobrole1).isEnabled()) {
			System.out.println("job role 1 is enable");
			
		} else {
			System.out.println("job role 1 is disable");
		}
	}

//	@Test
	public void TC_03_Selected() {
		
		
		//Verify checkbox/ radio button are deselected
		
		
	        Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCHeckBox).isSelected());
		
		// Click to checkbox/ radio
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCHeckBox).click();
		sleepInSecond(5);
		//Verify checkbox/ radio button are selected
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCHeckBox).isSelected());
	}
	
	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Abdulloh@gmail.com");
		
		By passwordTextbox = By.xpath("//input[@id='new_password']");
		
		By clickOnButton = By.xpath("//button[@id='create-account-enabled']");
		
		driver.findElement(passwordTextbox).sendKeys("abc");
//		driver.findElement(clickOnButton).click();
		sleepInSecond(10);
		
		//Verify lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
//		driver.findElement(clickOnButton).click();
		sleepInSecond(5);
		
		//Verify uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
//		driver.findElement(clickOnButton).click();
		sleepInSecond(5);
		
		//Verify number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#");
//		driver.findElement(clickOnButton).click();
		sleepInSecond(5);
		
		//Verify special char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABCDEFGH");
//		driver.findElement(clickOnButton).click();
		sleepInSecond(5);
		
		//Verify char >= 8
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123abcABC!@#");
//		driver.findElement(clickOnButton).click();
		sleepInSecond(5);
		
		//Verify full data
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		
		
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