package org.ATBSelenium.Replicon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Lab01 {
    @Test
    public void Login(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        driver.get("https://so1.replicon.com");
        driver.findElement(By.id("CompanyNameTextBox")).sendKeys("Apollopsa2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoginNameTextBox"))).sendKeys("admin");
        driver.findElement(By.id("PasswordTextBox")).sendKeys("Replicon@123");
        driver.findElement(By.id("LoginButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"desktop-menu-container\"]/ul[3]/li[3]/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"employeesandorganization\"]/ul[4]/li[1]/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@value=\"+ Add Location\"]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class =\"errorField\"]"))).sendKeys("loc-nithu1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type=\"button\" and @value=\"Add\"]"))).click();
    }

}
