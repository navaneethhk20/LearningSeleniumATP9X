package org.ATBSelenium.OtionsAndLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

public class Lab04 {
@Test
    public void test1() {

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");

    WebDriver driver = new EdgeDriver(edgeOptions);
    driver.get("https://app.vwo.com");

    WebElement a_tag_free_trail =  driver.findElement(By.linkText("Start a free trial"));
       a_tag_free_trail.click();

//    WebElement partialT = driver.findElement(By.partialLinkText("Start"));
//    partialT.click();
//
    driver.quit();


    }

}