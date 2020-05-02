package com.selenium4.feature;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;


import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElementScreenshotPageSection {

	static WebDriver driver = null;

	@BeforeClass
	public static void setUp() {
		WebDriverManager.chromedriver().setup();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://omayo.blogspot.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		WebElement form = driver.findElement(By.id("HTML31"));
		elementScreenshot(form, "form");
		
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	public void elementScreenshot(WebElement element, String name) {
		File srcFile = element.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFileToDirectory(srcFile, new File("screenshots/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
