package com.selenium4.feature;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewWindowDemo {

	static WebDriver driver = null;

	@BeforeClass
	public static void setUp() {
		WebDriverManager.chromedriver().setup();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {
		driver.get("http://omayo.blogspot.com/");
		String parentWindowHandle = driver.getWindowHandle();
		driver.findElement(By.name("q")).sendKeys("Selenium 4 feature");
		
		
		//driver.switchTo().newWindow(WindowType.TAB); // opens a new blank tab
		driver.switchTo().newWindow(WindowType.WINDOW); // opens a new blank window
		driver.get("http://www.newtours.demoaut.com/");
		driver.findElement(By.name("userName")).sendKeys("Iqbal"); // enter username
		driver.findElement(By.name("password")).sendKeys("password"); // enter password
		driver.findElement(By.xpath("//input[@name='login']")).click();
		Thread.sleep(5000);
		
		driver.close(); // close the new tab/window
		
		driver.switchTo().window(parentWindowHandle); // switch back the driver control to parent window
		
		driver.findElement(By.id("ta1")).sendKeys("Again switched back to parent window");
		
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}

	
}
