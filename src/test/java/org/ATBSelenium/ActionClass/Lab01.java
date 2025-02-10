package org.ATBSelenium.ActionClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Lab01 {

        EdgeDriver driver;
              @BeforeTest
              public void openBrowser(){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--guest");
             driver = new EdgeDriver(edgeOptions);

        }

        @Test
        public void test_actions() throws InterruptedException {
            String URL = "https://awesomeqa.com/practice.html";
            driver.get(URL);
            driver.manage().window().maximize();

            WebElement firstName = driver.findElement(By.name("firstname"));

            // Keyboard
            // KeyDown + Shift -> Send Keys(type) -> Key Up
            Actions actions = new Actions(driver);

            actions
                    .keyDown(Keys.SHIFT)
                    .sendKeys(firstName,"the testing academy")
                    .keyUp(Keys.SHIFT).build().perform();










        }

    @AfterTest
    public void closeBrowser() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();


    }
}
