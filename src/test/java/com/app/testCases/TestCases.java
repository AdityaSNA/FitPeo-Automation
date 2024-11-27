package com.app.testCases;

import java.util.ArrayList;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.app.pages.TestBase;



public class TestCases extends TestBase {	
	@Test
	public void verifyHomePage() throws Exception {
		String actualName = homePage();
		String expectedName="Remote Patient Monitoring (RPM) - fitpeo.com";
		if(actualName==expectedName) {
			Assert.assertEquals(actualName, expectedName);
		}
	}
		@Test(dependsOnMethods = "verifyHomePage")
		public void verifyRevCalPage() {
			String actualName = navigateMethod();
			System.out.println(actualName);
			String expectedName="https://www.fitpeo.com/revenue-calculator";
			if(actualName==expectedName) {
				Assert.assertEquals(actualName, expectedName);
			}
		}	
		
		@Test(dependsOnMethods = "verifyRevCalPage")
		public void scrollDownButton() throws InterruptedException {
			scrollToBar();
			adjustBar();
		}
		
		@Test(dependsOnMethods="scrollDownButton")
		public void selectCPR() {
			ArrayList<String> as=new ArrayList();
			as.add("CPT-99091");
			as.add("CPT-99453");
			as.add("CPT-99454");
			as.add("CPT-99474");
			System.out.println("Codes to be selected are : "+ as);
			addCPR(as);
		}
		
		@AfterSuite
		public static void close() {
			driver.quit();
			//tearDown();
		}
}
