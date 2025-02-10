package org.ATBSelenium.ActionClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Lab02 {

        EdgeDriver driver;
        @BeforeTest
            public void openBrowser(){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--guest");
            driver = new EdgeDriver(edgeOptions);
        }

        @Test
        public void source_test(){
            driver.get("https://www.spicejet.com/");
            driver.manage().window().maximize();
            WebElement source = driver.findElement(By.xpath("//div[@data-testid ='to-testID-origin']/div/div/input"));

            source.sendKeys("BLR");
            Actions actions = new Actions(driver);
            actions.moveToElement(source).sendKeys("BLR").build().perform();
        }

        @AfterTest
                public void closeBrowser() throws InterruptedException {
                 Thread.sleep(3000);
                  driver.quit();
        }
    }

