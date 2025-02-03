package org.ATBSelenium.OptionsAndLocators;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Lab06 {
    @Test
    public void test1(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");

        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://katalon-demo-cura.herokuapp.com/");

        WebElement make_appointment = driver.findElement(By.xpath("//a[@id='btn-make-appointment']"));
        make_appointment.click();

        WebElement email = driver.findElement(By.xpath("//input[@id='txt-username']"));
        email.sendKeys("John Doe");

        WebElement password = driver.findElement(By.xpath("//input[@id='txt-password']"));
        password.sendKeys("ThisIsNotAPassword");

        WebElement login = driver.findElement(By.xpath("//button[@id='btn-login']"));
        login.click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/#appointment");
    }
}
