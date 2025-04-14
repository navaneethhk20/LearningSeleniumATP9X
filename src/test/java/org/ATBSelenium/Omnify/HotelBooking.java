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
                driver.get("https://www.makemytrip.com/");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy =\"closeModal\"]"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"headerIconWrapper\"])[2]"))).click();
                WebElement enterValue= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-cy=\"city\"]")));
                enterValue.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title=\"Where do you want to stay?\"]"))).sendKeys("New york");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\"hw__recentSearchTextWrapper\"])[1]"))).click();

                //date is selected from April 20-25, as 10-15 is no longer available
                WebElement checkInDatePicker = driver.findElement(By.xpath("//div[@class=\"DayPicker-Day\" and @aria-label=\"Sun Apr 20 2025\"]"));
                checkInDatePicker.click();

                WebElement checkOutDatePicker = driver.findElement(By.xpath("//div[ @aria-label=\"Fri Apr 25 2025\"]"));
                checkOutDatePicker.click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"APPLY\"]"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id=\"hsw_search_button\"]"))).click();
                Thread.sleep(4000);

                //adding refresh button because application is not working
                driver.navigate().refresh();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Hyatt Grand Central New York\"]"))).click();

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

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Proceed to Checkout')]"))).click();


            } catch (Exception e) {
                System.err.println("Test failed: " + e.getMessage());
                e.printStackTrace();
                Assert.fail("Test failed with exception: " + e.getMessage());
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
