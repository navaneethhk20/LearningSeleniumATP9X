package org.ATBSelenium.OtionsAndLocators;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

public class Lab01 {
    @Description("Test launch")
    @Test
    public void test1() throws InterruptedException {
        EdgeOptions edgeOptions = new EdgeOptions();
       edgeOptions.addArguments("--window-size=1920,1080");
    //    edgeOptions.addArguments("--incognito");

     //edgeOptions.addArguments("--window-size=800,600");
      // edgeOptions.addArguments("--incognito");
        // edgeOptions.addArguments("--start-maximized");
        //edgeOptions.addArguments("--headless");

        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://google.com");
        Thread.sleep(3000);
        driver.quit();
    }
}
