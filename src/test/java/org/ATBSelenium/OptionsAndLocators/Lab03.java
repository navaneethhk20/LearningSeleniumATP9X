package org.ATBSelenium.OptionsAndLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Lab03 {
    @Test
    public void test1() throws InterruptedException {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");

        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://app.vwo.com");

        WebElement emailInput = driver.findElement(By.id("login-username"));
        emailInput.sendKeys("admin@gmail.com");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("password");

        WebElement buttonSubmit = driver.findElement(By.id("js-login-btn"));
        buttonSubmit.click();

        Thread.sleep(5000);

        WebElement errormsg = driver.findElement(By.className("notification-box-description"));

        Assert.assertEquals(errormsg.getText(),"Your email, password, IP address or location did not match");

        driver.quit();
    }
}
