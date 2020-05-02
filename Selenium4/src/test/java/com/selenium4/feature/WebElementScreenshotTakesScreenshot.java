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
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;


import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElementScreenshotTakesScreenshot {

	static WebDriver driver = null;

	@BeforeClass
	public static void setUp() {
		WebDriverManager.chromedriver().setup();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		WebElement userNameTextBox = driver.findElement(By.id("txtUsername"));
		elementScreenshot(userNameTextBox, "txtUsername");
		
		WebElement loginBtn = driver.findElement(By.id("btnLogin"));
		elementScreenshot(loginBtn, "loginBtn");
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	public void elementScreenshot(WebElement element, String name) {
		
		TakesScreenshot screenshot = ((TakesScreenshot)element);
		
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFileToDirectory(srcFile, new File("./screenshots/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
