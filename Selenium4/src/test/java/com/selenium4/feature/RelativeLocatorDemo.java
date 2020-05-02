package com.selenium4.feature;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.locators.RelativeLocator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RelativeLocatorDemo {

	static WebDriver driver = null;

	@BeforeClass
	public static void setUp() {
		WebDriverManager.chromedriver().setup();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void belowDemoTest() throws InterruptedException {
		driver.get("http://tutorialsninja.com/demo/index.php?route=account/login");
		
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("16.mdiqbal@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("miqbal");
		
		// get the relative locator of login button using below() method
		driver.findElement(RelativeLocator.withTagName("input").below(By.xpath("//input[@id='input-password']"))).click();
		
	}
	
	//@Test
	public void toRightOfDemoTest() throws InterruptedException {
		driver.get("http://omayo.blogspot.com/");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium4");
		
		// get the relative locator for search button and click on that
		driver.findElement(RelativeLocator.withTagName("input").toRightOf(By.xpath("//input[@name='q']"))).click();
	}
	
	@Test
	public void belowWithtoLeftOfDemoTest() throws InterruptedException {
		driver.get("http://omayo.blogspot.com/");
		
		// locate the submit button which is below text and left of Login button
		WebElement submitBtnElement = driver.findElement(RelativeLocator.withTagName("button")
				.below(By.xpath("//h2[contains(text(),'Buttons with same name attribute values')]"))
				.toLeftOf(By.xpath("//button[contains(text(),'Login')]")));
		System.out.println(submitBtnElement.getText());
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}

	
}
