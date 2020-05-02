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

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParentFrameDemo {

	static WebDriver driver = null;

	@BeforeClass
	public static void setUp() {
		WebDriverManager.chromedriver().setup();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://qhmit.com/html/tags/html_iframe_tag.cfm");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		
		WebElement parentFrame = driver.findElement(By.cssSelector("iframe[src$='editorCode4']"));
		
		// switch to parent frame
		driver.switchTo().frame(parentFrame);
		
		WebElement childFrame = driver.findElement(By.cssSelector("iframe[src$='iframe_tag_example.cfm']"));
		// switch to child frame
		driver.switchTo().frame(childFrame);
		
		String childFrameText = driver.findElement(By.tagName("p")).getText();
		System.out.println(childFrameText);
		
		driver.switchTo().parentFrame();
		
		//confirm the control is in pparent frame
		if(childFrame.isDisplayed()) {
			System.out.println("Inside parent frame");
		}
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	
}
