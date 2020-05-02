package com.selenium4.feature;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElementScreenshotFullPageChrome {

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
		// take full page screenshot using getScreenShotAs for Chrome browser
		fullPageScreenshotChrome(driver, "fullpage");
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	public void fullPageScreenshotChrome(WebDriver driver, String name) {
		File fullpageSrc = ((ChromeDriver)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFileToDirectory(fullpageSrc, new File("screenshots/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
