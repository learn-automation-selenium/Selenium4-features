package com.selenium4.feature;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetRectDemo {

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
		WebElement registerBtn = driver.findElement(By.xpath("//button[contains(text(),'Register')]"));
		
		System.out.println("-------------- Selenium 3 -------------");
		Dimension dimension = registerBtn.getSize();
		System.out.println("Height : " + dimension.getHeight());
		System.out.println("Width : " + dimension.getWidth());
		
		Point location = registerBtn.getLocation();
		System.out.println("X co-ordinate : " + location.getX());
		System.out.println("Y co-ordinate : " + location.getY());
		
		System.out.println("-------------- Selenium 4 -------------");
		Rectangle rect = registerBtn.getRect();
		System.out.println("Height : " + rect.getHeight());
		System.out.println("Width : " + rect.getWidth());
		System.out.println("X co-ordinate : " + rect.getX());
		System.out.println("Y co-ordinate : " + rect.getY());
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	
}
