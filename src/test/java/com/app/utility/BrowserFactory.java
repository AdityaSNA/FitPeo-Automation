package com.app.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	
	public static WebDriver startApplication(WebDriver driver,String browserName,String appUrl) {
//		if(browserName.equals("Chrome")) {
//			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
//			ChromeOptions op=new ChromeOptions();
//			op.addArguments("--remote-allow-origins=*");
//			driver=new ChromeDriver();
//			//System.out.println("Driver : "+driver);
//		}
//		else if(browserName.equals("Firefox")) {
//			System.setProperty("webdriver.chrome.driver", "./Drivers/geckodriver.exe");
//			driver=new FirefoxDriver();
//		}
//		
//		else if(browserName.equals("IE")) {
//			System.setProperty("webdriver.chrome.driver", "./Drivers/IEDriverServer.exe");
//			driver=new InternetExplorerDriver();
//		}
//		else {
//			System.out.println("we do not support this browser : "+browserName);
//		}
		
		
		 // Check the browser name and initialize the appropriate WebDriver instance
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} 
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			System.out.println(browserName+" is not present so opening default browser as chrome");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		
		
		
		driver.manage().window().maximize();
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		System.out.println("Driver : "+driver);
		return driver;
	}
	
	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}
}
