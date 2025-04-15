package org.ATBSelenium.Omnify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class HotelBooking {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

        @Test
        public void testBookingWithCoupon() {
            try {
                driver.get("https://www.agoda.com/");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"Enter a destination or property\"]"))).sendKeys("New York");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class=\"Suggestion Suggestion__categoryName\" and @data-text=\"New York (NY)\"]"))).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//*[@id=\"occupancy-box\"]"))).click();
                //date is selected from April 20-25, as 10-15 is no longer available
                WebElement checkInDatePicker = driver.findElement(By.xpath("//span[@data-selenium-date=\"2025-04-20\"]"));
                checkInDatePicker.click();

                WebElement checkOutDatePicker = driver.findElement(By.xpath("//span[@data-selenium-date=\"2025-04-25\"]"));
                checkOutDatePicker.click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//*[@id=\"occupancy-box\"]"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"SEARCH\"]"))).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@data-element-name=\"property-card-content\"])[1]"))).click();

                String originalWindow = driver.getWindowHandle();
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));

                for (String windowHandle : driver.getWindowHandles()) {
                    if (!originalWindow.equals(windowHandle)) {
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='couponCode']"))).sendKeys("SUMMER25");
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Apply')]"))).click();

                WebElement discountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Discount applied')]")));
                Assert.assertTrue(discountElement.isDisplayed(), "Discount was not applied successfully");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()=\"Book now\"])[1]"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"NEXT: FINAL STEP\"]"))).click();


                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Proceed to Checkout')]"))).click();

            } catch (Exception e) {
                System.err.println("Test failed: " + e.getMessage());
            }
        }


        @AfterTest
        public void tearDown() {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
