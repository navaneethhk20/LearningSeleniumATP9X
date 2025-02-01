package org.ATBSelenium.ex02_SeleniumBasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Lab01 {
    @Test
    public void test1() throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        driver.manage().window().maximize();
        driver.getTitle();
        driver.getCurrentUrl();
        driver.getPageSource();
        if(driver.getPageSource().contains("CURA Healthcare Service")){
            System.out.println("CURA Healthcare Service is visible !!!");
        } else {
            throw new Exception("CURA Healthcare Service is Not visible.");
        }
       driver.quit();
    }

}
