package org.ATBSelenium.OtionsAndLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Lab05 {
    @Test
    public void test1() throws InterruptedException {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");

        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://app.vwo.com");

        WebElement a_tag_free_trail =  driver.findElement(By.linkText("Start a free trial"));
        a_tag_free_trail.click();

        WebElement email =  driver.findElement(By.name("email"));
        email.sendKeys("nithu");

        WebElement checkBox = driver.findElement(By.name("gdpr_consent_checkbox"));
        checkBox.click();

        List<WebElement> button = driver.findElements(By.tagName("button"));
        button.get(0).click();

        WebElement errormsg = driver.findElement(By.className("invalid-reason"));

        Assert.assertEquals(errormsg.getText(),"The email address you entered is incorrect.");
        Thread.sleep(3000);
        driver.quit();
    }
}
