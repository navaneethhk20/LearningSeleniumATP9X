package org.ATBSelenium.WebTableAndSVGs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Lab05 {
    @Test
    public void test() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.amcharts.com/svg-maps/?map=india");
        driver.manage().window().maximize();

        // States - // //*[local-name()='svg']/*[name()="g"][7]/*[name()="g"]/*[name()="g"]/*[name()="path"]

        List<WebElement> states = driver.findElements(By.xpath("//*[name()='svg']/*[name()='g'][7]/*[name()='g']/*[name()='g']/*[name()='path']"));
        for (WebElement state : states) {
            System.out.println(state.getAttribute("aria-label"));
            if (state.getAttribute("aria-label").contains("Tripura")) {
                state.click();
            }
        }
    }
}
