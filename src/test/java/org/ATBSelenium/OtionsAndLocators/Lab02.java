package org.ATBSelenium.OtionsAndLocators;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

import java.io.File;

public class Lab02 {
    @Description("test")
    @Test
    public void test1(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addExtensions(new File("C:\\Users\\Navaneeth H K\\IdeaProjects\\LearningSeleniumATP9X\\src\\test\\java\\org\\ATBSelenium\\OtionsAndLocators\\AdBlock.crx\\"));

       WebDriver driver = new EdgeDriver(edgeOptions);
       driver.get("https://www.youtube.com/watch?v=kaG3nTdiRX0&t=287s");
       driver.quit();

 }
}
