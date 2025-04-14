package org.ATBSelenium.Omnify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

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
                driver.findElement(By.xpath("(//div[@class=\"hw__recentSearchTextWrapper\"])[1]")).click();

//date is selected from April 20-25, as 10-15 is no longer available
                WebElement checkInDatePicker = driver.findElement(By.xpath("//div[@class=\"DayPicker-Day\" and @aria-label=\"Sun Apr 20 2025\"]"));
                checkInDatePicker.click();

                WebElement checkOutDatePicker = driver.findElement(By.xpath("//div[ @aria-label=\"Fri Apr 25 2025\"]"));
                checkOutDatePicker.click();


                // Click search button
                WebElement searchButton = driver.findElement(By.id("search-button"));
                searchButton.click();

                // Wait for search results to load
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("hotel-result")));

                // Select the first hotel from the search results
                List<WebElement> hotelResults = driver.findElements(By.className("hotel-result"));
                if (!hotelResults.isEmpty()) {
                    WebElement firstHotel = hotelResults.get(0);
                    WebElement selectButton = firstHotel.findElement(By.className("select-button"));
                    selectButton.click();
                } else {
                    throw new RuntimeException("No hotel results found!");
                }

                // Wait for hotel details page to load
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hotel-details")));

                // Store the original price for later comparison
                String originalPriceText = driver.findElement(By.id("total-price")).getText();
                double originalPrice = extractPrice(originalPriceText);

                // Apply the coupon code "SUMMER25"
                WebElement couponField = driver.findElement(By.id("coupon-code"));
                couponField.sendKeys("SUMMER25");

                WebElement applyCouponButton = driver.findElement(By.id("apply-coupon"));
                applyCouponButton.click();

                // Wait for price to update
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("discount-applied")));

                // Verify that the discount is applied correctly (25% off)
                String discountedPriceText = driver.findElement(By.id("discounted-price")).getText();
                double discountedPrice = extractPrice(discountedPriceText);

                // Verify discount is correctly applied (25% off)
                double expectedDiscountedPrice = originalPrice * 0.75; // 25% off
                Assert.assertEquals(discountedPrice, expectedDiscountedPrice, 0.01,
                        "Discount was not applied correctly. Expected: " + expectedDiscountedPrice +
                                ", Actual: " + discountedPrice);

                // Proceed to checkout
                WebElement proceedToCheckoutButton = driver.findElement(By.id("checkout-button"));
                proceedToCheckoutButton.click();

                // Wait for checkout page to load
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("payment-section")));

                // Verify we're on the checkout page but don't complete payment
                Assert.assertTrue(driver.findElement(By.id("payment-section")).isDisplayed(),
                        "Failed to reach the checkout page");

                System.out.println("Test passed: Successfully proceeded to checkout with discount applied!");

            } catch (Exception e) {
                System.err.println("Test failed: " + e.getMessage());
                e.printStackTrace();
                Assert.fail("Test failed with exception: " + e.getMessage());
            }
        }

        // Helper method to extract price from text (e.g., "$199.99" -> 199.99)
        private double extractPrice(String priceText) {
            return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
        }

        @AfterTest
        public void tearDown() {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
