package org.ATBSelenium.ex02_SeleniumBasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Lab02 {
    @Test
    public void test1(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");

        //Use of navigation methods
        driver.navigate().to("https://binge.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.close();
        //driver.quit();
    }
}
