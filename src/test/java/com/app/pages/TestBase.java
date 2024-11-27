package com.app.pages;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.app.utility.BrowserFactory;
import com.app.utility.ConfigDataProvider;


public class TestBase {

	public static WebDriver driver;
	public ConfigDataProvider config=new ConfigDataProvider();
	
	@BeforeSuite
	public void setup() {
		
		System.out.println("Browser launched succefully");
		driver=BrowserFactory.startApplication(driver, config.getBrowser(), config.getUrl());
		
	}
	
	public String navigateMethod() {
		driver.navigate().to("https://www.fitpeo.com/revenue-calculator");
		return driver.getTitle() ;
	}
	
	public String homePage() {
		String actname = driver.getTitle();
		System.out.println("Title : "+actname);
		return actname;
	}
	
	public void scrollToBar() {
		// Create an Actions object to simulate keyboard actions
        Actions actions = new Actions(driver);
        WebElement body = driver.findElement(Locators.sliderInput);

        // Simulate pressing the Down Arrow key
        actions.moveToElement(body).sendKeys("\uE015").perform();
	
	   
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(body));
	}
	
	
	public void adjustBar() throws InterruptedException {
		 // Locate the slider and the text field
        WebElement slider = driver.findElement(Locators.clickOnSlider1);
        WebElement textField = driver.findElement(Locators.inputSliderValue);
        
        // Locate the slider thumb (the element that is dragged)
        WebElement sliderThumb = driver.findElement(Locators.clickOnSlider); 

        // Find the width of the slider container and calculate the target position
        int sliderWidth = slider.getSize().getWidth();

     // Slider's max and min values (based on your slider)
        int maxValue = 2000;
        int minValue = 000;
        int targetValue = 820;

        // Calculate the target position based on the value (scaling the value to the width)
      int  targetPosition =(int) (((double)(targetValue - minValue) / (maxValue - minValue)) * sliderWidth);
        System.out.println("Target Position  (in pixels): " + targetPosition);

        // Create Actions object to simulate drag
        	Actions actions = new Actions(driver);

        // Move the slider thumb to the target position
        		actions.clickAndHold(sliderThumb) // Click on the slider thumb to start dragging
               .moveByOffset(targetPosition, 0) // Move by the calculated offset
               .release() // Release the thumb after dragging
               .perform(); // Perform the action
             
        // Optional: Assert that the value in the text field has been updated
        String updatedValue = textField.getAttribute("value");
        int currentValue = Integer.parseInt(updatedValue);
        int thumbPosition = (int) (((double) (currentValue - minValue) / (maxValue - minValue)) * sliderWidth);
        System.out.println("Slider Thumb Position (in pixels): " + thumbPosition);
        
        Thread.sleep(5000);
        textField.sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
        textField.sendKeys("420");
        String updatedValue1 = textField.getAttribute("value");
        int currentValue2 = Integer.parseInt(updatedValue1);
        Thread.sleep(5000);
        System.out.println("Updated value in text field: " + updatedValue1);
        int updatedThumbPosition = (int) (((double) (currentValue2 - minValue) / (maxValue - minValue)) * sliderWidth);
        System.out.println("Changed Slider Thumb Position (in pixels): " + updatedThumbPosition);
	}
		
	public void addCPR(ArrayList<String> as) {		
		int a=as.size();
		for(int i=0;i<a;i++){
			System.out.println("Select Checkbox for " + as.get(i));
			 By productLocator = Locators.getProductLocator(as.get(i));		    
		    // Wait for the element to be visible before scrolling
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));
		    
		    // Scroll to the element
		    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY - 100);", productElement);
		
			 By productLocator1= Locators.getProductCheckBox(as.get(i));
			 WebElement element=driver.findElement(productLocator1);
			     JavascriptExecutor js = (JavascriptExecutor) driver;
			     js.executeScript("arguments[0].click();", element);
		}
		 WebElement abc=driver.findElement(Locators.headerRecAmount);
		 String a1=    abc.getText();
		 System.out.println("Total Recurring Reimbursement for all Patients Per Month: "+ a1);
	}
//	

	    public static void tearDown() {
	        // Close the WebDriver after all tests have finished
			 try {
			        if (driver != null) {
			            // Print all open window handles
			            Set<String> windowHandles = driver.getWindowHandles();
			            System.out.println("Open windows: " + windowHandles);
			            
			            driver.quit();  // Close all browser windows and end the session
			            System.out.println("Browser closed successfully.");
			        }
			    } catch (Exception e) {
			        System.out.println("Failed to close the browser: " + e.getMessage());
			    }
	    }
}
