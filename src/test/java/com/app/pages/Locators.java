package com.app.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;

public class Locators {
	public static final By slider = By.xpath("//div[contains(@class,'MuiBox-root css-j7qwjs')]//span[1]//span[3]");
	public static final By sliderInput=By.xpath("//div[contains(@class,'MuiFormControl-root MuiTextField-root css-1s5tg4z')]");
	public static final By clickOnSlider1=By.xpath("//div[@class='MuiBox-root css-j7qwjs']//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-16i48op']");
	public static final By clickOnSlider=By.xpath("//div[contains(@class,'MuiBox-root css-j7qwjs')]//span[1]//span[3]//input");
    public static final By inputSliderValue = By.xpath("//div[contains(@class,'MuiFormControl-root MuiTextField-root css-1s5tg4z')]//input");
    public static final By input = By.xpath("//div[@class='MuiFormControl-root MuiTextField-root css-1s5tg4z']//input[@id=':R57alklff9da:']");
    
    public static By getProductLocator(String string) {
       return   By.xpath("//div[@class='MuiBox-root css-1eynrej']//p[contains(text(),'"+string+"')]");
    }
    
    public static By getProductCheckBox(String string) {  
     return By.xpath("//p[contains(text(),'"+string+"')]//following::input[contains(@class, 'PrivateSwitchBase-input')][1]");
     }
  
    public static final By headerRecAmount=By.xpath("//div[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1lnu3ao']//p[contains(text(),'Total Recurring Reimbursement for all Patients Per Month:')]//p");
   
    
}
