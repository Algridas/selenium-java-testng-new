package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Javascript_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		
	}

//	@Test
	public void TC_01_Tech_Panda() {
		
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		Assert.assertEquals(executeForBrowser("return document.domain"), "live.techpanda.org");
		Assert.assertEquals(executeForBrowser("return document.URL"), "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(5);
		
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(5);
		
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(5);
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(5);
		
		sendkeyToElementByJS("//input[@id='newsletter']", "Misha" + getRandomNumber() + "@yahoo.com");
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(5);
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(5);
		Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
		Assert.assertEquals(executeForBrowser("return document.URL"), "https://demo.guru99.com/v4/");
	}
	
	@Test
	public void TC_02_HTML5_Validation_Message() {
		
		navigateToUrlByJS("https://warranty.rode.com/login");
		sleepInSecond(5);
		Assert.assertEquals(executeForBrowser("return document.domain"), "warranty.rode.com");
		Assert.assertEquals(executeForBrowser("return document.URL"), "https://warranty.rode.com/login");
		
		
		hightlightElement("//a[text()=' Create an Account ']");
		clickToElementByJS("//a[text()=' Create an Account ']");
		sleepInSecond(5);
		
		String name = "//input[@id='name']";
		String email = "//input[@id='email']";
		String password = "//input[@id='password']";
		String confirmPassword= "//input[@id='password_confirmation']";
		
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(3);
		
		
		Assert.assertEquals(getElementValidationMessage(name), "Please fill out this field.");
		
		sendkeyToElementByJS(name, "Alan");
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(email), "Please fill out this field.");
		
		sendkeyToElementByJS(email, "Misha" + getRandomNumber() + "@yahoo.com");
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(password), "Please fill out this field.");
		
		sendkeyToElementByJS(password, "12345");
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(confirmPassword), "Please fill out this field.");
		
		sendkeyToElementByJS(confirmPassword, "12345");
		clickToElementByJS("//button[text()=' Register ']");
		sleepInSecond(3);
	}
	
	
	
	
	
	
	
	
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	
	public String getDomainName() {
		return (String) jsExecutor.executeScript("return document.domain");
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}
	
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	
	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	

	@Test
	public void TC_02_() {
	}

	@Test
	public void TC_03_() {
	}
	
	public int getRandomNumber() {
		return new Random().nextInt(9999);
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