package org.ATBSelenium.ex01_SeleniumBasics;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Lab01 {
    @Test
    public void test1(){
        //Open the browser & login page
        //get the title of login page
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://app.vwo.com/");
        driver.getTitle();
        System.out.println(driver.getTitle());
    }

}
