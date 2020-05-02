package com.selenium4.feature;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.log.Log;
import org.openqa.selenium.devtools.security.Security;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromiumDevToolChromeLogDemo {

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
	public void logPrintTest() {
		
		DevTools tool = ((ChromeDriver)driver).getDevTools();
		
		// create session
		tool.createSession();
		
		// enable log
		tool.send(Log.enable());
		
		// create listener
		tool.addListener(Log.entryAdded(), entry -> System.out.println(entry.getText()));
		
		driver.get("http://omayo.blogspot.com/");
	}
	
	@Test
	public void loadInsecureWebsiteTest() {
		
		DevTools tool = ((ChromeDriver)driver).getDevTools();
		
		//create session
		tool.createSession();
		
		//enable the security
		tool.send(Security.enable());
		
		//ignore certificate error
		tool.send(Security.setIgnoreCertificateErrors(true));
		
		driver.get("https://wrong.host.badssl.com/");
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	
}
